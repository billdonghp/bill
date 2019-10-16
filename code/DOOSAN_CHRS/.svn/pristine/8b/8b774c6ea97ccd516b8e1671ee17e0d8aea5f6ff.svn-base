package com.ait.etn;


import java.sql.*;
import java.util.Vector;
import com.ait.util.*;


public class EtnPersonalTotalResult {
  private static ServiceLocator services;
  private String etnPlanNO;
  private String empID;
  private String chineseName;
  private String satisfactRate;
  private String examScore;
  private String makeUpScore;
  private String appraiseResultName;
  private String contentName;
  private String contentCode;
  private String appraiseResultCode;
  private String planEduItemTypeCode;
  private String planEduItemTypeName;
  private String planClassType;
  private String planPerriorCount;
  private String planEduTypeCode;
  private String planEduTypeName;
  private String planEduPlace;
  private String planStartDate;
  private String planEndDate;
  private String planEduTime;
  private String planColor;
  private String planAppraiseContent;
  private String planCode;
  private String teachTextFee;
  private String groudFee;
  private String eattingFee;
  private String quarterFee;
  private String trafficFee;
  private String passportFee;
  private String visaFee;
  private String insureFee;
  private String livingFee;
  private String otherFee;
  private String countTeacherFee;
  private String totalFee;
  private String age;
  private String joinDate;
  private String department;
  private String position;
  private String postGroupName;
  private String postGrade;
  private String finalDegree;
  private String sex;
  private String dob;
  private String idCardNO;
  private String idCardAddr;
  private String passportNO;
  private String visaDate;
  private String institutionName;
  private String subject;
  private String seqProtocolEtnCode;
  private String protocolCode;
  private String protocolName;
  private String protocolStartDate;
  private String protocolEndDate;
  private String protocolTerm;
  private String protocolContent;
  private String countGroup;
  private String postCode;
  private String post;
  private String workStatus;
  private String statusCode;
  public EtnPersonalTotalResult() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {
    }
  }
  public Vector getEtnPersonalTotalResult(String sql,String content)throws DataAccessException{
    Vector vector = new Vector();
    String sqlcontent = "";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "select SEQ_ETN_PLAN, EMPID, CHINESENAME, "+
                 " SATISFACT_RATE, EXAM_SCORE, MAKEUP_SCORE, "+
                 " APPRAISE_RESULT, CONTENT, "+
                 " CONTENT_NAME, PLAN_EDU_ITEM_CODE, PLAN_EDU_ITEM_NAME, "+
                 " PLAN_CLASS_TYPE, PLAN_EDU_TYPE_CODE, "+
                 " PLAN_EDU_TYPE_NAME, PLAN_EDU_PLACE, PLAN_START_DATE, "+
                 " PLAN_END_DATE, PLAN_EDU_TIME,"+
                 " PLAN_APPRAISE_CONTENT, PLAN_CODE, FEE_TEACH_TEXT, "+
                 " FEE_GROUND, FEE_EATTING, FEE_QUARTER, "+
                 " FEE_TRRAFIC, FEE_PASSPORT, FEE_VISA, "+
                 " FEE_INSURE, FEE_LIVING, FEE_OTHER, "+
                 " COUNT_TEACHER_FEE, TOTAL_FEE, AGE, "+
                 " JOINDATE, DEPARTMENT, POSITION, "+
                 " POST_GROUP_NAME, POST_GRADE, FINAL_DEGREE, "+
                 " SEX, DOB, IDCARD_NO, "+
                 " IDCARD_ADDR, PASSPORT_NO, VISA_DATE, "+
                 " SEQ_PROTOCOL_ETN, "+
                 " PROTOCOL_CODE, PROTOCOL_NAME, PROTOCOL_START_DATE, "+
                 " PROTOCOL_END_DATE, PROTOCOL_TERM, PROTOCOL_CONTENT ,COUNTGROUP, POST_CODE, POST,"+
                 " WORKSTATUS, STATUS_CODE "+
                 " from ETN_PLAN_PERSONAL_TOTAL where";


//    String sqlend = sql.substring(0,sql.length());
    sqlcontent = sql+"like '%"+content+"%'";
    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL+sqlcontent);
      rs = pstmt.executeQuery();
      while(rs.next()){
        vector.add(createEtnPersonalTotalResult(rs));
      }
    }
    catch (SQLException ex) {
    }
    catch (ServiceLocatorException ex) {
    }
    finally {
            SqlUtil.close(pstmt, conn);
          }
    return vector;
  }
  /**
   *
   * @param sql String
   * @param startdate String
   * @param enddate String
   * @throws DataAccessException
   * @return Vector
   */
  public Vector getEtnPersonalTotalResult(String sql,String startdate,String enddate)throws DataAccessException{
    Vector vector = new Vector();
    String sqlcontent = "";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "select SEQ_ETN_PLAN, EMPID, CHINESENAME, "+
                 " SATISFACT_RATE, EXAM_SCORE, MAKEUP_SCORE, "+
                 " APPRAISE_RESULT, CONTENT, "+
                 " CONTENT_NAME, PLAN_EDU_ITEM_CODE, PLAN_EDU_ITEM_NAME, "+
                 " PLAN_CLASS_TYPE,  PLAN_EDU_TYPE_CODE, "+
                 " PLAN_EDU_TYPE_NAME, PLAN_EDU_PLACE, PLAN_START_DATE, "+
                 " PLAN_END_DATE, PLAN_EDU_TIME, "+
                 " PLAN_APPRAISE_CONTENT, PLAN_CODE, FEE_TEACH_TEXT, "+
                 " FEE_GROUND, FEE_EATTING, FEE_QUARTER, "+
                 " FEE_TRRAFIC, FEE_PASSPORT, FEE_VISA, "+
                 " FEE_INSURE, FEE_LIVING, FEE_OTHER, "+
                 " COUNT_TEACHER_FEE, TOTAL_FEE, AGE, "+
                 " JOINDATE, DEPARTMENT, POSITION, "+
                 " POST_GROUP_NAME, POST_GRADE, FINAL_DEGREE, "+
                 " SEX, DOB, IDCARD_NO, "+
                 " IDCARD_ADDR, PASSPORT_NO, VISA_DATE, "+
                 "  SEQ_PROTOCOL_ETN, "+
                 " PROTOCOL_CODE, PROTOCOL_NAME, PROTOCOL_START_DATE, "+
                 " PROTOCOL_END_DATE, PROTOCOL_TERM, PROTOCOL_CONTENT,COUNTGROUP, POST_CODE, POST, "+
                 " WORKSTATUS, STATUS_CODE "+
                 " from ETN_PLAN_PERSONAL_TOTAL where";
    sqlcontent = sql+">=to_date('"+startdate+"','yyyy-mm-dd') and "+sql+"<=to_date('"+enddate+"','yyyy-mm-dd')";
    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL+sqlcontent);
      rs = pstmt.executeQuery();
      while(rs.next()){
        vector.add(createEtnPersonalTotalResult(rs));
      }
    }
    catch (SQLException ex) {
    }
    catch (ServiceLocatorException ex) {
    }
    finally {
            SqlUtil.close(pstmt, conn);
          }
    return vector;
  }
  /**
   *
   * @param sql String
   * @param startdate String
   * @param enddate String
   * @throws DataAccessException
   * @return Vector
   */
  public Vector getEtnPersonalTotalResultByEmpid(String empid)throws DataAccessException{
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "select SEQ_ETN_PLAN, EMPID, CHINESENAME, "+
                 " SATISFACT_RATE, EXAM_SCORE, MAKEUP_SCORE, "+
                 " APPRAISE_RESULT, CONTENT, "+
                 " CONTENT_NAME, PLAN_EDU_ITEM_CODE, PLAN_EDU_ITEM_NAME, "+
                 " PLAN_CLASS_TYPE,  PLAN_EDU_TYPE_CODE, "+
                 " PLAN_EDU_TYPE_NAME, PLAN_EDU_PLACE, PLAN_START_DATE, "+
                 " PLAN_END_DATE, PLAN_EDU_TIME, "+
                 " PLAN_APPRAISE_CONTENT, PLAN_CODE, FEE_TEACH_TEXT, "+
                 " FEE_GROUND, FEE_EATTING, FEE_QUARTER, "+
                 " FEE_TRRAFIC, FEE_PASSPORT, FEE_VISA, "+
                 " FEE_INSURE, FEE_LIVING, FEE_OTHER, "+
                 " COUNT_TEACHER_FEE, TOTAL_FEE, AGE, "+
                 " JOINDATE, DEPARTMENT, POSITION, "+
                 " POST_GROUP_NAME, POST_GRADE, FINAL_DEGREE, "+
                 " SEX, DOB, IDCARD_NO, "+
                 " IDCARD_ADDR, PASSPORT_NO, VISA_DATE, "+
                 " SEQ_PROTOCOL_ETN, "+
                 " PROTOCOL_CODE, PROTOCOL_NAME, PROTOCOL_START_DATE, "+
                 " PROTOCOL_END_DATE, PROTOCOL_TERM, PROTOCOL_CONTENT,COUNTGROUP, POST_CODE, POST, "+
                 " WORKSTATUS, STATUS_CODE "+
                 " from ETN_PLAN_PERSONAL_TOTAL where EMPID=?";
    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1,empid);
      rs = pstmt.executeQuery();
      while(rs.next()){
        vector.add(createEtnPersonalTotalResult(rs));
      }
    }
    catch (SQLException ex) {
    }
    catch (ServiceLocatorException ex) {
    }
    finally {
      SqlUtil.close(pstmt, conn);
          }
    return vector;
  }


  /**
   *
   * @param rs ResultSet
   * @throws DataAccessException
   * @return EtnEndTotalResult
   */
  private EtnPersonalTotalResult createEtnPersonalTotalResult(ResultSet rs)throws DataAccessException{
     EtnPersonalTotalResult  etnpersonaltotalresult = new EtnPersonalTotalResult();
      try {
        etnpersonaltotalresult.setEtnPlanNO(rs.getString("SEQ_ETN_PLAN")!=null?rs.getString("SEQ_ETN_PLAN"):"");
        etnpersonaltotalresult.setEmpID(rs.getString("EMPID")!=null?rs.getString("EMPID"):"");
        etnpersonaltotalresult.setChineseName(rs.getString("CHINESENAME")!=null?rs.getString("CHINESENAME"):"");
        etnpersonaltotalresult.setSatisfactRate(rs.getString("SATISFACT_RATE")!=null?rs.getString("SATISFACT_RATE"):"");
        etnpersonaltotalresult.setExamScore(rs.getString("EXAM_SCORE")!=null?rs.getString("EXAM_SCORE"):"");
        etnpersonaltotalresult.setMakeUpScore(rs.getString("MAKEUP_SCORE")!=null?rs.getString("MAKEUP_SCORE"):"");
        etnpersonaltotalresult.setAppraiseResultCode(rs.getString("APPRAISE_RESULT")!=null?rs.getString("APPRAISE_RESULT"):"");
        etnpersonaltotalresult.setContentCode(rs.getString("CONTENT")!=null?rs.getString("CONTENT"):"");
        etnpersonaltotalresult.setContentName(rs.getString("CONTENT_NAME")!=null?rs.getString("CONTENT_NAME"):"");
        etnpersonaltotalresult.setPlanEduItemTypeCode(rs.getString("PLAN_EDU_ITEM_CODE")!=null?rs.getString("PLAN_EDU_ITEM_CODE"):"");
        etnpersonaltotalresult.setPlanEduItemTypeName(rs.getString("PLAN_EDU_ITEM_NAME")!=null?rs.getString("PLAN_EDU_ITEM_NAME"):"");
        etnpersonaltotalresult.setPlanClassType(rs.getString("PLAN_CLASS_TYPE")!=null?rs.getString("PLAN_CLASS_TYPE"):"");
        etnpersonaltotalresult.setPlanEduTypeCode(rs.getString("PLAN_EDU_TYPE_CODE")!=null?rs.getString("PLAN_EDU_TYPE_CODE"):"");
        etnpersonaltotalresult.setPlanEduTypeName(rs.getString("PLAN_EDU_TYPE_NAME")!=null?rs.getString("PLAN_EDU_TYPE_NAME"):"");
        etnpersonaltotalresult.setPlanEduPlace(rs.getString("PLAN_EDU_PLACE")!=null?rs.getString("PLAN_EDU_PLACE"):"");
        etnpersonaltotalresult.setPlanStartDate(rs.getString("PLAN_START_DATE")!=null?rs.getString("PLAN_START_DATE").substring(0,10):"");
        etnpersonaltotalresult.setPlanEndDate(rs.getString("PLAN_END_DATE")!=null?rs.getString("PLAN_END_DATE").substring(0,10):"");
        etnpersonaltotalresult.setPlanEduTime(rs.getString("PLAN_EDU_TIME")!=null?rs.getString("PLAN_EDU_TIME"):"");
        etnpersonaltotalresult.setPlanAppraiseContent(rs.getString("PLAN_APPRAISE_CONTENT")!=null?rs.getString("PLAN_APPRAISE_CONTENT"):"");
        etnpersonaltotalresult.setPlanCode(rs.getString("PLAN_CODE")!=null?rs.getString("PLAN_CODE"):"");
        etnpersonaltotalresult.setTeachTextFee(rs.getString("FEE_TEACH_TEXT")!=null?rs.getString("FEE_TEACH_TEXT"):"");
        etnpersonaltotalresult.setGroudFee(rs.getString("FEE_GROUND")!=null?rs.getString("FEE_GROUND"):"");
        etnpersonaltotalresult.setEattingFee(rs.getString("FEE_EATTING")!=null?rs.getString("FEE_EATTING"):"");
        etnpersonaltotalresult.setQuarterFee(rs.getString("FEE_QUARTER")!=null?rs.getString("FEE_QUARTER"):"");
        etnpersonaltotalresult.setTrafficFee(rs.getString("FEE_TRRAFIC")!=null?rs.getString("FEE_TRRAFIC"):"");
        etnpersonaltotalresult.setPassportFee(rs.getString("FEE_PASSPORT")!=null?rs.getString("FEE_PASSPORT"):"");
        etnpersonaltotalresult.setVisaFee(rs.getString("FEE_VISA")!=null?rs.getString("FEE_VISA"):"");
        etnpersonaltotalresult.setInsureFee(rs.getString("FEE_INSURE")!=null?rs.getString("FEE_INSURE"):"");
        etnpersonaltotalresult.setLivingFee(rs.getString("FEE_LIVING")!=null?rs.getString("FEE_LIVING"):"");
        etnpersonaltotalresult.setOtherFee(rs.getString("FEE_OTHER")!=null?rs.getString("FEE_OTHER"):"");
        etnpersonaltotalresult.setCountTeacherFee(rs.getString("COUNT_TEACHER_FEE")!=null?rs.getString("COUNT_TEACHER_FEE"):"");
        etnpersonaltotalresult.setTotalFee(rs.getString("TOTAL_FEE")!=null?rs.getString("TOTAL_FEE"):"");
        etnpersonaltotalresult.setAge(rs.getString("AGE")!=null?rs.getString("AGE"):"");
        etnpersonaltotalresult.setJoinDate(rs.getString("JOINDATE")!=null?rs.getString("JOINDATE").substring(0,10):"");
        etnpersonaltotalresult.setDepartment(rs.getString("DEPARTMENT")!=null?rs.getString("DEPARTMENT"):"");
        etnpersonaltotalresult.setPosition(rs.getString("POSITION")!=null?rs.getString("POSITION"):"");
        etnpersonaltotalresult.setPostGroupName(rs.getString("POST_GROUP_NAME")!=null?rs.getString("POST_GROUP_NAME"):"");
        etnpersonaltotalresult.setPostGrade(rs.getString("POST_GRADE")!=null?rs.getString("POST_GRADE"):"");
        etnpersonaltotalresult.setFinalDegree(rs.getString("FINAL_DEGREE")!=null?rs.getString("FINAL_DEGREE"):"");
        etnpersonaltotalresult.setSex(rs.getString("SEX")!=null?rs.getString("SEX"):"");
        etnpersonaltotalresult.setDob(rs.getString("DOB")!=null?rs.getString("DOB"):"");
        etnpersonaltotalresult.setIdCardNO(rs.getString("IDCARD_NO")!=null?rs.getString("IDCARD_NO"):"");
        etnpersonaltotalresult.setIdCardAddr(rs.getString("IDCARD_ADDR")!=null?rs.getString("IDCARD_ADDR"):"");
        etnpersonaltotalresult.setPassportNO(rs.getString("PASSPORT_NO")!=null?rs.getString("PASSPORT_NO"):"");
        etnpersonaltotalresult.setVisaDate(rs.getString("VISA_DATE")!=null?rs.getString("VISA_DATE"):"");
        //etnpersonaltotalresult.setInstitutionName(rs.getString("INSTITUTION_NAME")!=null?rs.getString("INSTITUTION_NAME"):"");
        //etnpersonaltotalresult.setSubject(rs.getString("SUBJECT")!=null?rs.getString("SUBJECT"):"");
        etnpersonaltotalresult.setSeqProtocolEtnCode(rs.getString("SEQ_PROTOCOL_ETN")!=null?rs.getString("SEQ_PROTOCOL_ETN"):"");
        etnpersonaltotalresult.setProtocolCode(rs.getString("PROTOCOL_CODE")!=null?rs.getString("PROTOCOL_CODE"):"");
        etnpersonaltotalresult.setProtocolName(rs.getString("PROTOCOL_NAME")!=null?rs.getString("PROTOCOL_NAME"):"");
        etnpersonaltotalresult.setProtocolStartDate(rs.getString("PROTOCOL_START_DATE")!=null?rs.getString("PROTOCOL_START_DATE").substring(0,10):"");
        etnpersonaltotalresult.setProtocolEndDate(rs.getString("PROTOCOL_END_DATE")!=null?rs.getString("PROTOCOL_END_DATE").substring(0,10):"");
        etnpersonaltotalresult.setProtocolTerm(rs.getString("PROTOCOL_TERM")!=null?rs.getString("PROTOCOL_TERM"):"");
        etnpersonaltotalresult.setProtocolContent(rs.getString("PROTOCOL_CONTENT")!=null?rs.getString("PROTOCOL_CONTENT"):"");
        etnpersonaltotalresult.setCountGroup(rs.getString("COUNTGROUP")!=null?rs.getString("COUNTGROUP"):"");
        etnpersonaltotalresult.setPostCode(rs.getString("POST_CODE")!=null?rs.getString("POST_CODE"):"");
        etnpersonaltotalresult.setPost(rs.getString("POST")!=null?rs.getString("POST"):"");
        etnpersonaltotalresult.setWorkStatus(rs.getString("WORKSTATUS")!=null?rs.getString("WORKSTATUS"):"");
        etnpersonaltotalresult.setStatusCode(rs.getString("STATUS_CODE")!=null?rs.getString("STATUS_CODE"):"");

      }
      catch (SQLException ex) {
        ex.printStackTrace();
      }
      return etnpersonaltotalresult;
  }

  public String getEtnPlanNO() {
    return etnPlanNO;
  }
  public void setEtnPlanNO(String etnPlanNO) {
    this.etnPlanNO = etnPlanNO;
  }
  public String getEmpID() {
    return empID;
  }
  public void setEmpID(String empID) {
    this.empID = empID;
  }
  public String getChineseName() {
    return chineseName;
  }
  public void setChineseName(String chineseName) {
    this.chineseName = chineseName;
  }
  public String getSatisfactRate() {
    return satisfactRate;
  }
  public void setSatisfactRate(String satisfactRate) {
    this.satisfactRate = satisfactRate;
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
  public String getContentCode() {
    return contentCode;
  }
  public void setContentCode(String contentCode) {
    this.contentCode = contentCode;
  }
  public String getAppraiseResultCode() {
    return appraiseResultCode;
  }
  public void setAppraiseResultCode(String appraiseResultCode) {
    this.appraiseResultCode = appraiseResultCode;
  }
  public String getPlanEduItemTypeCode() {
    return planEduItemTypeCode;
  }
  public void setPlanEduItemTypeCode(String planEduItemTypeCode) {
    this.planEduItemTypeCode = planEduItemTypeCode;
  }
  public String getPlanEduItemTypeName() {
    return planEduItemTypeName;
  }
  public void setPlanEduItemTypeName(String planEduItemTypeName) {
    this.planEduItemTypeName = planEduItemTypeName;
  }
  public String getPlanClassType() {
    return planClassType;
  }
  public void setPlanClassType(String planClassType) {
    this.planClassType = planClassType;
  }
  public String getPlanPerriorCount() {
    return planPerriorCount;
  }
  public void setPlanPerriorCount(String planPerriorCount) {
    this.planPerriorCount = planPerriorCount;
  }
  public String getPlanEduTypeCode() {
    return planEduTypeCode;
  }
  public void setPlanEduTypeCode(String planEduTypeCode) {
    this.planEduTypeCode = planEduTypeCode;
  }
  public String getPlanEduTypeName() {
    return planEduTypeName;
  }
  public void setPlanEduTypeName(String planEduTypeName) {
    this.planEduTypeName = planEduTypeName;
  }
  public String getPlanEduPlace() {
    return planEduPlace;
  }
  public void setPlanEduPlace(String planEduPlace) {
    this.planEduPlace = planEduPlace;
  }
  public String getPlanStartDate() {
    return planStartDate;
  }
  public void setPlanStartDate(String planStartDate) {
    this.planStartDate = planStartDate;
  }
  public String getPlanEndDate() {
    return planEndDate;
  }
  public void setPlanEndDate(String planEndDate) {
    this.planEndDate = planEndDate;
  }
  public String getPlanEduTime() {
    return planEduTime;
  }
  public void setPlanEduTime(String planEduTime) {
    this.planEduTime = planEduTime;
  }
  public String getPlanColor() {
    return planColor;
  }
  public void setPlanColor(String planColor) {
    this.planColor = planColor;
  }
  public String getPlanAppraiseContent() {
    return planAppraiseContent;
  }
  public void setPlanAppraiseContent(String planAppraiseContent) {
    this.planAppraiseContent = planAppraiseContent;
  }
  public String getPlanCode() {
    return planCode;
  }
  public void setPlanCode(String planCode) {
    this.planCode = planCode;
  }
  public String getTeachTextFee() {
    return teachTextFee;
  }
  public void setTeachTextFee(String teachTextFee) {
    this.teachTextFee = teachTextFee;
  }
  public String getGroudFee() {
    return groudFee;
  }
  public void setGroudFee(String groudFee) {
    this.groudFee = groudFee;
  }
  public String getEattingFee() {
    return eattingFee;
  }
  public void setEattingFee(String eattingFee) {
    this.eattingFee = eattingFee;
  }
  public String getQuarterFee() {
    return quarterFee;
  }
  public void setQuarterFee(String quarterFee) {
    this.quarterFee = quarterFee;
  }
  public String getTrafficFee() {
    return trafficFee;
  }
  public void setTrafficFee(String trafficFee) {
    this.trafficFee = trafficFee;
  }
  public String getPassportFee() {
    return passportFee;
  }
  public void setPassportFee(String passportFee) {
    this.passportFee = passportFee;
  }
  public String getVisaFee() {
    return visaFee;
  }
  public void setVisaFee(String visaFee) {
    this.visaFee = visaFee;
  }
  public String getInsureFee() {
    return insureFee;
  }
  public void setInsureFee(String insureFee) {
    this.insureFee = insureFee;
  }
  public String getLivingFee() {
    return livingFee;
  }
  public void setLivingFee(String livingFee) {
    this.livingFee = livingFee;
  }
  public String getOtherFee() {
    return otherFee;
  }
  public void setOtherFee(String otherFee) {
    this.otherFee = otherFee;
  }
  public String getCountTeacherFee() {
    return countTeacherFee;
  }
  public void setCountTeacherFee(String countTeacherFee) {
    this.countTeacherFee = countTeacherFee;
  }
  public String getTotalFee() {
    return totalFee;
  }
  public void setTotalFee(String totalFee) {
    this.totalFee = totalFee;
  }
  public String getAge() {
    return age;
  }
  public void setAge(String age) {
    this.age = age;
  }
  public String getJoinDate() {
    return joinDate;
  }
  public void setJoinDate(String joinDate) {
    this.joinDate = joinDate;
  }
  public String getDepartment() {
    return department;
  }
  public void setDepartment(String department) {
    this.department = department;
  }
  public String getPosition() {
    return position;
  }
  public void setPosition(String position) {
    this.position = position;
  }
  public String getPostGroupName() {
    return postGroupName;
  }
  public void setPostGroupName(String postGroupName) {
    this.postGroupName = postGroupName;
  }
  public String getPostGrade() {
    return postGrade;
  }
  public void setPostGrade(String postGrade) {
    this.postGrade = postGrade;
  }
  public String getFinalDegree() {
    return finalDegree;
  }
  public void setFinalDegree(String finalDegree) {
    this.finalDegree = finalDegree;
  }
  public String getSex() {
    return sex;
  }
  public void setSex(String sex) {
    this.sex = sex;
  }
  public String getDob() {
    return dob;
  }
  public void setDob(String dob) {
    this.dob = dob;
  }
  public String getIdCardNO() {
    return idCardNO;
  }
  public void setIdCardNO(String idCardNO) {
    this.idCardNO = idCardNO;
  }
  public String getIdCardAddr() {
    return idCardAddr;
  }
  public void setIdCardAddr(String idCardAddr) {
    this.idCardAddr = idCardAddr;
  }
  public String getPassportNO() {
    return passportNO;
  }
  public void setPassportNO(String passportNO) {
    this.passportNO = passportNO;
  }
  public String getVisaDate() {
    return visaDate;
  }
  public void setVisaDate(String visaDate) {
    this.visaDate = visaDate;
  }
  public String getInstitutionName() {
    return institutionName;
  }
  public void setInstitutionName(String institutionName) {
    this.institutionName = institutionName;
  }
  public String getSubject() {
    return subject;
  }
  public void setSubject(String subject) {
    this.subject = subject;
  }
  public String getSeqProtocolEtnCode() {
    return seqProtocolEtnCode;
  }
  public void setSeqProtocolEtnCode(String seqProtocolEtnCode) {
    this.seqProtocolEtnCode = seqProtocolEtnCode;
  }
  public String getProtocolCode() {
    return protocolCode;
  }
  public void setProtocolCode(String protocolCode) {
    this.protocolCode = protocolCode;
  }
  public String getProtocolName() {
    return protocolName;
  }
  public void setProtocolName(String protocolName) {
    this.protocolName = protocolName;
  }
  public String getProtocolStartDate() {
    return protocolStartDate;
  }
  public void setProtocolStartDate(String protocolStartDate) {
    this.protocolStartDate = protocolStartDate;
  }
  public String getProtocolEndDate() {
    return protocolEndDate;
  }
  public void setProtocolEndDate(String protocolEndDate) {
    this.protocolEndDate = protocolEndDate;
  }
  public String getProtocolTerm() {
    return protocolTerm;
  }
  public void setProtocolTerm(String protocolTerm) {
    this.protocolTerm = protocolTerm;
  }
  public String getProtocolContent() {
    return protocolContent;
  }
  public void setProtocolContent(String protocolContent) {
    this.protocolContent = protocolContent;
  }
  public String getCountGroup() {
    return countGroup;
  }
  public void setCountGroup(String countGroup) {
    this.countGroup = countGroup;
  }
  public String getPostCode() {
    return postCode;
  }
  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }
  public String getPost() {
    return post;
  }
  public void setPost(String post) {
    this.post = post;
  }
  public String getWorkStatus() {
    return workStatus;
  }
  public void setWorkStatus(String workStatus) {
    this.workStatus = workStatus;
  }
  public String getStatusCode() {
    return statusCode;
  }
  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }
}
