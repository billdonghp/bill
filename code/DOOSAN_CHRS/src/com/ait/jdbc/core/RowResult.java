/**
 * @date 2006-6-16
 */
package com.ait.jdbc.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ait.core.exception.GlRuntimeException;

/**
 * 结果集行对象
 * 注意：取字段值的索引和ResultSet一样，从1开始
 * @version 1.0
 */
public class RowResult {
	public final static int FIRSTINDEX = 1;
	private List valueList;
	private List colNameList;
	
	public RowResult(List colNameList){
		valueList = new ArrayList(colNameList.size());
		this.colNameList = colNameList;
	}
	
	public void add(Object value){
		valueList.add(value);
	}
	
	private int parseInt(Object o){
		int ret = 0;
		if (o == null)
			return ret;
		if (o instanceof Number)
			ret = ((Number)o).intValue();
		else
			ret = Integer.parseInt(o.toString());
		return ret;		
	}
	
	public int getInt(int i){
		Object o = getObj(i);
		return parseInt(o);
	}
	
	public Integer getInteger(int i){
		Object o = getObj(i);
		return o == null ? null : new Integer(parseInt(o));
	}
	
	public String getString(int i){
		Object o = getObj(i);
		return o == null ? null : o.toString();
	}
	
	private double parseDouble(Object o){
		double ret = 0;
		if (o == null)
			return ret;
		if (o instanceof Number)
			ret = ((Number)o).doubleValue();
		else
		    ret = Double.parseDouble(o.toString());
		return ret;		
	}
	
	public double getDouble(int i){
		Object o = getObj(i);
		return parseDouble(o);
	}
	
	public Double getDoubleObj(int i){
		Object o = getObj(i);
		return o == null ? null : new Double(parseDouble(o));
	}
	
	public float getFloat(int i){
		float ret = 0;
		Object o = getObj(i);
		if (o instanceof Number)
			ret = ((Number)o).floatValue();
		else
		    ret = Float.parseFloat(o.toString());
		return ret;
	}
	
	public Date getDate(int i){
		return ((Date)getObj(i));
	}
	
	private Object getObj(int i){
		return valueList.get(i - 1);
	}
	
	public int getInt(String columnName){
		return getInt(findColumn(columnName));
	}
	
	public Integer getInteger(String columnName){
		return getInteger(findColumn(columnName));
	}
	
	public String getString(String columnName){
		return getString(findColumn(columnName));
	}
	
	public Date getDate(String columnName){
		return getDate(findColumn(columnName));
	}
	
	public double getDouble(String columnName){
		return getDouble(findColumn(columnName));
	}
	
	public Double getDoubleObj(String columnName){
		return getDoubleObj(findColumn(columnName));
	}
	
	public float getFloat(String columnName){
		return getFloat(findColumn(columnName));
	}
	
	private int findColumn(String columnName){
		int ret = colNameList.indexOf(columnName.toUpperCase()) + 1;
		if (ret <= 0){
			throw new GlRuntimeException("查询结果集中不存在列[" + columnName + "]");
		}
		return ret;
	}

}
