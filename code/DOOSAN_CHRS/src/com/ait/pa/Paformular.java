package com.ait.pa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.pa.business.PaServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.bean.SysCodeBean;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.NotExistException;
import com.ait.web.ApplicationContext;

public class Paformular {
  private static ServiceLocator services;
  AdminBean admin=ApplicationContext.getTheadLocal();
  public Paformular() {
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
      + " formular_no,"
      + " pa_item_no,"
      + " condition_cn,"
      + " formular_cn,"
      + " condition_seq"
      + " FROM pa_formular"
      + " WHERE pa_item_no=? and cpny_id='"+admin.getCpnyId()+"'  ";
  //顺序
  String ORDER_SQL = " ORDER BY condition_seq desc";

  //选出所有记录列表的sql语句
  String SELECT_SQL = "SELECT"
      + " formular_no,"
      + " pa_item_no,"
      + " condition,"
      + " formular,"
      + " condition_seq"
      + " FROM pa_formular"
      + " WHERE pa_item_no=?";

  //插入新记录的sql语句，"?"代表参数变量
  String INSERT_SQL = "INSERT INTO pa_formular ("
      + " formular_no, pa_item_no,"
      + " condition, formular,"
      + " condition_cn, formular_cn,"
      + " condition_seq,cpny_id "
      + " )VALUES("
      + " pa_formular_seq.nextval, ?,"
      + " ?, ?,"
      + " ?, ?,"
	  + " (select case when max(condition_seq) is null "
	  + " then 1 else max(condition_seq)+1 end as condition_seq from pa_formular where pa_item_no = ? AND CPNY_ID = ? ),?)";

  //更新记录的sql语句，"?"代表参数变量
  String UPDATE_SQL = "UPDATE pa_formular SET"
      + " condition = ?,formular = ?, "
      + " condition_cn = ?,formular_cn = ? "
      + " WHERE formular_no = ?";

  //删除记录的sql语句，"?"代表参数变量
  String DELETE_SQL = "DELETE pa_formular WHERE formular_no = ?";

  //返回连接
  public Connection Conn() throws Exception {
    Connection Conn = null; //建立参数声明
    try {
      Conn = services.getConnection(); //得到连接
    }
    catch (Exception e) {
      Logger.getLogger(getClass()).error("Conn Error:"+e);
    }
    return Conn;
  }

  //返回搜索结果
  public Vector List(int no) throws Exception { //搜索信息方法(公司号,搜索方式,搜索信息,开始日期,结束日期,分页)

    //初始化搜索参数
	Paformular paformular = null ;
    Connection conn = Conn(); //建立连接
    PreparedStatement pstmt = null; //建立参数声明
    ResultSet rs = null; //建立数据集
    try {
      pstmt = conn.prepareStatement(LIST_SQL + ORDER_SQL); //执行按屏幕别搜索信息方法
      Logger.getLogger(getClass()).debug(LIST_SQL + ORDER_SQL);
	  pstmt.setInt(1, no);
      rs = pstmt.executeQuery();
      while (rs.next()) { //产生搜索信息循环
        paformular = new Paformular(); //产生实体对象
        //按照上面搜索信息依次附值，以下方法如下
        paformular.setformular_no(rs.getInt(1));
        paformular.setpa_item_no(rs.getInt(2));
        paformular.setCondition_cn(rs.getString(3));
        paformular.setFormular_cn(rs.getString(4));
        paformular.setcondition_seq(rs.getInt(5));
        Vlist.addElement(paformular); //形成一条记录，插入记录列
      }
    }
    catch (SQLException e) {

      Logger.getLogger(getClass()).error(e);
    }
    finally {
        SqlUtil.close(rs,pstmt,conn);
      }
      return Vlist; //返回记录列表
  }

  //返回全部记录，用于没有分页和搜索的情况
  public Vector Select(String no) throws Exception { //搜索信息方法(公司号,搜索方式,搜索信息,开始日期,结束日期,分页)

    Connection conn = Conn(); //建立连接
    PreparedStatement pstmt = null; //建立参数声明
    ResultSet rs = null; //建立数据集
    try {
      pstmt = conn.prepareStatement(SELECT_SQL + ORDER_SQL);
	  pstmt.setString(1, no);
      rs = pstmt.executeQuery(); //得到信息数据集
      while (rs.next()) { //依次得到值
        Paformular paformular = new Paformular(); //产生实体对象
        //按照上面搜索信息依次附值，以下方法如下
        paformular.setformular_no(rs.getInt(1));
        paformular.setpa_item_no(rs.getInt(2));
        paformular.setcondition(rs.getString(3));
        paformular.setformular(rs.getString(4));
        paformular.setcondition_seq(rs.getInt(5));
        Vlist.addElement(paformular); //形成一条记录，插入记录列表
      }
    }
    catch (SQLException e) {
      
      Logger.getLogger(getClass()).error(e);
    }
    finally {
        SqlUtil.close(rs,pstmt,conn);
      }
      return Vlist; //返回记录列表
  }

  //得到详细信息数据
  public static Paformular Detail(int No) throws Exception {
    Paformular paformular = null;
    Connection conn = services.getConnection();
    PreparedStatement pstmt = null;
	ResultSet rs = null; //建立数据集

    //选出详细信息的sql语句，"?"代表参数变量
    String DETAIL_SQL = "SELECT"
        + " formular_no,"
        + " pa_item_no,"
        + " condition_cn,"
        + " formular_cn,"
        + " condition_seq"
        + " FROM pa_formular"
        + " WHERE formular_no = ?";

    try {
      pstmt = conn.prepareStatement(DETAIL_SQL); //执行详细信息语句
      pstmt.setInt(1, No); //为详细信息的?即详细信息的条件赋值
      rs = pstmt.executeQuery(); //得到信息数据集
      if (rs.next()) { //依次得到值
        paformular = new Paformular(); //产生实体对象
        //按照上面搜索信息依次附值，以下方法如下
        paformular.setformular_no(rs.getInt(1));
        paformular.setpa_item_no(rs.getInt(2));
        paformular.setCondition_cn(rs.getString(3));
        paformular.setFormular_cn(rs.getString(4));
        paformular.setcondition_seq(rs.getInt(5));
      }
    }
    catch (SQLException e) {
      System.out.print("Detail Error:" + e);
    }
    finally {
        SqlUtil.close(rs,pstmt,conn);
      }
      return paformular; //返回记录列表
  }

  //删除信息方法
  public void Delete(int No) throws Exception {
    Connection conn = Conn(); //建立连接
    PreparedStatement pstmt = null; //建立参数声明
    try {
      pstmt = conn.prepareStatement(DELETE_SQL);
      pstmt.setInt(1, No);
      if (pstmt.executeUpdate() == 0) //调用上面形成的DELETE语句，为?即参数变量赋值
        throw new NotExistException("No data exists");
    }
    catch (SQLException e) {
    	Logger.getLogger(getClass()).error(e);
      
    }
    finally {
      if (pstmt != null) {
        pstmt.close();
      } //关闭参数声明
      if (conn != null) {
        conn.close();
      } //关闭参数声明
    }

  }

  //更新数据方法
  public void Update() throws Exception {

    Connection conn = Conn();
    PreparedStatement pstmt = null;
    try {
      formatData() ;
      pstmt = conn.prepareStatement(UPDATE_SQL); //执行更新数据方法
      pstmt.setString(1, this.getcondition());
      pstmt.setString(2, this.getformular());
      pstmt.setString(3, this.getCondition_cn());
      pstmt.setString(4, this.getFormular_cn());
      pstmt.setInt(5, this.getformular_no());
      if (pstmt.executeUpdate() == 0) {
        throw new NotExistException("Update No data exists");
      }
    }
    catch (SQLException e) {
      Logger.getLogger(getClass()).error("Udate Error:" + e);
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

  //执行插入数据方法
  public void Insert() throws Exception {

    Connection conn = Conn();
    PreparedStatement pstmt = null;
    try {
      formatData() ;
      pstmt = conn.prepareStatement(INSERT_SQL);
      pstmt.setInt(1, this.getpa_item_no());
      pstmt.setString(2, this.getcondition());
      pstmt.setString(3, this.getformular());
      pstmt.setString(4, this.getCondition_cn());
      pstmt.setString(5, this.getFormular_cn());
      pstmt.setInt(6, this.getpa_item_no());
      pstmt.setString(7, admin.getCpnyId());
      pstmt.setString(8, admin.getCpnyId());
      if (pstmt.executeUpdate() == 0) {
        throw new NotExistException("No data exists");
      }

    }
    catch (SQLException e) {
      Logger.getLogger(getClass()).error(e);
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
  
  // 格式化数据 中文专成ID 形式
  private void formatData()
  {
	  this.condition = StringUtil.checkNull(this.condition_cn) ;
	  this.formular = StringUtil.checkNull(this.formular_cn) ;
	 
	  String statTypeCode = "" ;
	  String id = "" ;
	  String name = "" ;
	  SysCodeBean codeBean = new SysCodeBean() ;
	  SimpleMap parameterObject = new SimpleMap();
	  SimpleMap simpleMap = new SimpleMap();
	  PaServices services = PaServices.getInstance();
	  parameterObject.set("cpnyId", admin.getCpnyId());
	  
	  
	  List pa_item_list = services.retrievePa_Item_List_Month(parameterObject) ;
	  int size = pa_item_list.size() ;
	  for(int k = 0; k < size ; k++ )
	  {
		  simpleMap = (SimpleMap)pa_item_list.get(k) ;
		  id = simpleMap.getString("ITEM_ID") ;
		  name = "计算项目." + simpleMap.getString("ITEM_NAME") ;
		  
		  if(this.condition.indexOf(name) != -1) 
		  {
			  this.condition = this.condition.replaceAll(name, id) ;
		  }
		  
		  if(this.formular.indexOf(name) != -1) 
		  {
			  this.formular = this.formular.replaceAll(name, id) ;
		  }
	  }
	  
	  List pa_param_item_list = services.retrievePa_param_item_list(parameterObject);
	  size = pa_param_item_list.size() ;
	  for(int m = 0; m < size ; m++ )
	  {
		  simpleMap = (SimpleMap)pa_param_item_list.get(m) ;
		  id = simpleMap.getString("PARAM_ID") ;
		  name = "输入项目." + simpleMap.getString("PARAM_NAME") ;
		  
		  if(this.condition.indexOf(name) != -1) 
		  {
			  this.condition = this.condition.replaceAll(name, id) ;
		  }
		  
		  if(this.formular.indexOf(name) != -1) 
		  {
			  this.formular = this.formular.replaceAll(name, id) ;
		  }
	  }
	  
	  List pa_distinct_list = services.retrievePa_distinct_list(parameterObject) ;
	  size = pa_distinct_list.size() ;
	  for(int n = 0; n < size ; n++ )
	  {
		  simpleMap = (SimpleMap)pa_distinct_list.get(n) ;
		  id = simpleMap.getString("DISTINCT_FIELD") ;
		  name = "固定参数." + simpleMap.getString("FIELD_NAME") ;
		  
		  if(this.condition.indexOf(name) != -1) 
		  {
			  this.condition = this.condition.replaceAll(name, id) ;
		  }
		  
		  if(this.formular.indexOf(name) != -1) 
		  {
			  this.formular = this.formular.replaceAll(name, id) ;
		  }
	  }
		  
  }

  private int formular_no = 0;
  private int pa_item_no = 0;
  private String condition = "";
  private String formular = "";
  private int condition_seq = 0;
  private String condition_cn = "";
  private String formular_cn = "";
  

//Get
  public int getformular_no() {
    return this.formular_no;
  }

  public int getpa_item_no() {
    return this.pa_item_no;
  }

  public String getcondition() {
    return this.editNull(this.condition);
  }

  public String getformular() {
    return this.editNull(this.formular);
  }

  public int getcondition_seq() {
    return this.condition_seq;
  }

//Set
  public void setformular_no(int formular_no) {
    this.formular_no = formular_no;
  }

  public void setpa_item_no(int pa_item_no) {
    this.pa_item_no = pa_item_no;
  }

  public void setcondition(String condition) {
    this.condition = condition;
  }

  public void setformular(String formular) {
    this.formular = formular;
  }

  public void setcondition_seq(int condition_seq) {
    this.condition_seq = condition_seq;
  }

  //entity

  public String editNull(String s) {
    if (s == null || s.equals("")) {
      return "";
    }
    else {
      return s;
    }
  }

	public String getFormular_cn() {
		return formular_cn;
	}
	
	public void setFormular_cn(String formular_cn) {
		this.formular_cn = formular_cn;
	}
	
	public String getCondition_cn() {
		return condition_cn;
	}
	
	public void setCondition_cn(String condition_cn) {
		this.condition_cn = condition_cn;
	}

}