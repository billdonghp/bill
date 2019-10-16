/*
 * @(#)EmployeeDAO.java 1.0 2006-12-23 下午04:22:25
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.dao.hrcommand;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

/**
 * 发令> 添加员工 Copyright: AIT (c) Company: AIT
 * 
 * @author aqing (eqing@ait.net.cn)
 * @Date 2006-12-23 下午04:22:31
 * @version 1.0
 * 
 */
public class EmpHireComDAO {
    private CommonSQLMapAdapter commonSQLMapAdapter = null;

    private static final Logger logger = Logger.getLogger(EmpHireComDAO.class);

    public EmpHireComDAO() {
	commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
    }

    /**
     * 发令添加员工 基本信息 个人信息 职务信息 合同信息 登陆信息
     * 
     * @param emp
     * @param personal
     * @param con
     */
    public void addEmployee(Object emp, Object personal, Object contract)
	    throws GlRuntimeException {

	try {
	    commonSQLMapAdapter.startTransaction();
	    this.addEmployee(emp);
	    this.addPersonalInfo(personal);
	    this.addEmpPost(emp);
	    this.addAdmin(emp);
	    this.addContract(contract);
	    this.addExpInside(emp);
	    commonSQLMapAdapter.commitTransation();

	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("添加员工失败！", e);
	} finally {
	    try {
		commonSQLMapAdapter.endTransation();
	    } catch (SQLException e) {
		logger.error(e.toString());
		throw new GlRuntimeException("连接关闭错误！", e);
	    }
	}
    }

    /**
     * 发令添加员工
     * 
     * @param personal
     */
    public void addEmployee(Object emp) {
	try {
	    commonSQLMapAdapter.insert("hrm.hrcommand.insertEmployee", emp);
	} catch (SQLException e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("添加员工失败！", e);
	}
    }

    /**
     * 发令添加员工 个人信息
     * 
     * @param personal
     */
    public void addPersonalInfo(Object personal) {
	try {
	    commonSQLMapAdapter.insert("hrm.hrcommand.insertPersonalInfo",
		    personal);
	} catch (SQLException e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("添加员工基本信息失败！", e);
	}

    }

    /**
     * 发令添加员工 职务信息
     * 
     * @param emp
     */
    public void addEmpPost(Object emp) {
	try {
	    commonSQLMapAdapter.insert("hrm.hrcommand.insertEmpPost", emp);
	} catch (SQLException e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("添加员工职务失败！", e);
	}

    }

    /**
     * 发令添加员工 登陆信息
     * 
     * @param emp
     */
    public void addAdmin(Object emp) {
	try {
	    commonSQLMapAdapter.insert("hrm.hrcommand.insertAdmin", emp);
	} catch (SQLException e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("添加员工登陆者失败！", e);
	}

    }

    /**
     * 发令添加员工 社内经历初始信息
     * 
     * @param emp
     */
    public void addExpInside(Object emp) {
	try {
	    commonSQLMapAdapter.insert("hrm.hrcommand.insertExpInsideByEmp", emp);
	} catch (SQLException e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("添加员工社内经历！", e);
	}

    }

    /**
     * 发令添加员工 基本信息 个人信息 职务信息 登陆信息
     * 
     * @param emp
     * @param personal
     * @param con
     */
    public void addEmployee(Object emp, Object personal)
	    throws GlRuntimeException {

	try {
	    commonSQLMapAdapter.startTransaction();
	    this.addEmployee(emp);
	    this.addPersonalInfo(personal);
	    this.addEmpPost(emp);
	    this.addAdmin(emp);//设置登陆用户
	    this.addExpInside(emp);
	    commonSQLMapAdapter.commitTransation();

	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("添加员工失败！", e);
	} finally {
	    try {
		commonSQLMapAdapter.endTransation();
	    } catch (SQLException e) {
		logger.error(e.toString());
		throw new GlRuntimeException("连接关闭错误！", e);
	    }
	}
    }

    /**
     * 合同信息
     * @param contract
     */
    public void addContract(Object contract) {
	try {
	    commonSQLMapAdapter.insert("hrm.contract.addContract", contract);

	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("添加合同失败！", e);
	}

    }

}
