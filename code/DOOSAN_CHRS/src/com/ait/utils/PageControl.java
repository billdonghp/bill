package com.ait.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.util.SqlUtil;

public class PageControl {
    public int intPageSize;

    public int RowCount;

    public int intPageCount;

    public int intPage;

    public int i;

    public int subsum;

    public int sumsum;

    private int requestbig;

    public PageControl() {
        this.intPageSize = 1;
        this.subsum = 10;
    }

    public PageControl(int pagesize, int subpage) {
        this.intPageSize = pagesize;
        this.subsum = subpage;
    }

    public int setintPage(int strPage, int bigpage) {
        if (strPage == 0 && bigpage == 1) {
            this.intPage = 1;
        }
        if (strPage > 0) {
            this.intPage = strPage;
        }
        if (strPage == 0 && bigpage > 1) {
            this.intPage = (bigpage - 1) * 10 + 1;
        }
        return intPage;
    }

    public void setRowCount(String tablename) throws Exception {
        Connection conn = Conn();
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM " + tablename;
        try {
            rs = stmt.executeQuery(sql);
            rs.next();
            RowCount = rs.getInt(1);
            setintPageCount();
            setSumSum();
        } catch (SQLException se) {
        	Logger.getLogger(getClass()).debug(sql);
            Logger.getLogger(getClass()).debug(se.toString());
        } finally {
            SqlUtil.close(rs, stmt, conn);
        }
    }

    private void setintPageCount() {
        this.intPageCount = (this.RowCount + this.intPageSize - 1)
                / this.intPageSize;
    }

    public void seti() {
        this.i = (this.intPage - 1) * this.intPageSize;
    }

    public void setii() {
        this.i = 0;
    }

    public void setiii() {
        this.i = this.i + 1;
    }

    private void setSumSum() {
        int temp = (intPageCount + subsum - 1) / subsum;
        this.sumsum = temp;
    }

    public boolean isNextBig(int bigpage) {
        boolean flag = false;
        if (bigpage + 1 <= this.sumsum) {
            flag = true;
            return flag;
        }
        return flag;
    }

    public boolean isforward(int bigpage) {
        boolean flag = false;
        if (bigpage - 1 >= 1) {
            flag = true;
            return flag;
        }
        return flag;
    }

    public int getI() {
        return this.i;
    }

    public int getIntPagedSize() {
        return this.intPageSize;
    }

    public boolean getNowSmall(int strpage, int big) {// \uD138\uB664\uCEF4硅鬼女\uBD40鞫刻
        boolean flag = false;
        if (big == 1) {
            if (strpage <= this.intPageCount) {
                flag = true;
            }
        }
        if (big > 1) {
            if ((strpage + (big - 1) * 10) <= this.intPageCount) {
                flag = true;
            }
        }
        return flag;
    }

    public int getTmpBig(String big) {
        if (big == null || big.equals("")) {
            big = "1";
        }
        this.requestbig = Integer.parseInt(big);
        return this.requestbig;
    }

    public int getTmpSmall(String strpage, int bigpage) {
        if (bigpage == 1) {
            if (strpage == null || strpage.equals("")) {
                this.requestbig = 1;
            } else {
                this.requestbig = Integer.parseInt(strpage);
            }
        }
        if (bigpage > 1) {
            if (strpage == null || strpage.equals("")) {
                this.requestbig = (bigpage - 1) * 10 + 1;
            } else {
                this.requestbig = (bigpage - 1) * 10
                        + Integer.parseInt(strpage);
            }
        }
        return this.requestbig;
    }

    public int getListSmall(int strpage, int bigpage) {
        int a = 0;
        if (bigpage == 1) {
            a = strpage;
        }
        if (bigpage > 1) {
            a = (bigpage - 1) * 10 + strpage;
        }
        return a;
    }

    public Connection Conn() throws Exception {
        Connection Conn = null;// \uC254\uC811\uAF5D?b??\uCE20
        try {
            Conn = ConnBean.getConn(); // \uB3E4\uB3D5\uC82F\uC308
        } catch (Exception e) {
            System.out.print(e);
        }
        return Conn;
    }

    /**
     * 动态连接数据库所需函数
     * 
     * @param tablename
     * @param m_session
     * @throws java.lang.Exception
     */
    public void setRowCount(String tablename, HttpSession m_session)
            throws Exception { // \uC0BF\uD624\uC158\uCA4C???b
        Connection conn = Conn(); // \uC254\uC811\uC82F\uC308
        Statement stmt = conn.createStatement();
        ; // \uC254\uC811\uAF5D?b??\uCE20
        ResultSet rs = null;
        String sql = "select count(*) from " + tablename;
        try {
            rs = stmt.executeQuery(sql);
            rs.next(); // \uF95F蕨\uB4A4???S\uC158\uCA4C
            RowCount = rs.getInt(1); // \uC0BF\uD624\uC158\uCA4C???b
            setintPageCount();// \uC158炬??女?b
            setSumSum();// \uC155炬??\uBB3E唐\uC12F\uB315女
        } catch (SQLException se) {
            System.out.println("SQLException in getFriends() of FriendBean: "
                    + se.getMessage());
            System.out.println("SQL :" + sql);
        } finally {
            if (rs != null) {
                rs.close();
            }// \uBC11\uADE0?b\uC574\uC11E
            if (stmt != null) {
                stmt.close();
            }// \uBC11\uADE0\uAF5D?b??\uCE20
            if (conn != null) {
                conn.close();
            }// \uBC11\uADE0\uC82F\uC308
        }
    }

}
