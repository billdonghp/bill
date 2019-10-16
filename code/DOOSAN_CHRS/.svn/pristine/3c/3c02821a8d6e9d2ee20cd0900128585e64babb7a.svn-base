/**
 * @Copyright AIT
 * @author qinxd
 * @date 2006-6-15
 */
package com.ait.jdbc.core;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;

import org.apache.log4j.Logger;

/**
 * Statement辅助类
 * @version 
 */
public class StatementUtils {
	private static final Logger log = Logger.getLogger(StatementUtils.class);

	
	static void setParameterValue(
	    PreparedStatement ps, int paramIndex, int sqlType, Object inValue)
	    throws SQLException {

		if (log.isDebugEnabled()) {
			log.debug("Setting SQL statement parameter value: column index " + paramIndex +
					", parameter value [" + inValue +
					"], value class [" + (inValue != null ? inValue.getClass().getName() : "null") +
					"], SQL type " + Integer.toString(sqlType));
		}

//		if (inValue == null) {
//			log.debug("column index[" + paramIndex + "]参数值为null");
//		    ps.setObject(paramIndex, inValue, sqlType);
//			return;
//			//throw new GlRuntimeException("执行查询准备时，触发参数值不能为null异常");
//		}
//		else {  // inValue != null
			if (sqlType == Types.VARCHAR) {
				ps.setString(paramIndex, inValue == null ? null : inValue.toString());
			}
			else if (sqlType == Types.DECIMAL || sqlType == Types.NUMERIC) {
				if (inValue instanceof BigDecimal) {
					ps.setBigDecimal(paramIndex, (BigDecimal) inValue);
				}
				else {
					ps.setObject(paramIndex, inValue, sqlType);
				}
			}
			else if (sqlType == Types.DATE) {
				if (inValue instanceof java.util.Date) {
					if (inValue instanceof java.sql.Date) {
						ps.setDate(paramIndex, (java.sql.Date) inValue);
					}
					else {
						ps.setDate(paramIndex, inValue == null ? null : new java.sql.Date(((java.util.Date) inValue).getTime()));
					}
				}
				else if (inValue instanceof Calendar) {
					Calendar cal = (Calendar) inValue;
					ps.setDate(paramIndex, inValue == null ? null : new java.sql.Date(cal.getTime().getTime()), cal);
					if (log.isInfoEnabled())
						log.debug(new java.sql.Date(cal.getTime().getTime()));
				}
				else {
					ps.setObject(paramIndex, inValue, Types.DATE);
				}
			}
			else if (sqlType == Types.TIME) {
				if (inValue instanceof java.util.Date) {
					if (inValue instanceof java.sql.Time) {
						ps.setTime(paramIndex, (java.sql.Time) inValue);
					}
					else {
						ps.setTime(paramIndex, inValue == null ? null : new java.sql.Time(((java.util.Date) inValue).getTime()));
					}
				}
				else if (inValue instanceof Calendar) {
					Calendar cal = (Calendar) inValue;
					ps.setTime(paramIndex, inValue == null ? null : new java.sql.Time(cal.getTime().getTime()), cal);
				}
				else {
					ps.setObject(paramIndex, inValue, Types.TIME);
				}
			}
			else if (sqlType == Types.TIMESTAMP) {
				if (inValue instanceof java.util.Date) {
					if (inValue instanceof java.sql.Timestamp) {
						ps.setTimestamp(paramIndex, (java.sql.Timestamp) inValue);
					}
					else {
						ps.setTimestamp(paramIndex, inValue == null ? null : new java.sql.Timestamp(((java.util.Date) inValue).getTime()));
						if (log.isDebugEnabled())
							log.debug(inValue == null ? null : new java.sql.Timestamp(((java.util.Date) inValue).getTime()));
					}
				}
				else if (inValue instanceof Calendar) {
					Calendar cal = (Calendar) inValue;
					ps.setTimestamp(paramIndex, inValue == null ? null : new java.sql.Timestamp(cal.getTime().getTime()), cal);
				}
				else {
					ps.setObject(paramIndex, inValue, Types.TIMESTAMP);
				}
			}
			else {
				// Fall back to generic setObject call with SQL type specified.
				
				ps.setObject(paramIndex, inValue, sqlType);
			}
		}
	//}
	
	
}
