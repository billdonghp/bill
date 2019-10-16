package com.ait.core.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class NamedResultSet extends LinkedHashMap{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1582938008889038382L;

	private String name;
	
	//如果不存在key则按照类型自动初始一个值
	private boolean nullToInitialize;

    public NamedResultSet(int initialCapacity, float loadFactor) {
    	super(initialCapacity,loadFactor);
    }

    public NamedResultSet(int initialCapacity) {
    	super(initialCapacity);
    }

    public NamedResultSet() {
    	super();
    }

    public NamedResultSet(Map m) {
    	super(m);
    
    }

    public NamedResultSet(int initialCapacity, float loadFactor,
                         boolean accessOrder) {
    	super(initialCapacity,loadFactor,accessOrder);
    }
    
    public void setName(String name){
    	this.name = name;
    }
    
    public String getName(){
    	return this.name;
    }
    
    public void setNullToInitialize(boolean value){
    	this.nullToInitialize = value;
    }
    
    public boolean getNullToInitialize(){
    	return this.nullToInitialize;
    }

}
