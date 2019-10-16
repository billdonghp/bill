/*
 * @(#)ProcessBarJsTag.java 1.0 2008-3-3 下午02:38:49
 *
 *Copyright 2001 - 2008 AIT. All Rights Reserved.
 */
package com.ait.taglib;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kellywang (wangliwei@ait.net.cn)
 * @Date 2008-3-3 下午02:38:49
 * @version 1.0
 * 
 */
public class CalendarTag extends TagSupport {
	
    /* declare tag parameter */
    private String year = null;

    private String month = null;
    
    private String dailyStatus = null;

    /* declare calendar standard variable */
    private final String[] months = { "1", "2", "3", "4", "5", "6", "7", "8",
            "9", "10", "11", "12" };

    private final String[] months_en = { "January", "February", "March",
            "April", "May", "June", "July", "August", "September", "October",
            "November", "December" };

    private final int dom[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    /* declare variable */
    private int yy = 0;

    private int mm = 0;

    private String language = "en";
    
    private String country = "US";
    
    private List dailyAttStatusList = null;

    private StringBuffer sbuf = null;

    // decalare day number in the month
    private int days = 0;

    // decalare blank number in the month
    private int lead = 0;

    /**
     * display calendar
     * 
     * @return int
     */
    public int doEndTag() throws JspException {

        JspWriter out = pageContext.getOut();

        try {
            this.initally();
            this.buildHead();
            this.buildBody(dailyAttStatusList);
            out.print(sbuf.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return EVAL_PAGE;
    }

    /**
     * initiate data
     *  
     */
    private void initally() throws JspException {
    	
    	sbuf = new StringBuffer();
        Calendar calendar = null;
        GregorianCalendar gCalendar = null;
        HttpServletRequest request = (HttpServletRequest) pageContext
                .getRequest();

        // get attribut value
        year = (String) eval("year", year, Object.class);
        month = (String) eval("month", month, Object.class);

        if (year.equals("") || month.equals("")) {

            /* get current year and month */
            calendar = Calendar.getInstance();
            yy = calendar.get(Calendar.YEAR);
            mm = calendar.get(Calendar.MONTH);
            year = String.valueOf(yy);
            month = String.valueOf(mm);

        } else {

            // format month data
            if (month.length() == 2 && Integer.parseInt(month) < 10) {
                month = month.substring(1);
            }

            // get month (construct GregorianCalendar paramter 'january
            // for 0,February for 1.....')
            for (int i = 0; i < months.length; i++) {

                if (months[i].equals(month)) {
                    mm = i;
                    break;
                }
            }
            /* get pass year */
            yy = Integer.parseInt(year);
        }

        /* init GregorianCalendar object */
        gCalendar = new GregorianCalendar(yy, mm, 1);
        /* get number for display blank */
        lead = gCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        /* get day number in the month */
        days = dom[mm];

        /* whether is leap year */
        if (gCalendar.isLeapYear(gCalendar.get(Calendar.YEAR)) && mm == 1) {
            ++days;
        }

        /* get parameter object */
        if (dailyStatus != null && !"".equals(dailyStatus)) {

        	dailyStatus = (String) eval("dailyStatus", dailyStatus, Object.class);
        	dailyAttStatusList = (List) request.getAttribute(dailyStatus);
        }

        // get login language
        AdminBean admin=(AdminBean)request.getSession(false).getAttribute("admin");
        language = admin.getLanguagePreference();
        country = admin.getCountryPreference();
    }

    /**
     * build calendar head
     *  
     */
    private void buildHead() {

        sbuf
                .append(
                        "<table width='100%' border='0' cellspacing='1' cellpadding='0'  class='table_line_complex'>")
                .append("<tr>")
                .append(
                        "<td width='15%' height='22' class='table_header_complex_c' align='center'><font color='#A53439'>Sun</font></td>")
                .append(
                        "<td width='14%' class='table_header_complex_c' align='center'><font color='#135054'>Mon</font></td>")
                .append(
                        "<td width='14%' class='table_header_complex_c' align='center'><font color='#135054'>Tue</font></td>")
                .append(
                        "<td width='14%' class='table_header_complex_c' align='center'><font color='#135054'>Wed</font></td>")
                .append(
                        "<td width='14%' class='table_header_complex_c' align='center'><font color='#135054'>Thu</font></td>")
                .append(
                        "<td width='14%' class='table_header_complex_c' align='center'><font color='#135054'>Fri</font></td>")
                .append(
                        "<td width='15%' class='table_header_complex_c' align='center'><font color='#A53439'>Sat</font></td>")
                .append("</tr>");

    }

    /**
     * build calendar body (display calendar only)
     *  
     */
    private void buildBody() {

        sbuf.append("<tr>");
        /* display blank */
        for (int i = 0; i < lead; i++) {
            sbuf.append("<td class='table_padding'>&nbsp;</td>");
        }
        /* display content */
        for (int i = 1; i <= days; i++) {
            /* display font is red for sat and sun */
            if ((i + lead) % 7 == 0 || (i + lead) % 7 == 1) {
                sbuf
                        .append(
                                "<td height='22' align='center' class='table_padding'><font color='#A53439'>")
                        .append(i).append("</font></td>");
            } else {
                sbuf
                        .append(
                                "<td align='center' class='table_padding'><font color='#135054'>")
                        .append(i).append("</td>");
            }
            /* newline each seven day */
            if ((lead + i) % 7 == 0) {
                sbuf.append("</tr><tr>");
            }
            /* append blank */
            if (i == days && (lead + i) % 7 != 0) {
                /* get blank number */
                int blanks = 7 - ((lead + days) % 7);

                for (int k = 1; k <= blanks; k++) {
                    sbuf.append("<td class='table_padding'>&nbsp;</td>");
                }
            }
        }
        sbuf.append("</tr></table>");
    }

    /**
     * build calendar body (display calendar,daily attendance status)
     * 
     * @param holidayList
     * @param dailyAttStatusList
     */
    private void buildBody(List dailyAttStatusList) {

        /* declare paramter */
        String url = "/ar/ar_modify_detail_lock.jsp?menu_code=ar0211&date=";

        /* declare attendance status in the day */
        String status = null;
        String statusDate = null;

        /* display calendar if daily attendance status is null */
        if (dailyAttStatusList == null || dailyAttStatusList.size() == 0) {
            this.buildBody();
            return;
        }

        sbuf.append("<tr>");
        /* display blank */
        for (int i = 0; i < lead; i++) {
            sbuf.append("<td class='table_padding'>&nbsp;</td>");
        }
        /* display content */
        for (int i = 1; i <= days; i++) {

            /* get daily status data object */
        	SimpleMap statusData = (SimpleMap) this.getData(dailyAttStatusList, i);
        	
        	if (statusData != null) {
	        	status = statusData.getString("STATUS");
	        	statusDate = statusData.getString("AR_DATE_STR");
        	} else {
        		status = "";
        		statusDate = "";
        	}
        	
            /* not exist daily status data */
        	if (status.equals("")) {
        		
        		 sbuf
                 .append("<td align='center' class='table_padding' style='height:40px'>")
                 .append(
                         "<table width='100%'  border='0' cellspacing='0' cellpadding='0' height='100%'><tr><td align='center' valign='middle'>")
                 .append(i)
                 .append(
                         "</td></tr><tr><td align='right'>")
                 .append("</td></tr></table></td>");
        	} else {
        		
        		 /* display font is green */
        	     sbuf
                 .append("<td align='center' class='table_padding'  style='height:40px'>")
                 .append(
                         "<table width='100%'  border='0' cellspacing='0' cellpadding='0' height='100%'><tr><td align='center' valign='middle'>")

                 .append("<a href=javascript:showDetailWindow('")
                 .append(url)
                 .append(statusDate)
                 .append("&status=")
                 .append(status)
                 .append("')>")
                 .append(i)
                 .append("</a>")
                 .append(
                         "</td></tr><tr><td align='right' height='0px'><span style='display:block;width:16px;height:16px;position:absolute;margin:-17px 0px 0px -17px;'>")
                 .append(status.equals("LockStatus010")?"<image src='/images/1.gif'/>":"<image src='/images/0.gif'/>")
                 .append("</span></td></tr></table></td>");
        	}
            
            /* newline each seven day */
            if ((lead + i) % 7 == 0) {
                sbuf.append("</tr><tr>");
            }
            /* append blank */
            if (i == days && (lead + i) % 7 != 0) {
                /* get blank number */
                int blanks = 7 - ((lead + days) % 7);

                for (int k = 1; k <= blanks; k++) {
                    sbuf.append("<td class='table_padding'>&nbsp;</td>");
                }
            }
        }
        sbuf.append("</tr></table>");

    }

    /**
     * get data object for the day
     * 
     * @param list
     * @param day
     * @return Object
     */
    private Object getData(List list, int day) {

        String dayStr = String.valueOf(day);
        if (day < 10) {
            dayStr = "0" + dayStr;
        }

        for (int i = 0; i < list.size(); i++) {

        	SimpleMap data = (SimpleMap) list.get(i);
            String date = data.getString("AR_DATE_STR").substring(8);
            // if exist status data then return SimpleMap object
            if (date.equals(dayStr)) {
                return data;
            }
        }
        // if don't exist status data then return null
        return null;
    }

    /**
     * get expression value
     * 
     * @param attName
     * @param attValue
     * @param clazz
     * @return Object
     * @throws JspException
     */
    private Object eval(String attName, String attValue, Class clazz)
            throws JspException {

        Object obj = ExpressionEvaluatorManager.evaluate(attName, attValue,
                clazz, this, pageContext);
        if (obj == null) {
            return attValue;
        } else {
            return obj;
        }
    }

    /**
     * build date by join (year,month,day)
     * 
     * @param year
     * @param month
     * @param day
     * @return String
     */
    private String getDate(String year, String month, int day) {

        StringBuffer date = new StringBuffer();
        date.append(year);

        // format month data
        if (month.length() < 2) {
        	date.append("0");
        }
        date.append(month);
        // format day data
        if (day < 10) {
        	date.append("0");
        }
        date.append(day);

        return date.toString();
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setMonth(String month) {
        this.month = month;
    }
    
    public void setDailyStatus(String dailyStatus) {
    	this.dailyStatus = dailyStatus;
    }

}
