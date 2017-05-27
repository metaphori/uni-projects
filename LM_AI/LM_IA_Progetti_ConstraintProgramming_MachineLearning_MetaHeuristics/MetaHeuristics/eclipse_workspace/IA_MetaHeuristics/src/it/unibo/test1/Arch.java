package it.unibo.test1;
	public  class Arch {
		private Node from;
		private Node to;
		
		public Arch(Node from, Node to){ this.from = from; this.to = to; }
		
		public Node getStartNode(){ return from; }
		public Node getEndNode(){ return to; }
		
		@Override public String toString(){
			return "("+from +";" + to + ")";
		}
		
		public double getCost(){
			return from.distanceTo(to);
		}
		
		public void setStartNode(Node from){ this.from = from; }
		public void setEndNode(Node to){ this.to = to; }
	}