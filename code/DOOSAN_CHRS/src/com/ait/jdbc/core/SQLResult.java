/**
 * @Copyright AIT
 * @author qinxd
 * @date 2006-6-15
 */
package com.ait.jdbc.core;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 结果集对象
 * @version 
 */
public class SQLResult {
	private List rowList = new ArrayList();
	private String[] columnClassNameList;//<String>
	private int[] sqlTypes;
	private List colNameList;
	private int columnCount;
	
	public SQLResult(ResultSetMetaData rsmd) throws SQLException{
		initialized(rsmd);
	}	
	
	private void initialized(ResultSetMetaData rsmd) throws SQLException{
		columnCount = rsmd.getColumnCount();
		columnClassNameList = new String[columnCount];
		sqlTypes = new int[columnCount];
		colNameList = new ArrayList(columnCount);
		for (int i = 0; i < columnCount; i++){
			columnClassNameList[i] = rsmd.getColumnClassName(i + 1);
			sqlTypes[i] = rsmd.getColumnType(i + 1);
			colNameList.add(rsmd.getColumnName(i + 1).toUpperCase());
		}
	} 
	
	void add(RowResult rt){
		rowList.add(rt);
	}
	
	public RowResult get(int index){
		Object o = rowList.get(index);
		return (RowResult)o;
	}
	
	public Iterator iterator(){
		return rowList.iterator();
	}
	
	public int size(){
		return rowList.size();
	}

	public String[] getColumnClassNameList() {
		return columnClassNameList;
	}

	public void setColumnClassNameList(String[] columnClassNameList) {
		this.columnClassNameList = columnClassNameList;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

	public List getColNameList() {
		return colNameList;
	}

	/*public List getRowList() {
		return rowList;
	}*/

	public int[] getSqlTypes() {
		return sqlTypes;
	}
	
	public String toString(){
		return rowList.toString();
	}

}
