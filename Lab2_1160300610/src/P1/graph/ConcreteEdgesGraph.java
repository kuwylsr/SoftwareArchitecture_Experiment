/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //  如果表示属于vertices，则映射为一系列在vertices中的L类型的点
    //  如果表示属于edges，则映射为一系列在edges中的Edge类型的边
    
    // Representation invariant（表示 不变性）:
    //   泛型L应为immutable类型
    //   edges中的元素中的source和target必须存在在vertices集合中,且edges中的元素中的weight必须大于零
    
    // Safety from rep exposure（表示暴露的安全性）:
    //   所有的区域块都是私有的。
    //   尽管vertices以及edges分别是可变数据类型Set和List，
    //但类中重写的方法并没有以他们作为参数，并没有多个变量指向一个相同的单元。
    //并且将其有关键词final定义，使得其引用不可变.
    //总之不存在表示暴露。
    
    // TODO constructor
    
    // TODO checkRep
     protected void checkRep(){
    	for(Edge<L> e:edges) {
    		assert this.vertices.contains(e.get_source())==true;
    		assert this.vertices.contains(e.get_target())==true;
    		assert e.get_weight()>0;
    	}
    }
    
    
    @Override public boolean add(L vertex) {
    	checkRep();
        for(L x:vertices) {
        	if(x.equals(vertex)) {
        		return false;
        	}else {
        		continue;
        	}
        }
        vertices.add(vertex);
        checkRep();
        return true;
    }
    
    @Override public int set(L source, L target, int weight) {
    	checkRep();
    	Edge<L> e=new Edge<L>(source,target,weight);
    	Iterator<Edge<L>> it=edges.iterator();
    	int w1;
        if(weight!=0) {
        	for(Edge<L> x:edges) {
        		if(x.equals(e)) {
        			w1=x.get_weight();
        			x.set_weight(e.get_weight());;
        			checkRep();
        			return w1;
        		}else {
        			continue;
        		}
        	}
        	add(source);
        	add(target);
        	edges.add(e);
        	checkRep();
        	return 0;        	
        }else {
        	while(it.hasNext()) {//使用Iterator的remove方法删除元素，
        		//用foreach循环遍历容器时，不能对容器的内容进行改变
        		Edge<L> x=it.next();
        		if(x.equals(e)) {
        			it.remove();
        			checkRep();
        			return x.get_weight();
        		}else {
        			continue;
        		}
        	}
        	checkRep();
        	return 0;
        }
    }
    
    @Override public boolean remove(L vertex) {
    	checkRep();
    	Iterator<Edge<L>> it=edges.iterator();
        if(add(vertex)) {
        	vertices.remove(vertex);
        	checkRep();
        	return false;
        }else {
        	while(it.hasNext()){ //使用Iterator的remove方法删除元素，
        		//用foreach循环遍历容器时，不能对容器的内容进行改变
        		Edge<L> e=it.next();
        		if(e.get_source().equals(vertex)||e.get_target().equals(vertex)) {
        			it.remove();
        		}else {
        			continue;
        		}
        	}
        	vertices.remove(vertex);
        	checkRep();
        	return true;
        }
    }
    
    @Override public Set<L> vertices() {
    	checkRep();
        return vertices;
    }
    
    @Override public Map<L, Integer> sources(L target) {
    	checkRep();
    	Map<L, Integer> map=new HashMap<>();
        for(Edge<L> e:edges) {
        	if(e.get_target().equals(target)) {
        		map.put(e.get_source(), e.get_weight());
        	}
        }
        checkRep();
        return map;
    }
    
    @Override public Map<L, Integer> targets(L source) {
    	checkRep();
    	Map<L, Integer> map=new HashMap<>();
        for(Edge<L> e:edges) {
        	if(e.get_source().equals(source)) {
        		map.put(e.get_target(), e.get_weight());
        	}
        }
        checkRep();
        return map;
    }
    
    // TODO toString()
    @Override public String toString() {
    	checkRep();
    	String a = "The vertices:"+vertices.toString();
    	a=a+"  "+"The edge:"+edges.toString();
//    	System.out.println(a);
    	checkRep();
    	return a;
    }
    
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
	final class Edge<L> {
	
    // TODO fields
	private final L source;
	private  final L target;
	private  int weight;
    
    // Abstraction function:
    //   如果表示属于source或target，则映射为L类型的点
	//   如果表死属于weight，则映射为整型的数
	
    // Representation invariant:
	//  泛型L应为immutable类型
	//   weight需要满足大于0
	
    // Safety from rep exposure:
    //   所有的区域都是私有的，并且所有成员变量的类型都是immutable的
    
    // TODO constructor
	public Edge(L source,L target,int weight) {
		this.source=source;
		this.target=target;
		this.weight=weight;
	}
    
    // TODO checkRep
	private void checkRep(){
    	assert this.weight > 0; 	
    }
    
    // TODO methods
	/*
	 * get the source of the edge
	 * 
	 *@return the source of the edge 
	 */
	public L get_source() {
		checkRep();
		return this.source;
		
	}
	/*
	 * get the target of the edge
	 * 
	 * @return the target of the edge 
	 */
	public L get_target() {
		checkRep();
		return this.target;
	}
	/*
	 * get the weight of the edge
	 * 
	 * @return the weight of the edge 
	 */
	public int get_weight() {
		checkRep();
		return this.weight;
	}
	/*
	 * set the weight of the edge
	 * 
	 * @return the weight of the edge after setting
	 */
	public int set_weight(int weight) {
		checkRep();
		this.weight=weight;
		checkRep();
		return this.weight;
	}
	@Override
	public boolean equals(Object b) {
		Edge<L> other=(Edge<L>) b;
		if(this.source.equals(other.source)&&this.target.equals(other.target)) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
        int result = 1;
        result = prime * result + ((this == null) ? 0 : this.hashCode());
        return result;
	}
    
    // TODO toString()
	@Override
	public String toString() {
		checkRep();
		return String.format("(%s,%s)--%d", this.source,this.target,this.weight);
	}
}
