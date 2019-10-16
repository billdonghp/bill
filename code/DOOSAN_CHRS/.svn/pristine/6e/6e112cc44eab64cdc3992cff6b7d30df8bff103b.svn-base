<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="org.apache.log4j.Logger,com.ait.utils.*,java.util.Vector,com.ait.util.StringUtil"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
ActivityReport pareport = new ActivityReport();
String startdate= StringUtil.checkNull(request.getParameter("startDate"));
String enddate= StringUtil.checkNull(request.getParameter("endDate"));
String cpnyId =StringUtil.checkNull(request.getParameter("cpnyId"));
String codeStr =StringUtil.checkNull(request.getParameter("codeStr"));
Vector list = new Vector();
Vector data = new Vector();
String sql =" select de.deptname as \"部门\", "+
            " detail.wpname as \"制品名称\", "+
            " detail.wp_l as \"制品长\", "+
        " detail.wp_w as \"制品宽\", "+
        " detail.wp_h as \"制品高\", "+
        " sum(detail.wpnumber) as \"数量合计\", "+
        " sum(detail.wp_sumprice) as \"费用合计\" "+

   " from ga_products_detail  detail, "+
        " ga_products_apply   base, "+
       "  hr_employee         hr, "+
       "  hr_department       de, "+
       "  ga_sealproducts_set wset "+

  " where detail.apply_no = base.apply_no(+) "+
    " and base.confirm_flag = 3 "+
    " and base.wp_applyerid = hr.person_id(+) "+
    " and detail.wpid = TO_CHAR(wset.woodproducts_id(+)) ";
    if(!codeStr.equals("")){
    sql +=" and base.apply_type in("+codeStr+") "+
      " and detail.wp_type in("+codeStr+") ";
    }    
   sql += "  and hr.deptid=de.deptid(+) "+
   "  and base.cpny_id='"+cpnyId+"' "+
   "  and base.apply_date between to_date('"+startdate+"','YYYY-MM-DD')   and to_date('"+enddate+"','YYYY-MM-DD') + 1"+
  " group by de.deptname, detail.wpname, detail.wp_l, detail.wp_w, detail.wp_h"+
  " union  "+
  " select de.deptname||'(小计)' as \"部门\", "+
    "     '' as \"制品名称\", "+
      "   to_number('', '99999.00') as \"制品长\", "+
     "    to_number('', '99999.00') as \"制品宽\", "+
     "    to_number('', '99999.00') as \"制品高\", "+
     "    to_number('', '99999.00') as \"数量合计\", "+
     "    sum(detail.wp_sumprice)   as \"费用合计\" "+

   " from ga_products_detail  detail, "+
       "  ga_products_apply   base, "+
      "   hr_employee         hr, "+
     "    hr_department       de   "+

  " where detail.apply_no = base.apply_no(+) "+
   "  and base.confirm_flag = 3 "+
   "  and base.wp_applyerid = hr.person_id(+)  "+
   "  and hr.deptid=de.deptid(+) ";
   if(!codeStr.equals("")){
    sql +=" and base.apply_type in("+codeStr+") "+
      " and detail.wp_type in("+codeStr+") ";
    }  
  sql += "  and base.cpny_id='"+cpnyId+"' "+
   "  and base.apply_date between to_date('"+startdate+"','YYYY-MM-DD')   and to_date('"+enddate+"','YYYY-MM-DD') + 1 "+
  " group by de.deptname ";
Logger.getLogger(getClass()).debug(StringUtil.toISO(sql));
list = pareport.DataSelect(sql);
String tabledata = "";
String tr="";
response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
response.setHeader("Content-Disposition", "attachment; filename=areaCompleteOrders.xls");
response.setHeader("Pragma", "public");
response.setHeader("Cache-Control", "max-age=0");
response.setCharacterEncoding("UTF-8");
%>
<table width="100%" border="1" cellspacing="0" cellpadding="0">
<tr><td colspan="7" align="center" rowspan="2" style="font-size:14pt;Font-FAMILY:宋体;font-weight:900"><%=startdate.substring(0,4)%>年<%=startdate.substring(5,7)%>月<%=startdate.substring(8,10)%>日--<%=enddate.substring(0,4)%>年<%=enddate.substring(5,7)%>月<%=enddate.substring(8,10)%>日 制品统计表</td></tr>
</table>

<table width="100%" border="1" cellspacing="0" cellpadding="0">
	<%
				if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
			pareport = (ActivityReport) list.elementAt(i);
	%>
	<tr align="center" style="font-size:9pt;FONT-FAMILY:宋体">
		<%
				data = pareport.getData();
				tr = "";
				if (data.size() != 0) {
					for (int j = 0; j < data.size(); j++) {
						tabledata = (String) data.elementAt(j);
						tr += "<td>" + tabledata + "</td>";
					}
		%>
		<%=tr%>
		<%
		}
		%>
	</tr>
	<%
			}
			}
	%>
</table>

