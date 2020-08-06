/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {

	private final Graph<String> graph = Graph.empty();

	// Abstraction function:
	//   映射为一个空图
	// Representation invariant:
	//   true
	// Safety from rep exposure:
	//   所有的区域都是私有的，并且所有成员变量的类型都是immutable的

	/**
	 * Create a new poet with the graph from corpus (as described above).
	 * 
	 * @param corpus text file from which to derive the poet's affinity graph
	 * @throws IOException if the corpus file cannot be found or read
	 */

	public GraphPoet(File corpus) throws IOException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(corpus));
			String s = null;
			String b=" ";
			while ((s = reader.readLine()) != null) {
				b=b+" "+s;
			}
			b=b.trim();
			String a[] = b.split("\\s+|\\n");
			for(int i=0;i<a.length-1;i++) {
				graph.add(a[i].toLowerCase());
				graph.add(a[i+1].toLowerCase());
				int w=graph.set(a[i].toLowerCase(), a[i+1].toLowerCase(), 1);
				if(w!=0) {
					graph.set(a[i].toLowerCase(), a[i+1].toLowerCase(), w+1);
				}else {
					graph.set(a[i].toLowerCase(), a[i+1].toLowerCase(), 1);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// TODO checkRep

	/**
	 * Generate a poem.
	 * 
	 * @param input string from which to create the poem
	 * @return poem (as described above)
	 */
	public String poem(String input) {

		String b[]=input.split(" ");
		String af=b[0]+" ";
		int i=0;
		while(i<b.length-1) {
			Map<String,Integer> mapf =new HashMap<>();
			Map<String,Integer> map1 =new HashMap<>();
			map1=graph.targets(b[i].toLowerCase());
			for(Map.Entry<String, Integer> entry1:map1.entrySet()) {
//				if(entry1.getKey().toLowerCase().equals(b[i+1].toLowerCase())) {
//					continue;
//				}else {
					Map<String,Integer> map2 =new HashMap<>();
					map2=graph.targets(entry1.getKey().toLowerCase());
					for(Map.Entry<String, Integer> entry2:map2.entrySet()) {
						if(entry2.getKey().toLowerCase().equals(b[i+1].toLowerCase())) {
							mapf.put(entry1.getKey().toLowerCase(),entry1.getValue()+entry2.getValue());	
						}else {
							continue;
						}
					}
//				}
			}
			Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String, Integer>>() {
				@Override
				public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
					return o2.getValue() - o1.getValue();
				}
			};
			List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(mapf.entrySet());
			Collections.sort(list, valueComparator);
			for (Map.Entry<String, Integer> entry : list) {
				af=af+entry.getKey().toLowerCase()+" ";
				break;
			}
			i++;
			af=af+b[i]+" ";
		}
		af=af.trim();
		return af;
	}

	// TODO toString()
	@Override
	public String toString() {
		return graph.toString();
	}
}
