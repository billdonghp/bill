package com.ait.sy.sy0101.bean;

/**
 * <p>Title: AIT  INTRANET</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: AIT</p>
 * @author AIT
 * @version 1.0
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.sy.bean.AdminBean;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
import com.ait.utils.NotExistException;
import com.ait.utils.PageControl;

public class Biz {
	private final static Logger log = Logger.getLogger(Biz.class);

    Vector Vlist = new Vector(); // 产生Vector的对象Vlist
    Vector Vlist1 = new Vector(); // 产生Vector的对象Vlist

    Connection Conn = null; // 产生连接初始化的对象Conn

    int i = 0; // 初始化循环Vector的变量值

    HttpSession m_session = null;

    public Biz() {

    }

    String LIST_SQL_0 = "SELECT MENU_NO,   " + "    MENU_CODE,	        "
            + "    MENU_INTRO,		" + "    MENU_PARENT_CODE,	  "
            + "    TABLE_NAME,		  " + "    MENU_URL,		" + "    MENU_IMG,		 "
            + "    to_char(CREATE_DATE,'YYYYMMDD'),	 "
            + "    CREATED_BY,			        "
            + "    to_char(UPDATE_DATE,'YYYYMMDD'),			" + "    UPDATED_BY,	  "
            + "    ACTIVITY,			        " + "    ORDERNO,				"
            + "    DEPTH ,MENU_EN_INTRO from sy_menu 	        ";  

    String TREE_SQL = "WHERE MENU_CODE <> '00000' AND ACTIVITY = 1 START WITH DEPTH=0 CONNECT BY PRIOR MENU_CODE=MENU_PARENT_CODE ORDER SIBLINGS BY MENU_CODE";

    String UPDATE_SQL = "UPDATE sy_menu SET" + " MENU_CODE = ?, "
            + " MENU_INTRO = ?," + " MENU_PARENT_CODE = ?," + " MENU_URL = ?, "
            + " UPDATE_DATE = SYSDATE," + " UPDATED_BY = ?," + " DEPTH = ?,MENU_EN_INTRO=?"  
            + " where MENU_NO = ? ";

    String DELETE_SQL = "DELETE sy_menu WHERE MENU_NO = ?";                                                         
    String DELETE_SQL1 = "delete from sy_screen_grant where screen_code=(select menu_code from sy_menu where MENU_NO=?)";    
    String DELETE_SQL2 = "select *   from sy_screen_grant where screen_code=(select menu_code from sy_menu where MENU_NO=?)";    
    String DETAIL_SQL = "WHERE MENU_NO = ?";

    String INSERT_SQL = " INSERT INTO sy_menu (  	"
            + "                       MENU_NO,                       "
            + "			MENU_CODE,			" + "			MENU_INTRO,			"
            + "		        MENU_PARENT_CODE,			" + "		        MENU_URL,			"
            + "                       CREATE_DATE,                     "
            + "                       CREATED_BY,                      "
            + "                       ACTIVITY,                       "
            + "			DEPTH,			"
            +" MENU_EN_INTRO )"
            + "		VALUES(SY_MENUNO_SEQ.NEXTVAL,?,?,?,?,sysDate,?,1,?,?)";    

    String ORDER_SQL = " ORDER BY MENU_NO desc";

    public Connection Conn() throws Exception {
        Connection Conn = null; // 建立参数声明
        try {
            Conn = ConnBean.getConn(); // 得到连接
        } catch (Exception e) {
            System.out.print("Conn Error:" + e);
        }
        return Conn; 
    }

    // 选出所有记录列表的sql语句
    public Vector List(PageControl pc) throws Exception {
    	String sql = LIST_SQL_0 + TREE_SQL;
//    	String sql = LIST_SQL_0;
    	log.debug(sql);        
        Connection conn = Conn();
        PreparedStatement pstmt = null; // 建立参数声明
        ResultSet rs = null; // 建立数据集
        try {
            pc.seti();
            pstmt = conn.prepareStatement(sql);
            Logger.getLogger(getClass()).debug(sql);
            rs = pstmt.executeQuery();
            for (int i = 0; i < pc.getI(); i++)
                rs.next(); 
               pc.setii();
            while ((pc.getI() < pc.getIntPagedSize()) && rs.next()) {
                Ent Ent = new Ent();
                Ent.setMenuNo(rs.getInt(1));
                Ent.setMenuCode(rs.getString(2));           
                Ent.setMenuIntro(rs.getString(3));
                Ent.setMenuParentCode(rs.getString(4));
                Ent.setTableName(rs.getString(5));
                Ent.setMenuUrl(rs.getString(6));
                Ent.setMenuImage(rs.getString(7));
                Ent.setCreateDate(rs.getString(8));
                Ent.setCreatorID(rs.getString(9));
                Ent.setModifyDate(rs.getString(10));
                Ent.setModifierID(rs.getString(11));
                Ent.setActivity(rs.getInt(12));
                Ent.setOrderNo(rs.getInt(13));
                Ent.setDepth(rs.getInt(14));
                Ent.setMenuEnIntro(rs.getString(15));
                Vlist.addElement(Ent);
                
                pc.setiii();
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).debug(e.toString());
        } finally {
            SqlUtil.close(rs, pstmt, conn);
        }
        return Vlist; // 返回记录列表
    }

    // 根据权限组号列出屏幕记录 By Pennix 20060404
    public Vector List(PageControl pc, int screenGrantNo) throws Exception {
        Connection conn = Conn();
        PreparedStatement pstmt = null; // 建立参数声明
        ResultSet rset = null; // 建立数据集
        PreparedStatement ps = null; // 建立参数声明
        ResultSet rs = null; // 建立数据集
        try {
            pc.seti();
            pstmt = conn.prepareStatement(LIST_SQL_0 + TREE_SQL);
            String sql = "SELECT SELECTR, INSERTR, UPDATER, DELETER, code_type FROM SY_SCREEN_GRANT WHERE SCREEN_CODE=? AND SCREEN_GRANT_NO=?";
            Logger.getLogger(getClass()).debug(LIST_SQL_0 + TREE_SQL);
            Logger.getLogger(getClass()).debug(sql);
            ps = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();
            for (int i = 0; i < pc.getI(); i++) {
                rset.next();
            }
            pc.setii();
            while ((pc.getI() < pc.getIntPagedSize()) && rset.next()) {
                ps.setString(1, rset.getString(2));
                ps.setInt(2, screenGrantNo);
                rs = ps.executeQuery();
                Ent Ent = new Ent();
                Ent.setMenuNo(rset.getInt(1));
                Ent.setMenuCode(rset.getString(2));
                Ent.setMenuIntro(rset.getString(3));
                Ent.setMenuParentCode(rset.getString(4));
                Ent.setTableName(rset.getString(5));
                Ent.setMenuUrl(rset.getString(6));
                Ent.setMenuImage(rset.getString(7));
                Ent.setCreateDate(rset.getString(8));
                Ent.setCreatorID(rset.getString(9));
                Ent.setModifyDate(rset.getString(10));
                Ent.setModifierID(rset.getString(11));
                Ent.setActivity(rset.getInt(12));             
                Ent.setOrderNo(rset.getInt(13));
                Ent.setDepth(rset.getInt(14));
                Ent.setMenuEnIntro(rset.getString(15));
                if (rs.next()) {
                    Ent.setInsertr(rs.getInt("INSERTR"));
                    Ent.setUpdater(rs.getInt("UPDATER"));
                    Ent.setDeleter(rs.getInt("DELETER"));
                    Ent.setSelectr(rs.getInt("SELECTR"));
                    Ent.setCode(rs.getString("CODE_TYPE")!= null?rs.getString("CODE_TYPE"):null);
                }
                Vlist.addElement(Ent);
                pc.setiii();
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).debug(e.toString());
        } finally {
            SqlUtil.close(rs, pstmt, conn);
        }
        return Vlist; // 返回记录列表
    }

    // //////////
    public Vector List(PageControl pc, String likes) throws Exception {
        Connection conn = Conn();

        PreparedStatement pstmt = null; // 建立参数声明
        ResultSet rs = null; // 建立数据集

        String ORDER_SQL = likes + " START WITH DEPTH=0 CONNECT BY PRIOR MENU_CODE=MENU_PARENT_CODE ORDER SIBLINGS BY MENU_CODE";

        try {
            pc.seti();

            pstmt = conn.prepareStatement(LIST_SQL_0 + ORDER_SQL);
            Logger.getLogger(getClass()).debug(LIST_SQL_0 + ORDER_SQL);
            rs = pstmt.executeQuery();
            for (int i = 0; i < pc.getI(); i++) {
                rs.next();
            }
            pc.setii();

            while ((pc.getI() < pc.getIntPagedSize()) && rs.next()) {
                Ent Ent = new Ent();
                Ent.setMenuNo(rs.getInt(1));
                Ent.setMenuCode(rs.getString(2));
                Ent.setMenuIntro(rs.getString(3));
                Ent.setMenuParentCode(rs.getString(4));
                Ent.setTableName(rs.getString(5));
                Ent.setMenuUrl(rs.getString(6));
                Ent.setMenuImage(rs.getString(7));
                Ent.setCreateDate(rs.getString(8));
                Ent.setCreatorID(rs.getString(9));
                Ent.setModifyDate(rs.getString(10));
                Ent.setModifierID(rs.getString(11));
                Ent.setActivity(rs.getInt(12));
                Ent.setOrderNo(rs.getInt(13));
                Ent.setDepth(rs.getInt(14));
                Ent.setMenuEnIntro(rs.getString(15));
                Vlist.addElement(Ent);
                pc.setiii();   
            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            if (rs != null) {
                rs.close();
            } // 关闭数据集
            if (pstmt != null) {
                pstmt.close();
            } // 关闭参数声明
            if (conn != null) {
                conn.close();
            } // 关闭连接
        }
        return Vlist; // 返回记录列表
    }

    public Vector Detail(String No) throws Exception {
        Connection conn = Conn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            pstmt = conn.prepareStatement(LIST_SQL_0 + DETAIL_SQL); // 执行详细信息语句
            pstmt.setInt(1, Integer.parseInt(No)); // 为详细信息的?即详细信息的条件赋值
            rs = pstmt.executeQuery(); // 得到信息数据集
            while (rs.next()) { // 依次得到值
                Ent Ent = new Ent(); // 产生实体对象
                // 按照上面搜索信息依次附值，以下方法如下
                Ent.setMenuNo(rs.getInt(1));
                Ent.setMenuCode(rs.getString(2));
                Ent.setMenuIntro(rs.getString(3));
                Ent.setMenuParentCode(rs.getString(4));
                Ent.setDepth(rs.getInt(14));  
                Ent.setMenuUrl(rs.getString("MENU_URL"));
                Ent.setMenuEnIntro(rs.getString("MENU_EN_INTRO"));

                Vlist.addElement(Ent); // 形成一条记录，插入记录列表
            }
        } catch (SQLException e) {
            System.out.print("Detail Error:" + e);
        } finally {
            if (rs != null) {
                rs.close();
            } // 关闭数据集
            if (pstmt != null) {
                pstmt.close();
            } // 关闭参数声明
            if (conn != null) {
                conn.close();
            } // 关闭连接
        }
        return Vlist; // 返回记录列表
    }

    public void Insert(Ent ent) throws Exception {

        Connection conn = Conn();
        PreparedStatement pstmt = null;

        try {
            // String Oracle_Sql="insert into
            // sy_menu(menu_code,menu_intro,menu_parent_code,menu_url,depth)
            // values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(INSERT_SQL);
            pstmt.setString(1, ent.getMenuCode());
            pstmt.setString(2, ent.getMenuIntro().trim());
            pstmt.setString(3, ent.getMenuParentCode());
            pstmt.setString(4, ent.getMenuUrl());
            pstmt.setString(5, ent.getCreatorID());
            pstmt.setInt(6, ent.getDepth());
            pstmt.setString(7, ent.getMenuEnIntro());
            if (pstmt.executeUpdate() == 0) {
                throw new NotExistException("No data exists");
            }

        } catch (SQLException e) {

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void Delete(int No) throws Exception {
        Connection conn = Conn();
        PreparedStatement pstmt = null; // 建立参数声明
        try {
            pstmt = conn.prepareStatement(DELETE_SQL);
            pstmt.setInt(1, No);
            if (pstmt.executeUpdate() == 0) // 调用上面形成的DELETE语句，为?即参数变量赋值
                throw new NotExistException("No data exists");
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            if (pstmt != null) {
                pstmt.close();
            } // 关闭参数声明
            if (conn != null) {
                conn.close();
            } // 关闭参数声明
        }

    }
    
    
    public boolean DeleteMenuCodeCascade(int No) throws Exception {
    	boolean flag=false;
        Connection conn = Conn();
        PreparedStatement pstmt = null; // 建立参数声明
        try {
            pstmt = conn.prepareStatement(DELETE_SQL1);
            pstmt.setInt(1, No);
            if (pstmt.executeUpdate() == 0) // 调用上面形成的DELETE语句，为?即参数变量赋值
                throw new NotExistException("No data exists");
            
            flag=true;
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            if (pstmt != null) {
                pstmt.close();
            } // 关闭参数声明
            if (conn != null) {
                conn.close();
            } // 关闭参数声明
        }   
  return flag;
    }
    
    
    public boolean searchInScreenGrant(int No) throws Exception {
    	boolean flag=false;
        Connection conn = Conn();
        PreparedStatement pstmt = null; // 建立参数声明
        try {
            pstmt = conn.prepareStatement(DELETE_SQL2);
            pstmt.setInt(1, No);
           ResultSet rst=pstmt.executeQuery();
           if(rst.next())
           {
        	   flag=true;
           }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            if (pstmt != null) {
                pstmt.close();
            } // 关闭参数声明
            if (conn != null) {
                conn.close();
            } // 关闭参数声明
        }   
     return flag;
    }
    
    
    
    

    public void Update(Ent ent) throws Exception {

        Connection conn = Conn();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(UPDATE_SQL); // 执行更新数据方法
            pstmt.setString(1, ent.getMenuCode());
            pstmt.setString(2, ent.getMenuIntro());
            pstmt.setString(3, ent.getMenuParentCode());
            pstmt.setString(4, ent.getMenuUrl());
            pstmt.setString(5, ent.getCreatorID());
            pstmt.setInt(6, ent.getDepth());
            pstmt.setInt(8, ent.getMenuNo());
            pstmt.setString(7, ent.getMenuEnIntro());
            if (pstmt.executeUpdate() == 0) {
                throw new NotExistException("Update No data exists");
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).debug(e.toString());
        } finally {
            SqlUtil.close(pstmt, conn);
        }
    }
    
    public void updateActive(String statsValue, int menuNO) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
				String update_active = "UPDATE sy_menu set ACTIVITY=? where MENU_NO=?";
	
				try {
					Logger.getLogger(getClass()).debug(update_active);
					pstmt = conn.prepareStatement(update_active);
					if (statsValue.equals("1")) {
						pstmt.setInt(1, 0);
					}
					else if (statsValue.equals("0")) 
					{
						pstmt.setInt(1, 1);
					}	
					pstmt.setInt(2, menuNO);
					pstmt.executeUpdate() ;
				}
	
				catch (SQLException e) {
					e.printStackTrace() ;
					Logger.getLogger(getClass()).error(e.toString());
				}
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}

    //
    public String getMenu_intro(String menu_code) throws Exception {
        String menu_intro = null;
        Connection conn = Conn();
        ResultSet rs = null; // 建立数据集

        String ORDER_SQL = " select MENU_INTRO from sy_menu where menu_code='"
                + menu_code + "'";
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(ORDER_SQL);
            while (rs.next()) {
                menu_intro = rs.getString("MENU_INTRO");
            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            if (rs != null)
                rs.close();
            if (conn != null)
                conn.close();
        }
        return menu_intro;
    }

    //
    public ArrayList getMenu_parent_code(HttpServletRequest request) throws Exception {

        Connection conn = Conn();
        ResultSet rs = null; // 建立数据集
        ArrayList names = new ArrayList();
        ArrayList values = new ArrayList();
        ArrayList nv = new ArrayList();
        String Oracle_Sql = "select menu_intro,menu_code from sy_menu where depth < 2";
        AdminBean admin=(AdminBean)request.getSession().getAttribute("admin");
        if(!admin.getLanguagePreference().equals("zh"))         
        	Oracle_Sql= "select menu_en_intro menu_intro,menu_code from sy_menu where depth < 2";
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(Oracle_Sql);
            while (rs.next()) {
                names.add(rs.getString("menu_intro"));
                values.add(rs.getString("menu_code"));           
            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            if (rs != null)
                rs.close();
            if (conn != null)
                conn.close();
        }
        nv.add(names);
        nv.add(values);
        return nv;
    }

    //
    public int getUpLevel(String Table, String LevelFiled, String CodeNo,
            String ParntCodeValue) throws Exception {
        int old = 0;
        int news = 0;
        if(!ParntCodeValue.equals("0"))
        {
        Connection conn = Conn();
        ResultSet rs = null;
        String Oracle_Sql = "select " + LevelFiled + " from " + Table
                + " where " + CodeNo + "='" + ParntCodeValue + "'";
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(Oracle_Sql);
            while (rs.next()) {
                old = rs.getInt(LevelFiled);
            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            if (rs != null)
                rs.close();
            if (conn != null)    
                conn.close();
        }
        news = old + 1;
        }else{
        	news=0;            
        }
        return news;

    }

    //
    public int getSy_menuSum() throws Exception {
        int count = 0;
        Connection conn = Conn();
        ResultSet rs = null;
        String Oracle_Sql = "select count(*) as sySum from sy_menu";
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(Oracle_Sql);
            while (rs.next()) {
                count = rs.getInt("sySum");
            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            if (rs != null)
                rs.close();
            if (conn != null)
                conn.close();
        }
        return count;

    }
    public Vector ListCode()throws Exception {
    	String sql = "SELECT SY.CODE_ID,SY.CODE_NAME FROM SY_CODE SY WHERE SY.PARENT_CODE IN( SELECT M.CODE_ID FROM SY_CODE M WHERE M.PARENT_CODE='GA') AND SY.ACTIVITY=1";
    	log.debug(sql);        
        Connection conn = Conn();
        PreparedStatement pstmt = null; // 建立参数声明
        ResultSet rs = null; // 建立数据集
        try {
            pstmt = conn.prepareStatement(sql);
            Logger.getLogger(getClass()).debug(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	Ent Ent = new Ent(); // 产生实体对象
            	Ent.setMenuCode(rs.getString("CODE_ID"));
            	Ent.setMenuEnIntro(rs.getString("CODE_NAME"));
                Vlist1.addElement(Ent); // 形成一条记录，插入记录列表
//            	}
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).debug(e.toString());
        } finally {
            SqlUtil.close(rs, pstmt, conn);
        }
        return Vlist1; // 返回记录列表
    }

}
