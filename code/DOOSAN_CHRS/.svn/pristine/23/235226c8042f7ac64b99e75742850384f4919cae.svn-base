package com.ait.hrm.dao.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ait.hrm.bean.InfoType;
import com.ait.util.DataAccessException;
import com.ait.util.DebugUtil;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;

public class InfoTypeDAO {
  private static ServiceLocator services;
  public InfoTypeDAO() {
    try{
      services = ServiceLocator.getInstance();
    }catch(ServiceLocatorException e){
      e.printStackTrace();
    }
  }

  /**
   * getAllInfoType
   * <br>
   * 列出所有可搜索的分类
   *
   * @return List
   * @throws DataAccessException
   */
  public List getAllInfoType()throws DataAccessException  {
    String sql = "select * from INFO_TYPE order by type_seq";
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    List list = new ArrayList();
    try {
      con = services.getConnection();
      stmt = con.createStatement();
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        list.add(createInfoType(rs));
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
      SqlUtil.close(rs, stmt, con);
    }
     return list;
  }


  private InfoType createInfoType(ResultSet rs) {
    InfoType infotype = new InfoType();
    try {
      infotype.setTypeId(StringUtil.checkNull(rs.getString("TYPE_ID")));
      infotype.setTypeName(StringUtil.checkNull(rs.getString("TYPE_NAME")));
      infotype.setEnTypeName(StringUtil.checkNull(rs.getString("TYPE_EN_NAME")));
      infotype.setKorTypeName(StringUtil.checkNull(rs.getString("TYPE_KOR_NAME")));
    }
    catch (SQLException ex) {
      DebugUtil.printStackTrace(ex);
    }
    return infotype;

  }


}
