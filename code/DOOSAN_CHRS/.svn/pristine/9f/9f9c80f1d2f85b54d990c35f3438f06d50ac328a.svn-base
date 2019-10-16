package com.ait.kpa;

/**
 * <p>Title: Emanager</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * @author AIT.Duel
 * @version 2.0
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.ait.util.PageControl;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.utils.NotExistException;

public class PaProgress {
  private static ServiceLocator services;
  public PaProgress() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {
    }
  }

  Vector Vlist = new Vector(); //产生Vector的对象Vlist
  Connection Conn = null; //产生连接初始化的对象Conn
  int i = 0; //初始化循环Vector的变量值

  //选出所有记录列表的sql语句
  String LIST_SQL = "SELECT"
      + " pa_month_str,"
      + " att_mo_flag,"
      + " param_initial_flag,"
      + " calc_flag,"
      + " att_mo_lock_flag,"
      + " calc_lock_flag,"
      + " pa_lock_flag,"
      + " lock_user_id,"
      + " lock_date"
      + " FROM pa_progress ";

  //顺序
  String ORDER_SQL = " ORDER BY pa_month_str";

  //选出所有记录列表的sql语句
  String SELECT_SQL = "SELECT"
      + " pa_month_str,"
      + " att_mo_flag,"
      + " param_initial_flag,"
      + " calc_flag,"
      + " att_mo_lock_flag,"
      + " calc_lock_flag,"
      + " pa_lock_flag,"
      + " lock_user_id,"
      + " lock_date"
      + " FROM pa_progress ";

  //选出详细信息的sql语句，"?"代表参数变量
  String DETAIL_SQL = "SELECT"
      + " pa_month_str,"
      + " att_mo_flag,"
      + " param_initial_flag,"
      + " calc_flag,"
      + " att_mo_lock_flag,"
      + " calc_lock_flag,"
      + " pa_lock_flag,"
      + " lock_user_id,"
      + " lock_date"
      + " FROM pa_progress"
      + " WHERE pa_month_str = ?";

  //插入新记录的sql语句，"?"代表参数变量
  String INSERT_SQL = "INSERT INTO pa_progress ("
      + " pa_month_str,"
      + " att_mo_flag,"
      + " param_initial_flag,"
      + " calc_flag,"
      + " att_mo_lock_flag,"
      + " calc_lock_flag,"
      + " pa_lock_flag,"
      + " lock_user_id,"
      + " lock_date)"
      + " VALUES("
      + " ?,"
      + " ?,"
      + " ?,"
      + " ?,"
      + " ?,"
      + " ?,"
      + " ?,"
      + " ?,"
      + " ?)";

  //更新记录的sql语句，"?"代表参数变量
  String UPDATE_SQL = "UPDATE pa_progress SET"
      + " pa_month_str = ?,"
      + " att_mo_flag = ?,"
      + " param_initial_flag = ?,"
      + " calc_flag = ?,"
      + " att_mo_lock_flag = ?,"
      + " calc_lock_flag = ?,"
      + " pa_lock_flag = ?,"
      + " lock_user_id = ?,"
      + " lock_date = ?,"
      + " ModifyUser = ?,"
      + " ModifyDate = GETDATE()"
      + " WHERE pa_month_str = ?";

  //删除记录的sql语句，"?"代表参数变量
  String DELETE_SQL = "DELETE pa_progress WHERE pa_month_str = ?";

  //返回连接
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

  //返回搜索结果
  public Vector List(PageControl pc) throws Exception { //搜索信息方法(搜索方式,搜索信息,开始日期,结束日期,分页)
    Connection conn = Conn(); //建立连接
    PreparedStatement pstmt = null; //建立参数声明
    ResultSet rs = null; //建立数据集
    PaProgress paprogress = null;
    try {
      pstmt = conn.prepareStatement(LIST_SQL + ORDER_SQL);
      rs = pstmt.executeQuery();
      pc.seti();
      for (int i = 0; i < pc.getI(); i++) {
        rs.next();
      }
      pc.setii();
      while ( (pc.getI() < pc.getIntPagedSize()) && rs.next()) { //产生搜索信息循环
        paprogress = new PaProgress(); //产生实体对象
        //按照上面搜索信息依次附值，以下方法如下
        paprogress.setpa_month_str(rs.getString(1));
        paprogress.setatt_mo_flag(rs.getInt(2));
        paprogress.setparam_initial_flag(rs.getInt(3));
        paprogress.setcalc_flag(rs.getInt(4));
        paprogress.setatt_mo_lock_flag(rs.getInt(5));
        paprogress.setcalc_lock_flag(rs.getInt(6));
        paprogress.setpa_lock_flag(rs.getInt(7));
        paprogress.setlock_user_id(rs.getString(8));
        paprogress.setlock_date(rs.getString(9));
        Vlist.addElement(paprogress); //形成一条记录，插入记录列表
        pc.setiii(); //分页方法
      }
    }
    catch (SQLException e) {
      System.out.print(e);
    }
    finally {
        SqlUtil.close(rs,pstmt,conn);
      }
      return Vlist; //返回记录列表
  }

  //返回全部记录，用于没有分页和搜索的情况
  public Vector Select() throws Exception { //搜索信息方法(搜索方式,搜索信息,开始日期,结束日期,分页)

    Connection conn = Conn(); //建立连接
    PreparedStatement pstmt = null; //建立参数声明
    ResultSet rs = null;
    PaProgress paprogress = null; //建立数据集
    try {
      pstmt = conn.prepareStatement(SELECT_SQL + ORDER_SQL);
      rs = pstmt.executeQuery(); //得到信息数据集
      while (rs.next()) { //依次得到值
        paprogress = new PaProgress(); //产生实体对象
        //按照上面搜索信息依次附值，以下方法如下
        paprogress.setpa_month_str(rs.getString(1));
        paprogress.setatt_mo_flag(rs.getInt(2));
        paprogress.setparam_initial_flag(rs.getInt(3));
        paprogress.setcalc_flag(rs.getInt(4));
        paprogress.setatt_mo_lock_flag(rs.getInt(5));
        paprogress.setcalc_lock_flag(rs.getInt(6));
        paprogress.setpa_lock_flag(rs.getInt(7));
        paprogress.setlock_user_id(rs.getString(8));
        paprogress.setlock_date(rs.getString(9));
        Vlist.addElement(paprogress); //形成一条记录，插入记录列表
      }
    }
    catch (SQLException e) {
      System.out.print(e);
    }
    finally {
        SqlUtil.close(rs,pstmt,conn);
      }
      return Vlist; //返回记录列表
  }



  //更新数据方法
  public void Update(String pamonth,String column,String flag,String user) throws Exception {
    String SQL = "Update PA_PROGRESS set"
        +" "+column+" = ? ,"
        +" lock_user_id = ? ,"
        +" lock_date = SYSDATE "
        +" where pa_month_str = ? ";
    Connection conn = Conn();
    PreparedStatement pstmt = null;
    try {
      pstmt = conn.prepareStatement(SQL); //执行更新数据方法
      pstmt.setString(1, flag);
      pstmt.setString(2, user);
      pstmt.setString(3, pamonth);
      if (pstmt.executeUpdate() == 0) {
        throw new NotExistException("Update No data exists");
      }
    }
    catch (SQLException e) {
      System.out.print("Update Error:" + e);
    }
    finally {
      SqlUtil.close(pstmt,conn);
    }

  }

  public String pa_month_str = "";
  public int att_mo_flag = 0;
  public int param_initial_flag = 0;
  public int calc_flag = 0;
  public int att_mo_lock_flag = 0;
  public int calc_lock_flag = 0;
  public int pa_lock_flag = 0;
  public String lock_user_id = "";
  public String lock_date = "";

//Get
  public String getpa_month_str() {
    return editNull(this.pa_month_str);
  }

  public int getatt_mo_flag() {
    return this.att_mo_flag;
  }

  public int getparam_initial_flag() {
    return this.param_initial_flag;
  }

  public int getcalc_flag() {
    return this.calc_flag;
  }

  public int getatt_mo_lock_flag() {
    return this.att_mo_lock_flag;
  }

  public int getcalc_lock_flag() {
    return this.calc_lock_flag;
  }

  public int getpa_lock_flag() {
    return this.pa_lock_flag;
  }

  public String getlock_user_id() {
    return editNull(this.lock_user_id);
  }

  public String getlock_date() {
    return editNull(this.lock_date);
  }

//Set
  public void setpa_month_str(String pa_month_str) {
    this.pa_month_str = pa_month_str;
  }

  public void setatt_mo_flag(int att_mo_flag) {
    this.att_mo_flag = att_mo_flag;
  }

  public void setparam_initial_flag(int param_initial_flag) {
    this.param_initial_flag = param_initial_flag;
  }

  public void setcalc_flag(int calc_flag) {
    this.calc_flag = calc_flag;
  }

  public void setatt_mo_lock_flag(int att_mo_lock_flag) {
    this.att_mo_lock_flag = att_mo_lock_flag;
  }

  public void setcalc_lock_flag(int calc_lock_flag) {
    this.calc_lock_flag = calc_lock_flag;
  }

  public void setpa_lock_flag(int pa_lock_flag) {
    this.pa_lock_flag = pa_lock_flag;
  }

  public void setlock_user_id(String lock_user_id) {
    this.lock_user_id = lock_user_id;
  }

  public void setlock_date(String lock_date) {
    this.lock_date = lock_date;
  }

  //entity

  public String editNull(String s) {
    if (s == null) {
      return "";
    }
    else {
      return s;
    }
  }
  public String check(int s) {
    if (s == 0) {
      return "否";
    }
    else {
      return "是";
    }
  }
  public int flag(int s) {
    if (s == 0) {
      return 1;
    }
    else {
      return 0;
    }
  }

}