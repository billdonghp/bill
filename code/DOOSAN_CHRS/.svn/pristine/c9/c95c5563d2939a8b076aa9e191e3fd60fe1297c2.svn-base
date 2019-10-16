/**
 * @date 2006-6-16
 */
package com.ait.jdbc.core;

import java.sql.Types;

/**
 * sql 参数类型
 * @version 
 */
public class ParameterType {
	
	private int value;
	
	public static ParameterType STRING = new ParameterType(Types.VARCHAR);
	
	public static ParameterType INTEGER = new ParameterType(Types.INTEGER);
	
	public static ParameterType DOUBLE = new ParameterType(Types.DOUBLE);
	
	public static ParameterType FLOAT = new ParameterType(Types.FLOAT);
	
	public static ParameterType DATE = new ParameterType(Types.DATE);
	
	public static ParameterType TIMESTAMP = new ParameterType(Types.TIMESTAMP);
	
	private ParameterType(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public String toString(){
		return Integer.toString(getValue());
	}

}
