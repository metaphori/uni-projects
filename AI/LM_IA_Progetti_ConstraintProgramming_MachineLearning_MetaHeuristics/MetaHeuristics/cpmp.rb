################################################
################### SOLVERS ####################
################################################

module Solvers

  class Solver
    attr_accessor :best_solution, :best_cost, :seed
    
    def seed
      1
    end
    
  end

  # RANDOM SOLVERS
  class RandomSolver < Solver
    attr_accessor :attempts

    def initialize()
      @attemps = 100
    end
    
    def solve(cpmp)
      random = Random.new seed
      curr = cpmp.getRandomFeasibleSolution(random)
      best = curr.dup
      cost_of_best = cpmp.eval(best)
      
      attempts.times do
        curr = cpmp.getRandomFeasibleSolution(random)
        cost_of_curr = cpmp.eval(curr)
        best, cost_of_best = curr.dup, cost_of_curr if cost_of_curr<cost_of_best
      end
      
      self.best_solution = best
      self.best_cost = cost_of_best
    end
  end
  
  
  ############################
  # METAHEURISTIC
  ############################
  
  module MetaHeuristics
    
    # SIMULATED ANNEALING
    class SimulatedAnnealing < Solver
      attr_accessor :init_temperature
      
      def initialize()
        @init_temperature = 100
      end
      
      def solve(cpmp)
        @temperature = self.init_temperature
        random = Random.new seed
        sol = cpmp.getRandomFeasibleSolution(random)
        cost = cpmp.eval(sol)
        
        # puts "Initial feasible solution of cost #{cost}"
        
        best = sol
        best_cost = cost
        
        until end_condition do
          neigh = cpmp.getNeighborSolution(sol)
          neigh_cost = cpmp.eval(neigh)
          
          if neigh_cost<cost then
            sol = neigh
            cost = neigh_cost
            
            if cost < best_cost then
              best = neigh
              best_cost = neigh_cost
            end
          else
            # accetta di porre sol = neigh con probabilità p = e^-(neigh_cost-cost)/kT
            # k = -T0 ln(0.8) / (neigh_cost-cost)
            k = -init_temperature * Math.log(0.8) / (neigh_cost-cost)
            cond = random.rand < Math.exp(-k*(neigh_cost-cost)/@temperature)
            if cond then 
              sol = neigh
              cost = neigh_cost
            end
          end
          
          @temperature = @temperature - 0.5 if annealing_condition
          
        end
        
        self.best_solution = best
        self.best_cost = best_cost
      end
      
      def annealing_condition
        true
      end
      
      def end_condition
        @temperature < 5 #(init_temperature/3)
      end
    end
    
    # TABU SEARCH
    class TabuSearch < Solver
      attr_accessor :tabu_list_size
      
      def initialize()
        @tabu_list_size = 10
      end
      
      def solve(cpmp)
        random = Random.new seed
        sol = cpmp.getRandomFeasibleSolution(random)
        cost = cpmp.eval(sol)
        
        # puts "Initial feasible solution of cost #{cost}"
        
        best = sol
        best_cost = cost
        
        tabu_list = TabuList.new(tabu_list_size)
        iterations = 0
        
        until iterations>=10 do
          iterations=iterations+1
          
          best_neighbors = cpmp.getNBestNeighbors(sol, tabu_list_size+1)
          begin
            sol = best_neighbors.delete_at 0
          end until not tabu_list.include? sol || best_neighbors.size==0
          
          tabu_list.add(sol)

          cost = cpmp.eval(sol)
          
          best, best_cost = sol, cost if cost<best_cost
        end
        
        self.best_solution = sol.dup
        self.best_cost = cpmp.eval(sol)
      end
      
      class TabuList
        attr_accessor :size
        
        def initialize(tsize)
          @size = tsize
          @list = []
          @pos = 0
        end
        
        def add(elem)
          @list[@pos] = elem
          @pos = @pos==size ? 0 : (@pos+1)
        end
        
        def include?(elem)
          @list.include? elem
        end
      end # of class TabuList
      
    end # of class TabuSearch

  end # of module MetaHeuristics

end

class Array
  
  def pick_n_random_elems(n, rand)
    res = []
    copy = self.dup
    n.times do
      res << copy.delete_at(rand.rand(copy.size))
    end
    res
  end
  
end

################################################
################### CPMP #######################
################################################

class CPMP
  attr_accessor :n, :m, :q
  attr_reader :clients
  
  attr_accessor :id, :bestknown
  
  def initialize(n,m,q,clients)
    @n = n; @m = m; @q = q; @clients = clients
  end
  
  def eval(sol)
    lst = sol.services
    cost = 0
    lst.each do |s|
      cs = s.clients
      cs.each do |c|
        cost = cost + s.distanceTo(c)
      end
    end
    cost    
  end
  
  def isValid(sol)
    return false if sol.services.size!=@m
    
    # 1) check for all clients covered 
    # 2)check for demand that do not exceed capacity
    
    cs = self.clients.map { |c| c.dup }
    
    sol.services.each do |s|
      total_demand = 0
      s.clients.each do |c|
        total_demand = total_demand + c.demand
        cs = cs.delete_if { |elem| elem==c }
      end
      #puts "Total demand #{total_demand} for #{@q} capacity"
      return false  if total_demand>@q
    end

    return false if cs.size>0
    true
  end
  
  def getRandomFeasibleSolution(seed=Random.new)
    valid = false
    until valid
      sol = getRandomSolution(seed)
      valid =  self.isValid(sol)
    end
    sol
  end
  
  def getRandomSolution(random=Random.new)
    sol = Solution.new(self)
    
    customers = self.clients.map { |c| c.dup }
    services = customers.pick_n_random_elems(self.m, random)
    clients_to_assign = customers
    services.each do |s|
      s.clients << s
      clients_to_assign.delete s
    end
    
    clients_to_assign.each do |c|
      services[ random.rand(services.size) ].clients << c
    end
    
    sol.services = services.dup
    sol
  end
  
  
  def getNeighborSolution(sol)
    newsol = sol.dup
    
=begin    
    customers = newsol.services
    
    valid = true
    begin
      services = customers.pick_n_random_elems(2, Random.new)       
      choice = Random.rand(2) 
      
      newsol.services.sort_by! {|s| s.clients.map{|c| c.distanceTo(s)}.inject(0){|sum,v| sum+v} }
    
      if choice==0 then
        # flip most distant clients of two randomly picked services
        services[0].clients.sort_by! { |c| c.distanceTo(services[0]) }
        services[1].clients.sort_by! { |c| c.distanceTo(services[0]) }
        c1 = services[0].clients.delete_at(-1)
        c2 = services[1].clients.delete_at(-1)
        services[0].clients << c2
        services[1].clients << c1
      elsif choice==1 then
        #
        s1 = newsol.services[-1]
        s2 = newsol.services[-2]
        
        tempclients = s1.clients
        s1.clients = s2.clients
        s2.clients = tempclients
        
      end
      
      valid = self.isValid(newsol)
    end while not valid
=end
    
    newsol
  end
  
  def getNBestNeighbors(sol, num)
=begin
    pos = 0
    results = []
    
    customers = clients.keep_if{ |c| not sol.services.map{|cl| cl.id }.include? c.id }
    
    # TODO:
    # combinazioni possibili
    
    (num*2).times do
      results << getNeighborSolution(sol)
    end
    
    results.sort_by!{ |solution| self.eval(solution) }
    
    results.first num
=end
    [getNeighborSolution(sol)]*num
  end
  
end

# A CUSTOMER IN THE CPMP PROBLEM
class Client
  attr_accessor :id, :x, :y, :demand
  
  attr_accessor :clients
  
  def initialize(id, x, y, demand)
    @id = id; @x = x; @y = y; @demand = demand
    @clients = []
  end
  
  def distanceTo(other)
    distx = (other.x - x).abs
    disty = (other.y - y).abs
    Math.sqrt(distx*distx + disty*disty)
  end
  
  def to_s
    "<"+id.to_s+","+x.to_s+","+y.to_s+","+demand.to_s+"=>"+clients.size.to_s+">"
  end
  
  def dup
    newclient = Client.new(id,x,y,demand)
    newclient.clients = clients.dup
    newclient
  end
  
  def ==(other)
    self.id == other.id
  end
  
end

# A SOLUTION OF THE CPMP PROBLEM
class Solution

  attr_accessor :services
  attr_reader :cpmp

  def initialize(cpmp)
    @services = []
    @clients = cpmp.clients.map{ |c| c.dup }
    @cpmp = cpmp
  end
  
  def to_s
    "["+self.services.map{|c| c.to_s}.join(" ")+"]"
  end
  
  def dup
    s = Solution.new(cpmp)
    s.services = self.services.map{ |s| s.dup }
    s
  end

end

# ######################################################
# ################ Testing on CPMP #####################
# ######################################################

lines = IO.readlines("p-median-capacitated/pmedcap1.txt")

nproblems = 0
problems = [] # list of capacitated p-median problems
curproblem_id = 0
cpmp = nil
bestforcurrent = 0

# parsing data file
lines.each do |l|
  l = l.strip.split " "
  ln = l.size
  if ln==1 then
    nproblems = l[0].to_i
  elsif ln==2 then
    curproblem_id = l[0].to_i
    bestforcurrent = l[1].to_i 
  elsif ln==3 then
    n = l[0].to_i
    m = l[1].to_i
    q = l[2].to_i
    cpmp = CPMP.new(n,m,q,[])
    cpmp.id = curproblem_id
    cpmp.bestknown = bestforcurrent
    problems << cpmp
  elsif ln==4 then
    cid = l[0].to_i
    x = l[1].to_i
    y = l[2].to_i
    demand = l[3].to_i
    c = Client.new(cid,x,y,demand)
    cpmp.clients << c
  end
end

# seed for random
SEED = 77

# configuring the solvers
sa = Solvers::MetaHeuristics::SimulatedAnnealing.new
sa.seed = SEED
sa.init_temperature = 60
  
ts = Solvers::MetaHeuristics::TabuSearch.new
ts.seed = SEED
  
rnd = Solvers::RandomSolver.new
ts.seed = SEED
rnd.attempts = 10

# applying the solvers to the problems
problems[0,10].each do |p|
  puts "-"*60
  puts "CPMP Instance " + p.id.to_s + "(N="+p.n.to_s+", M="+p.m.to_s+", Q="+p.q.to_s+")"
  
  sa.solve(p)
  puts "Cost of solution 1 (SimulatedAnnealing): " + sa.best_cost.to_s
  ts.solve(p)
  puts "Cost of solution 2 (TabuSearch): " + ts.best_cost.to_s
  rnd.solve(p)
  puts "Cost of solution 3 (RandomSolver): " + rnd.best_cost.to_s
  puts "Best known solution: " + p.bestknown.to_s
  
end

#puts "Clients: " + cpmp.clients.to_s
#puts "Solution: " + sol.services.to_s
#puts "Valid? " + cpmp.isValid(sol).to_s
#puts "Cost: " + cpmp.eval(sol).to_s
