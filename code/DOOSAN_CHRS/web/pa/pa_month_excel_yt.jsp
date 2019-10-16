<%@ page contentType="application/vnd.ms-excel; charset=utf-8" language="java" %>
<%@ include file="../inc/taglibs.jsp"%>
<%
response.setHeader("Content-Type", "application/vnd.ms-excel; charset=utf-8");
response.setHeader("Content-Disposition", "attachment; filename=pa_detailMonth_yt.xls");
response.setHeader("Pragma", "public");
response.setHeader("Cache-Control", "max-age=0"); 
%>
<table width="100%"  border="1" cellpadding="1" cellspacing="1" >
<tr><td align="left" colspan="44">
<font size="5">${pamonthstr}</font></td></tr></table>
<table width="100%"  border="1" cellpadding="1" cellspacing="1" >
    <tr bgcolor="#CCCCCC">
        <td align="center" rowspan="2"  height="40" ><b>序号</b></td>
        <td align="center" rowspan="2"><b>职号</b></td>
        <td align="center" rowspan="2"><b>姓名</b></td>
        <td align="center" rowspan="2"><b>所在地</b></td>
        <td align="center"  rowspan="2"><b>职级</b></td>
        <td align="center"  rowspan="2"><b>固定工资</b></td>
        
        <td align="center"  rowspan="2"><b>燃油费</b></td>
        <td align="center"  rowspan="2"><b>地区补助</b></td>
        
        <td align="center"  rowspan="2"><b>派遣津贴</b></td>
        <td align="center"  rowspan="2"><b>住宅补助</b></td>
        <td align="center"  rowspan="2"><b>高温补助</b></td>
        <td align="center" rowspan="2"><b>特殊补贴</b></td>
        <td align="center" rowspan="2"><b>午餐补助</b></td>
        <td align="center" rowspan="2"><b>电话补助</b></td>
        <td align="center" rowspan="2"><b>值班补助</b></td>
        <td align="center" rowspan="2"><b>其他支给</b></td>
        <td align="center" rowspan="2"><b>免税支给</b></td>
        <td align="center" rowspan="2"><b>独生子女父母<br>奖励费</b></td>
        <td align="center" rowspan="2"><b>扣除</b></td>
        <td align="center" rowspan="2"><b>工资总额</b></td>
        <td align="center" colspan="3"><b>养老保险</b></td>
         <td align="center" colspan="3"><b>失业保险</b></td>
         <td align="center" colspan="3"><b>医疗保险</b></td>
         <td align="center" colspan="3"><b>住房公积金</b></td>
        <td align="center" rowspan="2"><b>保险合计</b></td>
        <td align="center" rowspan="2"><b>免税额</b></td>
        <td align="center" rowspan="2"><b>其他计税</b></td>
        <td align="center" rowspan="2"><b>当前月个税抵扣合计</b></td>
        <td align="center" rowspan="2"><b>应纳税所得额</b></td>
        <td align="center" rowspan="2"><b>所得税</b></td>
        <td align="center" rowspan="2"><b>税后扣除</b></td>
        <td align="center" rowspan="2"><b>税后支给</b></td>
        <td align="center" rowspan="2"><b>实发工资</b></td> 
        <td align="center" rowspan="2"><b>之前月税前工资合计</b></td>
        <td align="center" rowspan="2"><b>之前月个税抵扣合计</b></td>
        <td align="center" rowspan="2"><b>之前月基本减除费用</b></td>
        <td align="center" rowspan="2"><b>之前月个人保险合计</b></td>
        <td align="center" rowspan="2"><b>之前月个税合计</b></td>
    </tr>
    <tr bgcolor="#CCCCCC" height="20">
        <td align="center" ><b>基数</b></td>
        <td align="center" ><b>比例</b></td>
        <td align="center" ><b>金额</b></td>
        
        <td align="center" ><b>基数</b></td>
        <td align="center" ><b>比例</b></td>
        <td align="center" ><b>金额</b></td>
        
        <td align="center" ><b>基数</b></td>
        <td align="center" ><b>比例</b></td>
        <td align="center" ><b>金额</b></td>
        
        <td align="center" ><b>基数</b></td>
        <td align="center" ><b>比例</b></td>
        <td align="center" ><b>金额</b></td> 
          
    </tr>
    

	 <c:forEach items="${recordList}" var="list" varStatus="i">	
	    <tr>  
	     	<td align="center">${i.index+1 }</td>
	     	<td align="center">${list.EMPID}</td>
	     	<td align="center">${list.CHINESENAME}</td>
	     	<td align="center">${list.ATTENDANCE_PERIOD}</td>
	     	<td align="center">${list.POST}</td>
	     	<td align="center">${list.FIXED_WAGES}</td>
	     	
	     	<td align="center">${list.FUEL_SURCHARGE_FEE}</td>
	     	<td align="center">${list.REGIONAL_GRANTS}</td>
	     	
	     	<td align="center">${list.DISPATCH_ALLOWANCE}</td>
	     	<td align="center">${list.RESIDENTIAL_GRANTS}</td>
	     	<td align="center">${list.HIGH_T_SUBSIDY}</td>
	     	<td align="center">${list.SPECIAL_SUBSIDY}</td>
	     	<td align="center">${list.DINNER_ALLOWANCE_DICI}</td>
	     	<td align="center">${list.TELEPHONE_SUBSIDY}</td>
	     	<td align="center">${list.DUTY_SUBSIDIES}</td>
	     	<td align="center">${list.TO_THE_OTHER}</td>
	     	<td align="center">${list.TAX_FREE_SUPPORT}</td>		     	
	     	<td align="center">${list.ONE_CHILD_SUBSIDY}</td>
	     	<td align="center">${list.OTHER_DEDUCTIONS}</td>
	     	<td align="center">${list.PRE_TAX_WAGES}</td>
	     	<td align="center">${list.PENSION_BASE}</td>
	     	<td align="center">${list.PERSONAL_PENSION_RATE}</td>
	     	<td align="center">${list.PERSONAL_PENSION_INSURANCE}</td>
	     	
	     	<td align="center">${list.UNEMPLOYMENT_BASE}</td>
	     	<td align="center">${list.PERSONAL_UNEMPLOYED_RATE}</td>
	     	<td align="center">${list.PERSONAL_UNEMPLOYMENT}</td>
	     	
	     	<td align="center">${list.MEDICAL_BASE}</td>
	     	<td align="center">${list.PERSONAL_MEDICAL_RATE}</td>
	     	<td align="center">${list.PERSONAL_MEDICAL_INSURANCE}</td>
	     	
	     	<td align="center">${list.CPF_BASE}</td>
	     	<td align="center">${list.PERSONAL_HOUSING_RATE}</td>
	     	<td align="center">${list.PERSONAL_HOUSING_ACCUMULATION}</td>
	     	
	     	<td align="center">${list.TOTAL_PERSONAL_INSURANCE}</td>
	     	<td align="center">${list.TAX_FREE}</td>
	     	<td align="center">${list.GIFT_COST}</td>
	     	<td align="center">${list.CAL_DQGSDK_TOTAL}</td>
	     	<td align="center">${list.TAXABLE_INCOME}</td>
	     	<td align="center">${list.INCOME_TAX}</td>
	     	<td align="center">${list.TAX_AFTER_MINUS}</td>
	     	<td align="center">${list.TAX_AFTER_SUPPORT}</td>
	     	<td align="center">${list.TOTAL_AMOUNT}</td>
	     	<td align="center">${list.CAL_SQGZ_TOTAL}</td>
	     	<td align="center">${list.CAL_GSDK_TOTAL}</td>
	     	<td align="center">${list.CAL_JBJC_TOTAL}</td>
	     	<td align="center">${list.CAL_GRBX_TOTAL}</td>
	     	<td align="center">${list.CAL_GS_TOTAL}</td>
	     	
	 
       </tr>
	  </c:forEach>   
	  
	  <c:forEach items="${recordListsum}" var="listsum" varStatus="s">	
	    <tr bgcolor="#CCCCCC">  
	     	<td align="center" colspan="5">合计</td>
 
	     	<td align="center">${listsum.FIXED_WAGES}</td>
	     	
	     	<td align="center"> </td>
	     	<td align="center"> </td>
	     	
	     	<td align="center">${listsum.DISPATCH_ALLOWANCE}</td>
	     	<td align="center">${listsum.RESIDENTIAL_GRANTS}</td>
	     	<td align="center">${listsum.HIGH_T_SUBSIDY}</td>
	     	<td align="center">${listsum.SPECIAL_SUBSIDY}</td>
	     	
	     	<td align="center">${listsum.DINNER_ALLOWANCE_DICI}</td>
	     	<td align="center">${listsum.TELEPHONE_SUBSIDY}</td>
	     	<td align="center">${listsum.DUTY_SUBSIDIES}</td>
	     	<td align="center">${listsum.TO_THE_OTHER}</td>
	     	<td align="center">${listsum.TAX_FREE_SUPPORT}</td>
	     	
	     	<td align="center">${listsum.ONE_CHILD_SUBSIDY}</td>
	     	<td align="center">${listsum.OTHER_DEDUCTIONS}</td>
	     	<td align="center">${listsum.SHOULD_BE_ISSUED_THIS_MONTH}</td>
	     	
	     	<td align="center"> </td>
	     	<td align="center"> </td>
	     	<td align="center">${listsum.PERSONAL_PENSION_INSURANCE}</td>
	     	
	     	<td align="center"> </td>
	     	<td align="center"> </td>
	     	<td align="center">${listsum.PERSONAL_UNEMPLOYMENT}</td>
	     	
	     	<td align="center"> </td>
	     	<td align="center"> </td>
	     	<td align="center">${listsum.PERSONAL_MEDICAL_INSURANCE}</td>
	     	
	     	<td align="center"> </td>
	     	<td align="center"> </td>
	     	<td align="center">${listsum.PERSONAL_HOUSING_ACCUMULATION}</td>
	     	
	     	<td align="center">${listsum.TOTAL_PERSONAL_INSURANCE}</td>
	     	<td align="center">${listsum.TAX_FREE}</td>
	     	<td align="center">${listsum.GIFT_COST}</td>
	     	<td align="center">${listsum.CAL_DQGSDK_TOTAL}</td>
	     	<td align="center">${listsum.TAXABLE_INCOME}</td>
	     	<td align="center">${listsum.INCOME_TAX}</td>
	     	<td align="center">${listsum.TAX_AFTER_MINUS}</td>
	     	<td align="center">${listsum.TAX_AFTER_SUPPORT}</td>
	     	<td align="center">${listsum.TOTAL_AMOUNT}</td>
	     	<td align="center">${listsum.CAL_SQGZ_TOTAL}</td>
	     	<td align="center">${listsum.CAL_GSDK_TOTAL}</td>
	     	<td align="center">${listsum.CAL_JBJC_TOTAL}</td>
	     	<td align="center">${listsum.CAL_GRBX_TOTAL}</td>
	     	<td align="center">${listsum.CAL_GS_TOTAL}</td>
	     	
	 
       </tr>
	  </c:forEach>  
 
</table>
