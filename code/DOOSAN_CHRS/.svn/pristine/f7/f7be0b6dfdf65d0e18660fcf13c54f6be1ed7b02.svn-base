
package com.ait.kpa.cmd.bonus.bonusformular;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.kpa.business.PaServices;
import com.ait.kpa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;

public class RetrievePaBonusItemListCmd extends PaAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.putToolbarInfo(request);
		
		PaServices services = PaServices.getInstance();
		SimpleMap parameterObject = new SimpleMap() ;
		List bonusItemList = new ArrayList() ;
	    
		try {
//			 取得数据行数
			bonusItemList = services.retrievePaBonusItemOfFormularList(parameterObject) ;
			
			request.setAttribute("bonusItemList", bonusItemList);
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve pa bonus item of formula Exception. ", e);
		}

		return "/kpa_bonus_formular.jsp?menu_code=" + request.getParameter("menu_code") ; 
	}
}