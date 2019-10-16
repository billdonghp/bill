package com.ait.etn;


import java.sql.*;
import java.util.Vector;
import com.ait.util.*;


public class EtnPersonalScore extends ImmitPoxy{
  private static ServiceLocator services;
  private String empid;
  private String empName;
  private String examScore;
  private String makeUpScore;
  private String appraiseResult;
  private String content;
  private String appraiseResultName;
  private String contentName;
  public EtnPersonalScore() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {
    }
  }
  public String getAppraiseScore(String examScore)throws Exception{
       String appraiseScore = "";
       float examscore = Float.parseFloat(examScore);
       if(examscore >=90){
         appraiseScore="S";
       }else if(examscore >=80 && examscore < 90){
         appraiseScore="A";
       }else if(examscore >=70 && examscore < 80){
         appraiseScore="B";
       }else if(examscore >=60 && examscore < 70){
         appraiseScore="C";
       }else if(examscore < 60){
         appraiseScore="D";
       }
       return appraiseScore;
  }
  public void updateEtnPersonalScore(String vectorlength,String etnplanno,String[] empid,String[] examscore,String[] makeupscore,String[] content)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL = "update ETN_FACT_PLAN set EXAM_SCORE=?, MAKEUP_SCORE=?,APPRAISE_RESULT=?,  "+
                 "CONTENT=? where SEQ_ETN_PLAN=? and EMPID=?";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(5, Integer.parseInt(etnplanno));
        for(int i=0; i<Integer.parseInt(vectorlength);i++){
          pstmt.setString(1, examscore[i]);
          pstmt.setString(2, makeupscore[i]);
          pstmt.setString(3, getAppraiseScore(examscore[i]));
          pstmt.setString(4, content[i]);
          pstmt.setString(6, empid[i]);
          pstmt.executeUpdate();
        }
    }
    catch (SQLException ex) {
    }
    catch (ServiceLocatorException ex) {
    }
    finally{
      pstmt.close();
      conn.close();
    }
  }

  public Vector getEtnPersonalScore(String etnplanno)throws Exception{
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = " select EMPID, CHINESENAME, EXAM_SCORE, MAKEUP_SCORE, "+
                 " APPRAISE_RESULT,CONTENT, "+
                 "  CONTENT_NAME  from ETN_FACT_GROUP_V where SEQ_ETN_PLAN=? order by exam_score desc";
    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1,Integer.parseInt(etnplanno));
      rs = pstmt.executeQuery();
      while(rs.next()) {
        vector.add(this.createEtnPersonalScore(rs));
      }
    }
    catch (SQLException ex) {
    }
    catch (ServiceLocatorException ex) {
    }
    finally{
      rs.close();
      pstmt.close();
      conn.close();
    }
    return vector;
  }
  private EtnPersonalScore createEtnPersonalScore(ResultSet rs) throws Exception{
    EtnPersonalScore etnpersonalscore = new EtnPersonalScore();
      try {
        etnpersonalscore.setEmpid(rs.getString("EMPID") != null ? rs.getString("EMPID") : "");
        etnpersonalscore.setEmpName(rs.getString("chinesename") != null ? rs.getString("chinesename") : "");
        etnpersonalscore.setExamScore(rs.getString("EXAM_SCORE") != null ? rs.getString("EXAM_SCORE") : "");
        etnpersonalscore.setMakeUpScore(rs.getString("MAKEUP_SCORE") != null ? rs.getString("MAKEUP_SCORE") :  "");
        etnpersonalscore.setAppraiseResult(rs.getString("APPRAISE_RESULT") != null ? rs.getString("APPRAISE_RESULT") : "");
        etnpersonalscore.setContent(rs.getString("CONTENT") != null ? rs.getString("CONTENT") : "");
        etnpersonalscore.setContentName(rs.getString("CONTENT_NAME") != null ? rs.getString("CONTENT_NAME") : "");
      }
      catch (SQLException ex) {
      }
      return etnpersonalscore;
    }
   public boolean addEtnWholeSatisfactProcedure(String etnplanno)throws DataAccessException {
     Connection con = null;
     String sql = "{ call etn_score_statistic(?) }";
     CallableStatement cs = null;
     try {
       con = services.getConnection();
       cs = con.prepareCall(sql);
       cs.setInt(1,Integer.parseInt(etnplanno));
       return cs.execute();
     }
     catch (SQLException ex) {
      ex.printStackTrace();
      throw new DataAccessException("cant execute query ", ex);
    }
    catch (ServiceLocatorException ex) {
      throw new DataAccessException("cant get connection ", ex);
    }
    finally {
      SqlUtil.close(cs, con);
    }
   }

//////////2005-11-09   -2
public void  UpdateOpScore(String etnplanno,String[] pno,String[]pexamscore,String[]pmakeupscore,String[]pcontent){
	Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL = "update ETN_OPSCORE set EXAM_SCORE=? ,  MAKEUP_SCORE=? ,APPRAISE_RESULT=?, CONTENT=? "+
				" where PLAN_NO=? and PNO=? ";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(5, Integer.parseInt(etnplanno));
        for(int i=0; i<pno.length;i++){
		  pstmt.setString(6, pno[i]);
          pstmt.setString(1, pexamscore[i]);
          pstmt.setString(2, pmakeupscore[i]);
          pstmt.setString(3, getAppraiseScore(pexamscore[i]));
          pstmt.setString(4, pcontent[i]);
          
          pstmt.executeUpdate();
        }
    }
    catch (Exception ex) {
		System.out.println(ex.getMessage());
    }
    finally
    {
            SqlUtil.close(pstmt, conn);
    }
}

//////////2005-11-09       -1

public void addOpScore(String etnplanno,String[] pno,String[]pexamscore,String[]pmakeupscore,String[]pcontent){



	Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL = "INSERT INTO  ETN_OPSCORE ( SEQ_NO, PLAN_NO, PNO ,EXAM_SCORE, MAKEUP_SCORE, APPRAISE_RESULT, CONTENT)"+
		" VALUES (SEQ_ETN_OPSCORE.NEXTVAL,?,?,?,?,?,?) ";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1, Integer.parseInt(etnplanno));
        for(int i=0; i<pno.length;i++){
		  pstmt.setString(2, pno[i]);
          pstmt.setString(3, pexamscore[i]);
          pstmt.setString(4, pmakeupscore[i]);
          pstmt.setString(5, getAppraiseScore(pexamscore[i]));
          pstmt.setString(6, pcontent[i]);
          
          pstmt.executeUpdate();
        }
    }
    catch (Exception ex) {
		System.out.println(ex.getMessage());
    }
    finally
    {
            SqlUtil.close(pstmt, conn);
    }
}



public Vector getOpScore(String etnplanno){

 Vector vector = new Vector();
    Connection conn = null;
   Statement stmt=null;
    ResultSet rs = null;

	String SQL = "INSERT INTO  ETN_OPSCORE ( SEQ_NO, PLAN_NO, PNO ) "+
			"select SEQ_ETN_OPSCORE.NEXTVAL,po.PLAN_NO, po.PNO "+
			"from ETN_PLAN_OTHER po "+
			"where po.plan_no= "+etnplanno+" "+	
			"and po.pno not in ( "+
			"select s.pno from ETN_OPSCORE s where s.plan_no="+etnplanno+")";
    try {

      conn = services.getConnection();
      stmt = conn.createStatement();
	  stmt.executeUpdate(SQL);

	  SQL = " select s.*,p.*,sc.code_name as contentname "+
				"from sy_code sc,etn_opscore s,etn_other_peoples p "+
				"where s.content=sc.code_id(+) and p.seq_pno=s.pno and plan_no="+etnplanno;
      rs = stmt.executeQuery(SQL);
	  this.Immit(rs,OtherPeopleScore.class,vector);

    }
    catch (Exception ex) {
		System.out.println(ex.getMessage());
	}
    finally{
     SqlUtil.close(rs,stmt, conn);
    }
    return vector;
}





  public String getEmpid() {
    return empid;
  }
  public void setEmpid(String empid) {
    this.empid = empid;
  }
  public String getEmpName() {
    return empName;
  }
  public void setEmpName(String empName) {
    this.empName = empName;
  }
  public String getExamScore() {
    return examScore;
  }
  public void setExamScore(String examScore) {
    this.examScore = examScore;
  }
  public String getMakeUpScore() {
    return makeUpScore;
  }
  public void setMakeUpScore(String makeUpScore) {
    this.makeUpScore = makeUpScore;
  }
  public String getAppraiseResult() {
    return appraiseResult;
  }
  public void setAppraiseResult(String appraiseResult) {
    this.appraiseResult = appraiseResult;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getAppraiseResultName() {
    return appraiseResultName;
  }
  public void setAppraiseResultName(String appraiseResultName) {
    this.appraiseResultName = appraiseResultName;
  }
  public String getContentName() {
    return contentName;
  }
  public void setContentName(String contentName) {
    this.contentName = contentName;
  }
}
