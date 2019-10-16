package com.ait.ar.dao;
import java.util.List;

import com.ait.ar.bean.SupervisorInfo;
import com.ait.util.DataAccessException;

public interface SupervisorInfoDAO {
  /**
   * addSupervisorInfo
   *
   * @param supervisorInfo Supervisor
   */
  public int addSupervisorInfo(SupervisorInfo supervisorInfo)throws DataAccessException;
  
  /**
   * editSupervisorScreenGrantNo
   *
   * @param supervisorInfo Supervisor
   */
  public void editSupervisorScreenGrantNo(String supervisorId)throws DataAccessException;

  /**
   * deleteSupervisorScreenGrantNo
   *
   * @param supervisorInfo Supervisor
   */
  public void deleteSupervisorScreenGrantNo(String supervisorID)throws DataAccessException;
  
  /**
   * deleteSupervisorInfo
   * <br>
   * 考勤员直接管理的对象是员工，我们可以根据输入的员工工号来删除被考勤对象
   * @param empID String
   * @return int
   */
  public int deleteSupervisorInfo(SupervisorInfo supervisorInfo)throws DataAccessException;

  /**
   * getDeptListWithObject
   * <br>
   * 根据输入的考勤员编号,找到该考勤员所考勤的员工所在的所有部门.
   * @param supervisorId String
   * @return List
   */
  public List getDeptListWithObject(String supervisorId)throws DataAccessException;

  /**
   * getSupervisedDeptList
   * <br>
   * 根据输入的考勤员的工号，得到此考勤员所管理的所有员工。
   * @param supervisorID String
   * @return List
   */
  public List getSupervisorInfoList(String supervisorID) throws DataAccessException;

  public List getSupervisorItems(String supervisorID);

  public void upDateItemId(String supervisorID, String itemId);

}
