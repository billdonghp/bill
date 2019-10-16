package com.ait.ar.dao;
import java.util.List;

import com.ait.ar.bean.ArCalendar;
import com.ait.util.DataAccessException;

public interface ArCalendarDAO {
  /**
   * getCalendar
   *
   * @param month String
   * @throws DataAccessException
   * @return List
   */
  public List getCalendarByMonth(String month) throws DataAccessException;

  /**
   * getCalendar
   *
   * @param date String
   * @return ArCalendar
   */
  public ArCalendar getCalendar(String date)throws DataAccessException;

  /**
   * addCalendarProcedure
   *
   * @param year String
   * @param createdBy String
   * @throws DataAccessException
   * @return boolean
   */
  public boolean addCalendarProcedure(String year,String createdBy)throws DataAccessException;

  /**
   * modifyCalendarProcedure
   *
   * @param calendar ArCalendar
   * @throws DataAccessException
   * @return boolean
   */
  public boolean modifyCalendarProcedure(ArCalendar calendar)throws DataAccessException;
}
