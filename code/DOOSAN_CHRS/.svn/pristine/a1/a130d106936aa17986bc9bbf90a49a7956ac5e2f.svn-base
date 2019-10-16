/*
 * @(#)InsertExpiredContractCmd.java 1.0 2006-12-19 下午04:53:12
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
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Hue (sunhui@ait.net.cn)
 * @Date 2006-12-19 下午04:53:12
 * @version 1.0
 * 
 */
public class InsertExpiredContractCmd extends HrmAbstractCommand {

	private static final Logger logger = Logger
			.getLogger(InsertExpiredContractCmd.class);

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
			for(int i=0;i<contract.length;i++)
			if (contract != null) {
				for (int j = 0; j < contract.length; j++) {        
					Contract eContract = new Contract();
					ObjectBindUtil.setFormBean(request, eContract, "_"+contract[j]);
					list.add(eContract);
				}
				services.addMultiExpiredContract(list);
			}
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"The information Exception when running the IsertExpiredContract. ",
					e);
		}

		return "/hrm/hrm_view_expired_cont.jsp";
	}
}
