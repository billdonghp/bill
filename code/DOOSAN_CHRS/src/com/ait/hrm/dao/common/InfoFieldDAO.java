package com.ait.hrm.dao.common;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ait.hrm.bean.InfoField;
import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;

public class InfoFieldDAO {
  private static ServiceLocator services;
  public InfoFieldDAO() {
    try{
      services = ServiceLocator.getInstance();
    }catch(ServiceLocatorException e){
      e.printStackTrace();
    }
  }
  /**
   * getTableListByType
   * <br>
   * 通过输入table类型,搜索出所有关联于此table的字段。
   *
   * @param deptid String
   * @param searchcontent String
   * @return List
   * @throws DataAccessException
   */
  public List getFieldListByTable(String tableId)throws DataAccessException  {
    String sql = "select * from info_field where table_id = ? order by field_seq";
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    List list = new ArrayList();
    try {
      con = services.getConnection();
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1,tableId);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        list.add(createInfoField(rs));
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
  private InfoField createInfoField(ResultSet rs) {
    InfoField infoField = new InfoField();
    try{
      infoField.setFieldId(StringUtil.checkNull(rs.getString("FIELD_ID")));
      infoField.setEnFieldId(StringUtil.checkNull(rs.getString("FIELD_EN_ID")));
      infoField.setTableId(StringUtil.checkNull(rs.getString("TABLE_ID")));
      infoField.setFieldName(StringUtil.checkNull(rs.getString("FIELD_NAME")));
      infoField.setEnFieldName(StringUtil.checkNull(rs.getString("FIELD_EN_NAME")));
      infoField.setKorFieldName(StringUtil.checkNull(rs.getString("FIELD_KOR_NAME")));
      infoField.setFieldCode(StringUtil.checkNull(rs.getString("CODE")));
      //infoField.setCodeName(StringUtil.checkNull(rs.getString("CODENAME")));
      
    }catch(SQLException e){
      e.printStackTrace();
    }
    return infoField;
  }

}
