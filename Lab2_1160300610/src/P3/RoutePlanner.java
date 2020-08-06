package P3;

import java.util.List;

public interface RoutePlanner  {
	
	/**
	 * 
	 * @param search：子字符串
	 * @return 返回名称中包含提供的子字符串search的所有站点的列表
	 */
	public  List<Stop> findStopsBySubstring(String search);
	
	
	
	/**
	 * 
	 * @param src 起点
	 * @param dest 终点
	 * @param time 起始时间
	 * @return 返回一个描述行程的对象，该行程使得到达目的地dest的时间最小化
	 */
	public Itinerary computeRoute(Stop src  , Stop dest , int time );



	
}
