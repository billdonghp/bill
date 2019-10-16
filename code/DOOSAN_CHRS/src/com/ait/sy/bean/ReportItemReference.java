package com.ait.sy.bean;

public class ReportItemReference {

	private String type;
	
	private String table_name ;

	private int item_no;
	
	private int order_no ;

	private String item_name;

	private String item_id;
	
	public ReportItemReference(){}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public int getItem_no() {
		return item_no;
	}

	public void setItem_no(int item_no) {
		this.item_no = item_no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public String getTable_name() {
		return table_name;
	}

	
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
}
