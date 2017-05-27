% Roberto Casadei, 17 maggio 2013
% Constraint Logic Programming exercise: N-Queens
% Problem description: Placing N queens on a NxN chessboard so that no attacks are possible.

% Parameter: the number of queens
par int: N; 

% Chessboard
array[1..N, 1..N] of var 0..1: CS;

% STEP 1: CONSTRAINTS FOR N-ROCKS PROBLEM
constraint 
  forall(i in 1..N) (
    sum(j in 1..N) (CS[i,j]) <= 1 
	  /\
	  sum(j in 1..N) (CS[j,i]) <= 1
  );

% STEP 2: CONSTRAINTS FOR AVOIDING DIAGONAL ATTACKS
constraint
  forall(i in 1..N) (
	% check for diagonals from first line
    sum(j in 1..(N-i+1)) (CS[j,j+i-1]) <= 1
	  /\
	  sum(j in 1..i)       (CS[j,i-j+1]) <= 1
	  /\
	% check for diagonals from last line
	  sum(j in 1..(N-i+1)) (CS[N-j+1,j+i-1]) <= 1
	  /\
	  sum(j in 1..i)     (CS[N-j+1,i-j+1]) <= 1
  );

var int: summa = sum(i,j in 1..N) (CS[i,j]);
constraint summa==N;

solve satisfy;

%solve :: int_search(
%[CS[i,j] | i,j in 1..N], % list of vars to be "searched"
%first_fail,              % selection strategy: chooses the (undecided) variable with the smallest domain;
%indomain_min,            % domain reduction strategy: reduces a domain to a singleton value in an ascending order;
%complete                 % search strategy
%) satisfy;

output [ if(show(CS[i,j])=="0")then " . " else " Q " endif ++ "" ++ if(j==N) then "\n" else "" endif | i, j in 1..N ];
		
		
