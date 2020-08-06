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
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   映射为一系列在vertices中的L类型的点
    
    // Representation invariant（表示 不变性）:
    //   泛型L应为immutable类型
    //    vertices中不能包含重复的L类型的元素
    
    // Safety from rep exposure（表示暴露的安全性）:
   //  所有的区域块都是私有的。
   //   尽管vertices的类型是List，
   //但类中重写的方法并没有以他们作为参数，并没有多个变量指向一个相同的单元。
   //并且将其有关键词final定义，使得其引用不可变.
   //总之不存在表示暴露
    
    // TODO constructor
    
    // TODO checkRep
    private void checkRep(){
//    	for(int i=0;i<vertices.size();i++) {
//    		for(int j=0;j<vertices.size()&&j!=i;j++) {
//    			assert (vertices.get(i).get_title().equals(vertices.get(j).get_title()))==false;
//    		}
//    	}
//    	assert vertices.size() == new HashSet<Object>(vertices).size();
    }
    @Override public boolean add(L vertex) {
    	checkRep();
    	Vertex<L> v=new Vertex<L>(vertex);
        for(Vertex<L> v1:vertices) {
        	if(v1.get_title().equals(vertex)) {
        		return false;
        	}else {
        		continue;
        	}
        }
        vertices.add(v);
        checkRep();
        return true;
    }
    
    @Override public int set(L source, L target, int weight) {
    	checkRep();
    	int w1=0;
    	if(weight!=0) { //若weight不为0
    		for(Vertex<L> v:vertices) {
    			if(v.get_title().equals(source)) {//若起点存在
    				for(Map.Entry<Vertex<L>, Integer> entry: v.get_v_target().entrySet()) {
    					if(target.equals(entry.getKey().get_title())) {//在起点存在前提下，终点也存在
    						w1=entry.getValue(); //记录 先前存在边的weight
    						v.get_v_target().put(entry.getKey(), weight);//更新 weight
    						entry.getKey().get_v_source().put(v, weight);
    						checkRep();
    						return w1;// 返回先前边的weight
    					}else {
    						continue;
    					}
    				}
    				//若在起点存在前提下，终点不存在
    				Vertex<L> v2=new Vertex<L>(target);
    				if(contain(v2)==null)
    				vertices.add(v2); //终点加入
    				if(contain(v2)!=null) {			
    				v.get_v_target().put(contain(v2), weight); //更新 weight
    				contain(v2).get_v_source().put(v, weight);
    				}
    				checkRep();
    				return 0;
    			}else if(v.get_title().equals(target)) { //若终点存在
    				for(Map.Entry<Vertex<L>, Integer> entry: v.get_v_source().entrySet()) {
    					if(source.equals(entry.getKey().get_title())) {//在终点存在的前提下，起点也存在
    						w1=entry.getValue();//记录 先前存在边的weight
    						v.get_v_source().put(entry.getKey(), weight);//更新 weight
    						entry.getKey().get_v_target().put(v, weight);
    						checkRep();
    						return w1;// 返回先前边的weight
    					}else {
    						continue;
    					}
    				}
    				//若在终点存在前提下，起点不存在
    				Vertex<L> v1=new Vertex<L>(source);
    				if(contain(v1)==null)
    				vertices.add(v1);//起点加入
    				if(contain(v1)!=null) {
    				v.get_v_source().put(contain(v1), weight);//更新weight
    				contain(v1).get_v_target().put(v, weight);
    				}
    				checkRep();
    				return 0;
    			}else {
    				continue;
    			}
    		}
    		//若起点 终点均不存在
    		Vertex<L> v1=new Vertex<L>(source); 
    		Vertex<L> v2=new Vertex<L>(target);
    		vertices.add(v1);//均加入
    		vertices.add(v2);
    		v1.get_v_target().put(v2, weight);//更新 weight
    		v2.get_v_source().put(v1, weight);
    		checkRep();
    		return 0;
    	}else {//若 weight为0
    		for(Vertex<L> v:vertices){
    			if(v.get_title().equals(source)) {//若起点存在
    				Iterator<Vertex<L>> itx =v.get_v_target().keySet().iterator();
//    	    		Iterator<Integer> ity =v.v_target.values().iterator();
    				while(itx.hasNext()) {
    					Vertex<L> v1=itx.next();
//    					int w=ity.next();
    					if(target.equals(v1.get_title())) {//在起点存在前提下，终点也存在
//    						w1=w;//记录 先前边的weight
    						itx.remove();//删除先前存在的边（删除的为v的target）
//    						ity.remove();
    						w1=v1.get_v_source().get(v);
    						v1.get_v_source().remove(v);//（删除的为target的source）
    					}
    				}
    				checkRep();
    				return w1;//返回先前存在的边的 weight
    			}else if(v.get_title().equals(target)) {//若终点存在
    				Iterator<Vertex<L>> itx =v.get_v_source().keySet().iterator();
    				while(itx.hasNext()) {
    					Vertex<L> v1=itx.next();
    					if(source.equals(v1.get_title())) {//在终点存在的前提下，起点也存在
    						itx.remove();//删除先前存在的边（删除的为v的source）
    						w1=v1.get_v_target().get(v);//记录 先前边的weight
    						v1.get_v_target().remove(v);//（删除的为source的target）
    					}
    				}	
    				checkRep();
    				return w1;//返回先前存在的边的 wieght
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
        for(Vertex<L> v:vertices) {
        	if(v.get_title().equals(vertex)) {
        		//由于remove的元素 不影响遍历的 集合 ，若以不用改为迭代器的方法
        		for(Map.Entry<Vertex<L>, Integer> entry: v.get_v_source().entrySet()) {
        			entry.getKey().get_v_target().remove(v);
        		}
        		for(Map.Entry<Vertex<L>, Integer> entry: v.get_v_target().entrySet()) {
        			entry.getKey().get_v_source().remove(v);
        		}
        		v.get_v_source().clear();
        		v.get_v_target().clear();
        		vertices.remove(v);
        		checkRep();
        		return true;
        	}else {
        		continue;
        	}
        }
        checkRep();
        return false;
    }
    
    @Override public Set<L> vertices() {
    	checkRep();
    	Set<L> s=new HashSet<>();
        for(Vertex<L> v:vertices) {
        	s.add(v.get_title());
        }
        checkRep();
        return s;
    }
    
    @Override public Map<L, Integer> sources(L target) {
    	checkRep();
    	Map<L, Integer> map=new HashMap<>();
    	 for(Vertex<L> v:vertices) {
    		 for(Map.Entry<Vertex<L>, Integer> entry: v.get_v_target().entrySet()) {
    			 if(entry.getKey().get_title().equals(target)) {
    				 map.put(v.get_title(), entry.getValue());
    			 }
    		 }
    	 }
    	 checkRep();
    	 return map;
    }
    
    @Override public Map<L, Integer> targets(L source) {
    	checkRep();
    	Map<L, Integer> map=new HashMap<>();
    	for(Vertex<L> v:vertices) {
   		 for(Map.Entry<Vertex<L>, Integer> entry: v.get_v_source().entrySet()) {
   			 if(entry.getKey().get_title().equals(source)) {
   				 map.put(v.get_title(), entry.getValue());
   			 }
   		 }
   	 }
    	checkRep();
   	 return map;
    }
    
    // TODO toString()
    @Override
    public String toString() {
    	checkRep();
    	String a=vertices.toString();
    	checkRep();
    	return a;
    }
    public Vertex<L> contain(Vertex<L> v) {
    	checkRep();
    	for(Vertex<L> v1:vertices) {
        	if(v1.get_title().equals(v.get_title())) {
        		return v1;
        	}else {
        		continue;
        	}
        }
        checkRep();
        return null;
    }
}


/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {
	
	
    // TODO fields
	private  final L title;
	private final Map<Vertex<L>,Integer> v_source=new HashMap<>();
	private final Map<Vertex<L>,Integer> v_target=new HashMap<>();
    
    // Abstraction function:
    //   如果表示属于title，则映射为一个L类型的标题
	//   如果表示属于v_source，则映射为一系列的由Vertex类型到integer类型的映射
	//   如果表示属于v_target，则映射为一系列的由Vertex类型到integer类型的映射
	
    // Representation invariant:
	//   泛型L应为immutable类型
    //   v_source以及v_target中的value值要大于零。
	
    // Safety from rep exposure:
    //   所有的区域都是私有的，并且所有成员变量的类型都是immutable的
    
    // TODO constructor
	public Vertex(L title) {
		checkRep();
		this.title=title;
		checkRep();
	}
    
    // TODO checkRep
	private void checkRep(){
    	for(int w:this.v_source.values()) {
    		assert w>0;
    	}
    	for(int w:this.v_target.values()) {
    		assert w>0;
    	}
    }
    
    // TODO methods
	/*
	 * get the title of the vertex
	 * 
	 * @return the title of the vertex
	 */
	public L get_title() {
		checkRep();
		return this.title;
	}

	/*
	 * get the source of the vertex
	 * 
	 * @return the source of the vertex
	 */
	public Map<Vertex<L>,Integer> get_v_source() {
		checkRep();
		return this.v_source;
	}
	
	/*
	 * get the target of the vertex
	 * 
	 * @return the target of the vertex
	 */
	public Map<Vertex<L>,Integer> get_v_target() {
		checkRep();
		return this.v_target;
	}
	
	@Override
	public boolean equals(Object b) {
		Vertex<L> other=(Vertex<L>) b;
		if(this.title.equals(other.title)) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public int hashCode() {
		
		return title.hashCode()*37;
	}
    
    // TODO toString()
	@Override
    public String toString() {
    	checkRep();
    	String a;
    	a="title:"+this.title+"  ";
    	for(Map.Entry<Vertex<L>, Integer> entry:this.v_source.entrySet()) {
    		a=a+"("+entry.getKey().title+"->"+this.title+")";
    	}
    	for(Map.Entry<Vertex<L>, Integer> entry:this.v_target.entrySet()) {
    		a=a+"("+this.title+"->"+entry.getKey().title+")";
    	}
    	checkRep();
    	return a;
    }
}
