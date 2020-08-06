package P3;

public class WaitSegment implements TripSegment {

	
	private Stop stop_start;
	private Stop stop_end;
	private int time_start;
	private int time_end;
	private String WaitS;
	
	
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
		return WaitS;
	}
}
