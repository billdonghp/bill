package com.ait.ev.dao;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
/***********************************************************************
 *   
 *   ****所有，受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。   
 *   @copyright       Copyright: AIT
 *   @author         (shihuili@ait.net.cn) 
 *   @create-time   2014-8-1   下午15:27:58     
 *   @revision      1.0
 ***********************************************************************/
public class RegularlyEv {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;
	
private static final Logger logger = Logger.getLogger(RegularlyEv.class);

public RegularlyEv() {

	commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
}

	public void regularlyEv() throws Exception{	
			System.out.println("更新评价系统");
			try {
				commonSQLMapAdapter.delete("ess.common.deleteRegularlyEvEmp");
				commonSQLMapAdapter.insert("ess.common.insertEvEmpDeleteY");
			} catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException("deleteRegularlyEvEmp Exception. ", e);
			}
			try {
				commonSQLMapAdapter.delete("ess.common.deleteRegularlyEvDep");
				commonSQLMapAdapter.insert("ess.common.insertEvDepDeleteY");
			} catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException("deleteRegularlyEvDep Exception. ", e);
			}
			try {
				commonSQLMapAdapter.insert("ess.common.insertRegularlyEvEmp");
				commonSQLMapAdapter.insert("ess.common.insertEvEmpInsertY");
			} catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException("insertRegularlyEvEmp Exception. ", e);
			}
			try {
				commonSQLMapAdapter.insert("ess.common.insertRegularlyEvDep");
				commonSQLMapAdapter.insert("ess.common.insertEvDepInsertY");
			} catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException("insertRegularlyEvDep Exception. ", e);
			}
	}

}

