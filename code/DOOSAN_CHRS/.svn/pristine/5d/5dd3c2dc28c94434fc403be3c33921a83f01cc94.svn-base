package com.ait.api.cmd;

import com.ait.api.model.R;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.bean.GaAffirmList;
import com.ait.ga.business.ExpressApplyAndAffirmServices;
import com.ait.ga.business.SealMangerSerivces;
import com.ait.ga.cmd.visit.GaAffirm;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.service.SysService;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ait.utils.FormUtil;
import com.ait.web.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplyExpressCmd extends AbstractCmd implements Command{

	private ExpressApplyAndAffirmServices eaaServices = null;
	private Mail mail = null;
	private EssSysparam essSysparam = null;

	public ApplyExpressCmd() {
		eaaServices = new ExpressApplyAndAffirmServices();
		mail = new Mail();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Map map = getData(request);
			String content = StringUtil.checkNull(map.get("content"));
			//快件申请页面所需信息
			if ("expressApply".equals(content)) {
					this.expressApply(map,response);
				//公章编号
			} else if("getAffirmor".equals(content)) {
				this.getAffirmor(map,response);
			}
		} catch (Exception e) {
			response.getWriter().append(R.toJson(R.error()));
		}
		return null;
	}

	/* 增加快件申请信息,同时添加决裁信息 */
	public void expressApply(Map map, HttpServletResponse response) throws Exception {
		AdminBean admin = SysService.getInstance().searchEmp(map.get("empid").toString(),map.get("cpnyId").toString());
		essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, admin.getCpnyId());
		GaAffirm gaAffirm = new GaAffirm();

		boolean temp = false;
		SimpleMap parameterObject = null;
		List detailList = new ArrayList();

		List affirorList = new ArrayList();
		String[] affirmorId = StringUtil.checkNull(map.get("affirmorIdArr")).split(",");
		String[] affirmorDuty = StringUtil.checkNull(map.get("affirmorDutyArr")).split(",");
		String isOver_kg = StringUtil.checkNull(map.get("isOver_kg"));
		String personId=admin.getPersonId();
		int level = 1;
		for(int i=0;i<affirmorId.length;i++){
			if (isOver_kg.equals("0")){
				if (!affirmorDuty[i].equals("C_12005_43")){
					GaAffirmList vb = new GaAffirmList();
					int affirmFlag=0;
					if(personId.equals(affirmorId[i])){
						affirmFlag=1;
					}
					vb.setAffirmFlag(affirmFlag);
					vb.setAffirmLevel(level);
					level++;
					vb.setAffirmorId(affirmorId[i]);
					affirorList.add(vb);
				}
			}else{
				GaAffirmList vb = new GaAffirmList();
				int affirmFlag=0;
				if(personId.equals(affirmorId[i])){
					affirmFlag=1;
				}
				vb.setAffirmFlag(affirmFlag);
				vb.setAffirmLevel(i+1);
				vb.setAffirmorId(affirmorId[i]);
				affirorList.add(vb);
			}
		}


			parameterObject = (SimpleMap)map;
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());

			int Sequence = this.getSequence("GA_EXPRESS_APPLY_SEQ");
			parameterObject.set("applyno", Sequence);
			/*String SENDPERSON1 = StringUtil.checkNull(map.get("SENDPERSON1"));
			double costs = NumberUtil.parseDouble(map.get("costs"));
			String citySent = StringUtil.checkNull(map.get("citySent"));*/
			//System.out.print(SENDPERSON1+costs+citySent);

			// bind apply detail parameter
			/*int size = parameterObject.getInt("maxRowNum");
			SimpleMap map;
			for (int i=0; i<=size; i++) {

				//map = ObjectBindUtil.getData(request, "_"+i);
				if (map.keySet().size() > 0) {
					int Sequence = this.getSequence("GA_EXPRESS_APPLY_SEQ");
					parameterObject.set("applyno", Sequence);
					map.setString("SENDPERSON1", SENDPERSON1);
					map.setDouble("costs", costs);
					map.setString("citySent", citySent);
					map.setString("applerId", admin.getAdminID());
					map.setString("deptId", admin.getDeptID());
					map.setInt("applyno", Sequence);
					map.setString("adminId", admin.getAdminID());
					map.setString("cpnyId", admin.getCpnyId());
					map.setString("isOver_kg", isOver_kg);
				}
			}*/
			parameterObject.set("SENDPERSON1",parameterObject.get("sendperson1"));
			detailList.add(parameterObject);

			// apply present
			parameterObject.set("affirm", affirorList);
			parameterObject.set("detail", detailList);

			//temp = eaaServices.addExpressApply(parameterObject, affirorList);

			temp = eaaServices.addExpressApplyMany(parameterObject, affirorList);

			if (temp) {

				String affirorID = affirmorId[0];
				parameterObject.set("applerId", affirorID);
				String toEmail = eaaServices.getapplyemail(parameterObject);

				SimpleMap dateMap = parameterObject;
				if (!toEmail.equals("") && toEmail != null
						&& essSysparam.isGaSendMail()) {
					for (int j = 0; j < detailList.size() ; j++){
						SimpleMap s = (SimpleMap)detailList.get(j);

						sendApplyPlanMail("快件申请 请您确认", affirorID, admin
										.getChineseName(), toEmail, s.getString("cityarrive"),
								s.getString("postDescription"),
								admin.getCpnyId());
					}
				}
				response.getWriter().append(R.toJson(R.ok()));
			} else {
				response.getWriter().append(R.toJson(R.error("申请失败")));
			}
	}

	/* 得到序列号 */
	public int getSequence(String seqName) {
		int result = 0;
		Connection conn = ConnBean.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT " + seqName + ".NEXTVAL FROM DUAL";
		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				result = rs.getInt(1);
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取得序列号失败", e);
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return result;
	}

	private void sendApplyPlanMail(String title, String adminid,
								   String applyer, String toEmail, String citySent,
								   String postDescription, String cpny_id)
			throws Exception {

		SimpleMap inputData = new SimpleMap();

		inputData.set("申请人：", applyer);
		inputData.set("寄送城市：", citySent);
		//inputData.set("收件单位：", postAddress);
		inputData.set("邮件内容：", postDescription);

		mail.gaSendMail(inputData, cpny_id, toEmail, "快件申请,等待决裁");
		// }
		// essServices.insertOtAffirmMail(inputData);
	}


	public void getAffirmor(Map map, HttpServletResponse response) throws IOException {
		Map resultMap = new HashMap();
		String applyType = StringUtil.checkNull(map.get("applyType"));
		String cpnyId = StringUtil.checkNull(map.get("cpnyId"));
		String adminId = StringUtil.checkNull(map.get("adminId"));
		String isKg = StringUtil.checkNull(map.get("isKg"));
		//获取审批线
		GaAffirm ga = new GaAffirm();
		essSysparam = (EssSysparam) SysparamUtils.getSysparam( EssSysparam.class, cpnyId);

		List list=ga.getAffirmor1(adminId, applyType, essSysparam.isContainsOwner());
		List newList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			GaAffirmList vb = (GaAffirmList) list.get(i);
			//1斤一下不需要总监审批
			if ("0".equals(isKg) && "C_12005_43".equals(vb.getAffirmorDuty())) {
				continue;
			}else{
				newList.add(vb);
			}
		}
		resultMap.put("affirmorList", newList);
		response.getWriter().append(R.toJson(R.ok(resultMap)));
	}

}
