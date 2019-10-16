
package com.ait.kpa.cmd.diff.diffformular;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.i18n.MessageSource;
import com.ait.kpa.business.PaServices;
import com.ait.kpa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.bean.SysCodeBean;

public class UpdateDiffFormularCmd extends PaAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.putToolbarInfo(request);
		
		PaServices services = PaServices.getInstance();
		SimpleMap parameterObject;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		MessageSource messageSource ;
		
		List pa_item_list = new ArrayList() ;
		List pa_param_item_list = new ArrayList() ; 
		List pa_distinct_list = new ArrayList() ;
		
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			int size = 0 ;
			String id = "" ;
			String name = "" ;
			SimpleMap simpleMap ;
			String condition = parameterObject.getString("condition_cn") ;
			String formular = parameterObject.getString("formular_cn") ; 
			
			String statTypeCode = "";
			SysCodeBean codeBean = new SysCodeBean();

/*			List statTypeList = SysCodeParser.getSysCode("EmpDiffType");

			size = statTypeList.size();
			for (int i = 0; i < size; i++) {
				codeBean = (SysCodeBean) statTypeList.get(i);
				if (condition.indexOf(codeBean.getCodeName()) != -1) {
					statTypeCode = codeBean.getCodeId();
					break;
				}
			}

			if (statTypeCode.length() == 0 && size > 0) {
				codeBean = (SysCodeBean) statTypeList.get(0);
				statTypeCode = codeBean.getCodeId();
			}

			parameterObject.setString("statTypeCode", statTypeCode);*/
			
			pa_item_list = services.retrieveDiff_Item_List_Month() ;
			pa_param_item_list = services.retrieveDiff_param_item_list(parameterObject);
			pa_distinct_list = services.retrievePa_bonus_distinct_list(parameterObject) ;
			
			// 计算项目
			size = pa_item_list.size();
			for (int k = 0; k < size; k++) {
				simpleMap = (SimpleMap) pa_item_list.get(k);
				id = simpleMap.getString("ITEM_ID");
				name = "计算项目." + simpleMap.getString("ITEM_NAME");

				if (condition.indexOf(name) != -1) {
					condition = condition.replaceAll(name, id);
				}

				if (formular.indexOf(name) != -1) {
					formular = formular.replaceAll(name, id);
				}
			}
			
			// 输入项目
			size = pa_param_item_list.size();
			for (int m = 0; m < size; m++) {
				simpleMap = (SimpleMap) pa_param_item_list.get(m);
				id = simpleMap.getString("PARAM_ID");
				name = "输入项目." + simpleMap.getString("PARAM_NAME");
				System.out.println(formular+"dddddddddd"+name);
				if (condition.indexOf(name) != -1) {
					condition = condition.replaceAll(name, id);
				}

				if (formular.indexOf(name) != -1) {
					formular = formular.replaceAll(name, id);
				}
			}

			// 固定参数
			size = pa_distinct_list.size();
			for (int n = 0; n < size; n++) {
				simpleMap = (SimpleMap) pa_distinct_list.get(n);
				id = simpleMap.getString("DISTINCT_FIELD");
				name = "固定参数." + simpleMap.getString("FIELD_NAME");

				if (condition.indexOf(name) != -1) {
					condition = condition.replaceAll(name, id);
				}

				if (formular.indexOf(name) != -1) {
					formular = formular.replaceAll(name, id);
				}
			}

			parameterObject.set("condition", condition);
			parameterObject.set("formular", formular);
			
			services.updateDiffFormular(parameterObject) ;

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("update pa diff formular Exception. ", e);
		}
		//添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.modify_successfully"));
		request.setAttribute("url","/kpaControlServlet?operation=kpa_diff_formular_data&pa_item_no=" + parameterObject.getString("pa_item_no"));

		return "/inc/alertMessage.jsp";
	}
}