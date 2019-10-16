package com.ait.ar.dao;

import java.util.List;

import com.ait.ar.bean.DynamicGroup;
import com.ait.util.DataAccessException;

public interface DynamicGroupDAO {
	/**
	 * addDynamicGroup
	 * <br>
	 * 添加一个动态组，同时此动态组的组成条件也被添加到数据库中。
	 * @param group DynamicGroup
	 */
	public int addDynamicGroup(DynamicGroup group, String message) throws DataAccessException;

	/**
	 * deleteGroup
	 * <br>
	 * 根据输入的组号，删除一个动态组，在删除组本身之前，要删除所有关联于此组的条件。
	 * @param groupNo int
	 * @return int
	 */
	public int deleteGroup(int groupNo) throws DataAccessException;

	/**
	 * getGroupMembers
	 * <br>
	 * 根据输入的组号，得出所有符合本组条件的员工，将员工的工号和姓名存储到list中，供其它操作使用。
	 * @param groupNo int
	 * @return List
	 */
	public List getGroupMembers(int groupNo,String cpny_id) throws DataAccessException;

	/**
	 * getGroupList
	 * <br>
	 * 得出所有已经创建的组，并且按照组的拼音顺序排序。
	 * @return List
	 */
	public List getGroupList(String ccpny_id) throws DataAccessException;

	/**
	 * getGroupByNo
	 *
	 * @param groupNo int
	 * @return DynamicGroup
	 */
	public DynamicGroup getGroupByNo(int groupNo) throws DataAccessException;

	/**
	 * setLoginID
	 *
	 * @param loginID String
	 */
	public void setLoginID(String loginID);

	/**
	 * setCnpyID
	 *
	 * @param cnpyID String
	 */
	public void setCnpyID(String cnpyID);
}
