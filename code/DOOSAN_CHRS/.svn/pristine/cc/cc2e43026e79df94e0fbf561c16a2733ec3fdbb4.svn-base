package com.ait.ga.cmd.food;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class ExportFoodReportCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
			
			if(request.getParameter("check") == "foodStatus" || ("foodStatus").equals(request.getParameter("check"))){
				
				try {
					parameterObject = ObjectBindUtil.getData(request);
					parameterObject.setString("cpnyId", admin.getCpnyId());
					parameterObject.setInt("flag", 2);
					List recordList = services.selectFoodDeptReport(parameterObject);
					List recordDetailList = services.selectFoodDetail(parameterObject);
					
					if(recordList.size()>0){
						
						Object obj1 = services.getFoodApplyPrice(parameterObject);
						Object obj2 = services.getFoodApplyQuentity(parameterObject);
						if(obj1!=null&&obj2!=null){
							String quentity = (obj2.toString());
							String price = (obj1.toString());
							request.setAttribute("quentity", quentity);
							request.setAttribute("price", price);
							
						}
					}	
					request.setAttribute("recordList", recordList);
					request.setAttribute("recordDetailList", recordDetailList);
				
				} catch (Exception e) {
					
					Logger.getLogger(getClass()).error(e.toString());
					throw new GlRuntimeException(
							"export food report report Exception. ", e);
				}
				
				return "/ga_food_dept_report.jsp?menu_code="+ parameterObject.getString("menu_code");
				
			}else if (request.getParameter("check") == "foodTotalStatus" || ("foodTotalStatus").equals(request.getParameter("check"))) {
				
				try {
					
		// bind parameter
						parameterObject = ObjectBindUtil.getData(request);
						parameterObject.setString("cpnyId", admin.getCpnyId());
						parameterObject.setInt("flag", 1);
						
						List<SimpleMap> schemeName = services.selectFoodScheme(parameterObject);
						List<SimpleMap> foodNameList = services.selectFoodProduct(parameterObject);
						String sql1 = makeSql(schemeName,1);
						String sql2 = makeSql(foodNameList, 2);
						parameterObject.setString("sql1", sql1);
						List recordSchemeList = services.selectSchemeReport(parameterObject);
						parameterObject.setString("sql2", sql2);
						List recordFoodList = services.selectFoodReport(parameterObject);
						
						if(recordSchemeList.size()>0){
							
							Object obj1 = services.getFoodApplyPrice(parameterObject);
							Object obj2 = services.getFoodApplyQuentity(parameterObject);
							if(obj1!=null&&obj2!=null){
								String quentity = (obj2.toString());
								String price = (obj1.toString());
								request.setAttribute("quentity", quentity);
								request.setAttribute("price", price);
								
							}
						}
						request.setAttribute("recordSchemeList", recordSchemeList);
						request.setAttribute("schemeName", schemeName);
						request.setAttribute("foodNameList", foodNameList);
						request.setAttribute("recordFoodList", recordFoodList);
						
						
					} catch (Exception e) {
		
						Logger.getLogger(getClass()).error(e.toString());
						throw new GlRuntimeException(
								"export food report report Exception. ", e);
					}
				return "/ga_food_report.jsp?menu_code="+ parameterObject.getString("menu_code");
			}
		return null;
	}
	
	
	
	
	private String makeSql(List<SimpleMap> list,int flag) {
		
		StringBuffer str = new StringBuffer();
		if(flag == 1){
			for (SimpleMap map : list) {
				str.append("SUM(DECODE(A.FOOD_SCHEME_ID,'");
				str.append(map.getString("SEQ_NO"));
				str.append("',A.QUENTITY,0)) ");
				str.append(map.getString("SEQ_NO"));
				str.append(",");
			}
			return (str.toString());
		}else if (flag == 2) {
			for (SimpleMap map : list) {
				str.append("SUM(DECODE(C.FOOD_ID,'");
				str.append(map.getString("FOOD_NAME"));
				str.append("',B.QUENTITY,0)) ");
				str.append(map.getString("FOOD_NAME"));
				str.append(",");
			}
			return (str.toString());
		}
		return str.toString();
	}
}
