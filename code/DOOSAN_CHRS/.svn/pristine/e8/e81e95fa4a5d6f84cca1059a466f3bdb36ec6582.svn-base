package com.ait.ar.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArFactoryCalendar;
import com.ait.sy.bean.AdminBean;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
import com.ait.web.ApplicationContext;

public class ArFactoryCalendarBean {
    private ArFactoryCalendar factorycalendar = null;
    private String language;
    
    AdminBean admin=ApplicationContext.getTheadLocal();

    public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public ArrayList getDays(int year, int month) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String arMonth = year + this.getMonth(month);
        String sql = "SELECT * " + "FROM AR_CALENDER "
                + "WHERE (IYEAR || CASE WHEN IMONTH < 10 THEN '0' || IMONTH ELSE TO_CHAR(IMONTH) END) ='" + arMonth + "'  AND CPNY_ID='"+admin.getCpnyId()+"' "
                + "ORDER BY IYEAR, IMONTH, IDAY";
        Logger.getLogger(getClass()).debug(sql);
        ArrayList days = null;
        String DDate = null;
        try {
            days = new ArrayList();
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                ArFactoryCalendar factory = new ArFactoryCalendar();
                factory.setYear(rs.getInt("iyear"));
                factory.setMonth(rs.getInt("imonth"));
                factory.setDay(rs.getInt("iday"));
                DDate = rs.getInt("iyear") + "-" + rs.getInt("imonth") + "-"
                        + rs.getInt("iday");
                factory.setDDATE(DDate);
                factory.setDayColor(this.getDayColor(rs.getInt("iday"), rs
                        .getInt("iweek")));
                factory.setWeek(rs.getInt("iweek"));
                factory.setWeekName(this.getweekName(rs.getInt("iweek")));
                factory.setWorkdayflag(rs.getInt("workdayflag"));
                factory.setWorkdayflagName(this.getWorkName(rs
                        .getInt("workdayflag")));
                factory.setType(rs.getInt("typeid"));
                factory
                        .setTypeName(this.getTypeName(rs.getInt("typeid")) != null ? this
                                .getTypeName(rs.getInt("typeid"))
                                : "");
                factory.setOrverTYPEID(rs.getInt("overtypeid"));
                factory.setTypeID_DEFAULT(rs.getInt("typeid_default"));
                factory.setShift_no(rs.getInt("shift_no"));
                factory.setShift_name(this.getWorkShitName(rs
                        .getInt("shift_no")) != null ? this.getWorkShitName(rs
                        .getInt("shift_no")) : "");
                days.add(factory);
            }
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return days;
    }

    public String getweekName(int i) {
        String weekName = null;
        switch (i) {
        case 0:
            weekName = "<font color=red>日</font>";
            break;
        case 1:
            weekName = "一";
            break;
        case 2:
            weekName = "二";
            break;
        case 3:
            weekName = "三";
            break;
        case 4:
            weekName = "四";
            break;
        case 5:
            weekName = "五";
            break;
        case 6:
            weekName = "<font color=red>六</font>";
            break;
        }
        
        return weekName;
    }

    public String getWorkName(int i) {
    	if(this.getLanguage().equals("zh")){
    		if (i == 0)
                return "不工作";
            else
                return "工作";
    	}else if(this.getLanguage().equals("ko")){
    		
    	}else{
    		if (i == 0)
                return "Rest Day";
            else
                return "Work Day";
    	}
    	return "";
    }

    /**
     * get shift name
     * 
     * @param i
     *            int
     * @return String
     */
    public String getWorkShitName(int shift_no) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String shiftName = null;
        
        String sql = "select shift_name,shift_en_name,shift_kor_name from AR_SHIFT010 where shift_no = "
                + shift_no;
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            if (rs.next())
            	if(this.getLanguage().equals("zh")){
                    shiftName = rs.getString("shift_name");            		
            	}else if(this.getLanguage().equals("ko")){
            		shiftName = rs.getString("shift_kor_name");
            	}else{
            		shiftName = rs.getString("shift_en_name");
            	}
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return shiftName;
    }

    public String getTypeName(int typeid) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String sql = "select typename,type_en_name,type_kor_name " + "from AR_CALENDER_TYPE where typeid="
                + typeid;
        Logger.getLogger(getClass()).debug(sql);
        String typename = null;
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            if (rs.next()) {
            	if(this.getLanguage().equals("zh")){
            		typename = rs.getString("typename");
            	}else if(this.getLanguage().equals("ko")){
            		typename = rs.getString("type_kor_name");
            	}else{
            		typename = rs.getString("type_en_name");
            	}
            }
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return typename;
    }

    public String getCalendar(int year, int month, int type) {

        ArrayList days = this.getDays(year, month);
        if (days.size() <= 0)
            return null;
        // 先刷出来第1行表
        String temp = null;
        if (type == 0) {
            temp = this.getFrist(days);
        } else {
            temp = this.getFristEdit(days);
        }

        int out = Integer.parseInt(temp.substring(temp.lastIndexOf("*") + 1,
                temp.length()));
        String frist = temp.substring(0, temp.lastIndexOf("*"));
        String Default = null;
        if (type == 0) {
            Default = this.getDefault(out, days);
        } else {
            Default = this.getDefaultEdit(out, days);
        }
        return frist + Default;
    }

    public String getFrist(ArrayList days) {
        ArrayList workid = new ArrayList();
        ArrayList workname = new ArrayList();
        workid.add(new Integer(0));
        workid.add(new Integer(1));
        workname.add("不工作");
        workname.add("工作");

        // ArrayList types = this.getTypes();
        // ArrayList typeid = (ArrayList) types.get(0);
        // ArrayList typename = (ArrayList) types.get(1);
        // String check = null;

        String frist = "";
        int out = 0;
        ArFactoryCalendar factorycalendar1 = null;
        // 得到第1天是星期几
        factorycalendar = (ArFactoryCalendar) days.get(0);
        // 如果第1天是星期日
        if (factorycalendar.getWeek() == 0) {
            for (int i = 0; i < 7; i++) {
                factorycalendar1 = (ArFactoryCalendar) days.get(i);
                frist += "<td height='50' valign='top' onclick=editShow('"
                        + factorycalendar1.getDay() + "')>";
                frist += "<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
                frist += "<tr>";
                frist += "<td colspan='2'>" + factorycalendar1.getDayColor()
                        + "</td></tr>";
                frist += "<tr><td height='18'></td>&nbsp;&nbsp;<td height='18'>";
                frist += factorycalendar1.getWorkdayflagName() + "</td></tr>";

                frist += "<tr><td height='18'>";
                frist += factorycalendar1.getTypeName()
                        + "</td>&nbsp;&nbsp;<td height='18'>";
                frist += factorycalendar1.getShift_name() + "</td></tr>";
                frist += "</table></td>";
            }
            out = 7;
        }
        if (factorycalendar.getWeek() == 1) {
            frist += "<td>&nbsp;</td>";
            for (int i = 0; i < 6; i++) {
                factorycalendar1 = (ArFactoryCalendar) days.get(i);
                frist += "<td height='50' valign='top' onclick=editShow('"
                        + factorycalendar1.getDay() + "')>";
                frist += "<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
                frist += "<tr>";
                frist += "<td colspan='2'>" + factorycalendar1.getDayColor()
                        + "</td></tr>";
                frist += "<tr><td height='18'></td>&nbsp;&nbsp;<td height='18'>";
                frist += factorycalendar1.getWorkdayflagName() + "</td></tr>";

                frist += "<tr><td height='18'>";
                frist += factorycalendar1.getTypeName()
                        + "</td>&nbsp;&nbsp;<td height='18'>";
                frist += factorycalendar1.getShift_name() + "</td></tr>";
                frist += "</table></td>";

            }
            out = 6;
        }

        if (factorycalendar.getWeek() == 2) {
            frist += "<td>&nbsp;</td><td>&nbsp;</td>";
            for (int i = 0; i < 5; i++) {
                factorycalendar1 = (ArFactoryCalendar) days.get(i);
                frist += "<td height='50' valign='top' onclick=editShow('"
                        + factorycalendar1.getDay() + "')>";
                frist += "<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
                frist += "<tr>";
                frist += "<td colspan='2'>" + factorycalendar1.getDayColor()
                        + "</td></tr>";
                frist += "<tr><td height='18'></td>&nbsp;&nbsp;<td height='18'>";
                frist += factorycalendar1.getWorkdayflagName() + "</td></tr>";

                frist += "<tr><td height='18'>";
                frist += factorycalendar1.getTypeName()
                        + "</td>&nbsp;&nbsp;<td height='18'>";
                frist += factorycalendar1.getShift_name() + "</td></tr>";
                frist += "</table></td>";
            }
            out = 5;
        }
        if (factorycalendar.getWeek() == 3) {
            frist += "<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>";
            for (int i = 0; i < 4; i++) {
                factorycalendar1 = (ArFactoryCalendar) days.get(i);
                frist += "<td height='50' valign='top' onclick=editShow('"
                        + factorycalendar1.getDay() + "')>";
                frist += "<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
                frist += "<tr>";
                frist += "<td colspan='2'>" + factorycalendar1.getDayColor()
                        + "</td></tr>";
                frist += "<tr><td height='18'></td>&nbsp;&nbsp;<td height='18'>";
                frist += factorycalendar1.getWorkdayflagName() + "</td></tr>";

                frist += "<tr><td height='18'>";
                frist += factorycalendar1.getTypeName()
                        + "</td>&nbsp;&nbsp;<td height='18'>";
                frist += factorycalendar1.getShift_name() + "</td></tr>";
                frist += "</table></td>";
            }
            out = 4;
        }
        if (factorycalendar.getWeek() == 4) {
            frist += "<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>";
            for (int i = 0; i < 3; i++) {
                factorycalendar1 = (ArFactoryCalendar) days.get(i);
                frist += "<td height='50' valign='top' onclick=editShow('"
                        + factorycalendar1.getDay() + "')>";
                frist += "<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
                frist += "<tr>";
                frist += "<td colspan='2'>" + factorycalendar1.getDayColor()
                        + "</td></tr>";
                frist += "<tr><td height='18'></td>&nbsp;&nbsp;<td height='18'>";
                frist += factorycalendar1.getWorkdayflagName() + "</td></tr>";

                frist += "<tr><td height='18'>";
                frist += factorycalendar1.getTypeName()
                        + "</td>&nbsp;&nbsp;<td height='18'>";
                frist += factorycalendar1.getShift_name() + "</td></tr>";
                frist += "</table></td>";
            }
            out = 3;
        }
        if (factorycalendar.getWeek() == 5) {
            frist += "<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>";
            for (int i = 0; i < 2; i++) {
                factorycalendar1 = (ArFactoryCalendar) days.get(i);
                frist += "<td height='50' valign='top' onclick=editShow('"
                        + factorycalendar1.getDay() + "')>";
                frist += "<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
                frist += "<tr>";
                frist += "<td colspan='2'>" + factorycalendar1.getDayColor()
                        + "</td></tr>";
                frist += "<tr><td height='18'></td>&nbsp;&nbsp;<td height='18'>";
                frist += factorycalendar1.getWorkdayflagName() + "</td></tr>";

                frist += "<tr><td height='18'>";
                frist += factorycalendar1.getTypeName()
                        + "</td>&nbsp;&nbsp;<td height='18'>";
                frist += factorycalendar1.getShift_name() + "</td></tr>";
                frist += "</table></td>";
            }
            out = 2;
        }
        if (factorycalendar.getWeek() == 6) {
            frist += "<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>";
            for (int i = 0; i < 1; i++) {
                factorycalendar1 = (ArFactoryCalendar) days.get(i);
                frist += "<td height='50' valign='top' onclick=editShow('"
                        + factorycalendar1.getDay() + "')>";
                frist += "<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
                frist += "<tr>";
                frist += "<td colspan='2'>" + factorycalendar1.getDayColor()
                        + "</td></tr>";
                frist += "<tr><td height='18'></td>&nbsp;&nbsp;<td height='18'>";
                frist += factorycalendar1.getWorkdayflagName() + "</td></tr>";

                frist += "<tr><td height='18'>";
                frist += factorycalendar1.getTypeName()
                        + "</td>&nbsp;&nbsp;<td height='18'>";
                frist += factorycalendar1.getShift_name() + "</td></tr>";
                frist += "</table></td>";

            }
            out = 1;
        }
        return "<tr>" + frist + "</tr>*" + out;
    }

    //
    public String getFristEdit(ArrayList days) {
        ArrayList workid = new ArrayList();
        ArrayList workname = new ArrayList();
        workid.add(new Integer(0));
        workid.add(new Integer(1));
        workname.add("不工作");
        workname.add("工作");

        // ArrayList types = this.getTypes();
        // ArrayList typeid = (ArrayList) types.get(0);
        // ArrayList typename = (ArrayList) types.get(1);
        // String check = null;

        String frist = "";
        int out = 0;
        ArFactoryCalendar factorycalendar1 = null;
        // 得到第1天是星期几
        factorycalendar = (ArFactoryCalendar) days.get(0);
        // 如果第1天是星期日
        if (factorycalendar.getWeek() == 0) {
            for (int i = 0; i < 7; i++) {
                factorycalendar1 = (ArFactoryCalendar) days.get(i);
                frist += "<td height='50' valign='top'>";
                frist += "<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
                frist += "<tr><td><input name=\"day"
                        + factorycalendar1.getDay()
                        + "\" type= \"checkbox\" class=\"check\"/></td></tr>";
                frist += "<tr>";
                frist += "<td><strong>" + factorycalendar1.getDayColor()
                        + "</strong></td>";
                frist += "<td height='18'>";
                frist += factorycalendar1.getWorkdayflagName() + "</td></tr>";

                frist += "<tr><td height='18'>";
                frist += factorycalendar1.getTypeName()
                        + "</td>&nbsp;&nbsp;<td height='18'>";
                frist += factorycalendar1.getShift_name() + "</td></tr>";
                frist += "</table></td>";
            }
            out = 7;
        }
        if (factorycalendar.getWeek() == 1) {
            frist += "<td>&nbsp;</td>";
            for (int i = 0; i < 6; i++) {
                factorycalendar1 = (ArFactoryCalendar) days.get(i);
                frist += "<td height='50' valign='top' >";
                frist += "<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
                frist += "<tr><td><input name=\"day"
                        + factorycalendar1.getDay()
                        + "\" type= \"checkbox\" class=\"check\"/></td></tr>";
                frist += "<tr>";
                frist += "<td><strong>" + factorycalendar1.getDayColor()
                        + "</strong></td>";
                frist += "<td height='18'>";
                frist += factorycalendar1.getWorkdayflagName() + "</td></tr>";

                frist += "<tr><td height='18'>";
                frist += factorycalendar1.getTypeName()
                        + "</td>&nbsp;&nbsp;<td height='18'>";
                frist += factorycalendar1.getShift_name() + "</td></tr>";
                frist += "</table></td>";

            }
            out = 6;
        }

        if (factorycalendar.getWeek() == 2) {
            frist += "<td>&nbsp;</td><td>&nbsp;</td>";
            for (int i = 0; i < 5; i++) {
                factorycalendar1 = (ArFactoryCalendar) days.get(i);
                frist += "<td height='50' valign='top' >";
                frist += "<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
                frist += "<tr><td><input name=\"day"
                        + factorycalendar1.getDay()
                        + "\" type= \"checkbox\" class=\"check\"/></td></tr>";
                frist += "<tr>";
                frist += "<td><strong>" + factorycalendar1.getDayColor()
                        + "</strong></td>";
                frist += "<td height='18'>";
                frist += factorycalendar1.getWorkdayflagName() + "</td></tr>";

                frist += "<tr><td height='18'>";
                frist += factorycalendar1.getTypeName()
                        + "</td>&nbsp;&nbsp;<td height='18'>";
                frist += factorycalendar1.getShift_name() + "</td></tr>";
                frist += "</table></td>";
            }
            out = 5;
        }
        if (factorycalendar.getWeek() == 3) {
            frist += "<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>";
            for (int i = 0; i < 4; i++) {
                factorycalendar1 = (ArFactoryCalendar) days.get(i);
                frist += "<td height='50' valign='top' >";
                frist += "<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
                frist += "<tr><td><input name=\"day"
                        + factorycalendar1.getDay()
                        + "\" type= \"checkbox\" class=\"check\"/></td></tr>";
                frist += "<tr>";
                frist += "<td><strong>" + factorycalendar1.getDayColor()
                        + "</strong></td>";
                frist += "<td height='18'>";
                frist += factorycalendar1.getWorkdayflagName() + "</td></tr>";

                frist += "<tr><td height='18'>";
                frist += factorycalendar1.getTypeName()
                        + "</td>&nbsp;&nbsp;<td height='18'>";
                frist += factorycalendar1.getShift_name() + "</td></tr>";
                frist += "</table></td>";
            }
            out = 4;
        }
        if (factorycalendar.getWeek() == 4) {
            frist += "<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>";
            for (int i = 0; i < 3; i++) {
                factorycalendar1 = (ArFactoryCalendar) days.get(i);
                frist += "<td height='50' valign='top' >";
                frist += "<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
                frist += "<tr><td><input name=\"day"
                        + factorycalendar1.getDay()
                        + "\" type= \"checkbox\" class=\"check\"/></td></tr>";
                frist += "<tr>";
                frist += "<td><strong>" + factorycalendar1.getDayColor()
                        + "</strong></td>";

                frist += "<tr><td height='18'>";
                frist += factorycalendar1.getWorkdayflagName() + "</td></tr>";
                frist += "<td height='18'>";
                frist += factorycalendar1.getTypeName()
                        + "</td>&nbsp;&nbsp;<td height='18'>";
                frist += factorycalendar1.getShift_name() + "</td></tr>";
                frist += "</table></td>";
            }
            out = 3;
        }
        if (factorycalendar.getWeek() == 5) {
            frist += "<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>";
            for (int i = 0; i < 2; i++) {
                factorycalendar1 = (ArFactoryCalendar) days.get(i);
                frist += "<td height='50' valign='top' >";
                frist += "<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
                frist += "<tr><td><input name=\"day"
                        + factorycalendar1.getDay()
                        + "\" type= \"checkbox\" class=\"check\"/></td></tr>";
                frist += "<tr>";
                frist += "<td><strong>" + factorycalendar1.getDayColor()
                        + "</strong></td>";
                frist += "<td height='18'>";
                frist += factorycalendar1.getWorkdayflagName() + "</td></tr>";

                frist += "<tr><td height='18'>";
                frist += factorycalendar1.getTypeName()
                        + "</td>&nbsp;&nbsp;<td height='18'>";
                frist += factorycalendar1.getShift_name() + "</td></tr>";
                frist += "</table></td>";
            }
            out = 2;
        }
        if (factorycalendar.getWeek() == 6) {
            frist += "<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>";
            for (int i = 0; i < 1; i++) {
                factorycalendar1 = (ArFactoryCalendar) days.get(i);
                frist += "<td height='50' valign='top' >";
                frist += "<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
//                frist += "<tr><td><input name=\"day"
//                        + factorycalendar1.getDay()
//                        + "\" type= \"checkbox\" class=\"check\"/></td></tr>";
                frist += "<tr>";
                frist += "<td><strong>" + factorycalendar1.getDayColor()
                        + "</strong></td>";
                frist += "<td height='18'>";
                frist += factorycalendar1.getWorkdayflagName() + "</td></tr>";

                frist += "<tr><td height='18'>";
                frist += factorycalendar1.getTypeName()
                        + "</td>&nbsp;&nbsp;<td height='18'>";
                frist += factorycalendar1.getShift_name() + "</td></tr>";
                frist += "</table></td>";

            }
            out = 1;
        }
        return "<tr>" + frist + "</tr>*" + out;

    }

    public String getDefault(int out, ArrayList days) {
        ArrayList workid = new ArrayList();
        ArrayList workname = new ArrayList();
        workid.add(new Integer(0));
        workid.add(new Integer(1));
        workname.add("不工作");
        workname.add("工作");

        // / ArrayList types = this.getTypes();
        // ArrayList typeid = (ArrayList) types.get(0);
        // ArrayList typename = (ArrayList) types.get(1);
        // String check = null;

        int r = 0;
        int rows = 0;
        if ((days.size() - out) % 7 != 0) {// 算出 剩余的有几行
            rows = (days.size() - out) / 7 + 1;
        } else {
            rows = (days.size() - out) / 7;
        }
        rows = rows * 7;// 总共多少格子
        rows = rows - (days.size() - out);// 到最后一行剩余几格子
        String Default = "";
        for (int i = out; i < days.size(); i++) {
            factorycalendar = (ArFactoryCalendar) days.get(i);
            if (r == 0 || r == 7 || r == 14 || r == 21 || r == 28) {// 加换行
                Default += "<tr>";
            }

            Default += "<td height='50' valign='top' onclick=editShow('"
                    + factorycalendar.getDay() + "')>";
            Default += "<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
            Default += "<tr>";
            Default += "<td colspan='2'>" + factorycalendar.getDayColor()
                    + "</td></tr>";
            Default += "<tr><td height='18'></td>&nbsp;&nbsp;<td height='18'>";
            Default += factorycalendar.getWorkdayflagName() + "</td></tr>";

            Default += "<tr><td height='18'>";
            Default += factorycalendar.getTypeName()
                    + "</td>&nbsp;&nbsp;<td height='18'>";
            Default += factorycalendar.getShift_name() + "</td></tr>";
            Default += "</table></td>";

            if (r == 6 || r == 13 || r == 20 || r == 27 || r == 34) {// 加换行
                Default += "</tr>";
            }
            r += 1;
        }
        // 补空格
        if (rows > 0) {
            String temp = "";
            for (int i = 0; i < rows; i++) {
                temp += "<td>&nbsp;</td>";
            }
            Default += temp + "</tr>";
        }
        return Default;
    }

    //
    public String getDefaultEdit(int out, ArrayList days) {
        ArrayList workid = new ArrayList();
        ArrayList workname = new ArrayList();
        workid.add(new Integer(0));
        workid.add(new Integer(1));
        workname.add("不工作");
        workname.add("工作");

        // ArrayList types = this.getTypes();
        // ArrayList typeid = (ArrayList) types.get(0);
        // ArrayList typename = (ArrayList) types.get(1);
        // String check = null;

        int r = 0;
        int rows = 0;
        if ((days.size() - out) % 7 != 0) {// 算出 剩余的有几行
            rows = (days.size() - out) / 7 + 1;
        } else {
            rows = (days.size() - out) / 7;
        }
        rows = rows * 7;// 总共多少格子
        rows = rows - (days.size() - out);// 到最后一行剩余几格子
        String Default = "";
        for (int i = out; i < days.size(); i++) {
            factorycalendar = (ArFactoryCalendar) days.get(i);
            if (r == 0 || r == 7 || r == 14 || r == 21 || r == 28) {// 加换行
                Default += "<tr>";
            }

            Default += "<td height='50' valign='top'>";
            Default += "<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
            Default += "<tr><td><input name=\"day" + factorycalendar.getDay()
                    + "\" type= \"checkbox\" class=\"check\"/></td></tr>";
            Default += "<tr>";
            Default += "<td><strong>" + factorycalendar.getDayColor()
                    + "</strong></td>";
            Default += "<td height='18'>";
            Default += factorycalendar.getWorkdayflagName() + "</td></tr>";

            Default += "<tr><td height='18'>";
            Default += factorycalendar.getTypeName()
                    + "</td>&nbsp;&nbsp;<td height='18'>";
            Default += factorycalendar.getShift_name() + "</td></tr>";
            Default += "</table></td>";

            if (r == 6 || r == 13 || r == 20 || r == 27 || r == 34) {// 加换行
                Default += "</tr>";
            }
            r += 1;
        }
        // 补空格
        if (rows > 0) {
            String temp = "";
            for (int i = 0; i < rows; i++) {
                temp += "<td>&nbsp;</td>";
            }
            Default += temp + "</tr>";
        }
        return Default;

    }

    //
    public String getDayColor(int day, int week) {
        String daycolor = null;
        switch (week) {
        case 0:
            daycolor = "<font color='red'>" + day + "</font>";
            break;
        case 1:
            daycolor = Integer.toString(day);
            break;
        case 2:
            daycolor = Integer.toString(day);
            break;
        case 3:
            daycolor = Integer.toString(day);
            break;
        case 4:
            daycolor = Integer.toString(day);
            break;
        case 5:
            daycolor = Integer.toString(day);
            break;
        case 6:
            daycolor = "<font color='red'>" + day + "</font>";
            break;
        }
        return daycolor;
    }

    public ArrayList getTypes() {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        ArrayList typeid = null;
        ArrayList typeName = null;
        ArrayList types = null;
        String sql = "select typeid, typename from ar_calender_type";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            typeid = new ArrayList();
            typeName = new ArrayList();
            types = new ArrayList();
            while (rs.next()) {
                typeid.add(new Integer(rs.getInt("typeid")));
                typeName.add(rs.getString("typename"));
            }
            types.add(typeid);
            types.add(typeName);
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return types;
    }

    public ArrayList getFormer(int year, int month, String work, String type) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String sql = "select * from ar_calender where iyear=" + year
                + " and imonth=" + month + " order by iday";
        Logger.getLogger(getClass()).debug(sql);
        ArrayList days = new ArrayList();
        int i = 0;
        String DDate = null;
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            String[] works = work.split("-");
            String[] types = type.split("-");
            while (rs.next()) {
                ArFactoryCalendar factory = new ArFactoryCalendar();
                factory.setYear(rs.getInt("iyear"));
                factory.setMonth(rs.getInt("imonth"));
                factory.setDay(rs.getInt("iday"));
                DDate = rs.getInt("iyear") + "-" + rs.getInt("imonth") + "-"
                        + rs.getInt("iday");
                factory.setDDATE(DDate);
                factory.setWeek(rs.getInt("iweek"));
                factory.setWorkdayflag(Integer.parseInt(works[i].toString()));
                factory.setType(Integer.parseInt(types[i].toString()));
                factory.setOrverTYPEID(rs.getInt("overtypeid"));
                factory.setTypeID_DEFAULT(rs.getInt("typeid_default"));
                days.add(factory);
                i++;
            }
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return days;
    }

    /**
     * update single factory calendar
     * 
     * @param values
     * ArrayList @
     */
    public int editFactory(ArrayList values) {
        int affectRow = 0;
        Connection conn = null;
        Statement state = null;
        int year = ((Integer) values.get(0)).intValue();
        int month = ((Integer) values.get(1)).intValue();
        int day = ((Integer) values.get(2)).intValue();
        int work = ((Integer) values.get(3)).intValue();
        int type = ((Integer) values.get(4)).intValue();
        int shift = ((Integer) values.get(5)).intValue();
        String sql = "update ar_calender set " + "workdayflag='" + work + "', "
                + "typeid='" + type + "', " + "shift_no='" + shift + "' "
                + "where iyear='" + year + "' " + "and imonth='" + month + "' "
                + "and iday='" + day + "' ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            affectRow = state.executeUpdate(sql);
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(state, conn);
        }
        return affectRow;
    }

    /**
     * update factory calendar by batch
     * 
     * @param values
     * ArrayList @
     */
    public int editFactoryAll(ArrayList values) {
        int affRowArray[];
        int affRow = 0;
        Connection conn = null;
        Statement state = null;
        ArFactoryCalendar factoryCalendar;
        int year, month, day, work, type, shift;
        String cpnyId=null;
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            for (int i = 0; i < values.size(); i++) {
                factoryCalendar = (ArFactoryCalendar) values.get(i);
                year = factoryCalendar.getYear();
                month = factoryCalendar.getMonth();
                day = factoryCalendar.getDay();
                work = factoryCalendar.getWorkdayflag();
                type = factoryCalendar.getType();
                shift = factoryCalendar.getShift_no();
                cpnyId=factoryCalendar.getCpnyId();
                String sql = "update ar_calender set workdayflag=" + work
                        + ",typeid=" + type + ",shift_no=" + shift
                        + " where iyear=" + year + " and imonth=" + month
                        + " and iday=" + day +" and CPNY_ID='"+cpnyId+"' ";
                Logger.getLogger(getClass()).debug(sql);
                state.addBatch(sql);
            }
            affRowArray = state.executeBatch();
         
            for (int i = affRowArray.length - 1; i >= 0; i--) {
                affRow += affRowArray[i];
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).debug(e.toString());
            affRow = -1;
        } finally {
            SqlUtil.close(state, conn);
        }
        return affRow;
    }

    /**
     * add factory calendar
     * 
     * @param from_dates
     *            String
     * @param to_dates
     *            String
     * @param work_shift
     *            int
     * @param rest_shift
     * int @
     */
    public void addFactory(String from_dates, String to_dates, int work_shift,
            int rest_shift,String cpnyId) {
        Connection conn = null;
        Statement state = null;
        String sql = "{call add_date_to_ca(" + "to_date('" + from_dates
                + "', 'yyyy-mm-dd'), " + "to_date('" + to_dates
                + "','yyyy-mm-dd'), " + work_shift + "," + rest_shift + ",'"+cpnyId+"')} ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            state.execute(sql);
        } catch (SQLException e) {
            Logger.getLogger(getClass()).debug(e.toString());
        } finally {
            SqlUtil.close(state, conn);
        }
    }

    public String getMonth(int month) {
        String temp = Integer.toString(month);
        if (month < 10 && temp.length() < 2)
            temp = "0" + temp;
        return temp;
    }

    public int getEndDay(int year, int month) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        int endday = 0;
        String monthstr = year + this.getMonth(month);
        //String sql = "select ar_end_day('" + monthstr + "') as endday from dual";
        String sql = "SELECT TO_CHAR(LAST_DAY(TO_DATE('" + monthstr + "','YYYYMM')),'DD') AS ENDDAY FROM DUAL" ;
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                endday = rs.getInt("endday");
            }
        } catch (SQLException e) {
        	e.printStackTrace() ;
            Logger.getLogger(getClass()).debug(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return endday;
    }

    public ArrayList getGroupList() {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        ArrayList list = null;
        String sql = "SELECT " + "GROUP_NAME, " + "GROUP_ID, " + "SMONTH "
                + "FROM (" + "SELECT (" + "SELECT GROUP_NAME "
                + "FROM AR_DYNAMIC_GROUP " + "WHERE GROUP_NO= GROUP_ID"
                + ") AS GROUP_NAME, " + "GROUP_ID, "
                + "GET_ARMONTH(DDATE) AS SMONTH " + "FROM AR_CALENDER_GROUP"
                + ")" + "GROUP BY GROUP_NAME , SMONTH, GROUP_ID";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            list = new ArrayList();
            while (rs.next()) {
                ArFactoryCalendar info = new ArFactoryCalendar();
                info.setGroupNo(rs.getInt("group_id"));
                info.setGroupName(rs.getString("group_name"));
                info.setArMonth(rs.getString("smonth"));
                list.add(info);
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).debug(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return list;
    }

    public int addGroupCalendar(int year, int month, int groupID) {
        Connection conn = null;
        Statement state = null;
        int affRow = 0;
        String arMonth = year + this.getMonth(month);
        String sql = "insert into AR_CALENDER_GROUP " + "select "
                + "DDATE , IYEAR ,IMONTH , " + "IDAY, IWEEK , WORKDAYFLAG, "
                + "TYPEID ,OVERTYPEID ,TYPEID_DEFAULT, " + "'" + groupID
                + "' as GROUP_ID,SHIFT_NO " + "from AR_CALENDER "
                + "where get_armonth(ddate)='" + arMonth + "'";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            affRow = state.executeUpdate(sql);
        } catch (SQLException e) {
            Logger.getLogger(getClass()).debug(e.toString());
        } finally {
            SqlUtil.close(state, conn);
        }
        return affRow;
    }

    public int getGroupXTSum(int year, int month, int groupID) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String arMonth = year + this.getMonth(month);
        int sum = 0;
        String sql = "select count(*) as sum " + "from ar_calender_group "
                + "where get_armonth(ddate)='" + arMonth + "' "
                + "and group_id='" + groupID + "' ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                sum = rs.getInt("sum");
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).debug(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return sum;
    }

    public int delGroupCalendar(int groupID, String arMonth) {
        int affRow = 0;
        Connection conn = null;
        Statement state = null;
        String sql = "delete from ar_calender_group " + "where group_ID='"
                + groupID + "' " + "and get_armonth(ddate)='" + arMonth + "' ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            affRow = state.executeUpdate(sql);
        } catch (SQLException e) {
            Logger.getLogger(getClass()).debug(e.toString());
        } finally {
            SqlUtil.close(state, conn);
        }
        return affRow;
    }

    public ArrayList getGroupDays(int groupID, String arMonth) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        // String arMonth=year+this.getMonth(month);
        String sql = "select * from ar_calender_group where get_armonth(ddate)='"
                + arMonth
                + "' and group_id='"
                + groupID
                + "' order by iyear,imonth,iday";
        Logger.getLogger(getClass()).debug(sql);
        ArrayList days = null;
        String DDate = null;
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            days = new ArrayList();
            while (rs.next()) {
                ArFactoryCalendar factory = new ArFactoryCalendar();
                factory.setYear(rs.getInt("iyear"));
                factory.setMonth(rs.getInt("imonth"));
                factory.setDay(rs.getInt("iday"));
                DDate = rs.getInt("iyear") + "-" + rs.getInt("imonth") + "-"
                        + rs.getInt("iday");
                factory.setDDATE(DDate);
                factory.setDayColor(this.getDayColor(rs.getInt("iday"), rs
                        .getInt("iweek")));
                factory.setWeek(rs.getInt("iweek"));
                factory.setWeekName(this.getweekName(rs.getInt("iweek")));
                factory.setWorkdayflag(rs.getInt("workdayflag"));
                factory.setWorkdayflagName(this.getWorkName(rs
                        .getInt("workdayflag")));
                factory.setType(rs.getInt("typeid"));
                factory.setTypeName(this.getTypeName(rs.getInt("typeid")));
                factory.setOrverTYPEID(rs.getInt("overtypeid"));
                factory.setTypeID_DEFAULT(rs.getInt("typeid_default"));
                factory.setShift_no(rs.getInt("shift_no"));
                factory.setShift_name(this.getWorkShitName(rs
                        .getInt("shift_no")) != null ? this.getWorkShitName(rs
                        .getInt("shift_no")) : "");
                days.add(factory);
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).debug(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return days;
    }

    public String getGroupCalendar(int groupID, String arMonth, int type) {
        ArrayList days = this.getGroupDays(groupID, arMonth);
        if (days.size() <= 0)
            return null;
        // 先刷出来第1行表
        String temp = null;
        if (type == 0) {
            temp = this.getFrist(days);
        } else {
            temp = this.getFristEdit(days);
        }

        int out = Integer.parseInt(temp.substring(temp.lastIndexOf("*") + 1,
                temp.length()));
        String frist = temp.substring(0, temp.lastIndexOf("*"));
        String Default = null;
        if (type == 0) {
            Default = this.getDefault(out, days);
        } else {
            Default = this.getDefaultEdit(out, days);
        }
        return frist + Default;
    }

    public int editGroup(ArrayList values) {
        int affRow = 0;
        Connection conn = null;
        Statement state = null;
        int year = ((Integer) values.get(0)).intValue();
        int month = ((Integer) values.get(1)).intValue();
        int day = ((Integer) values.get(2)).intValue();
        int work = ((Integer) values.get(3)).intValue();
        int type = ((Integer) values.get(4)).intValue();
        int groupID = ((Integer) values.get(5)).intValue();
        int shift = ((Integer) values.get(6)).intValue();
        String sql = "update ar_calender_group " + "set workdayflag='" + work
                + "', " + "typeid='" + type + "', " + "shift_no='" + shift
                + "' " + "where iyear='" + year + "' " + "and imonth='" + month
                + "' " + "and iday='" + day + "' " + "and group_id='" + groupID
                + "' ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            affRow = state.executeUpdate(sql);
        } catch (SQLException e) {
            Logger.getLogger(getClass()).debug(e.toString());
        } finally {
            SqlUtil.close(state, conn);
        }
        return affRow;
    }
}
