package com.ait.pa;

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

import org.apache.log4j.Logger;

import com.ait.util.PageControl;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.utils.NotExistException;


public class PaExp {
	private static ServiceLocator services;
		public PaExp() {
		try {
		services = ServiceLocator.getInstance();
		}
		catch (ServiceLocatorException ex) {
		}
	}

        Vector Vlist = new Vector(); //产生Vector的对象Vlist
        Connection Conn = null; //产生连接初始化的对象Conn
        int i = 0;   //初始化循环Vector的变量值

        //选出所有记录列表的sql语句
        String LIST_SQL = "SELECT"
                                + " pa_expense_seq,"
                                + " expense_type,"
                                + " tag,"
                                + " debitcredit,"
                                + " descr,"
								+ " expense_formular"
                           + " FROM pa_expense_type";

        //顺序
        String ORDER_SQL = " ORDER BY debitcredit,expense_type";

        //选出所有记录列表的sql语句
        String SELECT_SQL = "SELECT"
                                + " pa_expense_seq,"
                                + " expense_type,"
                                + " tag,"
                                + " debitcredit,"
                                + " descr,"
								+ " expense_formular"
                           + " FROM pa_expense_type";


        //插入新记录的sql语句，"?"代表参数变量
        String INSERT_SQL = "INSERT INTO pa_expense_type ("
                                + " pa_expense_seq,"
                                + " expense_type,"
                                + " expense_formular,"
                                + " tag,"
                                + " debitcredit,"
                                + " descr)"
                           + " VALUES("
                                + " pa_expense_seq.nextval,"
                                + " ?,"
                                + " ?,"
                                + " ?,"
                                + " ?,"
                                + " ?)";

        //更新记录的sql语句，"?"代表参数变量
        String UPDATE_SQL = "UPDATE pa_expense_type SET"
                                + " expense_type = ?,"
								+ " expense_formular = ?,"
                                + " tag = ?,"
                                + " debitcredit = ?,"
                                + " descr = ? "
                           + " WHERE Pa_expense_seq = ?";

        //删除记录的sql语句，"?"代表参数变量
        String DELETE_SQL = "DELETE pa_expense_type WHERE Pa_expense_seq = ?";

        //返回连接
        public Connection Conn() throws Exception {
                Connection Conn = null;//建立参数声明
                try {
                        Conn = services.getConnection(); //得到连接
                }catch (Exception e) {
                        
                        Logger.getLogger(getClass()).error("Conn Error:" + e);
                }
                        return Conn;
        }


        //返回搜索结果
        public Vector List(String SearchContent,PageControl pc) throws Exception { //搜索信息方法(搜索方式,搜索信息,开始日期,结束日期,分页)

                Connection conn = Conn(); 			//建立连接
                PreparedStatement pstmt = null; 	//建立参数声明
                ResultSet rs = null; 				//建立数据集
                try {
                        pstmt = conn.prepareStatement(LIST_SQL + SearchContent + ORDER_SQL);
                        rs = pstmt.executeQuery();
                        pc.seti();
                        for(int i=0;i<pc.getI();i++){
                                rs.next();
                        }
                        pc.setii();
                        while ((pc.getI()<pc.getIntPagedSize()) && rs.next()) { //产生搜索信息循环
                                PaExp paexp = new PaExp();	//产生实体对象
                                //按照上面搜索信息依次附值，以下方法如下
                                paexp.setPa_expense_seq(rs.getInt(1));
                                paexp.setExpense_type(rs.getInt(2));
                                paexp.setTag(rs.getInt(3));
                                paexp.setDebitcredit(rs.getString(4));
								paexp.setDescr(rs.getString(5));
								paexp.setExpense_formular(rs.getString(6));
                                Vlist.addElement(paexp); //形成一条记录，插入记录列表
                                pc.setiii(); //分页方法
                        }
                }catch (SQLException e) {

                        Logger.getLogger(getClass()).error(e);
                } finally {
                    SqlUtil.close(rs,pstmt,conn);
                }
                        return Vlist; //返回记录列表
        }


        //返回全部记录，用于没有分页和搜索的情况
        public Vector Select() throws Exception { //搜索信息方法(搜索方式,搜索信息,开始日期,结束日期,分页)

                Connection conn = Conn(); 			//建立连接
                PreparedStatement pstmt = null; 	//建立参数声明
                ResultSet rs = null; 				//建立数据集
                try {
                        pstmt = conn.prepareStatement(SELECT_SQL + ORDER_SQL);
                        rs = pstmt.executeQuery(); //得到信息数据集
                        while (rs.next()) { //依次得到值
                                PaExp paexp = new PaExp(); 	//产生实体对象
                                //按照上面搜索信息依次附值，以下方法如下
                                paexp.setPa_expense_seq(rs.getInt(1));
                                paexp.setExpense_type(rs.getInt(2));
                                paexp.setTag(rs.getInt(3));
                                paexp.setDebitcredit(rs.getString(4));
                                Vlist.addElement(paexp); //形成一条记录，插入记录列表
                        }
                }catch (SQLException e) {
                        Logger.getLogger(getClass()).error(e);
                } finally {
                    SqlUtil.close(rs,pstmt,conn);
                }
                        return Vlist; //返回记录列表
        }


        //得到详细信息数据
        public static PaExp Detail(String No) throws Exception {
                Connection conn = services.getConnection();
                PreparedStatement pstmt = null;
                ResultSet rs = null;
				PaExp paexp = null;
				String DETAIL_SQL = "SELECT"
                                + " expense_type,"
                                + " expense_formular,"
                                + " tag,"
                                + " debitcredit,"
                                + " descr"
                           + " FROM pa_expense_type"
                           + " WHERE Pa_expense_seq = ?";

                try {
                        pstmt = conn.prepareStatement(DETAIL_SQL); //执行详细信息语句
                        pstmt.setString(1, No); //为详细信息的?即详细信息的条件赋值
                        rs = pstmt.executeQuery(); //得到信息数据集
                        if (rs.next()) { //依次得到值
                                paexp = new PaExp();	//产生实体对象
                                //按照上面搜索信息依次附值，以下方法如下
                                paexp.setExpense_type(rs.getInt(1));
                                paexp.setExpense_formular(rs.getString(2));
                                paexp.setTag(rs.getInt(3));
                                paexp.setDebitcredit(rs.getString(4));
                                paexp.setDescr(rs.getString(5));
                            }
                }catch (SQLException e) {
                        System.out.print("Detail Error:" + e);
                        
                } finally {
                    SqlUtil.close(rs,pstmt,conn);
                }
                        return paexp; //返回记录列表
        }


        //删除信息方法
        public void Delete(String No) throws Exception {
                Connection conn = Conn(); //建立连接
                PreparedStatement pstmt = null; //建立参数声明
                try {
                        pstmt = conn.prepareStatement(DELETE_SQL);
                        pstmt.setString(1, No);
                        if (pstmt.executeUpdate() == 0) //调用上面形成的DELETE语句，为?即参数变量赋值
                                throw new NotExistException("No data exists");
                        }catch (SQLException e) {
                                Logger.getLogger(getClass()).error(e);
                        }finally {
                                if (pstmt != null){pstmt.close(); }//关闭参数声明
                                if (conn != null){conn.close();} //关闭参数声明
                        }

                }


        //更新数据方法
        public void Update() throws Exception {

                Connection conn = Conn();
                PreparedStatement pstmt = null;
                try {
                        pstmt = conn.prepareStatement(UPDATE_SQL); //执行更新数据方法
                                pstmt.setInt(1, this.getExpense_type());
								pstmt.setString(2, this.getExpense_formular());
                                pstmt.setInt(3, this.getTag());
                                pstmt.setString(4, this.getDebitcredit());
                                pstmt.setString(5, this.getDescr());
                                pstmt.setInt(6, this.getPa_expense_seq());
                        if (pstmt.executeUpdate() == 0) {
                                throw new NotExistException("Update No data exists");
                        }
                }catch (SQLException e) {
                        Logger.getLogger(getClass()).error("Update Error:"+e);
                }finally {
                        if (pstmt != null) {pstmt.close();}
                        if (conn != null) {conn.close();}
                }

        }


        //执行插入数据方法
        public void Insert() throws Exception {

                Connection conn = Conn();
                PreparedStatement pstmt = null;
                try {
                        pstmt = conn.prepareStatement(INSERT_SQL);
                                pstmt.setInt(1, this.getExpense_type());
                                pstmt.setString(2, this.getExpense_formular());
                                pstmt.setInt(3, this.getTag());
                                pstmt.setString(4, this.getDebitcredit());
                                pstmt.setString(5, this.getDescr());
                        if (pstmt.executeUpdate() == 0) {
                                throw new NotExistException("No data exists");
                        }

                }catch (SQLException e) {
                        System.out.print(e);
                        Logger.getLogger(getClass()).error(e);
                }finally {
                        if (pstmt != null) {pstmt.close();}
                        if (conn != null) {conn.close();}
                }
        }

	private int pa_expense_seq = 0;
	private int expense_type = 0;
	private String expense_formular = "";
	private int tag = 0;
	private String debitcredit = "";
	private String descr = "";


//Get
	public int getPa_expense_seq() {
		return this.pa_expense_seq;
	}
	public int getExpense_type() {
		return this.expense_type;
	}
	public String getExpense_formular() {
		return editNull(this.expense_formular);
	}
	public int getTag() {
		return this.tag;
	}
	public String getDebitcredit() {
		return editNull(this.debitcredit);
	}
	public String getDescr() {
		return editNull(this.descr);
	}

//Set
	public void setPa_expense_seq(int pa_expense_seq) {
		this.pa_expense_seq = pa_expense_seq;
	}
	public void setExpense_type(int expense_type) {
		this.expense_type = expense_type;
	}
	public void setExpense_formular(String expense_formular) {
		this.expense_formular = expense_formular;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public void setDebitcredit(String debitcredit) {
		this.debitcredit = debitcredit;
	}
	public void setDescr(String descr) {
		this.descr = descr;
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

}
