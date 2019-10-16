package com.ait.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

public class PageControl {
  public int intPageSize; //鞫刻\uB3E8\uC158\uCA4C?b
  public int RowCount; //\uC158\uCA4C???b
  public int intPageCount; //??女?b
  public int intPage; //\uB364鞫刻女\uCBE4
  public int i; //\uD624\uB3E4\uB364鞫刻女\uCBE4
  public int subsum;//\uCCBC女鞫刻\uB3E8\uC82F\uC308?b，\uCE75\uD6F0角10\uBAB8
  public int sumsum;//\uBB3E唐\uC12F\uB315女
  private int requestbig;//\uB2D2女\uCDA9\uC308??\uB3E8\uB315女\uCDA9\uAE34\uC886

  private static ServiceLocator services;
  public PageControl() {
    this.intPageSize = 1; //?枇???女鞫刻\uB3E8\uC158\uCA4C?b
    this.subsum=10;
  }
  public PageControl(int pagesize,int subpage){
    this.intPageSize=pagesize;
    this.subsum=subpage;
  }
  public int setintPage(int strPage,int bigpage) { //?枇?\uB364鞫刻女\uCBE4
    if (strPage == 0 && bigpage==1) {
      this.intPage = 1; //\uAE4A\uCE20瞳QueryString??\uCCAD唐page?H??\uBAB8\uAF5D?b，\uB2AA珂鞫刻\uB4A4??女?b\uC574
    }
    if(strPage>0){
        this.intPage =strPage;
    }
    if(strPage==0 && bigpage>1){
      this.intPage =(bigpage-1)*10+1;
    }
    return intPage;
  }
  public void setRowCount(int rowCount) {
    this.RowCount = rowCount;
  }
  public void setRowCount(String tablename) throws Exception  { //\uC0BF\uD624\uC158\uCA4C???b
    Connection conn = Conn(); 			//\uC254\uC811\uC82F\uC308
    Statement stmt = conn.createStatement();; 	//\uC254\uC811\uAF5D?b??\uCE20
    ResultSet rs=null;
    String sql = "select count(*) from " + tablename;
    try {
      rs = stmt.executeQuery(sql);
      rs.next(); //\uF95F蕨\uB4A4???S\uC158\uCA4C
      RowCount = rs.getInt(1); //\uC0BF\uD624\uC158\uCA4C???b
      setintPageCount();//\uC158炬??女?b
      setSumSum();//\uC155炬??\uBB3E唐\uC12F\uB315女
    }
    catch (SQLException se) {
      System.out.println("SQLException in getFriends() of FriendBean: " +
                         se.getMessage());
      System.out.println("SQL :" + sql);
    }
    finally{
      SqlUtil.close(rs,stmt,conn);
    }
  }

  private void setintPageCount() { ////\uC158炬??女?b
    this.intPageCount = (this.RowCount + this.intPageSize - 1) /
        this.intPageSize;
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
  private void setSumSum(){
  int temp=(intPageCount+subsum-1)/subsum;
  this.sumsum=temp;
  }
  public boolean isNextBig(int bigpage){
    boolean flag=false;
    if(bigpage+1<=this.sumsum){
      flag=true;
      return flag;
    }
    return flag;
  }
  public boolean isforward(int bigpage){
    boolean flag=false;
    if(bigpage-1>=1){
      flag=true;
      return flag;
    }
    return flag;
  }
  public int getI(){
    return this.i;
  }
  public int getIntPagedSize(){
    return this.intPageSize;
  }
  public boolean getNowSmall(int strpage,int big){//\uD138\uB664\uCEF4硅鬼女\uBD40鞫刻
    boolean flag=false;
    if(big==1){
      if(strpage<=this.intPageCount){
        flag=true;
      }
    }
    if(big>1){
      if((strpage+(big-1)*10)<=this.intPageCount){
        flag=true;
      }
    }
    return flag;
  }
  public int getTmpBig(String big){
    if(big==null||big.equals("")){
      big="1";
    }
    this.requestbig=NumberUtil.parseInt(big);
    return this.requestbig;
  }
  public int getTmpSmall(String strpage,int bigpage){
  if(bigpage==1){
    if(strpage==null ||strpage.equals("")){
      this.requestbig=1;
    }
    else{
      this.requestbig=NumberUtil.parseInt(strpage);
    }
  }
  if(bigpage>1){
    if(strpage==null ||strpage.equals("")){
      this.requestbig=(bigpage-1)*10+1;
    }
    else{
      this.requestbig=(bigpage-1)*10+NumberUtil.parseInt(strpage);
    }
  }
  return this.requestbig;
 }
 public int getListSmall(int strpage,int bigpage){
   int a=0;
   if(bigpage==1){
     a=strpage;
   }
   if(bigpage>1){
     a=(bigpage-1)*10+strpage;
   }
   return a;
 }
 public Connection Conn() throws Exception {
   try{
     services = ServiceLocator.getInstance();
     return services.getConnection();
   }catch (ServiceLocatorException e){
     e.printStackTrace();
   }
   return null;
}
  /**
   *  动态连接数据库所需函数
   * @param tablename
   * @param m_session
   * @throws java.lang.Exception
   */
  public void setRowCount(String tablename,HttpSession m_session) throws Exception  { //\uC0BF\uD624\uC158\uCA4C???b
    Connection conn = Conn(); 			//\uC254\uC811\uC82F\uC308
    Statement stmt = conn.createStatement();; 	//\uC254\uC811\uAF5D?b??\uCE20
    ResultSet rs=null;
    String sql = "select count(*) from " + tablename;
    //System.out.println("count sql="+sql);
    try {
      rs = stmt.executeQuery(sql);
      rs.next(); //\uF95F蕨\uB4A4???S\uC158\uCA4C
      RowCount = rs.getInt(1); //\uC0BF\uD624\uC158\uCA4C???b
      //System.out.println("RowCount="+RowCount);
      setintPageCount();//\uC158炬??女?b
      setSumSum();//\uC155炬??\uBB3E唐\uC12F\uB315女
    }
    catch (SQLException se) {
      System.out.println("SQLException in getFriends() of FriendBean: " +
                         se.getMessage());
      System.out.println("SQL :" + sql);
    }
    finally{
      if (rs != null) { rs.close(); }//\uBC11\uADE0?b\uC574\uC11E
      if (stmt != null) { stmt.close(); }//\uBC11\uADE0\uAF5D?b??\uCE20
      if (conn != null) { conn.close(); }//\uBC11\uADE0\uC82F\uC308
    }
  }

}
