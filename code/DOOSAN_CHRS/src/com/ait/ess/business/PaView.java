package com.ait.ess.business;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;


public class PaView {
  private static ServiceLocator services;
  private java.util.Vector vlist;
  public PaView() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {
    }
  }
  public int getCountHistory(String gainSQL)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ResultSetMetaData rsmd = null;

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(gainSQL);
      rs = pstmt.executeQuery();
      rsmd = rs.getMetaData();
      return rsmd.getColumnCount();
    }
    catch (SQLException ex) {
      return 0;
    }
    catch (ServiceLocatorException ex) {
      return 0;
    }
    finally{
      rs.close();
      pstmt.close();
      conn.close();
    }
  }
  public Vector gainPaHistory(String gainSQL)throws Exception{

    PaView paview = new PaView();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ResultSetMetaData rsmd = null;
    Vector pa_vector = new Vector();
    Vector data_v =null;

    conn = services.getConnection();
    pstmt = conn.prepareStatement(gainSQL);
    rs = pstmt.executeQuery();
    rsmd = rs.getMetaData();
    data_v = new Vector();
    //int z = Integer.parseInt(x)*Integer.parseInt(y);
    try {
      for (int i = 1; i <= rsmd.getColumnCount(); i++) {
        data_v.add(rsmd.getColumnName(i));
      }
      paview.setVlist(data_v);
      pa_vector.add(paview);
      if (rs.next()) {
        Vector V_data = new Vector();
        PaView paviewsecond = new PaView();
        for(int i = 1; i <=rsmd.getColumnCount(); i++) {
          V_data.add(rs.getString(i)!=null?rs.getString(i):"");
        }
        paviewsecond.setVlist(V_data);
        pa_vector.add(paviewsecond);
      }
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }
    finally{
      rs.close();
      pstmt.close();
      conn.close();
    }
    return pa_vector;
  }
  public Vector getVlist() {
    return vlist;
  }
  public void setVlist(Vector vlist) {
    this.vlist = vlist;
  }
}
