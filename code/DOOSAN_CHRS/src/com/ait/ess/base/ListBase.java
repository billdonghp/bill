package com.ait.ess.base;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

//import org.springframework.jdbc.core.RowCallbackHandler;

/**
 * oracle 分页语句 select * from ( select * from ( select rownum record_id,a.* from (
 * [原始结果集] ) a) b ) c where rownum <= [每页显示多少行] and c.record_id >= [从第几行开始]
 * order by menu_no;
 */
public abstract class ListBase extends DAOBase
// implements RowCallbackHandler
{
	protected int pageSize = 0;// 每页显示的行数

	protected int pageNumber = 0;// 当前页数

	protected int pageCount = 0;// 页面数

	protected int recordCount = 0;// 记录总数

	protected String orderBy = null;// 使用的Order by 字段名称

	protected String tableName = null;// 查询的数据库表名

	protected String[] fieldNames = null;// 查询的字段名列表

	// record number of one page
	public void setPageSize(int size) {
		pageSize = size;
	}

	public int getPageSize() {
		return pageSize;
	}

	// current page number
	public void setPageNumber(int pageNo) {
		pageNumber = pageNo;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	// page count
	public void setPageCount() {
		if (pageSize > 0) {
			pageCount = recordCount / pageSize;
		} else {
			Logger.getLogger(getClass()).error("page size cannot < 0!");
		}
	}

	public int getPageCount() {
		return pageCount;
	}

	// total record count
	public abstract void setRecordCount();

	public int getRecordCount() {
		return recordCount;
	}

	// 获取全部列表
	public List getTotalList() {
		return getList(0);
	}

	// 获取分页列表第pageNumber页，每页显示pageSize行
	public List getPageList() {
		return getList(1);
	}

	public List getList(int page) {
		/*
		 * String sqlString = "select * " + "from ( " + "select * from ( " +
		 * "select rownum record_id,a.* " + "from sy_menu a) b ) c " + "where
		 * rownum <= " + pageSize + " " + "and c.record_id >= " + (pageNumber-1) *
		 * pageSize + " " + orderBy==null?"":"order by " + orderBy + " ";
		 */return null;
	}

	// 单行处理的回调
	public abstract void processRow(ResultSet rs) throws SQLException;
}