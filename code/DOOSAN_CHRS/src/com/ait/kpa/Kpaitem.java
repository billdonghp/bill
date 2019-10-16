package com.ait.kpa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.utils.NotExistException;
import com.ait.util.NumberUtil;

public class Kpaitem {
  private static ServiceLocator services;
  public Kpaitem() {
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
      + " pa_item_no, "
      + " item_name,"
      + " descr,"
      + " datatype, "
      + " calcu_order, "
      + " item_type, "
      + " pricision, "
      + " carry_bit, "
	  + " data_type_desc,data_type_en_desc,data_type_kor_desc,"
	  + " item_type_desc,item_type_en_desc,item_type_kor_desc,"
      + " item_id, "
      + " item_en_name, "
      + " item_kor_name, " 
      +	" RETROACTIVE_TAX_CALC_MARK"
      + " FROM kpa_item_V ";

  //顺序
  String ORDER_SQL = " ORDER BY calcu_order";

  //选出所有记录列表的sql语句
  String SELECT_SQL = "SELECT"
      + " pa_item_no,"
      + " item_name,"
      + " descr,"
      + " datatype,"
      + " calcu_order,"
      + " item_type,"
      + " pricision,"
      + " carry_bit,"
	  + " data_type_desc,"
	  + " item_type_desc "
      + " FROM kpa_item_v ";
  //选出详细信息的sql语句，"?"代表参数变量
  String DETAIL_SQL = "SELECT"
      + " pa_item_no,"
      + " item_name,"
      + " descr,"
      + " datatype,"
      + " calcu_order,"
      + " item_type,"
      + " pricision,"
      + " carry_bit,"
	  + " data_type_desc,"
	  + " item_type_desc "
      + " FROM kpa_item_v "
      + " WHERE pa_item_no = ?";

  //插入新记录的sql语句，"?"代表参数变量
  String INSERT_SQL = "INSERT INTO kpa_item ("
      + " pa_item_no, item_name, descr, datatype, item_type, pricision, carry_bit, "
      + " item_id, item_en_name, item_kor_name, RETROACTIVE_TAX_CALC_MARK,calcu_order "
      + " )VALUES("
      + " kpa_item_seq.nextval, ?, ?, ?, ?, ?,"
      + " ?, ?, ?, ?, ?,"
      + " (select case when max(calcu_order) is null "
	  + " then 1 else max(calcu_order)+1 end as calcu_order from kpa_item ))";

  //更新记录的sql语句，"?"代表参数变量
  String UPDATE_SQL = "UPDATE kpa_item SET"
      + " item_name = ?, descr = ?, datatype = ?,"
      + " item_type = ?, pricision = ?, carry_bit = ?,"
      + " item_id = ?, item_en_name = ?, item_kor_name = ?,"
      + " RETROACTIVE_TAX_CALC_MARK = ? "
      + " WHERE pa_item_no = ?";

  //删除记录的sql语句，"?"代表参数变量
  String DELETE_SQL = "DELETE kpa_item WHERE pa_item_no = ?";

  //修改计算顺序--向前
  String UP_SQL = "select * from (select pa_item_no, calcu_order "
      + " from kpa_item "
      + " where pa_item_no = ? ) a, "
      + " (select pa_item_no,calcu_order "
      + " from kpa_item ) b "
      + " where a. calcu_order > b.calcu_order "
      + " order by b.calcu_order desc ";

  //修改计算顺序--向后
  String DOWN_SQL = "select * from (select pa_item_no, calcu_order "
      + " from kpa_item "
      + " where pa_item_no = ? ) a, "
      + " (select pa_item_no,calcu_order "
      + " from kpa_item ) b "
      + " where a. calcu_order < b.calcu_order "
      + " order by b.calcu_order ";

  //修改计算顺序--向后
  String CHANGE_ORDER_SQL = "UPDATE kpa_item SET"
	  + " calcu_order = ? "
	  + " where pa_item_no = ? ";


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
  public Vector List() throws Exception { //搜索信息方法(公司号,搜索方式,搜索信息,开始日期,结束日期,分页)
    Connection conn = Conn(); //建立连接
    PreparedStatement pstmt = null; //建立参数声明
    ResultSet rs = null; //建立数据集
    Kpaitem  paitem  = null;
    try {
      pstmt = conn.prepareStatement(LIST_SQL + ORDER_SQL);
      rs = pstmt.executeQuery();
      while (rs.next()) { //产生搜索信息循环
        paitem = new Kpaitem(); //产生实体对象
        //按照上面搜索信息依次附值，以下方法如下
        paitem.setPa_item_no(rs.getInt("PA_ITEM_NO"));
        paitem.setItem_name(rs.getString("ITEM_NAME"));
        paitem.setItem_id(rs.getString("ITEM_ID"));
        paitem.setItem_en_name(rs.getString("ITEM_EN_NAME"));
        paitem.setItem_kor_name(rs.getString("ITEM_KOR_NAME"));
        paitem.setDatatype(rs.getString("DATATYPE"));
        paitem.setCalcu_order(rs.getInt("CALCU_ORDER"));
        paitem.setItem_type(rs.getString("ITEM_TYPE"));
        paitem.setPricision(rs.getInt("PRICISION"));
        paitem.setCarry_bit(rs.getString("CARRY_BIT"));
        paitem.setItem_id(rs.getString("ITEM_ID"));
        paitem.setItem_en_name(rs.getString("ITEM_EN_NAME"));
        paitem.setItem_kor_name(rs.getString("ITEM_KOR_NAME"));
        Vlist.addElement(paitem); //形成一条记录，插入记录列表
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

  public Vector List(String searchsql,com.ait.util.PageControl pc) throws Exception { //搜索信息方法(公司号,搜索方式,搜索信息,开始日期,结束日期,分页)
    Connection conn = Conn(); //建立连接
    PreparedStatement pstmt = null; //建立参数声明
    ResultSet rs = null; //建立数据集
    Kpaitem  paitem  = null;
    try {
      pstmt = conn.prepareStatement(LIST_SQL +searchsql+ ORDER_SQL);
      rs = pstmt.executeQuery();
	  pc.seti();
      for (int i = 0; i < pc.getI(); i++) {
        rs.next();
      }
      pc.setii();
      while ( (pc.getI() < pc.getIntPagedSize()) && rs.next()) { //产生搜索信息循环
        paitem = new Kpaitem(); //产生实体对象
        //按照上面搜索信息依次附值，以下方法如下
        paitem.setPa_item_no(rs.getInt("PA_ITEM_NO"));
        paitem.setItem_name(rs.getString("ITEM_NAME"));
        paitem.setItem_en_name(rs.getString("ITEM_EN_NAME"));
        paitem.setItem_kor_name(rs.getString("ITEM_KOR_NAME"));
        paitem.setDescr(rs.getString("DESCR"));
        paitem.setDatatype(rs.getString("DATATYPE"));
        paitem.setCalcu_order(rs.getInt("CALCU_ORDER"));
        paitem.setItem_type(rs.getString("ITEM_TYPE"));
        paitem.setPricision(rs.getInt("PRICISION"));
        paitem.setCarry_bit(rs.getString("CARRY_BIT"));
        paitem.setData_type_desc(rs.getString("DATA_TYPE_DESC"));
        paitem.setData_type_en_desc(rs.getString("DATA_TYPE_EN_DESC"));
        paitem.setData_type_kor_desc(rs.getString("DATA_TYPE_KOR_DESC"));
        paitem.setItem_type_desc(rs.getString("ITEM_TYPE_DESC"));
        paitem.setItem_type_en_desc(rs.getString("ITEM_TYPE_EN_DESC"));
        paitem.setItem_type_kor_desc(rs.getString("ITEM_TYPE_KOR_DESC"));
        paitem.setItem_id(rs.getString("ITEM_ID"));
        paitem.setItem_en_name(rs.getString("ITEM_EN_NAME"));
        paitem.setItem_kor_name(rs.getString("ITEM_KOR_NAME"));
        paitem.setRetroactiveRaxCalcMark(rs.getInt("RETROACTIVE_TAX_CALC_MARK")) ;
        Vlist.addElement(paitem); //形成一条记录，插入记录列表
        pc.setiii();
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

  //得到详细信息数据
  public static Kpaitem Detail(String No) throws Exception {
    Kpaitem paitem = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = services.getConnection();
      String DETAIL_SQL = "SELECT"
          + " pa_item_no,"
          + " item_name, item_en_name, item_kor_name, item_id, "
          + " descr,datatype, calcu_order, item_type, pricision,"
          + " carry_bit, RETROACTIVE_TAX_CALC_MARK "
          + " FROM kpa_item"
          + " WHERE pa_item_no = ?";
      pstmt = conn.prepareStatement(DETAIL_SQL); //执行详细信息语句
      pstmt.setString(1, No); //为详细信息的?即详细信息的条件赋值
      rs = pstmt.executeQuery(); //得到信息数据集
      if (rs.next()) { //依次得到值
        paitem = new Kpaitem(); //产生实体对象
        //按照上面搜索信息依次附值，以下方法如下
        paitem.setPa_item_no(rs.getInt(1));
        paitem.setItem_name(rs.getString(2));
        paitem.setItem_en_name(rs.getString(3));
        paitem.setItem_kor_name(rs.getString(4));
        paitem.setItem_id(rs.getString(5));
        paitem.setDescr(rs.getString(6));
        paitem.setDatatype(rs.getString(7));
        paitem.setCalcu_order(rs.getInt(8));
        paitem.setItem_type(rs.getString(9));
        paitem.setPricision(rs.getInt(10));
        paitem.setCarry_bit(rs.getString(11));
        paitem.setRetroactiveRaxCalcMark(rs.getInt(12)) ;
        //形成一条记录，插入记录列表
      }
    }
    catch (SQLException e) {
      System.out.print("Detail Error:" + e);
    }
    finally {
        SqlUtil.close(rs,pstmt,conn);
      }
      return paitem; 
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
      System.out.print(e);
    }
    finally {
        SqlUtil.close(pstmt,conn);
      }
  }

  //更新数据方法
  public void Update() throws Exception {

    Connection conn = Conn();
    PreparedStatement pstmt = null;
    try {
      pstmt = conn.prepareStatement(UPDATE_SQL); //执行更新数据方法
      pstmt.setString(1, this.getItem_name());
      pstmt.setString(2, this.getDescr());
      pstmt.setString(3, this.getDatatype());
      pstmt.setString(4, this.getItem_type());
      pstmt.setInt(5, this.getPricision());
      pstmt.setString(6, this.getCarry_bit());
      pstmt.setString(7, this.getItem_id());
      pstmt.setString(8, this.getItem_en_name());
      pstmt.setString(9, this.getItem_kor_name());
      pstmt.setInt(10, this.getRetroactiveRaxCalcMark()) ;
      pstmt.setInt(11, this.getPa_item_no());
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

  //执行插入数据方法
  public void Insert() throws Exception {

    Connection conn = Conn();
    PreparedStatement pstmt = null;
    try {
      pstmt = conn.prepareStatement(INSERT_SQL);
      pstmt.setString(1, this.getItem_name());
      pstmt.setString(2, this.getDescr());
      pstmt.setString(3, this.getDatatype());
      pstmt.setString(4, this.getItem_type());
      pstmt.setInt(5, this.getPricision());
      pstmt.setString(6, this.getCarry_bit());
      pstmt.setString(7, this.getItem_id());
      pstmt.setString(8, this.getItem_en_name());
      pstmt.setString(9, this.getItem_kor_name());
      pstmt.setInt(10, this.getRetroactiveRaxCalcMark()) ;
      if (pstmt.executeUpdate() == 0) {
        throw new NotExistException("No data exists");
      }
    }
    catch (SQLException e) {
      System.out.print(e);
    }
    finally {
        SqlUtil.close(pstmt,conn);
      }
  }


  //执行更新顺序
  public void ChangOrder(String type,String pa_item_no) throws Exception {

    Connection conn = Conn();
    PreparedStatement pstmt = null;
	ResultSet rs = null;
	String no1 = "";
	String no2 = "";
	String od1 = "";
	String od2 = "";
    try {
	  if (type.equals("up")){
		  pstmt = conn.prepareStatement(UP_SQL); //执行更新数据方法
	  }
	  else {
		  pstmt = conn.prepareStatement(DOWN_SQL); //执行更新数据方法
	  }
      pstmt.setString(1, pa_item_no);
      rs = pstmt.executeQuery(); //得到信息数据集
	  if (rs.next()){
		no1 = rs.getString(1);
		od1 = rs.getString(2);
		no2 = rs.getString(3);
		od2 = rs.getString(4);
		pstmt = conn.prepareStatement(CHANGE_ORDER_SQL);
		pstmt.setString(1, od2);
		pstmt.setString(2, no1);
		pstmt.executeQuery();
		pstmt.setString(1, od1);
		pstmt.setString(2, no2);
		pstmt.executeQuery();
		}
    }
    catch (SQLException e) {
      System.out.print("Update Error:" + e);
    }
    finally {
        SqlUtil.close(pstmt,conn);
      }
  }

	private int pa_item_no = 0;
	
	private String item_name = "";
	
	private String item_id = "";
	
	private String item_en_name = "";
	
	private String item_kor_name = "";
	
	private String descr = "";
	
	private String datatype = "";
	
	private int calcu_order = 0;
	
	private String item_type = "";
	
	private int pricision = 0;
	
	private String carry_bit = "";
	
	private String item_type_desc = "";
	
	private String item_type_en_desc = "";
	
	private String item_type_kor_desc = "";
	
	private String data_type_desc = "";
	
	private String data_type_en_desc = "";
	
	private String data_type_kor_desc = "";

	private int retroactiveRaxCalcMark = - 1 ;
 
	public int getPa_item_no() {
		return pa_item_no;
	}

	public void setPa_item_no(int pa_item_no) {
		this.pa_item_no = pa_item_no;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getItem_en_name() {
		return item_en_name;
	}

	public void setItem_en_name(String item_en_name) {
		this.item_en_name = item_en_name;
	}

	public String getItem_kor_name() {
		return item_kor_name;
	}

	public void setItem_kor_name(String item_kor_name) {
		this.item_kor_name = item_kor_name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public int getCalcu_order() {
		return calcu_order;
	}

	public void setCalcu_order(int calcu_order) {
		this.calcu_order = calcu_order;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public int getPricision() {
		return pricision;
	}

	public void setPricision(int pricision) {
		this.pricision = pricision;
	}

	public String getCarry_bit() {
		return carry_bit;
	}

	public void setCarry_bit(String carry_bit) {
		this.carry_bit = carry_bit;
	}

	public String getItem_type_desc() {
		return item_type_desc;
	}

	public void setItem_type_desc(String item_type_desc) {
		this.item_type_desc = item_type_desc;
	}

	public String getItem_type_en_desc() {
		return item_type_en_desc;
	}

	public void setItem_type_en_desc(String item_type_en_desc) {
		this.item_type_en_desc = item_type_en_desc;
	}

	public String getItem_type_kor_desc() {
		return item_type_kor_desc;
	}

	public void setItem_type_kor_desc(String item_type_kor_desc) {
		this.item_type_kor_desc = item_type_kor_desc;
	}

	public String getData_type_desc() {
		return data_type_desc;
	}

	public void setData_type_desc(String data_type_desc) {
		this.data_type_desc = data_type_desc;
	}

	public String getData_type_en_desc() {
		return data_type_en_desc;
	}

	public void setData_type_en_desc(String data_type_en_desc) {
		this.data_type_en_desc = data_type_en_desc;
	}

	public String getData_type_kor_desc() {
		return data_type_kor_desc;
	}

	public void setData_type_kor_desc(String data_type_kor_desc) {
		this.data_type_kor_desc = data_type_kor_desc;
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

	public int getRetroactiveRaxCalcMark() {
		return retroactiveRaxCalcMark;
	}

	public void setRetroactiveRaxCalcMark(int retroactiveRaxCalcMark) {
		this.retroactiveRaxCalcMark = retroactiveRaxCalcMark;
	}
}
