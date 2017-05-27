package it.unibo.test1;

	public  class Node {
		private int x;
		private int y;
		
		public Node(int x, int y){ this.x = x; this.y = y; }
		
		public int getX(){ return x; }
		public int getY(){ return y; }
		
		@Override public String toString(){
			return x + "," + y;
		}
		
		public double distanceTo(Node to){
			double xdist = Math.abs(this.x - to.getX());
			double ydist = Math.abs(this.y - to.getY());
			return Math.sqrt( Math.pow(xdist, 2) + Math.pow(ydist, 2) );
		}
	}