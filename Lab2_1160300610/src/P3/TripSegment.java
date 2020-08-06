package P3;


public interface TripSegment {
	
	String flag=null ;
	
	public int getStartTime();
	
	public int getEndTime();
	
	public Stop getStartLocation();
	
	public Stop getEndLocation();

	public String getInsructions();
	
}
