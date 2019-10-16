package com.ait.ar.dao;

import java.util.List;

import com.ait.ar.bean.GroupCondition;
import com.ait.util.DataAccessException;

public interface GroupConditionDAO {
    /**
     * addGroupCondition <br>
     * 为动态组添加条件，所有条件之间的关系是 intersection 关系。
     * 
     * @param condition
     *            GroupCondition
     */
    public int addGroupCondition(GroupCondition condition)
            throws DataAccessException;

    /**
     * addGroupCondition <br>
     * 为动态组批量添加条件，所有条件之间的关系是 intersection 关系。
     * 
     * @param condition
     *            GroupCondition
     */
    public int addGroupCondition(String groupNo, String[] condition,
            String[] relation, String[] field1, String[] field2)
            throws DataAccessException;

    /**
     * getConditionList <br>
     * 根据输入的组编号，来导出所有关联于此组的条件。
     * 
     * @param groupNo
     *            int
     * @return List
     */
    public List getConditionList(int groupNo) throws DataAccessException;

    /**
     * deleteGroupCondition <br>
     * 根据条件号，来删除某个关联动态组的条件,其中 conditionNo 是条件的编号。
     * 
     * @param groupNo
     *            String
     */
    public void deleteGroupCondition(int conditionNo)
            throws DataAccessException;

    public int modGroupCondition(String condition_no, String field1,
            String field2);
    /**
     * getConditionList <br>
     * 根据输入的组条件编号，来修改此条件的限制field。
     */
}
