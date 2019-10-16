/*
 * 创建日期 2005-8-10
 * 
 * Company: AIT
 * 
 * @author QING
 * @version 1.0
 */
package com.ait.evs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;

/**
 * @author AIT Administrator
 */
@SuppressWarnings("unchecked")
public class Constants {

    //评价操作方式
    public final static String EVOPERATE001 = "EVOPERATE001";//操作方式为内容输入

    public final static String EVOPERATE006 = "EVOPERATE006";//操作方式为相对化

    public final static String EVOPERATE003 = "EVOPERATE003";//操作方式为项目打分

    public final static String EVOPERATE005 = "EVOPERATE005";//操作方式为人员评分
    
    public final static String EVOPERATE008 = "EVOPERATE008";//操作方式为等级确认
    
    public final static String EVOPERATE009 = "EVOPERATE009";//操作方式为评分且默认
    
    //评价流程
    public final static String EVPROCESS008 = "EVPROCESS008";//流程TEAM内一次相对化

    public final static String EVPROCESS015 = "EVPROCESS015";//流程相对化
    
    public final static String EVPROCESS009="EVPROCESS009";//人事确认
    
    public final static String EVPROCESS001="EVPROCESS001";//目标设定
    
    public final static String EVPROCESS006="EVPROCESS006";//一次评价
    
    public final static String EVPROCESS007="EVPROCESS007";//二次评价
	
    //评价项目
    public final static String EVITEM005="EVITEM005";//职员培养
    
    public final static String EVITEM001="EVITEM001";//业务目标
    
    public final static String EVCOLUMN011="EVCOLUMN011";//工号列
    
    public final static String EVCOLUMN012="EVCOLUMN012";//名字
    
    public final static String EVOTHERITEM="('EVITEM001','EVITEM006','EVITEM007')"; 
    
    static List lEvsEmpGrade = new Vector();//TEAM内一次相对化员工等级比例
    public static Map<String ,String> mPosition = new HashMap();//员工职位
    public static Map<String ,String> mPositionCondition = new HashMap();//员工职位条件
    static {
        lEvsEmpGrade
                .add(0, new EvsDeptRadio("", "", "", "EVEMPGRADE001", 0.25f));
        lEvsEmpGrade
                .add(1, new EvsDeptRadio("", "", "", "EVEMPGRADE003", 0.5f));
        lEvsEmpGrade
                .add(2, new EvsDeptRadio("", "", "", "EVEMPGRADE005", 0.25f));
        
        mPosition.put("P1", "部长");
	mPosition.put("P2", "科长");
	mPosition.put("P3", "主任");
	mPosition.put("P4", "一般");
	
	mPositionCondition.put("P1", "'PositionCode130','PositionCode150','PositionCode160','PositionCode170','PositionCode180','PositionCode190'");
	mPositionCondition.put("P2", "'PositionCode200','PositionCode210','PositionCode215','PositionCode220','PositionCode230','PositionCode235'");
	mPositionCondition.put("P3", "'PositionCode240','PositionCode250','PositionCode260'");
	mPositionCondition.put("P4", "'PositionCode270','PositionCode280','PositionCode285','PositionCode290','PositionCode300','PositionCode310','PositionCode320','PositionCode340','PositionCode350'");
    }
   
    //相对评价期间评价类型的最后一次评价打分流程
    public static String getMarkLastProcess(String evPeriodId, String evTypeId)
            throws DataAccessException {
        String sql = " SELECT * FROM (                        "
                + " SELECT *  FROM EVS_TYPE_PROCESS             "
                + " WHERE EVS_TYPE_PROCESS.ev_period_id=?         "
                + " AND EVS_TYPE_PROCESS.ev_type_id=?            "
                + " AND  EXISTS                            "
                + " (SELECT DISTINCT EVS_RELATION.EV_PROCESS_ID FROM EVS_RELATION      "
                + " WHERE EVS_RELATION.ev_period_id=?            "
                + " AND EVS_RELATION.ev_type_id=?               "
                + " AND EVS_RELATION.EV_OPERATE_ID IN ('EVOPERATE003','EVOPERATE005')   "
                + " AND EVS_RELATION.ev_process_id=EVS_TYPE_PROCESS.ev_process_id)   "
                + " ORDER BY   EVS_TYPE_PROCESS.ev_process_order DESC               "
                + " )                                    " + " WHERE ROWNUM=1";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String evProcessId = "";
        try {
            con = ServiceLocator.getInstance().getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, evPeriodId);
            pstmt.setString(2, evTypeId);
            pstmt.setString(3, evPeriodId);
            pstmt.setString(4, evTypeId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                evProcessId = evProcessId+rs.getString("ev_process_id");
            }
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
            throw new DataAccessException("cant getConnection fail", e);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(
                    "cant get connection for getMarkLastProcess statistics", e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException(
                    "cant get connection for getMarkLastProcess statistics", e);
        } finally {
            SqlUtil.close(rs, pstmt, con);
        }
        return evProcessId;
    }
}