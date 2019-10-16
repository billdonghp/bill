package com.ait.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.ga.business.GaServices;
import com.ait.gm.business.WasteServices;
import com.ait.hrm.business.HrmServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.service.SysService;
import com.ait.util.GetUserName;
import com.ait.util.StringUtil;
import com.ait.utils.MenuBiz;
import com.ait.utils.MenuEnt;

public class LoginCommand implements Command {

	private SysService sysService;

	private UserConfiguration userConfig;

	private static final String defaultSysFile = "/system.properties";

	public LoginCommand() {

		sysService = SysService.getInstance();
		userConfig = UserConfiguration.getInstance(defaultSysFile);
	}

	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//发令执行
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(Calendar.getInstance().getTime());
		HrmServices services = HrmServices.getInstance();
		WasteServices ws = new WasteServices();
		HttpSession httpsession = request.getSession(true);
		AdminBean admin ;
		GetUserName getUserName = new GetUserName();
		String username = request.getAttribute("a")!=null?StringUtil.checkNull(request.getAttribute("a")):request.getParameter("a");	
		String cpnyId=request.getAttribute("b")!=null?StringUtil.checkNull(request.getAttribute("b")):request.getParameter("b");	
		try
		{
			admin=(AdminBean)request.getSession(false).getAttribute("admin");		
			
			//System.out.println(" username "+username);
			//System.out.println(" cpnyId  "+cpnyId);
			admin = sysService.searchEmp(username,cpnyId);	
			
			if (admin == null) {
				return "/loginfailed.jsp";
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "/loginfailed.jsp";
		}
		
		String loginEmpName = admin.getChineseName();
		String loginEmpId = admin.getAdminID();
		
		try {
			//记录最后一次登陆IP和时间
			String userIP = request.getRemoteAddr();
			sysService.updateUserIP(userIP,admin.getPersonId());	
			
			Logger.getLogger(getClass()).debug("-------------执行发令------------");
			//调动发令
			SimpleMap expMap = new SimpleMap();
			SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
			String date1 = format1.format(Calendar.getInstance().getTime());
			
			GaServices gaServices = new GaServices();
			
			/*List cardEndDateList = gaServices.getCardEndDate();
			
			for(int i=0 ; i<cardEndDateList.size() ; i++){
				expMap = (SimpleMap) cardEndDateList.get(i);
				String cardId = expMap.getString("CARD_ID");
				expMap.set("cardId", cardId);
				
				expMap.set("loginEmpName", admin.getChineseName());
				expMap.set("loginEmpId", admin.getAdminID());
				
				String cardEndDate = expMap.getString("CARD_ENDDATE");
				
				SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
				
				String enddate = format1.format(format2.parse(cardEndDate));
				
				if(date1.equals(enddate)){
					gaServices.updateCardStatus(expMap);
				}
			}*/
			
			Logger.getLogger(getClass()).debug("-------------调动发令完成------------");

		} catch (Exception e) {
			e.printStackTrace() ;			
			Logger.getLogger(getClass()).error("执行发令失败" + e.toString());
		}

		try {
			Object language = request.getSession(true).getAttribute("language");
			Object country = request.getSession(true).getAttribute("country");

			if (admin == null) {
				return "/loginfailed.jsp";
			}
			HttpSession session = request.getSession(true);
 
			// 设置语言环境
			Locale locale = request.getLocale() ;
//			if (language == null) {
//
//				admin.setLanguagePreference(locale.getLanguage());
//				admin.setCountryPreference(locale.getCountry());
//			} else {
//				
//				admin.setLanguagePreference(language.toString());
//				admin.setCountryPreference(country.toString());
//			}
//			
//			// 韩文默认显示英文画面
//			if (admin.getLanguagePreference().equals("ko")) {
//				admin.setLanguagePreference("en");
//				admin.setCountryPreference("US");
//			}
					
			admin.setLanguagePreference("zh");
			admin.setCountryPreference("CN");
			Logger.getLogger(getClass()).debug("language: " + admin.getLanguagePreference());
			Logger.getLogger(getClass()).debug("country: " + admin.getCountryPreference());

			session.setAttribute("admin", admin);
			//	设置静态线程变量
			ApplicationContext.setAdminBean(request, response);
			
			//TipMessageService tms = new TipMessageService(admin);
			///tms.setTipMessageInSession(request);
			this.setMenuListByPopedom(admin, session);
			
			request.getSession(true).removeAttribute("language");
			request.getSession(true).removeAttribute("country");
			
			return "/controlServlet?operation=main";
		} catch (Exception e) {
			e.printStackTrace();
			return "/loginfailed.jsp";
		}
	}

	/**
	 * 根据权限设置可以访问的菜单列表
	 * 
	 * @param admin
	 * @param request
	 */
	private void setMenuListByPopedom(AdminBean admin, HttpSession session) throws Exception {

		MenuBiz menubiz = new MenuBiz();
		String rodeLevel = admin.getScreenGrantNo() != null ? admin.getScreenGrantNo() : "";

		// 取得1级菜单对象列表(包含1级菜单对象)
		List<MenuEnt> topMenuList = menubiz.getTopMenu(rodeLevel);
		// 取得2级菜单对象列表(包含2级菜单对象)
		List<List> secondMenuList = new ArrayList<List>();
		for (MenuEnt topEnt : topMenuList) {

			// 根据1级菜单取得2级菜单对象列表
			secondMenuList.add(menubiz.thirdmenulist2(topEnt.getMenuCode(), rodeLevel));
		}

		//  取得3级菜单对象列表(包含3级菜单对象)
		List<List> thirdMenuList = new ArrayList<List>();
		for (MenuEnt topEnt : topMenuList) {

			// 根据1级菜单取得2级菜单对象列表
			List<MenuEnt> secondList = menubiz.thirdmenulist2(topEnt.getMenuCode(), rodeLevel);
			List<List> tempList = new ArrayList<List>();
			for (MenuEnt secondEnt : secondList) {

				// 根据2级菜单取得3级菜单对象列表
				tempList.add(menubiz.thirdmenulist2(secondEnt.getMenuCode(), rodeLevel));
			}
			thirdMenuList.add(tempList);
		}

		// 取得全部可显示菜单id
		List<String> allModuleNames = null;
			
		if(admin.getCpnyId().equals("59000000")){
			allModuleNames=java.util.Arrays.asList(userConfig.getString("global.module.name1").split(","));
		}else if(admin.getCpnyId().equals("78000000")){			
			allModuleNames=java.util.Arrays.asList(userConfig.getString("global.module.name2").split(","));
		}else{			
			allModuleNames=java.util.Arrays.asList(userConfig.getString("global.module.name").split(","));
		}
		List<MenuEnt> modules = new ArrayList<MenuEnt>();
		// 取得不可用菜单列表
		for (String moduleName : allModuleNames) {

			MenuEnt menuEnt = (MenuEnt) sysService.getMenuEnt(moduleName);
			if (!topMenuList.contains(menuEnt))
				modules.add(menuEnt);
		}
		session.setAttribute("topMenuList", topMenuList);
		session.setAttribute("secondMenuList", secondMenuList);
		session.setAttribute("thirdMenuList", thirdMenuList);
		session.setAttribute("moduleNames", allModuleNames);
		session.setAttribute("modules", modules);
	}
}