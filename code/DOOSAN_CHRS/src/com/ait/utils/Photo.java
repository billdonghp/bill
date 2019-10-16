package com.ait.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.sql.BLOB;

import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;

public class Photo {
  private static ServiceLocator services;
                public Photo() {
                try {
                services = ServiceLocator.getInstance();
                }
                catch (ServiceLocatorException ex) {
                }
        }

  String DelPhoto_SQL = "Delete HR_EMP_BINARY_PHOTO Where EmpID = ?"; //删除以二进制存储表的记录

  String CleanEmpPhoto_SQL = "Update hr_Personal_info Set"
      + "  PHOTOPATH = '' "
      + "  Where EmpID = ? ";

  String UpdatePhoto_SQL = " Update hr_Personal_info Set"
      + "  PHOTOPATH = ?  "
      + "  Where EmpID = ? ";

  String InserteEmptyBlob_SQL =
      "Insert Into HR_EMP_BINARY_PHOTO ( empID,binary_photo,Create_Date,Created_By,activity) values(?,empty_blob(),sysDate,?,1)";
  String ListBin_SQL =
      "Select binary_photo from HR_EMP_BINARY_PHOTO Where empID = ? For Update";
  String UpdateBin_SQL =
      " Update HR_EMP_BINARY_PHOTO Set binary_photo = ? Where empID = ?";

  String CheckBinary_SQL = "Select * from HR_EMP_BINARY_PHOTO Where EmpID = ?";
  String CheckEmp_SQL = "Select * from hr_Personal_info where EmpID = ? And ( PHOTOPATH <> '' Or PHOTOPATH <> null ) ";
  public Connection Conn() throws Exception {
    Connection Conn = null; //建立参数声明
    try {
      Conn = services.getConnection(); //得到连接
    }
    catch (Exception e) {
      System.out.print("Conn Error:" + e);
    }
    return Conn;
  }

  public void delPhoto(String delEmpID) throws Exception {
    try {
      int flag = checkPhoto(delEmpID);
      if (flag == 3 || flag == 1) {
        delEmpPhoto(delEmpID);
      }
      if (flag == 3 || flag == 2) {
        delBinPhoto(delEmpID);
      }
    }
    catch (Exception e) {
      System.out.println("Del Error: " + e);
    }

  }

  public void delBinPhoto(String delEmpID) throws Exception {

    PreparedStatement pstmt = null;
    Connection conn = Conn();
    //boolean flag = true;
    try {
      pstmt = conn.prepareStatement(DelPhoto_SQL);
      pstmt.setString(1, delEmpID);
      if (pstmt.executeUpdate() == 0) { //调用上面形成的DELETE语句，为?即参数变量赋值
        //flag = false;
        throw new NotExistException("DelPhoto_SQL : " + "No data exists");
      }
    }
    catch (SQLException e) {
      System.out.print("delBinPhoto: " + e);
    }
    finally {
      if (pstmt != null) {
        pstmt.close();
      } //关闭参数声明
      if (conn != null) {
        conn.close();
      } //关闭参数声明
      //return flag;
    }

  }

  public void delEmpPhoto(String delEmpID) throws Exception {
    PreparedStatement pstmt = null;
    Connection conn = Conn();
    //boolean flag = true;
    try {
      pstmt = conn.prepareStatement(CleanEmpPhoto_SQL);
      pstmt.setString(1, delEmpID);
      if (pstmt.executeUpdate() == 0) { //调用上面形成的DELETE语句，为?即参数变量赋值
        //flag = false;
        throw new NotExistException("CleanEmpPhoto_SQL : " + "No data exists");
      }
    }
    catch (SQLException e) {
      System.out.print("delEmpPhoto: " + e);
    }
    finally {
      if (pstmt != null) {
        pstmt.close();
      } //关闭参数声明
      if (conn != null) {
        conn.close();
      } //关闭参数声明
      //return flag;
    }

  }

  public void insert(String empID, String PhotoFileName) throws Exception {
    Connection conn = Conn();
    PreparedStatement pstmt = null;

    try {
      pstmt = conn.prepareStatement(UpdatePhoto_SQL); //执行更新数据方法

      pstmt.setString(1, PhotoFileName);
      pstmt.setString(2, empID);
      if (pstmt.executeUpdate() == 0) {
        throw new NotExistException("UpdataPhoto_SQL: " +
                                    "Update No data exists");
      }
    }
    catch (SQLException e) {
      System.out.print("insert Error:" + e);
    }
    finally {
      if (pstmt != null) {
        pstmt.close();
      }
      if (conn != null) {
        conn.close();
      }
    }

  }

  public int checkPhoto(String empID) throws Exception { //若两个表里都没有照片数据返回0，都有返回3，独有empPhoto返回1，独有binPhoto返回2
    int empFlag = 0;
    int binFlag = 0;
    int flag = 0;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Connection conn = Conn();

    try {
      pstmt = conn.prepareStatement(CheckEmp_SQL); //在HR_EMP_BINARY_PHOTO表中是否有二进制photo
      pstmt.setString(1, empID);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        empFlag = 1;
      }

      pstmt = null;
      rs = null;
      pstmt = conn.prepareStatement(CheckBinary_SQL); //在HR_EMP_BINARY_PHOTO表中是否有二进制photo
      pstmt.setString(1, empID);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        binFlag = 1;
      }

      if (empFlag == 1 && binFlag == 1) {
        flag = 3;
      }
      else
      if (empFlag == 1 && binFlag == 0) {
        flag = 1;
      }
      else
      if (empFlag == 0 && binFlag == 1) {
        flag = 2;
      }
    }
    catch (SQLException e) {
      System.out.print("checkPhoto Error:" + e);
    }
    finally {
      if (rs != null) {
        rs.close();
      } //关闭数据集
      if (pstmt != null) {
        pstmt.close();
      } //关闭参数声明
      if (conn != null) {
        conn.close();
      } //关闭连接
    }
    return flag;
  }

  public void insertBin(String empID, byte[] image, String Crd) throws
      Exception {
    PreparedStatement pstmt = null;
    //PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn = Conn();
    BLOB blob = null;
    //System.out.println("image byte[] length is:" + image.length);

    try {
      pstmt = conn.prepareStatement(InserteEmptyBlob_SQL); //执行更新数据方法
      pstmt.setString(1, empID);
      pstmt.setString(2, Crd);
      conn.setAutoCommit(false);
      //System.out.println("insert empty");

      if (pstmt.executeUpdate() == 0) {
        throw new NotExistException("InserteEmptyBlob_SQL: " +
                                    "Update No data exists");
      }
      pstmt.close();

      pstmt = conn.prepareStatement(ListBin_SQL); //执行更新数据方法
      pstmt.setString(1, empID);
      //System.out.println("list empty");
      rs = pstmt.executeQuery();

      if (rs.next()) {
        //System.out.print(1);
         blob =(BLOB) rs.getBlob(1);
      }

      pstmt.close();

      //System.out.println("before setBytes Blob");
      //blob.setBytes(0, image);
      //System.out.println("after setBytes Blob");

      BufferedOutputStream out = new BufferedOutputStream(blob.getBinaryOutputStream());
      //System.out.println("out 1");
      int size = 8192;

      byte[] buffer = new byte[size];
      int length = -1;
      ByteArrayInputStream in = new ByteArrayInputStream(image);
      //System.out.println("befor while");
      while ( (length = in.read(buffer)) != -1) {
        out.write(buffer,0,length);
      }
      //System.out.println("after while");
      in.close();
      out.close();

      /*
        InputStream in = blob.getBinaryStream();
        //System.out.println("1");
        in.read(image);
        //System.out.println("2");
        in.close();
        //System.out.println("3");
        pstmt = conn.prepareStatement(UpdateBin_SQL); //执行更新数据方法
        //System.out.println("4");
        pstmt.setBlob(1, blob);
        //System.out.println("5");
        pstmt.setString(2, empID);


        //System.out.println("update empty");
        if (pstmt.executeUpdate() == 0) {
          throw new NotExistException("InserteEmptyBlob_SQL: " +
                                      "Update No data exists");

        }
       */
      conn.commit();
    }
    catch (SQLException e) {
      e.printStackTrace();
      System.out.print("insertBin Error:" +e);
    }
    finally {
      if (pstmt != null) {
        pstmt.close();
      }
      if (conn != null) {
        conn.close();
      }
      if (rs != null) {
        rs.close();
      }
    }

  }
}
