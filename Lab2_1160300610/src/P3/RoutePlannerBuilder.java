package P3;


public interface RoutePlannerBuilder  {
	
	
	
	public RoutePlanner build(String filename,int maxWaitLimit);
	
	

}

	 class Vertex implements Stop{
		double Latitude;
		double Longitude;
		String name;
		int time;
		int title;
		
		
		public Vertex(String name,double Latitude,double Longitude,int time,int title) {
			this.name=name;
			this.Latitude=Latitude;
			this.Longitude=Longitude;
			this.time=time;
			this.title=title;
		}
		public boolean equals(Vertex a,Vertex b) {
			if((a.Latitude==(b.Latitude))&&(a.Longitude==(b.Longitude))&&(a.time==b.time)&&a.name.equals(b.name)) {
				return true;
			}else {
				return false;
			}
		}


		@Override
		public String getName() {
			return this.name;
		}


		@Override
		public double getLatitude() {
			return this.Latitude;
		}


		@Override
		public double getLongitude() {
			return this.Longitude;
		}
		@Override
		public int getTitle() {
			return this.title;
		}
		@Override
		public int getTime() {
			return this.time;
		}
	}
	
	
	
	class Edge{
		Vertex source;
		Vertex target;
		int weight;
		int flag=0;
		
		
		public Edge(Vertex source,Vertex target, int weight) {
			this.source=source;
			this.target=target;
			this.weight=weight;
		}
		
		public int get_weight() {
			return this.weight;
		}
		
		public void set_weight(int weight) {
			this.weight=weight;
		}
		
		public boolean equals(Edge a,Edge b) {
			if(a.source.equals(b.source)&&a.target.equals(b.target)) {
				return true;
			}else {
				return false;
			}
		}
	}