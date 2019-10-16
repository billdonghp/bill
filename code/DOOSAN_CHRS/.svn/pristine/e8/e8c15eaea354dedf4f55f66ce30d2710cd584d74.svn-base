package com.ait.ar.dao;

import java.util.List;

import com.ait.ar.bean.Supervisor;
import com.ait.util.DataAccessException;


public interface SupervisorDAO {
  /**
   * getAllSupervisor
   *
   * @return List
   */
  public List getAllSupervisor(String cpny_id)throws DataAccessException;
  
  /**
   * getAllSupervisorCount
   *
   * @return List
   */
  public int getAllSupervisorCount(Object parameterObject)throws DataAccessException;  
  
  /**
   * getAllSupervisorList
   *
   * @return List
   */
  public List getAllSupervisorList(Object parameterObject,int currentPage,int pageSize)throws DataAccessException;

  /**
   * getAllSupervisor
   * @param empId String
   * @return List
   */
  public List getAllSupervisorByEmpId(String empId, String cpny_id)throws DataAccessException;
  
  /**
   * deleteSupervisor
   *
   * @param supervisorID String
   */
  public void deleteSupervisor(String supervisorID)throws DataAccessException;

  /**
   * addSupervisor
   *
   * @param supervisor Supervisor
   */
  public void addSupervisor(Supervisor supervisor)throws DataAccessException;

  /**
   * getSupervisorById
   * <br>
   * 根据输入的考勤员工号，来返回考勤员
   * @param supervisorId String
   * @return Supervisor
   */
  public Supervisor getSupervisorById(String supervisorId)throws DataAccessException;

}
