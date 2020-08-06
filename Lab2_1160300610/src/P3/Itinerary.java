package P3;

public class Itinerary {
	
	private Stop stop_start;
	private Stop stop_end;
	private  int time_start;
	private int time_end;
	private int time_sumwait;
	private String Itinerary[];
	
	
	public Itinerary(Stop stop_start,Stop stop_end,int time_start) {
		this.stop_start=stop_start;
		this.stop_end=stop_end;
		this.time_start=time_start;
	}
	
	
	
	public int getStartTime() {
		return time_start;
	}

	public int getEndTime() {
		return time_end;
	}
	public int getWaitTime() {
		return time_sumwait;
	}
	public Stop getStartLocation() {
		return stop_start;
	}
	public Stop getEndLocation() {
		return stop_end;
	}
	public int getInsructions() {
		return time_end;
	}

	

//	public static void main(String[] args) {
//		// TODO 自动生成的方法存根
//
//	}

}
