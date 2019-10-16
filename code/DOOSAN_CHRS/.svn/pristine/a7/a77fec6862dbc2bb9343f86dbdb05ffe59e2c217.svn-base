package com.ait.api.cmd;

import com.ait.api.model.R;
import com.ait.api.service.ApiService;
import com.ait.api.service.impl.ApiServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class AffirmInfoCmd extends AbstractCmd{

	private ApiService apiService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		apiService = new ApiServiceImpl();
		response.getWriter().append(R.toJson(R.ok(apiService.affirmInfoList(request))));
		return null;
	}

	//如果查询待审批，则查询所有
		/*if (qryType == 2) {
			pageSize = 1000;
		}
		//考勤待审批
		if ("leaveaffirm".equals(content)) {
			viewLeaveAffirm(request, response);
		//加班待审批
		} else if ("otaffirm".equals(content)) {
			viewOvertimeAffirm(request, response);
		//考勤修改
		} else if ("armodifyaffirm".equals(content)) {
			viewArModifyAffirm(request, response);
		//礼品审批
		} else if ("retrievePresentAffirm".equals(content)) {
			retrievePresentAffirm( request,  response);
		//快件审批
		} else if ("expressAffirm".equals(content)) {
			getExpressAffirm(request, response);
		//公章审批
		} else if ("sealAffirm".equals(content)) {
			sealAffirm(request, response);
		//临时卡审批
		} else if ("tempCardAffirm".equals(content)) {
			getTempCardesAffirm(request, response);
		//参观者审批
		} else if ("visiterAffirm".equals(content)) {
			visiterApproval(request, response);
		//签证审批
		} else if ("visaAffirm".equals(content)) {
			visaAffirm(request, response);
		//工人评价
		} else if ("evaluateAffrim".equals(content)) {
			getEvaluateAffrimView(request, response);
		//评价成绩审批
		} else if ("evsResultAffirm".equals(content)) {
			evsResultAffirm(request, response);
		}*/

	/* 休假决裁页面 *//*
	private void viewLeaveAffirm(HttpServletRequest request,
								   HttpServletResponse response) {
		EssArDAO essArDAO = new EssArDAO();
		SimpleMap parameterObject = new SimpleMap();

		try {
			parameterObject.put("key", key);
			parameterObject.put("qryType", qryType);

			// 取得数据行数
			parameterObject.set("supervisor", super.getAdmin(request));

			List leaveList = essServices.retrieveLeaveAffirmList(parameterObject, currentPage, pageSize);

			for (int i = 0; i < leaveList.size(); i++) {
				EssLeaveBean leave = (EssLeaveBean) leaveList.get(i);
				leave.setAffirmorList((ArrayList) essArDAO.getAffirmorList(
						leave.getLeaveNo(), "LeaveApply"));
			}

			// paging parameter
			Map map = new HashMap();
			map.put("pageSize", pageSize);
			map.put("currentPage", currentPage);
			map.put("leaveList", leaveList);
			response.getWriter().append(R.toJson(R.ok(map)));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());
		}
	}

	*//* 加班决裁页 *//*
	private void viewOvertimeAffirm(HttpServletRequest request,
									  HttpServletResponse response) {
		EssArDAO essArDAO = new EssArDAO();
		String adminId = super.getAdmin(request);
		SimpleMap parameterObject = new SimpleMap();

		try {
			String key = StringUtil.checkNull(request.getParameter("key"));
			int qryType = Integer.parseInt(StringUtil.checkNull(request
					.getParameter("qryType"), "2"));

			parameterObject.set("supervisor", adminId);
			parameterObject.put("key", key);
			parameterObject.put("qryType", qryType);

			*//* paging logic *//*
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style4.pagesize");
			int pageGroupSize = config.getInt("page.style4.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// 取得数据行数
			int resultCount = essServices
					.retrieveOtAffirmListCnt(parameterObject);

			// 如果"当前页"大于最大页数,取最后一页
			if (currentPage > (resultCount % pageSize == 0 ? resultCount
					/ pageSize - 1 : resultCount / pageSize)
					&& resultCount != 0) {
				currentPage = resultCount % pageSize == 0 ? resultCount
						/ pageSize - 1 : resultCount / pageSize;
			}

			List overTimeList = essServices.retrieveOtAffirmList(
					parameterObject, currentPage, pageSize);
			EssOverTimeBean essOverTimeBean = new EssOverTimeBean();
			for (int i = 0; i < overTimeList.size(); i++) {
				essOverTimeBean = (EssOverTimeBean) overTimeList.get(i);
				parameterObject.setInt("applyNo", essOverTimeBean.getOtNo());
				parameterObject.setInt("level", essOverTimeBean
						.getAffirmLevel());
				essOverTimeBean.setAffirmorList((ArrayList) essArDAO
						.getAffirmorList(essOverTimeBean.getOtNo().intValue(),
								"OtApply"));

				SimpleMap arMonthObj = essArDAO.getArMonthDate(essOverTimeBean);

				// 取得个人已申请小时数
				essArDAO.getPersonOtTime(essOverTimeBean, arMonthObj, "arDate") ;
			}

			Map map = new HashMap();
			map.put("resultCount", resultCount);
			map.put("currentPage", currentPage);
			map.put("pageSize", pageSize);
			map.put("currentPage", currentPage);
			map.put("overTimeList", overTimeList);
			map.put("key", key);
			response.getWriter().append(R.toJson(R.ok(map)));

		} catch (Exception ex) {
			throw new GlRuntimeException(
					"View employee overtime affirm Exception. ", ex);
		}
	}

	*//* 考勤修改决裁页面 *//*
	private void viewArModifyAffirm(HttpServletRequest request,
									  HttpServletResponse response) {
		EssArDAO essArDAO = new EssArDAO();
		String adminId = super.getAdmin(request);
		SimpleMap parameterObject = new SimpleMap();

		try {
			String key = StringUtil.checkNull(request.getParameter("key"));
			int qryType = Integer.parseInt(StringUtil.checkNull(request
					.getParameter("qryType"), "2"));

			parameterObject.put("key", key);
			parameterObject.put("qryType", qryType);

			*//* paging logic *//*
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style4.pagesize");
			int pageGroupSize = config.getInt("page.style4.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// 取得数据行数
			parameterObject.set("supervisor", adminId);
			int resultCount = essServices
					.retrieveArModifyAffirmListCnt(parameterObject);

			// 如果"当前页"大于最大页数,取最后一页
			if (currentPage > (resultCount % pageSize == 0 ? resultCount
					/ pageSize - 1 : resultCount / pageSize)
					&& resultCount != 0) {
				currentPage = resultCount % pageSize == 0 ? resultCount
						/ pageSize - 1 : resultCount / pageSize;
			}

			List arModifyList = essServices.retrieveArModifyAffirmList(parameterObject, currentPage, pageSize);

			for (int i = 0; i < arModifyList.size(); i++) {
				ArDetailBack arModify = (ArDetailBack) arModifyList.get(i);
				arModify.setAffirmorList((ArrayList) essArDAO.getArModifyAffirmorList(arModify.getPkNo1()));
			}

			request.setAttribute("qryType", qryType);
			request.setAttribute("key", key);
			request.setAttribute("arModifyList", arModifyList);

			Map map = new HashMap();
			map.put("resultCount", resultCount);
			map.put("currentPage", currentPage);
			map.put("pageSize", pageSize);
			map.put("currentPage", currentPage);
			map.put("arModifyList", arModifyList);
			map.put("key", key);
			response.getWriter().append(R.toJson(R.ok(map)));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());
		}

	}

	//礼品审批
	public void retrievePresentAffirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
		List recordList = new ArrayList();
		String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
		try {
			*//* paging logic *//*
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("adminId", admin.getAdminID());
			parameterObject.setString("cpnyId", admin.getCpnyId());
			parameterObject.setString("affirmor", admin.getAdminID());
			parameterObject.setString("cpnyId", cpnyId);

			// retrieve apply list
			List<SimpleMap> applyList = services.retrievePresentApplyList(parameterObject, currentPage, pageSize);
			Object applyCount = services.retrievePresentApplyCnt(parameterObject);

			List detailList;
			List affirmList;
			for (SimpleMap applyInfo : applyList) {

				parameterObject.setString("applyNo", applyInfo.getString("SEQ_NO"));
				// retrieve detail list
				detailList = services.retrievePresentApplyDetail(parameterObject);
				// retrieve affirm list
				affirmList = services.retrievePresentAffirmList(parameterObject);

				applyInfo.set("detailList", detailList);
				applyInfo.set("affirmList", affirmList);
				recordList.add(applyInfo);
			}

			request.setAttribute("recordList", recordList);
			request.setAttribute("recordCount", Integer.parseInt(applyCount.toString()));

			// search parameter
			request.setAttribute("personId", parameterObject.getString("personId"));
			request.setAttribute("empId", parameterObject.getString("empId"));
			request.setAttribute("affirmType", parameterObject.getString("affirmType"));
			request.setAttribute("deptId", parameterObject.getString("deptId"));
			request.setAttribute("startDate", parameterObject.getString("startDate"));
			request.setAttribute("endDate", parameterObject.getString("endDate"));
			request.setAttribute("cpnyId", request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId());

			// paging parameter
			request.setAttribute("currentPage", currentPage + "");
			request.setAttribute("pageSize", pageSize + "");
			Map map = new HashMap();
			response.getWriter().append(R.toJson(R.ok(map)));
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve present affirm list Exception. ", e);
		}

	}

	*//* 根据登陆者得到决裁的信息 *//*
	public void getExpressAffirm(HttpServletRequest request, HttpServletResponse response) {
		try {

			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			//parameterObject.set("applerId", admin.getAdminID());
			SimpleMap dataMap = new SimpleMap();
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));


			*//*String companyId = request.getParameter("companyId") != null ? request
					.getParameter("companyId") : admin.getCpnyId();
			String cpnyId = request.getParameter("company") != null ? request.getParameter("company") : admin.getCpnyId();*//*

			parameterObject.put("qryType", qryType);
			parameterObject.put("key", key);
//			parameterObject.put("cpnyId", cpnyId);
			List companyList = eaaServices.getCompany(parameterObject);

			// 取得数据行数
			int resultCount = eaaServices
					.getExpressAffirmListNumber(parameterObject);
			List ExpressAffirmList = eaaServices.getExpressAffirmList(
					parameterObject, currentPage, pageSize);
			for (int i = 0; i < ExpressAffirmList.size(); i++) {
				SimpleMap parameterObject1 = new SimpleMap();
				dataMap = (SimpleMap) ExpressAffirmList.get(i);
				parameterObject1.set("applyno", dataMap.get("APPLY_NO"));
				List affirmorList = eaaServices
						.getExpressAffirmorList(parameterObject1);
				dataMap.set("affirmorList", affirmorList);
			}
			request.setAttribute("companyList", companyList);
			request.setAttribute("qryType", qryType);
			request.setAttribute("key", key);
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("expressAffirmList", ExpressAffirmList);

			if (ExpressAffirmList == null || ExpressAffirmList.size() == 0) {
				request.setAttribute("errorMsg", "没有您相关的决裁信息！");
			}
			Map map = new HashMap();
			response.getWriter().append(R.toJson(R.ok(map)));
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"getExpressAffirm happens Exception. ", e);
		}
	}

	*//* 得到公章决裁和决裁情况的信息 *//*
	public void sealAffirm(HttpServletRequest request, HttpServletResponse response) {
		try {
			*//*String adminId = ((AdminBean) request.getSession(false)
					.getAttribute("admin")).getAdminID();
			String adminName = ((AdminBean) request.getSession(false)
					.getAttribute("admin")).getChineseName();
			SimpleMap parameterObject = null;
			SimpleMap map = null;
			SimpleMap map1 = null;
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());

			String qryType = request.getParameter("qryType") != null ? request
					.getParameter("qryType") : "0";
			String deptid = request.getParameter("deptid") != null ? request
					.getParameter("deptid") : "";
			String endDate = request.getParameter("endDate") != null ? request
					.getParameter("endDate") : "";
			String startDate = request.getParameter("startDate") != null ? request
					.getParameter("startDate")
					: "";
			String key = request.getParameter("key") != null ? request
					.getParameter("key") : "";
			String cpnyId = request.getParameter("cpnyId");
			parameterObject.put("qryType", qryType);
			parameterObject.put("deptid", deptid);
			parameterObject.put("endDate", endDate);
			parameterObject.put("startDate", startDate);
			parameterObject.put("key", key);
			parameterObject.put("cpnyId", cpnyId);

			int pageSize = 10;
			int pageGroupSize = 10;
			int currentPage = 0;
			SimpleMap dataMap = null;
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// 取得数据行数
			int resultCount = smServices
					.getSealAffirmAndInformationListNumber(parameterObject);
			List applyList = smServices.getSealAffirmAndInformationList(
					parameterObject, currentPage, pageSize);
			for (int i = 0; i < applyList.size(); i++) {
				map = (SimpleMap) applyList.get(i);
				parameterObject.set("applyno", map.get("DOCUMENTNO"));
				map.set("applyId", map.get("DOCUMENTNO"));
				List affirmorList = smServices
						.getSealAffirmAndInformationAffirmorList(parameterObject);
				String affirmNo = "";
				String empid = "";
				String tempapplyId = "";
				for (int j = 0; j < affirmorList.size(); j++) {
					map1 = (SimpleMap) affirmorList.get(j);
					affirmNo = map1.getString("GA_AFFIRM_NO");
					String AFFIRMORIDEA = map1.getString("AFFIRMORIDEA");
					empid = map1.getString("AFFIRMOR_ID");
					map.set("AFFIRMORIDEA", AFFIRMORIDEA);
					if (empid.equals(adminId.toString())) {
						map.set("affirmNo", affirmNo);
					}
					tempapplyId = map1.getString("APPLY_NO");
				}
				request.setAttribute("empid", empid);
				request.setAttribute("tempapplyId", tempapplyId);
				map.set("affirmorList", affirmorList);
			}
			request.setAttribute("adminId", adminId);
			request.setAttribute("cpnyId", admin.getCpnyId());
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("applyList", applyList);
			request.setAttribute("loginAdmin", admin.getAdminID());

			request.setAttribute("qryType", qryType);
			request.setAttribute("deptid", deptid);
			request.setAttribute("endDate", endDate);
			request.setAttribute("startDate", startDate);
			request.setAttribute("key", key);
			request.setAttribute("cpnyId", cpnyId);
			if (applyList == null || applyList.size() == 0) {
				request.setAttribute("errorMsg", "没有您相关的决裁信息！");
			}*//*
			Map map = new HashMap();
			response.getWriter().append(R.toJson(R.ok(map)));
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"getSealAffirmAndInformation happens Exception. ", e);
		}
	}

	*//*临时卡审批*//*
	public void getTempCardesAffirm(HttpServletRequest request, HttpServletResponse response){
		try{

			*//*SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("adminId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			String qryType = request.getParameter("qryType")!=null?request.getParameter("qryType"):"0";
			String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
			parameterObject.set("qryType", qryType);
			parameterObject.set("cpnyId", cpnyId);
			int pageSize =10;
			int pageGroupSize =10;
			int currentPage = 0;
			SimpleMap dataMap = new SimpleMap();
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar today = Calendar.getInstance();
			String startDate = request.getParameter("startDate")!=null?request.getParameter("startDate"):sdf.format(today.getTime());
			String endDate = request.getParameter("endDate")!=null?request.getParameter("endDate"):sdf.format(today.getTime());
			parameterObject.set("startDate", startDate);
			parameterObject.set("endDate", endDate);

			//取得数据行数
			int resultCount = eatingCardes.getTempCardAffirmListNumber(parameterObject);
			List tempCardAffirmList=eatingCardes.getTempCardAffirmList(parameterObject,currentPage,pageSize);
			for(int i=0;i<tempCardAffirmList.size();i++){
				SimpleMap parameterObject1 =  new SimpleMap();
				dataMap=(SimpleMap)tempCardAffirmList.get(i);
				parameterObject1.set("applyNo", dataMap.get("APPLY_NO"));
				List affirmorList=eatingCardes.getTempCardAffirmorList(parameterObject1);
				dataMap.set("affirmorList", affirmorList);
				dataMap.set("permissionsTypeName",this.getPermissionsTypeName(dataMap.getString("PERMISSIONS_TYPE"), admin));

			}
			request.setAttribute("adminid", admin.getAdminID());
			request.setAttribute("cpnyId", request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId());
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("qryType", request.getParameter("qryType"));
			request.setAttribute("deptid", request.getParameter("deptid"));
			request.setAttribute("key", request.getParameter("key"));
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("loginAdmin", admin.getAdminID());
			request.setAttribute("tempCardAffirmList", tempCardAffirmList);
			request.setAttribute("qryType", qryType);*//*
			Map map = new HashMap();
			response.getWriter().append(R.toJson(R.ok(map)));

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("eatingCardAffirmList happens Exception. ", e);
		}
	}
	//参观者审批
	private void visiterApproval(HttpServletRequest request, HttpServletResponse response) {
		*//*String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String adminName = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getChineseName();
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		SimpleMap map = null;
		SimpleMap map1 = null;
		SimpleMap map2 = null;
		try {

			*//**//* paging logic *//**//*
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// bind parameter
			String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("ADMIN_ID", adminId);

			request.setAttribute("adminName", adminName);
			//ADMIN_ID parameterObject.se
			int allvisiterApplyInformationCount = visiterApplicationsServices.allvisiterApplyInformationCount(parameterObject);
			request.setAttribute("allvisiterApplyInformationCount", allvisiterApplyInformationCount+"");

			List allvisiterApplyInformation = visiterApplicationsServices.allvisiterApplyInformation(parameterObject, currentPage, pageSize);

			int allvisiterApplyInformationSize = allvisiterApplyInformation.size();
			for(int i=0 ; i<allvisiterApplyInformationSize ; i++){
				map = (SimpleMap) allvisiterApplyInformation.get(i);
				String applyId = map.getString("GA_VISITER_APPLY_ID");
				parameterObject.set("applyId", applyId);
				map.set("applyId", applyId);
				List allVisiterApproval = visiterApplicationsServices.allVisiterApproval(parameterObject);
				String affirmNo = "";
				String empid = "";
				String tempapplyId = "";
				for(int j=0 ; j<allVisiterApproval.size() ; j++){
					map1 = (SimpleMap) allVisiterApproval.get(j);
					affirmNo = map1.getString("GA_AFFIRM_NO");
					empid = map1.getString("AFFIRMOR_ID");
					if(empid.equals(adminId.toString())){
						map.set("affirmNo", affirmNo);
					}
					tempapplyId = map1.getString("APPLY_NO");
				}
				request.setAttribute("empid", empid);
				request.setAttribute("tempapplyId", tempapplyId);
				map.set("allVisiterApproval", allVisiterApproval);
			}
			request.setAttribute("startDate", request.getParameter("startDate"));
			request.setAttribute("endDate", request.getParameter("endDate"));
			request.setAttribute("cpnyId", request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId());
			request.setAttribute("qryType", request.getParameter("qryType")!=null?request.getParameter("qryType"):0);
			request.setAttribute("deptid", request.getParameter("deptid"));
			request.setAttribute("key", request.getParameter("key"));
			request.setAttribute("allvisiterApplyInformation", allvisiterApplyInformation);
			request.setAttribute("recordCount", NumberUtil
					.parseInt(allvisiterApplyInformationCount));
			// paging parameter
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("visiterApproval error ", e);
		}*//*

		Map map = new HashMap();
		try {
			response.getWriter().append(R.toJson(R.ok(map)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*//* 得到签证决裁和决裁情况的信息 *//*
	public void visaAffirm(HttpServletRequest request, HttpServletResponse response) {
		*//*try {
			String adminId = ((AdminBean) request.getSession(false)
					.getAttribute("admin")).getAdminID();
			String adminName = ((AdminBean) request.getSession(false)
					.getAttribute("admin")).getChineseName();
			SimpleMap parameterObject = null;
			SimpleMap map = null;
			SimpleMap map1 = null;
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());

			String qryType = request.getParameter("qryType") != null ? request.getParameter("qryType") : "0";
			String deptid = request.getParameter("deptid") != null ? request.getParameter("deptid") : "";
			String endDate = request.getParameter("endDate") != null ? request.getParameter("endDate") : "";
			String startDate = request.getParameter("startDate") != null ? request.getParameter("startDate")	: "";
			String key = request.getParameter("key") != null ? request.getParameter("key") : "";
			String cpnyId = request.getParameter("cpnyId");
			parameterObject.put("qryType", qryType);
			parameterObject.put("deptid", deptid);
			parameterObject.put("endDate", endDate);
			parameterObject.put("startDate", startDate);
			parameterObject.put("key", key);
			parameterObject.put("cpnyId", cpnyId);

			int pageSize = 10;
			int pageGroupSize = 10;
			int currentPage = 0;
			SimpleMap dataMap = null;
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// 取得数据行数
			int resultCount = smServices.getSealAffirmAndInformationListNumber(parameterObject);
			List applyList = smServices.getSealAffirmAndInformationList(parameterObject, currentPage, pageSize);
			for (int i = 0; i < applyList.size(); i++) {
				map = (SimpleMap) applyList.get(i);
				parameterObject.set("applyno", map.get("DOCUMENTNO"));
				map.set("applyId", map.get("DOCUMENTNO"));
				List affirmorList = smServices.getSealAffirmAndInformationAffirmorList(parameterObject);
				String affirmNo = "";
				String empid = "";
				String tempapplyId = "";
				for (int j = 0; j < affirmorList.size(); j++) {
					map1 = (SimpleMap) affirmorList.get(j);
					affirmNo = map1.getString("GA_AFFIRM_NO");
					String AFFIRMORIDEA = map1.getString("AFFIRMORIDEA");
					empid = map1.getString("AFFIRMOR_ID");
					map.set("AFFIRMORIDEA", AFFIRMORIDEA);
					if (empid.equals(adminId.toString())) {
						map.set("affirmNo", affirmNo);
					}
					tempapplyId = map1.getString("APPLY_NO");
				}
				request.setAttribute("empid", empid);
				request.setAttribute("tempapplyId", tempapplyId);
				map.set("affirmorList", affirmorList);
			}
			request.setAttribute("adminId", adminId);
			request.setAttribute("cpnyId", admin.getCpnyId());
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("applyList", applyList);
			request.setAttribute("loginAdmin", admin.getAdminID());

			request.setAttribute("qryType", qryType);
			request.setAttribute("deptid", deptid);
			request.setAttribute("endDate", endDate);
			request.setAttribute("startDate", startDate);
			request.setAttribute("key", key);
			request.setAttribute("cpnyId", cpnyId);
			if (applyList == null || applyList.size() == 0) {
				request.setAttribute("errorMsg", "没有您相关的决裁信息！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"getSealAffirmAndInformation happens Exception. ", e);
		}*//*

		Map map = new HashMap();
		try {
			response.getWriter().append(R.toJson(R.ok(map)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	*//*得到评价决裁信息*//*
	public void getEvaluateAffrimView(HttpServletRequest request, HttpServletResponse response){
		*//*EssArDAO essArDAO = new EssArDAO();
		cpnyId=((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		String adminId = admin.getAdminID();
		SimpleMap parameterObject = new SimpleMap();
		parameterObject = ObjectBindUtil.getData(request);
		parameterObject.set("applerId", admin.getAdminID());
		//List deptList = new ArrayList();

		try {

			String key = StringUtil.checkNull(request.getParameter("key"));
			int qryType = Integer.parseInt(StringUtil.checkNull(request
					.getParameter("qryType"), "2"));


			parameterObject.put("key", key);
			parameterObject.put("qryType", qryType);
			parameterObject.set("ADMIN_ID", admin.getAdminID());
			parameterObject.set("cpnyId", admin.getCpnyId());


			List deptList=evServices.getEvaluateAffirmDeptList(parameterObject);
			List positionList=evServices.getEvaluateAffirmPositionList(parameterObject);

			String yearMonth = request.getParameter("year") + request.getParameter("month");

			if(request.getParameter("selectDept")== null){
				if(deptList.size()>0){
					SimpleMap s =(SimpleMap)deptList.get(0);
					String sd = s.getString("DEPTID");
					//parameterObject.set("dept", sd);
					//request.setAttribute("selectDept", sd);
				}
			}else{
				parameterObject.set("dept", request.getParameter("selectDept"));
				request.setAttribute("selectDept", request.getParameter("selectDept"));
			}

			if(request.getParameter("selectPosition")== null){
				if(positionList.size()>0){
					SimpleMap p =(SimpleMap)positionList.get(0);
					String pl = p.getString("AFFIRM_POSITION");
					parameterObject.set("position", pl);
					request.setAttribute("selectPosition", pl);
				}
			}else{
				parameterObject.set("position", request.getParameter("selectPosition"));
				request.setAttribute("selectPosition", request.getParameter("selectPosition"));
			}

			if(request.getParameter("year")==null || request.getParameter("month")==null){

				// 取日期参数
				GregorianCalendar currentDay = new GregorianCalendar();
				currentDay.add(2,-1);//月份减一
				int m = currentDay.get(Calendar.MONTH) + 1;
				int y = currentDay.get(Calendar.YEAR);

				String month = ("0" + String.valueOf(m)).substring(("0" + String
						.valueOf(m)).length() - 2, ("0" + String.valueOf(m)).length());
				String year = String.valueOf(y);

				parameterObject.set("yearMonth", year + month);
				request.setAttribute("year", year);
				request.setAttribute("month", month);

			}else{
				parameterObject.set("yearMonth", yearMonth);
				request.setAttribute("year", request.getParameter("year"));
				request.setAttribute("month", request.getParameter("month"));
			}






			//parameterObject.set("dept", request.getParameter("selectDept"));
			//parameterObject.set("position", request.getParameter("selectPosition"));
			//parameterObject.set("yearMonth", yearMonth);

			List itemList = evServices.getEvaluateItem(parameterObject.getString("yearMonth"),parameterObject.getString("dept"),parameterObject.getString("position"),parameterObject.getString("ADMIN_ID"),2);


			//System.out.println("************************"+itemName);

			*//**//* paging logic *//**//*
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style6.pagesize");
			int pageGroupSize = config.getInt("page.style6.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// 取得数据行数
			parameterObject.set("supervisor", adminId);

			int resultCount = evServices.getEvaluateAffirmListCnt(parameterObject);
			// 如果"当前页"大于最大页数,取最后一页
			if (currentPage > (resultCount % pageSize == 0 ? resultCount
					/ pageSize - 1 : resultCount / pageSize)
					&& resultCount != 0) {
				currentPage = resultCount % pageSize == 0 ? resultCount
						/ pageSize - 1 : resultCount / pageSize;
			}


			List evaluateList=evServices.getEvaluateAffirmList(parameterObject,currentPage,pageSize);


			for (int i = 0; i < evaluateList.size(); i++) {
				EvaluateInfo evi = (EvaluateInfo) evaluateList.get(i);
				//parameterObject.setInt("applyNo", leave.getLeaveNo());
				///parameterObject.setInt("level", leave.getAffirmLevel());

				// leave.setNext_flag(essServices.retrieveApplyAffirmNextFlag(parameterObject))
				// ;
				// 上级决裁"已通过"或者不存在上级决裁, 并且人事未否决时才能进行操作
				if ((evi.getNext_flag() == 0 && evi.getUp_flag() == 1
						//		&& arModify.getActivity() != 2
				)
						|| evi.getMaxLevel_flag() == 1) {
					// 决裁状态为"待决裁"
					if (evi.getAffirm_flag() == 0)
						// 可进行"通过"和"否决"
						evi.setOpFlag(0);
						// 决裁后可进行修改并且状态为"已通过"
					else if (essSysparam.isLeaveModifiedAfterAffirm()
							&& evi.getAffirm_flag() == 1)
						// 可进行"否决"
						evi.setOpFlag(2);
						// 决裁后可进行修改并且状态为"否决"
					else if (essSysparam.isLeaveModifiedAfterAffirm()
							&& evi.getAffirm_flag() == 2)
						// 可进行"通过"
						evi.setOpFlag(1);
				}
				evi.setAffirmorList((ArrayList) evServices.getAffirmorList(evi.getNo()));

			}

			MessageSource messageSource = new MessageSource("ess", admin
					.getLocale(), "UTF-8");
			String message1 = messageSource
					.getMessage("display.ess.approval.pending");
			String message2 = messageSource
					.getMessage("display.ess.approval.approved");
			String message3 = messageSource
					.getMessage("display.ess.approval.rejected");

			Map statusMap = new HashMap();
			Map colorMap = new HashMap();
			if (evaluateList.size() > 0) {
				statusMap.put("0", message1);
				statusMap.put("1", message2);
				statusMap.put("2", message3);

				colorMap.put("0", "#990099");
				colorMap.put("1", "#00CC00");
				colorMap.put("2", "#CC0000");
			}


			request.setAttribute("qryType", qryType);
//				request.setAttribute("deptID", deptID);
//				request.setAttribute("deptList", deptList);
			request.setAttribute("key", key);
			request.setAttribute("positionList",positionList);
			request.setAttribute("deptList",deptList);
			//request.setAttribute("selectDept", request.getParameter("selectDept"));
			//request.setAttribute("selectPosition", request.getParameter("selectPosition"));
			//request.setAttribute("year", request.getParameter("year"));
			//request.setAttribute("month", request.getParameter("month"));
			//request.setAttribute("itemName",itemName);
			request.setAttribute("itemList",itemList);

			request.setAttribute("statusMap", statusMap);
			request.setAttribute("colorMap", colorMap);
			request.setAttribute("evaluateList", evaluateList);

			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			if(evaluateList==null ||evaluateList.size()==0){
				request.setAttribute("errorMsg", "暂无相关信息,请尝试其他查询条件！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());
		}*//*

		Map map = new HashMap();
		try {evs0307_modify_affirm.jsp
			response.getWriter().append(R.toJson(R.ok(map)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//评价成绩审批
	public void evsResultAffirm(HttpServletRequest request, HttpServletResponse response){
		*//*EvsCraft evscraft = new EvsCraft();
		EvsGxjndj evsGxjndj = new EvsGxjndj();
		Evsupcode evSupcode = new Evsupcode();
		String qryType = "";
		qryType=request.getParameter("qryType")!=null?request.getParameter("qryType"):qryType;
		if(qryType.equals("")){
			qryType=qryType+"";
		}

		SimpleMap parameterObject =new SimpleMap();
		parameterObject.set("supervisor", super.getAdmin(request));
		parameterObject.setString("qryType", qryType);
		List levStypecodeid= null;
		try {
			levStypecodeid = evsGxjndj.getEvsSetupCodeconfirm(parameterObject);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		if(levStypecodeid!=null) {
			for (int i = 0; i < levStypecodeid.size(); i++) {
				evSupcode = (Evsupcode) levStypecodeid.get(i);
				essAffirmorList = evSupcode.getAffirmorList();

				int applyNo = evSupcode.getPkNo1();
				int affirmNo = 0;
				String person_id = "";
				int opFlag = evSupcode.getOpFlag();



			}
		}*//*
		Map map = new HashMap();
		try {
			response.getWriter().append(R.toJson(R.ok(map)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

}
