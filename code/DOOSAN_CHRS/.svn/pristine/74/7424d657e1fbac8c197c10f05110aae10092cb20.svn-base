/*
 * @(#)RetrieveEvaluateCmd.java 1.0 2007-5-7 下午03:22:07
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.evs.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.evs.EvsColumn;
import com.ait.evs.EvsEmp;
import com.ait.evs.EvsItemDetail;
import com.ait.evs.EvsPeriod;
import com.ait.evs.EvsScore;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kellywang (wangliwei@ait.net.cn)
 * @Date 2007-5-7 下午03:22:07
 * @version 1.0
 * 
 */
public class RetrieveEvaluateCmd implements Command {

	private static final Logger logger = Logger
			.getLogger(RetrieveEvaluateCmd.class);

	private EvsPeriod evsPeriod = new EvsPeriod();

	private EvsScore scoreOp = new EvsScore();

	private EvsItemDetail evsItemDetail = new EvsItemDetail();

	private EvsColumn evsColumn = new EvsColumn();

	private EvsScore evsScore = new EvsScore();

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
    
		EvsEmp evsEmp = new EvsEmp();
		
		String adminID = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();

		try {

			// 取得员工ID和评价区间
			String empID = request.getParameter("EmpID") == null ? adminID
					: request.getParameter("EmpID");
			String period = request.getParameter("Period") == null ? evsPeriod
					.getCurrentEvPeriod() : request.getParameter("Period");

			// 取得评价期间列表
			List periodList = evsPeriod.getEvsPeriodByYear("");

			// 取得被评价者的相关信息
			evsEmp.setEvEmpID(empID);
			evsEmp.setEvPeriodID(period);
			evsEmp.getEvEmpByEmpIdPeriod();

			// 验证当前流程是否在允许时间范围内
			boolean isOverPeriod = scoreOp.getIsOverPeriod(period, evsEmp
					.getEvCurrentProcessID(), evsEmp.getEvTypeID());

			// 取得登陆者可以操作的流程
			String operate = scoreOp.getEmpOpoerateByMaster(period, empID,
					adminID);

			// 取得当前流程中的评价项目列表
			Vector itemVector = evsItemDetail.getItemIDByEmp(period, empID);

			List itemList = new ArrayList();
			List columnNameList = new ArrayList();

			Iterator itemItor = itemVector.iterator();
			while (itemItor.hasNext()) {

				Hashtable item = (Hashtable) itemItor.next();

				// 取得当前评价项目对应的细则数据集合(SEQ_EV_ITEM_DETAIL集合)
				Vector detailVector = evsColumn.getDetailIDByItemID(period,
						empID, item.get("ItemID").toString());

				List detailList = new ArrayList();
				String[] evMarks = new String[0];
				String[] evMarksName = new String[0];
				String evMarkDefault = "";

				Iterator detailItor = detailVector.iterator();
				while (detailItor.hasNext()) {

					String detailSequence = detailItor.next().toString();

					// 取得细则的内容
					Vector column_v = evsColumn.getEvsColumnByDetailID(Integer
							.parseInt(detailSequence));
					;

					// 取得当前循环中的项目细则对应的相关选项和默认数据（如果存在评价分数和意见数据，也取出）
					Hashtable scoreHashtable = evsScore
							.getDefaultByDetailID(Integer
									.parseInt(detailSequence));

					evMarkDefault = scoreHashtable.get("DefaultMark")
							.toString();

					if (scoreHashtable.get("CurrentMark") != null
							&& !scoreHashtable.get("CurrentMark").equals(""))
						evMarkDefault = scoreHashtable.get("CurrentMark")
								.toString();
					evMarks = scoreHashtable.get("Marks").toString().split(",");
					evMarksName = scoreHashtable.get("MarksName").toString()
							.split(",");

					SimpleMap detailMap = new SimpleMap();
					detailMap.setString("detailSequence", detailSequence);
					detailMap.setString("evMarkDefault", evMarkDefault);
					detailMap.set("evMarks", evMarks);
					detailMap.set("evMarksName", evMarksName);
					detailMap.set("detailContent", column_v);
					detailMap.set("currentIdea", scoreHashtable
							.get("CurrentIdea"));
					detailMap.set("currentMark", scoreHashtable
							.get("CurrentMark"));

					// 添加每条细则对应的相关数据到detailList
					detailList.add(detailMap);
				}

				// 添加每个项目对应的细则数据集合到itemList
				itemList.add(detailList);

				// 取得当前评价项目对应的细则数据的列名称集合
				Vector columnNameVector = new Vector();
				if (detailVector != null && detailVector.size() > 0)
					columnNameVector = evsColumn
							.getEvsColumnNameByDetailID(((Integer) detailVector
									.get(0)).intValue());

				columnNameList.add(columnNameVector);

			}

			request.setAttribute("adminID", adminID);
			request.setAttribute("empID", empID);
			request.setAttribute("period", period);
			request.setAttribute("evType", evsEmp.getEvTypeID());
			request.setAttribute("isOverPeriod", isOverPeriod);
			request.setAttribute("operate", operate);
			request.setAttribute("evsEmp", evsEmp);
			request.setAttribute("periodList", periodList);
			request.setAttribute("itemVector", itemVector);
			request.setAttribute("itemList", itemList);
			request.setAttribute("columnNameList", columnNameList);
			
			logger.debug("Evaluate operate : " + operate);

		} catch (Exception e) {

			logger.error("Retrieve evaluate Exception." + e.toString());
			throw new GlRuntimeException("Retrieve evaluate Exception.", e);
		}
		logger.debug("evaluate type : " + evsEmp.getEvTypeID());
		// 季度评价
		if (evsEmp.getEvTypeID() != null
				&& evsEmp.getEvTypeID().equals("EVTYPE007"))
			return "/evs0205_quarter.jsp";
		// 一般职员1评价
		if (evsEmp.getEvTypeID() != null
				&& evsEmp.getEvTypeID().equals("EVTYPE001"))
			return "/evs0205_personnel.jsp";
		// 一般职员2评价
		if (evsEmp.getEvTypeID() != null
				&& evsEmp.getEvTypeID().equals("EVTYPE002"))
			return "/evs0205_personnel.jsp";
		// 管理者1评价
		if (evsEmp.getEvTypeID() != null
				&& evsEmp.getEvTypeID().equals("EVTYPE003"))
			return "/evs0205_ruler.jsp";
		// 管理者2评价
		if (evsEmp.getEvTypeID() != null
				&& evsEmp.getEvTypeID().equals("EVTYPE004"))
			return "/evs0205_ruler.jsp";
		// 营业人员1评价
		if (evsEmp.getEvTypeID() != null
				&& evsEmp.getEvTypeID().equals("EVTYPE005"))
			return "/evs0205_salesman.jsp";
		// 营业人员2评价
		if (evsEmp.getEvTypeID() != null
				&& evsEmp.getEvTypeID().equals("EVTYPE006"))
			return "/evs0205_salesman.jsp";
		else
			return "/evs0205.jsp";
	}
}
