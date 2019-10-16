package com.ait.etn;

import java.sql.*;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.util.*;

public class OtherPeopleManage
{

	 private static ServiceLocator services;


	 public OtherPeopleManage() {
		try {
		services = ServiceLocator.getInstance();
		}
		catch (ServiceLocatorException ex) {
			System.out.println(ex.getMessage());
		}
	}


	public int addOtherPeople(OtherPeople op)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt=null;
		ResultSet rs = null;
		conn = services.getConnection();
		int i=-1;
		String SQL = "SELECT count(*) as c FROM ETN_OTHER_PEOPLES WHERE PNAME ='"+op.getPname()+"'";
	    stmt = conn.createStatement();
        rs = stmt.executeQuery(SQL);
		rs.next();
        if(rs.getInt("c")>0)
		    return -1;
        SQL = "INSERT into ETN_OTHER_PEOPLES(SEQ_PNO, PNAME, PSOURCE, CREATE_TIME) "+
			"VALUES (SEQ_ETN_OTHER_PEOPLES.nextval,?,?,to_date(?,'yyyy-mm-dd'))";
		 try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, op.getPname());
			pstmt.setString(2, op.getPsource());
			pstmt.setString(3, op.getCreate_time());
			i= pstmt.executeUpdate();
		}
		catch (Exception ex) {
				Logger.getLogger(getClass()).error(ex.toString());
			}
			finally
			{
				SqlUtil.close(rs, stmt, pstmt, conn);
			}

			return i;
	}


	public Vector getAllOtherPeople(){
		 Vector vector = new Vector();
		 Connection conn = null;
		 Statement stmt=null;
		 ResultSet rs = null;
		 String SQL ="SELECT SEQ_PNO,PNAME,PSOURCE,TO_CHAR(CREATE_TIME, 'YYYY-MM-DD') as CREATE_TIME FROM ETN_OTHER_PEOPLES";

    try {
      conn = services.getConnection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(SQL);
      while(rs.next()) {
           OtherPeople op = new OtherPeople();
		   op.setSeq_pno(rs.getString("seq_pno"));
			op.setPname(rs.getString("pname"));
			if(rs.getString("psource")==null)
				op.setPsource("");
			else
				op.setPsource(rs.getString("psource"));
			op.setCreate_time(rs.getString("create_time"));
           vector.add(op);
      }
    }
    catch (Exception ex) {
      Logger.getLogger(getClass()).error(ex.toString());
	  }

    finally{
		SqlUtil.close(stmt, conn);
    }
    return vector;
	}
//2005-11-06
  public Vector getAllOtherPeople(PageControl pc)throws Exception{
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
   String SQL ="SELECT SEQ_PNO,PNAME,PSOURCE,TO_CHAR(CREATE_TIME, 'YYYY-MM-DD') as CREATE_TIME FROM ETN_OTHER_PEOPLES";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      rs = pstmt.executeQuery();
      pc.seti();
      for (int i = 0; i < pc.getI(); i++) {
        rs.next();
      }
      pc.setii();

      while ( (pc.getI() < pc.getIntPagedSize()) && rs.next()) {
        OtherPeople op = new OtherPeople();
		   op.setSeq_pno(rs.getString("seq_pno"));
			op.setPname(rs.getString("pname"));
			if(rs.getString("psource")==null)
				op.setPsource("");
			else
				op.setPsource(rs.getString("psource"));
			op.setCreate_time(rs.getString("create_time"));
           vector.add(op);
        pc.setiii();
      }
    }
    catch (SQLException ex) {
      Logger.getLogger(getClass()).error(ex.toString());
    }
    catch (ServiceLocatorException ex) {
      Logger.getLogger(getClass()).error(ex.toString());
    }
    finally{
      SqlUtil.close(rs,pstmt,conn);
    }
    return vector;
  }






  public Vector getAllOtherPeople(PageControl pc,String querytype,String queryname )throws Exception{
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
   String SQL ="SELECT SEQ_PNO,PNAME,PSOURCE,TO_CHAR(CREATE_TIME, 'YYYY-MM-DD') as CREATE_TIME FROM ETN_OTHER_PEOPLES  WHERE "+
	   querytype+" LIKE '%"+queryname+"%'";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      rs = pstmt.executeQuery();
      pc.seti();
      for (int i = 0; i < pc.getI(); i++) {
        rs.next();
      }
      pc.setii();

      while ( (pc.getI() < pc.getIntPagedSize()) && rs.next()) {
        OtherPeople op = new OtherPeople();
		   op.setSeq_pno(rs.getString("seq_pno"));
			op.setPname(rs.getString("pname"));
			if(rs.getString("psource")==null)
				op.setPsource("");
			else
				op.setPsource(rs.getString("psource"));
			op.setCreate_time(rs.getString("create_time"));
           vector.add(op);
        pc.setiii();
      }
    }
    catch (SQLException ex) {
      Logger.getLogger(getClass()).error(ex.toString());
    }
    catch (ServiceLocatorException ex) {
      Logger.getLogger(getClass()).error(ex.toString());
    }
    finally{
      SqlUtil.close(rs,pstmt,conn);
    }
    return vector;
  }





public void  delOtherPeople(String pno){
	 Connection conn = null;
	Statement stmt=null;
	String sql="delete from  ETN_OTHER_PEOPLES where seq_pno="+pno;
 try {
	 conn = services.getConnection();
      stmt = conn.createStatement();
//     int f=
      stmt.executeUpdate(sql);
	 //System.out.println(f);
  }
    catch (Exception ex) {
      Logger.getLogger(getClass()).error(ex.toString());
	  }

    finally{
		SqlUtil.close(stmt, conn);
    }
}

public void  UpdateOtherPeople(OtherPeople op){
	Connection conn = null;
	Statement stmt=null;
	String sql="update ETN_OTHER_PEOPLES set pname='"+op.getPname()+"',psource='"+op.getPsource()+"' where seq_pno="+op.getSeq_pno();
 try {
	 conn = services.getConnection();
      stmt = conn.createStatement();
     //int f=
      stmt.executeUpdate(sql);
	 //System.out.println(f);
  }
    catch (Exception ex) {
      Logger.getLogger(getClass()).error(ex.toString());
	  }

    finally{
		SqlUtil.close(stmt, conn);
    }
}





public OtherPeople getSingleOtherPeopleByPno(String pno){
		 Connection conn = null;
		 Statement stmt=null;
		 ResultSet rs = null;
		 OtherPeople op=null;
		 String SQL ="SELECT SEQ_PNO,PNAME,PSOURCE,TO_CHAR(CREATE_TIME, 'YYYY-Mon-DD') as CREATE_TIME FROM ETN_OTHER_PEOPLES where SEQ_PNO="+pno;

    try {
      conn = services.getConnection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(SQL);
      while(rs.next()) {
           op = new OtherPeople();
		   op.setSeq_pno(rs.getString("seq_pno"));
			op.setPname(rs.getString("pname"));
			if(rs.getString("psource")==null)
				op.setPsource("");
			else
				op.setPsource(rs.getString("psource"));
			op.setCreate_time(rs.getString("create_time"));
      }
    }
    catch (Exception ex) {
      Logger.getLogger(getClass()).error(ex.toString());
	  }

    finally{
		SqlUtil.close(stmt, conn);
    }
    return op;

}


	///===end
}
