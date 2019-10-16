package com.ait.itfc.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 一个人事表与一个接口表的映射
 * @author Pennix
 *
 */
public class Mapping implements Serializable {

	private static final long serialVersionUID = 1L;

	private String emTable;

	private String ifTable;

	/**
	 * 人事表字段与计算公式Map
	 */
	private Map<String, String> formulas = new HashMap<String, String>();

	public String getEmTable() {
		return this.emTable;
	}

	public void setEmTable(String emTable) {
		this.emTable = emTable;
	}

	public String getIfTable() {
		return this.ifTable;
	}

	public void setIfTable(String ifTable) {
		this.ifTable = ifTable;
	}

	/**
	 * 添加一条人事表字段与计算公式的对应
	 * @param columnName
	 * @param formula
	 */
	public void addFormula(String columnName, String formula) {
		this.formulas.put(columnName, formula);
	}

	public Set<String> getEmColumns() {
		return this.formulas.keySet();
	}

	/**
	 * 取得人事表字段对应的计算公式
	 * @param columnName
	 * @return
	 */
	public String getFormula(String columnName) {
		return this.formulas.get(columnName);
	}
}