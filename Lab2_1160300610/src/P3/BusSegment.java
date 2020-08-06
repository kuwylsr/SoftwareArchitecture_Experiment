package P3;

public class BusSegment implements TripSegment {
	
	private Stop stop_start;
	private Stop stop_end;
	private  int time_start;
	private int time_end;
	private String BusS;
	
	public BusSegment(Stop stop_start,Stop stop_end,int time_start,int time_end) {
		this.stop_start=stop_start;
		this.stop_end=stop_end;
		this.time_start=time_start;
		this.time_end=time_end;
	}


	@Override
	public int getStartTime() {
		return time_start;
	}

	@Override
	public int getEndTime() {
		return time_end;
	}

	@Override
	public Stop getStartLocation() {
		return stop_start;
	}

	@Override
	public Stop getEndLocation() {
		return stop_end;
	}

	@Override
	public String getInsructions() {
		// TODO 自动生成的方法存根
		return BusS;
	}
	
	


}
