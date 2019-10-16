package com.ait.ar.arBalance;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap ;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;

public class ArBalanceDAO {
	private static ServiceLocator services;
	
	private CommonSQLMapAdapter commonSQLMapAdapter = null;   
	
	public ArBalanceDAO() {
        try {
            services = ServiceLocator.getInstance();
            commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
        } catch (ServiceLocatorException ex) {
        }
    }
	
	public List getArBlanlance(Object parameterObject)
	{
		List list = new ArrayList() ;
		
		SimpleMap parameter = (SimpleMap)parameterObject ;
		
		Connection con = null;
        CallableStatement cs = null;
        String sqlPrc = "{ call ar_detail_balance(?) }";
		try
		{
			new Dao_ardetail() ; //导入dicc老考勤系统的数据
			
			con = services.getConnection();
            cs = con.prepareCall(sqlPrc);
            cs.setString(1, parameter.getString("AR_MONTH")) ;
            cs.execute();
            
            list = commonSQLMapAdapter.executeQueryForMulti("ar.common.arBalanceList",parameterObject);
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}
		finally 
		{
            SqlUtil.close(cs, con);
        }
		
		return list ;
	}
}
