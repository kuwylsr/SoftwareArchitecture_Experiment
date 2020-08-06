package P3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Planner implements RoutePlanner{
	private static final int INFINITY = 0;
	private static final int TIME_INDEX = 0;
	Graph_route g=new Graph_route();	
	

	@Override
	public List<Stop> findStopsBySubstring(String search) {
		List<Stop> S =new ArrayList<>();
		Set<Vertex> x=g.vertices();
		for(Vertex v:x) {
			if(v.name.indexOf(search)!=-1) {
				Stop s=v;
				S.add(s);
			}
		}
		return S;
	}

	@Override
	public Itinerary computeRoute(Stop src, Stop dest, int time) {		
		Itinerary plan =new Itinerary(src,dest,time);
		int totalWaitTime = 0;
		
		List<Vertex> visited = new LinkedList<Vertex>();
		Queue<Vertex> queue =new LinkedList<>();
		Map<Vertex,Vertex> previous= new HashMap<Vertex,Vertex>();
		
		
		List<TripSegment> travelSegments = new LinkedList<TripSegment>();
		Vertex current;
		 Vertex start = findStartLoc(src, time);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	private Vertex findStartLoc(Stop src, int time) {
		String name;
        int minDiff = INFINITY;
        int currDiff;
        int currVertexTime;
        String[] values;
        Vertex start = null;
        /* For each vertex, if the stop is potentially the stop we want to
         * start at, check the difference between its time and the desired
         * starting time. We want to closes time to the desired.
         */
        for(Vertex v : graph.vertices()){
            name = v.getName();
            if(name.contains(src.getName())){
                values = name.split(",");
                currVertexTime = Integer.parseInt(values[TIME_INDEX]);
                currDiff = Math.abs(time-currVertexTime);
                if(currDiff < minDiff){
                    minDiff = currDiff;
                    start = v;
                }
            }
        }
        return start;
	}

}
