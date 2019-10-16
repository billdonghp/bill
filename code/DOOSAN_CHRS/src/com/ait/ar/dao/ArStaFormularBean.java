package com.ait.ar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArItem;
import com.ait.ar.bean.ArStaFormular;
import com.ait.ar.bean.ArStaItem;
import com.ait.pa.bean.PaDistinctList;
import com.ait.sy.bean.AdminBean;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
import com.ait.web.ApplicationContext;

public class ArStaFormularBean {

	AdminBean admin =ApplicationContext.getTheadLocal();
    //根据编号得到公式信息
    public ArrayList getrollFormula(int item_no) {
        ArrayList rollFormula = null;
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String sql = "SELECT ITEM_NO, FORMULAR_NO, CONDITION,FORMULAR,CONDITION_CN,FORMULAR_CN FROM AR_STA_FORMULAR WHERE ITEM_NO='" + item_no + "'  AND CPNY_ID='"+admin.getCpnyId()+"'  ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            rollFormula = new ArrayList();
            while (rs.next()) {
                ArStaFormular rollformu = new ArStaFormular();
                rollformu.setItem_no(rs.getInt("item_No"));
                rollformu.setFormular_no(rs.getInt("formular_no"));
                rollformu.setCondition(rs.getString("condition"));
                rollformu.setFormular(rs.getString("formular"));
                rollformu.setCondition_cn(rs.getString("condition_cn"));
                rollformu.setFormular_cn(rs.getString("formular_cn"));
                rollFormula.add(rollformu);
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return rollFormula;
    }
    
    public String formatConditionOrFormular(String exchange, List items ,List staitems, List paList)
    {
    	int size = 0 ;
    	String id = "" ;
    	String name = "" ;

    	size = items.size() ;
    	ArItem info = new ArItem();
    	for (int i = 0 ; i < size ; i ++)
    	{
    		info = (ArItem)items.get(i) ;
    		id = "ATT_ITEM." + info.getItemId() ;
    		name = "考勤明细." + info.getItemName() ;
    		if(exchange.indexOf(name) != -1)
    			exchange = exchange.replaceAll(name, id) ;
    	}
    	
    	size = staitems.size() ;
    	ArStaItem ar = new ArStaItem();
    	for (int i = 0 ; i < size ; i ++)
    	{
    		ar = (ArStaItem)staitems.get(i) ;
    		id = "STA_ITEM." + ar.getStaItemId() ;
    		name = "考勤汇总." + ar.getItemName() ;
    		if(exchange.indexOf(name) != -1)
    			exchange = exchange.replaceAll(name, id) ;
    	}
    	
    	size = paList.size() ;
    	PaDistinctList pdl = new PaDistinctList();
    	for (int i = 0 ; i < size ; i ++)
    	{
    		pdl = (PaDistinctList)paList.get(i) ;
    		id = "STA_ITEM." + pdl.getDistinctField() ;
    		name = "考勤汇总." + pdl.getFieldName() ;
    		if(exchange.indexOf(name) != -1)
    			exchange = exchange.replaceAll(name, id) ;
    	}
    	
    	return exchange ;
    }

    //添加
    public int Addformular(ArrayList values) {
        int affRow = 0;
        Connection conn = null;
        PreparedStatement pstate = null;

        String sql = " INSERT INTO AR_STA_FORMULAR(FORMULAR_NO, ITEM_NO, CONDITION, FORMULAR, CONDITION_CN, FORMULAR_CN,CPNY_ID) VALUES ("
                   + " AR_STA_FORMULAR_SEQ.NEXTVAL, ?, ?, ?, ?, ?,?)";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            pstate=conn.prepareStatement(sql);
            pstate.setString(1,values.get(0).toString());
            pstate.setString(2,values.get(1).toString());
            pstate.setString(3,values.get(2).toString());
            pstate.setString(4,values.get(3).toString());
            pstate.setString(5,values.get(4).toString());
            pstate.setString(6,admin.getCpnyId());
            pstate.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(pstate, conn);
        }
        return affRow;
    }

    //根据编号得到信息
    public ArStaFormular getRollformula(int formularNo, int itemNo) {
        ArStaFormular ro = null;
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM AR_STA_FORMULAR WHERE FORMULAR_NO=" + formularNo + " " + "AND ITEM_NO='" + itemNo + "' ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            ro = new ArStaFormular();
            if (rs.next()) {
                ro.setFormular_no(rs.getInt("formular_no"));
                ro.setItem_no(rs.getInt("item_no"));
                ro.setCondition(rs.getString("condition"));
                ro.setFormular(rs.getString("formular"));
                ro.setCondition_cn(rs.getString("condition_cn"));
                ro.setFormular_cn(rs.getString("formular_cn"));
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return ro;
    }

    //更新信息
    public int Editformular(ArrayList values) {
        int affRow = 0;
        Connection conn = null;
        PreparedStatement pstate = null;
        String sql = "UPDATE AR_STA_FORMULAR SET CONDITION=?, FORMULAR=? , CONDITION_CN=?, FORMULAR_CN=? WHERE FORMULAR_NO=? AND ITEM_NO= ?";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            pstate = conn.prepareStatement(sql);
            pstate.setString(1, values.get(0).toString());
            pstate.setString(2, values.get(1).toString());
            pstate.setString(3, values.get(2).toString());
            pstate.setString(4, values.get(3).toString());
            pstate.setString(5, values.get(4).toString());
            pstate.setString(6, values.get(5).toString());
            pstate.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(pstate, conn);
        }
        return affRow;
    }

    //删除信息
    public int Delformular(int formularID) {
        int affRow = 0;
        Connection conn = null;
        Statement state = null;
        String sql = "DELETE FROM AR_STA_FORMULAR " + "WHERE FORMULAR_NO='"
                + formularID + "' ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            affRow = state.executeUpdate(sql);
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(state, conn);
        }
        return affRow;
    }

}
