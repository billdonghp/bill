package com.ait.ess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;

public class EssEvResult {
    private static ServiceLocator services;

    public EssEvResult() {
        try {
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException ex) {
        }
    }

    private String empid;

    private Hashtable master;

    private String empName;

    private String evSeasonCode;

    private Hashtable evProcessName;

    private String evFinal;

    private String evScore;

    private String evSdate;

    private String evEdate;

    private Hashtable masterAndProcess;

    public String getEmpid() {
        return this.empid;
    }

    public Hashtable getMaster() {
        return this.master;
    }

    public String getEmpName() {
        return this.empName;
    }

    public String getEvSeasonCode() {
        return this.evSeasonCode;
    }

    public Hashtable getEvProcessName() {
        return this.evProcessName;
    }

    public String getEvFinal() {
        return this.evFinal;
    }

    public String getEvScore() {
        return this.evScore;
    }

    public String getEvSdate() {
        return this.evSdate;
    }

    public String getEvEdate() {
        return this.evEdate;
    }

    public Hashtable getMasterAndProcess() {
        return this.masterAndProcess;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public void setMaster(Hashtable master) {
        this.master = master;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setEvSeasonCode(String evSeasonCode) {
        this.evSeasonCode = evSeasonCode;
    }

    public void setEvProcessName(Hashtable evProcessName) {
        this.evProcessName = evProcessName;
    }

    public void setEvFinal(String evFinal) {
        this.evFinal = evFinal;
    }

    public void setEvScore(String evScore) {
        this.evScore = evScore;
    }

    public void setEvSdate(String evSdate) {
        this.evSdate = evSdate;
    }

    public void setEvEdate(String evEdate) {
        this.evEdate = evEdate;
    }

    public void setMasterAndProcess(Hashtable masterAndProcess) {
        this.masterAndProcess = masterAndProcess;
    }

    public Vector getEvResultByEmp(String Empid) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Vector v = null;
        String sql = " select distinct " + " ev_season_code," + " ev_final,"
                + " ev_mark," + " sdate," + " edate "
                + " from ess_ev_result_v where empid = ?";
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, Empid);
            rs = ps.executeQuery();
            while (rs.next()) {
                v = new Vector();
                this.setEvSeasonCode(rs.getString("ev_season_code"));
                this.setEvFinal(rs.getString("ev_final"));
                this.setEvScore(rs.getString("ev_mark"));
                this.setEvSdate(rs.getString("sdate"));
                this.setEvEdate(rs.getString("edate"));
                this.setMasterAndProcess(EssEvResult.getMasterAndProcess(rs.getString("ev_season_code"), Empid));
            }
            return v;
        } catch (Exception ex) {
            Logger.getLogger(getClass()).debug(
                    "getEvResultByEmp=" + ex.getMessage());
            return null;
        } finally {
        	SqlUtil.close(rs, ps, con);
        }
    }

    public static Hashtable getMasterAndProcess(String season, String empid) {
        Hashtable a = new Hashtable();
        Hashtable b = new Hashtable();
        Hashtable c = new Hashtable();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " select "
                + " master,"
                + " ev_process_name "
                + " from ess_ev_result_v where empid = ? and ev_season_code = ?";
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, empid);
            ps.setString(2, season);
            rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                a.put(i + "", rs.getString("master"));
                b.put(i + "", rs.getString("ev_process_name"));
                i += 1;
            }
            c.put("master", a);
            c.put("ev_process_name", b);
            return c;
        } catch (Exception ex) {
            Logger.getLogger("getMasterAndProcess").debug("getMasterAndProcess=" + ex.getMessage());
            return null;
	    } finally {
	    	SqlUtil.close(rs, ps, con);
	    }

    }

}