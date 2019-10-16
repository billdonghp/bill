package com.ait.ess.base;


public class DAOBase  {
	/*protected DataSource dataSource;
	protected PlatformTransactionManager transactionManager;
	protected JdbcTemplate jdbcTemplate;
	protected ApplicationContext ctx;
	public String sqlString = null;
	public static String format = "yyyy-MM-dd HH:mm:ss.SSS";
	
	private int returnValue;
	
	// data source
	public void setDataSource(DataSource ds) {
		dataSource = ds;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	// transaction manager
	public void setTransactionManager(PlatformTransactionManager tm) {
		transactionManager = tm;
	}
	
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}
	
	//jdbcTemplate
	public void setJdbcTemplate(JdbcTemplate jt){
		jdbcTemplate = jt;
	}
	
	public JdbcTemplate getJdbcTemplate(){
		return jdbcTemplate;
	}
	
	// application context
	public void setApplicationContext(ApplicationContext appCtx) {
		ctx = appCtx;
	}
	
	public ApplicationContext getApplicationContext() {
		return ctx;
	}
	
	//update
	protected int update(String sqlString){
		try{
			returnValue = getJdbcTemplate().update(sqlString);
		}catch(DataAccessException daex){
			returnValue = -1;
			Logger.getLogger(getClass()).error(daex.toString());
		}catch(Exception ex){
			returnValue = -2;
			Logger.getLogger(getClass()).error("UNKNOW EXCEPTION! " + ex.toString());
		}
		Logger.getLogger(getClass()).debug("execute update result value:" + returnValue);
		return returnValue;
	}
	
	//get new id for test
	
	protected synchronized int getNewId(String tableName, String fieldName){
		String sqlString = 
					"select " +
						"max(" + fieldName + ") " +	
					"from " +
						tableName + " ";
		Logger.getLogger(getClass()).debug(sqlString);
		int newId = -1;
		final JdbcTemplate jt = getJdbcTemplate();
		try{
			newId = jt.queryForInt(sqlString) + 1;
		}catch(IncorrectResultSizeDataAccessException irsdaex){
			Logger.getLogger(getClass()).error(irsdaex.toString());
			newId = 0;
		}
		catch(DataAccessException daex){
			Logger.getLogger(getClass()).error(daex.toString());
			newId = -2;
		}catch(Exception ex){
			Logger.getLogger(getClass()).error("UNKNOW EXCEPTION! " + ex.toString());
			newId = -3;
		}
		return newId;
	}
	*/
}