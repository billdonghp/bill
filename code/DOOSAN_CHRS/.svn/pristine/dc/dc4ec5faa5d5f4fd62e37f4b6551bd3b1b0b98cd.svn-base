/*
 * @(#)InsertContractCmd.java 1.0 2006-12-19 下午04:41:46
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.contract;

import java.io.IOException;
import java.util.ArrayList;    
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;        

import com.ait.core.exception.GlRuntimeException;     
import com.ait.hrm.bean.Contract;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author hue (sunhui@ait.net.cn)
 * @Date 2006-12-19 下午04:41:46
 * @version 1.0
 * 
 */
public class InsertContractCmd extends HrmAbstractCommand {

	private static final Logger logger = Logger
			.getLogger(ViewContractByInsertCmd.class);

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			this.putDeptTree(request);
			this.putToolbarInfo(request);

			HrmServices services = HrmServices.getInstance();

			String adminId = ((AdminBean) request.getSession(false)
					.getAttribute("admin")).getAdminID();
			
			List<Contract> list = new ArrayList<Contract>();
			String[] contract = null;
			contract = request.getParameterValues("isChecked");
			String emps="";
			AdminBean admin =(AdminBean) request.getSession().getAttribute("admin");
			MessageSource messageSource = new MessageSource("hrm",admin.getLocale(), "UTF-8");
			String errorMsg = messageSource.getMessage("alert.emp.contract_info.contract_query.same"); 
			if (contract != null) {
				for (int i = 0; i < contract.length; i++) {
					Contract newContract = new Contract();                    
					ObjectBindUtil.setFormBean(request, newContract, "_"+contract[i]);
					SimpleMap map=new SimpleMap();
					map.put("empID",newContract.getEmpID());
					map.put("beginDate",newContract.getContractStartDate());
					map.put("endDate",newContract.getContractEndDate());               
					map.put("contractType",newContract.getContractTypeCode());
					String emp=services.checkNewContractValidity(map);
					if(emp.equals(""))
					list.add(newContract);  
					else                               
						emps=emps+","+emp;                                                             
				}  
				  errorMsg=errorMsg.replace("ID","[ "+emps+"]"); 
				if(list.size()>0)          
				{
				  if(list.size()<contract.length)
				  request.setAttribute("message",errorMsg);
				  services.addMultiNewContract(list);            
				}  
				else
					request.setAttribute("message",errorMsg);
					
			}
		} catch (Exception e) {  
	
			logger.error(e.toString());
			throw new GlRuntimeException(
					"The information Exception when running the InsertContractCmd. ",
					e);
		}
		return "/hrmControlServlet?operation=viewContractByInsert&menu_code=hr0302";
	}

}
