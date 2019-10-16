package com.ait.itfc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

/**
 * 公式解析类
 * @author Pennix
 *
 */
public class Translator {

	private static final Logger logger = Logger.getLogger(Translator.class);
	
	/**
	 * 公式转译
	 * @param formula
	 * @param ifData
	 * @return
	 * @throws Exception 
	 */
	public static final Object translateFormula(String formula, Map<String, Object> ifData) throws Exception {
		if (StringUtils.isEmpty(formula))
			return null;
		else if (formula.toUpperCase().startsWith("CST:"))
			return translateCst(StringUtils.substringAfter(formula, "CST:"));
		else if (formula.toUpperCase().startsWith("COL:"))
			return translateCol(StringUtils.substringAfter(formula, "COL:"), ifData);
		else if (formula.toUpperCase().startsWith("SQL:"))
			return translateSql(StringUtils.substringAfter(formula, "SQL:"));
		else if (formula.toUpperCase().startsWith("COD:"))
			return translateCod(StringUtils.substringAfter(formula, "COD:"), ifData);
		return null;
	}

	/**
	 * 解析SQL公式
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	private static final Object translateSql(String sql) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnBean.getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				return rs.getObject(1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("SQL公式解析失败[" + sql + "]");
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return null;
	}

	/**
	 * 解析字段公式
	 * @param columnName
	 * @param ifData
	 * @return
	 * @throws Exception 
	 */
	private static final Object translateCol(String columnName, Map<String, Object> ifData) throws Exception {
		if (!ifData.containsKey(columnName))
			throw new Exception("字段[" + columnName + "]在接口表中不存在");
		return ifData.get(columnName);
	}

	/**
	 * 解析常量公式
	 * @param constant
	 * @return
	 * @throws Exception 
	 */
	private static final Object translateCst(String constant) throws Exception {
		if (constant.startsWith("\"") && constant.endsWith("\""))
			return constant.substring(1, constant.length() - 1);
		else
			try {
				return Integer.parseInt(constant);
			} catch (Exception e) {
				try {
					return Long.parseLong(constant);
				} catch (Exception ee) {
					try {
						return Double.parseDouble(constant);
					} catch (Exception eee) {
						try {
							return Float.parseFloat(constant);
						} catch (Exception eeee) {
							throw new Exception("常量公式解析失败[" + constant + "]");
						}
					}
				}
			}
	}

	/**
	 * 解析代码公式
	 * @param columnName
	 * @param ifData
	 * @return
	 * @throws Exception
	 */
	private static final Object translateCod(String formula, Map<String, Object> ifData) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String[] code = formula.split("@");
		String codeId = null;
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement("SELECT CODE_ID FROM SY_CODE WHERE CODE_NAME = ? AND PARENT_CODE = ?");
			pstmt.setString(1, ObjectUtils.toString(ifData.get(code[0])));
			pstmt.setString(2, code[1]);
			rs = pstmt.executeQuery();
			if (rs.next())
				codeId = rs.getString(1);
			else
				/**
				 * 如果没有记录,抛出异常
				 */
				throw new Exception("[" + formula + "]无法查询到对应CODE_ID");
			/**
			 * 如果仍有记录,说明结果不唯一,抛出异常
			 */
			if (rs.next())
				throw new Exception("[" + formula + "]取得CODE_ID不唯一");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("代码公式解析失败[" + formula + "]");
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return codeId;
	}
}