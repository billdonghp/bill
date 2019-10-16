package com.ait.hrm.dao.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ait.hrm.bean.InfoTable;
import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;

public class InfoTableDAO {
  private static ServiceLocator services;
  public InfoTableDAO() {
    try{
      services = ServiceLocator.getInstance();
    }catch(ServiceLocatorException e){
      e.printStackTrace();
    }
  }

  /**
   * getTableListByType
   * <br>
   * 通过输入信息类型,搜索出所有关联于此类信息的数据表。
   *
   * @param deptid String
   * @param searchcontent String
   * @return List
   * @throws DataAccessException
   */
  public List getTableListByType(String infoType)throws DataAccessException  {
    String sql = "select * from info_table where info_type = ? order by table_en_name";
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    List list = new ArrayList();
    try {
      con = services.getConnection();
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1,infoType);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        list.add(createInfoTable(rs));
      }
    }
    catch (ServiceLocatorException e) {
      throw new DataAccessException("Unable to retrieveconnection;" +
                                    e.getMessage(), e);
    }
    catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Unable to execute query;" + e.getMessage(),
                                    e);
    }
    finally {
      SqlUtil.close(rs, pstmt, con);
    }
     return list;
  }

  /**
   * createInfoTable
   *
   * @param rs ResultSet
   * @return InfoTable
   */
  private InfoTable createInfoTable(ResultSet rs) {
    InfoTable infoTable = new InfoTable();
    try{
      infoTable.setInfoType(StringUtil.checkNull(rs.getString("info_type")));
      infoTable.setTableId(StringUtil.checkNull(rs.getString("table_id")));
      infoTable.setTableName(StringUtil.checkNull(rs.getString("table_name")));
      infoTable.setEnTableName(StringUtil.checkNull(rs.getString("TABLE_EN_NAME")));
      infoTable.setKorTableName(StringUtil.checkNull(rs.getString("TABLE_KOR_NAME")));
    }catch(SQLException e){
      e.printStackTrace();
    }
    return infoTable;
  }

}
