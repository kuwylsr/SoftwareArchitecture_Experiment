/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Set;


/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even exist
 * as a key in the map; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".
 * A username should appear at most once as a key in the map or in any given
 * map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets.
     *         One kind of evidence that Ernie follows Bert is if Ernie
     *         @-mentions Bert in a tweet. This must be implemented. Other kinds
     *         of evidence may be used at the implementor's discretion.
     *         All the Twitter usernames in the returned social network must be
     *         either authors or @-mentions in the list of tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
    	Map<String, Set<String>> v =new HashMap<>();
    	//使用正则表达式
    	Pattern up =Pattern.compile("(?:[^\\w-]|^)@([\\w-]+)");
    	for(int i=0;i<tweets.size();i++) {
    		Set<String> v1=new HashSet<String>();
    		Matcher um =up.matcher(tweets.get(i).getText());
    		while(um.find()) {
    			v1.add(um.group(1).toLowerCase());
    		}
    		v.put(tweets.get(i).getAuthor(), v1);
    	}
    	return v;
//    	第一次尝试没用正则表达式，发现对一些特殊的用例不适用
//    	for(int i=0;i<tweets.size();i++) {
//    		Set<String> v1=new HashSet<String>();
//    		String txt[] =tweets.get(i).getText().split(" ");
//			for(int j=0;j<txt.length;j++) {
//				if(!txt[j].isEmpty()) {
//					if(txt[j].charAt(0)=='@') {
//						if(txt[j].charAt(txt[j].length()-1)==':') {
//							v1.add(txt[j].substring(1, txt[j].length()-1));
//							
//						}else {
//							v1.add(txt[j].substring(1));
//						}
//					}
//				}
//			}
//    		v.put(tweets.get(i).getAuthor(), v1);
//    	}
//    	return v;
    }

    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
        List<String> v =new ArrayList<>();
        HashMap<String,Integer> map= new HashMap<>();
        Collection<Set<String>> y=followsGraph.values();
        Iterator<Set<String>> it =y.iterator();
        
        while(it.hasNext()) {
        	Iterator<String> itx =it.next().iterator();
        	while(itx.hasNext()) {
        		String foll =itx.next();
        		if(!map.containsKey(foll)) {
        			map.put(foll, 1);
        		}else {
        			int z=map.get(foll).intValue();
        			map.put(foll,z+1);
        		}
        	}
        }
        
//        输出每个人 及其关注的人        
//        for(Entry<String, Set<String>> entry : followsGraph.entrySet())
//        {
//        	System.out.println(entry.getKey());
//        	System.out.println("--------------------------------------");
//        	for(String str : entry.getValue())
//        	{
//        		System.out.println(str);
//        	}
//        }
        
        
        //输出排序前的map
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//        	String key = entry.getKey();
//        	String value = entry.getValue().toString();
//        	System.out.println("key = " + key + " value = " +value);
//        	}
//        	System.out.println("");
        	
//       map按Value值进行排序的函数
		Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		};
		// map转换成list进行排序
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		// 排序
		Collections.sort(list, valueComparator);
		for (Map.Entry<String, Integer> entry : list) {
//			System.out.println("key = " + entry.getKey() + " value = " +entry.getValue()); //输出排序后的map
			v.add(entry.getKey());
		}
        	return v;
    }
}

