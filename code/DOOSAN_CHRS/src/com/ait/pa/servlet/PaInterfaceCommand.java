package com.ait.pa.servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.excel.util.ExcelBatchProcessor;
import com.ait.excel.util.ExcelParameterBean;
import com.ait.excel.util.ReportConstant;
import com.ait.excel.util.ReportUtil;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
import com.ait.web.Command;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-04-01
 * 
 */
public class PaInterfaceCommand implements Command {
	
    private static ServiceLocator services;

	private CommonSQLMapAdapter commonSQLMapAdapter = new CommonSQLMapAdapter(
	"em2");
    public PaInterfaceCommand() {
        try {
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException ex) {
        }
    }

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");	
		String content = request.getParameter("content");
		String returnPage = null;
		if (!content.equals("") && content.equals("voucherPage")) {
			returnPage = "/pa0506.jsp";
		} else if (!content.equals("")
				&& content.equals("FactoriesPayProvision")) {// 工厂工资计提
			returnPage = this.FactoriesPayProvision(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesPayProvision59B")) {// dici北京工资计提
			returnPage = this.FactoriesPayProvision59B(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesPayProvision59Y")) {// dici烟台工资计提
			returnPage = this.FactoriesPayProvision59Y(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesPayProvision_G")) {// 工厂工人工资计提
			returnPage = this.FactoriesPayProvision_G(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesPayProvision_Z")) {// 工厂职员工资计提
			returnPage = this.FactoriesPayProvision_Z(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesPayProvision_61_G")) {// disc工厂gr工资计提
			returnPage = this.FactoriesPayProvision_61_G(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesPayProvision_61_Z")) {// disc工厂zy工资计提
			returnPage = this.FactoriesPayProvision_61_Z(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesBonusesProvision")) {// 工厂奖金计提
			returnPage = this.FactoriesBonusesProvision(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesBonusesProvision59B")) {// DICI北京奖金计提
			returnPage = this.FactoriesBonusesProvision59B(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesBonusesProvision59Y")) {// DICI烟台奖金计提
			returnPage = this.FactoriesBonusesProvision59Y(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesBonusesProvisionA")) {// DIY工厂A职员奖金计提
			returnPage = this.FactoriesBonusesProvisionA(request,admin);
		}else if (!content.equals("")
				&& content.equals("FactoriesBonusesProvisionB")) {// DIY工厂B职员奖金计提
			returnPage = this.FactoriesBonusesProvisionB(request,admin);
		}else if (!content.equals("")
				&& content.equals("FactoriesPayProvisionalProvision")) {// 工厂临时工工资计提
			returnPage = this.FactoriesPayProvisionalProvision(request,admin);
		}else if (!content.equals("")
				&& content.equals("FactoriesPayProvisionalProvision_G")) {// 工厂退休反聘人员工资计提
			returnPage = this.FactoriesPayProvisionalProvision_G(request,admin);

		} else if (!content.equals("")
				&& content.equals("FactoriesEndowmentInsurance")) {// 工厂养老保险
			returnPage = this.FactoriesEndowmentInsurance(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesEndowmentInsurance59B")) {// DICI北京养老保险
			returnPage = this.FactoriesEndowmentInsurance59B(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesEndowmentInsurance59Y")) {// DICI烟台养老保险
			returnPage = this.FactoriesEndowmentInsurance59Y(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesEndowmentInsurance_G")) {// 工厂工人养老保险
			returnPage = this.FactoriesEndowmentInsurance_G(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesEndowmentInsurance_Z")) {// 工厂职员养老保险
			returnPage = this.FactoriesEndowmentInsurance_Z(request,admin);

		} else if (!content.equals("")
				&& content.equals("FactoriesMedicalInsurance")) {// 工厂医疗保险
			returnPage = this.FactoriesMedicalInsurance(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesMedicalInsurance59B")) {// DICI北京医疗保险
			returnPage = this.FactoriesMedicalInsurance59B(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesMedicalInsurance59Y")) {// DICI烟台医疗保险
			returnPage = this.FactoriesMedicalInsurance59Y(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesMedicalInsurance_G")) {// 工厂医疗保险
			returnPage = this.FactoriesMedicalInsurance_G(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesMedicalInsurance_Z")) {// 工厂医疗保险
			returnPage = this.FactoriesMedicalInsurance_Z(request,admin);

		} else if (!content.equals("")
				&& content.equals("FactoriesUnemployedInsurance")) {// 工厂待业保险
			returnPage = this.FactoriesUnemployedInsurance(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesUnemployedInsurance59B")) {// DICI北京待业保险
			returnPage = this.FactoriesUnemployedInsurance59B(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesUnemployedInsurance59Y")) {// DICI烟台待业保险
			returnPage = this.FactoriesUnemployedInsurance59Y(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesUnemployedInsurance_G")) {// 工厂员工待业保险
			returnPage = this.FactoriesUnemployedInsurance_G(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesUnemployedInsurance_Z")) {// 工厂职员待业保险
			returnPage = this.FactoriesUnemployedInsurance_Z(request,admin);

		} else if (!content.equals("")
				&& content.equals("FactoriesInjuriesInsurance")) {// 工厂工伤保险
			returnPage = this.FactoriesInjuriesInsurance(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesInjuriesInsurance59B")) {// DICI北京工伤保险
			returnPage = this.FactoriesInjuriesInsurance59B(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesInjuriesInsurance59Y")) {// DICI烟台工伤保险
			returnPage = this.FactoriesInjuriesInsurance59Y(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesInjuriesInsurance_G")) {// 工厂工人工伤保险
			returnPage = this.FactoriesInjuriesInsurance_G(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesInjuriesInsurance_Z")) {// 工厂职员工伤保险
			returnPage = this.FactoriesInjuriesInsurance_Z(request,admin);

		} else if (!content.equals("")
				&& content.equals("FactoriesGrowthInsurance")) {// 工厂生育保险
			returnPage = this.FactoriesGrowthInsurance(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesGrowthInsurance59B")) {// DICI北京生育保险
			returnPage = this.FactoriesGrowthInsurance59B(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesGrowthInsurance59Y")) {// DICI烟台生育保险
			returnPage = this.FactoriesGrowthInsurance59Y(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesGrowthInsurance_G")) {// 工厂工人生育保险
			returnPage = this.FactoriesGrowthInsurance_G(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesGrowthInsurance_Z")) {// 工厂职员生育保险
			returnPage = this.FactoriesGrowthInsurance_Z(request,admin);

		} else if (!content.equals("")
				&& content.equals("FactoriesProvidentFundInsurance")) {// 工厂住房公积金
			returnPage = this.FactoriesProvidentFundInsurance(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesProvidentFundInsurance59B")) {// DICI北京住房公积金
			returnPage = this.FactoriesProvidentFundInsurance59B(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesProvidentFundInsurance59Y")) {// DICI烟台住房公积金
			returnPage = this.FactoriesProvidentFundInsurance59Y(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesProvidentFundInsurance_G")) {// 工厂工人住房公积金
			returnPage = this.FactoriesProvidentFundInsurance_G(request,admin);
			
		} else if (!content.equals("")
				&& content.equals("FactoriesProvidentFundInsurance_Z")) {// 工厂职员住房公积金
			returnPage = this.FactoriesProvidentFundInsurance_Z(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesProvidentFundInsurance_61_G")) {// disc工厂gr住房公积金
			returnPage = this.FactoriesProvidentFundInsurance_61_G(request,admin);
		} else if (!content.equals("")
				&& content.equals("FactoriesProvidentFundInsurance_61_Z")) {// disc工厂zy住房公积金
			returnPage = this.FactoriesProvidentFundInsurance_61_Z(request,admin);

		} else if (!content.equals("") && content.equals("BranchPayProvision")) {// 支社工资计提
			returnPage = this.BranchPayProvision(request,admin);
		} else if (!content.equals("") && content.equals("BranchPayProvision_61")) {// 61支社工资计提
			returnPage = this.BranchPayProvision_61(request,admin);
		} else if (!content.equals("") && content.equals("BranchProvidentFundInsurance_61")) {// 61支社住房公积金
			returnPage = this.BranchProvidentFundInsurance_61(request,admin);
		} else if (!content.equals("")
				&& content.equals("BranchProvisionLaborInsurance")) {// 支社计提公司负担劳动保险
			returnPage = this.BranchProvisionLaborInsurance(request,admin);

		} else if (!content.equals("")
				&& content.equals("BranchBurdenManagementFees")) {// 支社负担国企管理费
			returnPage = this.BranchBurdenManagementFees(request,admin);

		}  else if (!content.equals("")
				&& content.equals("BranchBurdenManagementFees59B")) {// DICI北京负担国企管理费
			returnPage = this.BranchBurdenManagementFees59B(request,admin);

		}  else if (!content.equals("")
				&& content.equals("BranchBurdenManagementFees59Y")) {// DICI烟台支社负担国企管理费
			returnPage = this.BranchBurdenManagementFees59Y(request,admin);

		} else if (!content.equals("")
				&& content.equals("BranchFixedBonusesProvision")) {// 支社固定奖金计提
			returnPage = this.BranchFixedBonusesProvision(request,admin);

		}else if (!content.equals("")
				&& content.equals("BranchFixedBonusesProvisionA")) {// DIY支社A职员固定奖金计提
			returnPage = this.BranchFixedBonusesProvisionA(request,admin);

		}else if (!content.equals("")
				&& content.equals("BranchFixedBonusesProvisionB")) {// DIY支社B职员固定奖金计提
			returnPage = this.BranchFixedBonusesProvisionB(request,admin);

		}
		else if (!content.equals("")
				&& content.equals("BranchPerformanceResultsBonusesProvision")) {// 支社成果业绩奖金计提
			returnPage = this.BranchPerformanceResultsBonusesProvision(request,admin);

		} else if (!content.equals("")
				&& content.equals("BranchLegalRoomBonusesProvision")) {// 法律事务室诉讼奖金计提
			returnPage = this.BranchLegalRoomBonusesProvision(request,admin);

		} else if (!content.equals("")
				&& content.equals("RetirementCompensation")) {//退职补偿金
			returnPage = this.RetirementCompensation(request);//目前这个一直没有用，也存在部分问题，暂时不解决

		} else if(!content.equals("")
				&& content.equals("outExcel")){
			try {
				returnPage = this.outExcel(request,admin);
			} catch (ServiceLocatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(!content.equals("")
				&& content.equals("inputExcel")){
			returnPage=this.inputExcel(request,admin);
		}else if(!content.equals("")& content.equals("interfaceOutExcel")){
			returnPage=this.interfaceOutExcel(request,admin);		
			
		}else if(!content.equals("")& content.equals("deleteInterfaceData")){
			returnPage=this.deleteInterfaceData(request);		
		}else{
			return "error.jsp";
		}
		return returnPage;
	}
	

    public String  FactoriesPayProvision(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "";
        String cpnyId = admin.getCpnyId();
        if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
        	 sql = "{CALL PA_F_PayProvision_PRO_590(?,?,?,?,?)}";
        }else if(cpnyId == "60000000" || "60000000".equals(cpnyId)){//DIY和DICC工资计提凭证不同
        	 sql = "{CALL PA_F_PayProvision_PRO_600(?,?,?,?,?)}";
        }else{
        	 sql = "{CALL PA_F_PayProvision_PRO(?,?,?,?,?)}";
        }
       
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, admin.getCpnyId());
            cs.registerOutParameter(5,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(5);
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
        
        
    }
    public String  FactoriesPayProvision59B(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "{CALL PA_F_PayProvision_PRO_590B(?,?,?,?,?)}";

        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, admin.getCpnyId());
            cs.registerOutParameter(5,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(5);
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
        
        
    }
    public String  FactoriesPayProvision59Y(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "{CALL PA_F_PayProvision_PRO_590Y(?,?,?,?,?)}";

        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, admin.getCpnyId());
            cs.registerOutParameter(5,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(5);
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
        
        
    }
    public String  FactoriesPayProvision_G(HttpServletRequest request,AdminBean admin) {
    	String s ="";
    	Connection con = null;
    	CallableStatement cs = null;
    	
    	String sql = "";
    	String cpnyId = admin.getCpnyId();
    	if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
    		sql = "{CALL PA_F_PayProvision_PRO_590(?,?,?,?,?)}";
    	}else{
    		sql = "{CALL PA_F_PayProvision_PRO_G(?,?,?,?,?)}";
    	}
    	
    	try {
    		con = services.getConnection();
    		cs = con.prepareCall(sql);
    		cs.setString(1, request.getParameter("AnnualPaymentsYear"));
    		cs.setString(2, request.getParameter("OnPayMonth"));
    		cs.setString(3, request.getParameter("PaymentDates"));
    		cs.setString(4, admin.getCpnyId());
    		cs.registerOutParameter(5,Types.VARCHAR);
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
    		Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
    		cs.executeUpdate();
    		s =cs.getString(5);
    	} catch (ServiceLocatorException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		SqlUtil.close(cs, con);
    	}
    	request.setAttribute("alert", s);
    	request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
    	return "/inc/alertMessage.jsp";
    	
    	
    }
    public String  FactoriesPayProvision_Z(HttpServletRequest request,AdminBean admin) {
    	String s ="";
    	Connection con = null;
    	CallableStatement cs = null;
    	
    	String sql = "";
    	String cpnyId = admin.getCpnyId();
    	if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
    		sql = "{CALL PA_F_PayProvision_PRO_590(?,?,?,?,?)}";
    	}else if(cpnyId == "78000000" || "78000000".equals(cpnyId)){
    		sql = "{CALL PA_F_PayProvision_PRO_78_Z(?,?,?,?,?)}";
    	}else{
    		sql = "{CALL PA_F_PayProvision_PRO_Z(?,?,?,?,?)}";
    	}
    	
    	try {
    		con = services.getConnection();
    		cs = con.prepareCall(sql);
    		cs.setString(1, request.getParameter("AnnualPaymentsYear"));
    		cs.setString(2, request.getParameter("OnPayMonth"));
    		cs.setString(3, request.getParameter("PaymentDates"));
    		cs.setString(4, admin.getCpnyId());
    		cs.registerOutParameter(5,Types.VARCHAR);
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
    		Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
    		cs.executeUpdate();
    		s =cs.getString(5);
    	} catch (ServiceLocatorException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		SqlUtil.close(cs, con);
    	}
    	request.setAttribute("alert", s);
    	request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
    	return "/inc/alertMessage.jsp";
    	
    	
    }
    public String  FactoriesPayProvision_61_G(HttpServletRequest request,AdminBean admin) {
    	String s ="";
    	Connection con = null;
    	CallableStatement cs = null;
    	
    	String sql = "";
    	String cpnyId = admin.getCpnyId();
    	if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
    		sql = "{CALL PA_F_PayProvision_PRO_590(?,?,?,?,?)}";
    	}else{
    		sql = "{CALL PA_F_PayProvision_PRO_61_G(?,?,?,?,?)}";
    	}
    	
    	try {
    		con = services.getConnection();
    		cs = con.prepareCall(sql);
    		cs.setString(1, request.getParameter("AnnualPaymentsYear"));
    		cs.setString(2, request.getParameter("OnPayMonth"));
    		cs.setString(3, request.getParameter("PaymentDates"));
    		cs.setString(4, admin.getCpnyId());
    		cs.registerOutParameter(5,Types.VARCHAR);
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
    		Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
    		cs.executeUpdate();
    		s =cs.getString(5);
    	} catch (ServiceLocatorException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		SqlUtil.close(cs, con);
    	}
    	request.setAttribute("alert", s);
    	request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
    	return "/inc/alertMessage.jsp";
    	
    	
    }
    public String  FactoriesPayProvision_61_Z(HttpServletRequest request,AdminBean admin) {
    	String s ="";
    	Connection con = null;
    	CallableStatement cs = null;
    	
    	String sql = "";
    	String cpnyId = admin.getCpnyId();
    	if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
    		sql = "{CALL PA_F_PayProvision_PRO_590(?,?,?,?,?)}";
    	}else{
    		sql = "{CALL PA_F_PayProvision_PRO_61_Z(?,?,?,?,?)}";
    	}
    	
    	try {
    		con = services.getConnection();
    		cs = con.prepareCall(sql);
    		cs.setString(1, request.getParameter("AnnualPaymentsYear"));
    		cs.setString(2, request.getParameter("OnPayMonth"));
    		cs.setString(3, request.getParameter("PaymentDates"));
    		cs.setString(4, admin.getCpnyId());
    		cs.registerOutParameter(5,Types.VARCHAR);
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
    		Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
    		cs.executeUpdate();
    		s =cs.getString(5);
    	} catch (ServiceLocatorException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		SqlUtil.close(cs, con);
    	}
    	request.setAttribute("alert", s);
    	request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
    	return "/inc/alertMessage.jsp";
    	
    	
    }
    
    public String  FactoriesBonusesProvision(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "";
        String cpnyId = admin.getCpnyId();
        if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
        	 sql = "{CALL PA_F_BonusesProvision_PRO_590(?,?,?,?,?,?)}";
        }else if(cpnyId == "63000000" || "63000000".equals(cpnyId)){
        	 sql = "{CALL PA_F_BonusesProvision_PRO_630(?,?,?,?,?,?)}";//DISD工厂奖金计提
        }else{
        	 sql = "{CALL PA_F_BonusesProvision_PRO(?,?,?,?,?,?)}";
        }
        
        //String sql = "{CALL PA_F_BonusesProvision_PRO(?,?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            Logger.getLogger(getClass()).debug("InputBase:" + request.getParameter("InputBase"));
            
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    
    public String  FactoriesBonusesProvision59B(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "{CALL PA_F_BonusesProvision_PRO_590B(?,?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            Logger.getLogger(getClass()).debug("InputBase:" + request.getParameter("InputBase"));
            
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    
    
    public String  FactoriesBonusesProvision59Y(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "{CALL PA_F_BonusesProvision_PRO_590Y(?,?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            Logger.getLogger(getClass()).debug("InputBase:" + request.getParameter("InputBase"));
            
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    
    
    public String  FactoriesBonusesProvisionA(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        
        String cpnyId = admin.getCpnyId();
        
        
        String sql = "{CALL PA_F_BonusesProvision_PRO_A(?,?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    
    public String  FactoriesBonusesProvisionB(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        
        String cpnyId = admin.getCpnyId();
        
        
        String sql = "{CALL PA_F_BonusesProvision_PRO_B(?,?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    
    public String  FactoriesPayProvisionalProvision(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{CALL PA_F_ProvisionalProvision_PRO(?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates")); 
            cs.setString(4, admin.getCpnyId()); 
            cs.registerOutParameter(5,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(5);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  FactoriesPayProvisionalProvision_G(HttpServletRequest request,AdminBean admin) {
    	String s ="";
    	Connection con = null;
    	CallableStatement cs = null;
    	String sql = "{CALL PA_F_ProvisionalProvision_PRO1(?,?,?,?,?)}";
    	try {
    		con = services.getConnection();
    		cs = con.prepareCall(sql);
    		cs.setString(1, request.getParameter("AnnualPaymentsYear"));
    		cs.setString(2, request.getParameter("OnPayMonth"));
    		cs.setString(3, request.getParameter("PaymentDates")); 
    		cs.setString(4, admin.getCpnyId()); 
    		cs.registerOutParameter(5,Types.VARCHAR);
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
    		Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
    		cs.executeUpdate();
    		s =cs.getString(5);
    		
    	} catch (ServiceLocatorException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		SqlUtil.close(cs, con);
    	}
    	request.setAttribute("alert", s);
    	request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
    	return "/inc/alertMessage.jsp";
    }
    
    public String  FactoriesEndowmentInsurance(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "";
        String cpnyId = admin.getCpnyId();
        if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
        	sql = "{CALL PA_F_EndowmentInsurance_PRO_59(?,?,?,?,?,?)}";
        }else{
        	sql = "{CALL PA_F_EndowmentInsurance_PRO(?,?,?,?,?,?)}";
        }
        
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  FactoriesEndowmentInsurance59B(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "{CALL PA_F_EndowmentInsurance_PRO59B(?,?,?,?,?,?)}";
        
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  FactoriesEndowmentInsurance59Y(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "{CALL PA_F_EndowmentInsurance_PRO59Y(?,?,?,?,?,?)}";
        
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  FactoriesEndowmentInsurance_G(HttpServletRequest request,AdminBean admin) {
    	String s ="";
    	Connection con = null;
    	CallableStatement cs = null;
    	
    	String sql = "";
    	String cpnyId = admin.getCpnyId();
    	if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
    		sql = "{CALL PA_F_EndowmentInsurance_PRO_59(?,?,?,?,?,?)}";
    	}else{
    		sql = "{CALL PA_F_EndowmentInsurance_PRO_G(?,?,?,?,?,?)}";
    	}
    	
    	try {
    		con = services.getConnection();
    		cs = con.prepareCall(sql);
    		cs.setString(1, request.getParameter("AnnualPaymentsYear"));
    		cs.setString(2, request.getParameter("OnPayMonth"));
    		cs.setString(3, request.getParameter("PaymentDates"));
    		cs.setString(4, request.getParameter("InputBase"));
    		cs.setString(5, admin.getCpnyId());
    		cs.registerOutParameter(6,Types.VARCHAR);
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
    		Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
    		cs.executeUpdate();
    		s =cs.getString(6);
    		
    	} catch (ServiceLocatorException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		SqlUtil.close(cs, con);
    	}
    	request.setAttribute("alert", s);
    	request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
    	return "/inc/alertMessage.jsp";
    }
    public String  FactoriesEndowmentInsurance_Z(HttpServletRequest request,AdminBean admin) {
    	String s ="";
    	Connection con = null;
    	CallableStatement cs = null;
    	
    	String sql = "";
    	String cpnyId = admin.getCpnyId();
    	if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
    		sql = "{CALL PA_F_EndowmentInsurance_PRO_59(?,?,?,?,?,?)}";
    	}else{
    		sql = "{CALL PA_F_EndowmentInsurance_PRO_Z(?,?,?,?,?,?)}";
    	}
    	
    	try {
    		con = services.getConnection();
    		cs = con.prepareCall(sql);
    		cs.setString(1, request.getParameter("AnnualPaymentsYear"));
    		cs.setString(2, request.getParameter("OnPayMonth"));
    		cs.setString(3, request.getParameter("PaymentDates"));
    		cs.setString(4, request.getParameter("InputBase"));
    		cs.setString(5, admin.getCpnyId());
    		cs.registerOutParameter(6,Types.VARCHAR);
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
    		Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
    		cs.executeUpdate();
    		s =cs.getString(6);
    		
    	} catch (ServiceLocatorException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		SqlUtil.close(cs, con);
    	}
    	request.setAttribute("alert", s);
    	request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
    	return "/inc/alertMessage.jsp";
    }
    public String  FactoriesMedicalInsurance(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "";
        String cpnyId = admin.getCpnyId();
        if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
        	sql = "{CALL PA_F_MedicalInsurance_PRO_590(?,?,?,?,?,?)}";
        }else{
        	sql = "{CALL PA_F_MedicalInsurance_PRO(?,?,?,?,?,?)}";
        }
        
        //String sql = "{CALL PA_F_MedicalInsurance_PRO(?,?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  FactoriesMedicalInsurance59B(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "{CALL PA_F_MedicalInsurance_PRO_590B(?,?,?,?,?,?)}";

        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  FactoriesMedicalInsurance59Y(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "{CALL PA_F_MedicalInsurance_PRO_590Y(?,?,?,?,?,?)}";
       
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  FactoriesMedicalInsurance_G(HttpServletRequest request,AdminBean admin) {
    	String s ="";
    	Connection con = null;
    	CallableStatement cs = null;
    	
    	String sql = "";
    	String cpnyId = admin.getCpnyId();
    	if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
    		sql = "{CALL PA_F_MedicalInsurance_PRO_590(?,?,?,?,?,?)}";
    	}else{
    		sql = "{CALL PA_F_MedicalInsurance_PRO_G(?,?,?,?,?,?)}";
    	}
    	
    	//String sql = "{CALL PA_F_MedicalInsurance_PRO(?,?,?,?,?,?)}";
    	try {
    		con = services.getConnection();
    		cs = con.prepareCall(sql);
    		cs.setString(1, request.getParameter("AnnualPaymentsYear"));
    		cs.setString(2, request.getParameter("OnPayMonth"));
    		cs.setString(3, request.getParameter("PaymentDates"));
    		cs.setString(4, request.getParameter("InputBase"));
    		cs.setString(5, admin.getCpnyId());
    		cs.registerOutParameter(6,Types.VARCHAR);
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
    		Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
    		cs.executeUpdate();
    		s =cs.getString(6);
    		
    	} catch (ServiceLocatorException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		SqlUtil.close(cs, con);
    	}
    	request.setAttribute("alert", s);
    	request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
    	return "/inc/alertMessage.jsp";
    }
    public String  FactoriesMedicalInsurance_Z(HttpServletRequest request,AdminBean admin) {
    	String s ="";
    	Connection con = null;
    	CallableStatement cs = null;
    	
    	String sql = "";
    	String cpnyId = admin.getCpnyId();
    	if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
    		sql = "{CALL PA_F_MedicalInsurance_PRO_590(?,?,?,?,?,?)}";
    	}else{
    		sql = "{CALL PA_F_MedicalInsurance_PRO_Z(?,?,?,?,?,?)}";
    	}
    	
    	//String sql = "{CALL PA_F_MedicalInsurance_PRO(?,?,?,?,?,?)}";
    	try {
    		con = services.getConnection();
    		cs = con.prepareCall(sql);
    		cs.setString(1, request.getParameter("AnnualPaymentsYear"));
    		cs.setString(2, request.getParameter("OnPayMonth"));
    		cs.setString(3, request.getParameter("PaymentDates"));
    		cs.setString(4, request.getParameter("InputBase"));
    		cs.setString(5, admin.getCpnyId());
    		cs.registerOutParameter(6,Types.VARCHAR);
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
    		Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
    		cs.executeUpdate();
    		s =cs.getString(6);
    		
    	} catch (ServiceLocatorException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		SqlUtil.close(cs, con);
    	}
    	request.setAttribute("alert", s);
    	request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
    	return "/inc/alertMessage.jsp";
    }
    public String  FactoriesUnemployedInsurance(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "";
        String cpnyId = admin.getCpnyId();
        if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
        	sql = "{CALL PA_F_UnemployedInsurance_PRO59(?,?,?,?,?,?)}";
        }else{
        	sql = "{CALL PA_F_UnemployedInsurance_PRO(?,?,?,?,?,?)}";
        }
        
        //String sql = "{CALL PA_F_UnemployedInsurance_PRO(?,?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  FactoriesUnemployedInsurance59B(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "{CALL PA_F_UnemployedInsurancePRO59B(?,?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  FactoriesUnemployedInsurance59Y(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "{CALL PA_F_UnemployedInsurancePRO59Y(?,?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  FactoriesUnemployedInsurance_G(HttpServletRequest request,AdminBean admin) {
    	String s ="";
    	Connection con = null;
    	CallableStatement cs = null;
    	
    	String sql = "";
    	String cpnyId = admin.getCpnyId();
    	if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
    		sql = "{CALL PA_F_UnemployedInsurance_PRO59(?,?,?,?,?,?)}";
    	}else{
    		sql = "{CALL PA_F_UnemployedInsurance_PRO_G(?,?,?,?,?,?)}";
    	}
    	
    	//String sql = "{CALL PA_F_UnemployedInsurance_PRO(?,?,?,?,?,?)}";
    	try {
    		con = services.getConnection();
    		cs = con.prepareCall(sql);
    		cs.setString(1, request.getParameter("AnnualPaymentsYear"));
    		cs.setString(2, request.getParameter("OnPayMonth"));
    		cs.setString(3, request.getParameter("PaymentDates"));
    		cs.setString(4, request.getParameter("InputBase"));
    		cs.setString(5, admin.getCpnyId());
    		cs.registerOutParameter(6,Types.VARCHAR);
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
    		Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
    		cs.executeUpdate();
    		s =cs.getString(6);
    		
    	} catch (ServiceLocatorException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		SqlUtil.close(cs, con);
    	}
    	request.setAttribute("alert", s);
    	request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
    	return "/inc/alertMessage.jsp";
    }
    public String  FactoriesUnemployedInsurance_Z(HttpServletRequest request,AdminBean admin) {
    	String s ="";
    	Connection con = null;
    	CallableStatement cs = null;
    	
    	String sql = "";
    	String cpnyId = admin.getCpnyId();
    	if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
    		sql = "{CALL PA_F_UnemployedInsurance_PRO59(?,?,?,?,?,?)}";
    	}else{
    		sql = "{CALL PA_F_UnemployedInsurance_PRO_Z(?,?,?,?,?,?)}";
    	}
    	
    	//String sql = "{CALL PA_F_UnemployedInsurance_PRO(?,?,?,?,?,?)}";
    	try {
    		con = services.getConnection();
    		cs = con.prepareCall(sql);
    		cs.setString(1, request.getParameter("AnnualPaymentsYear"));
    		cs.setString(2, request.getParameter("OnPayMonth"));
    		cs.setString(3, request.getParameter("PaymentDates"));
    		cs.setString(4, request.getParameter("InputBase"));
    		cs.setString(5, admin.getCpnyId());
    		cs.registerOutParameter(6,Types.VARCHAR);
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
    		Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
    		cs.executeUpdate();
    		s =cs.getString(6);
    		
    	} catch (ServiceLocatorException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		SqlUtil.close(cs, con);
    	}
    	request.setAttribute("alert", s);
    	request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
    	return "/inc/alertMessage.jsp";
    }
    public String  FactoriesInjuriesInsurance(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "";
        String cpnyId = admin.getCpnyId();
        if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
        	sql = "{CALL PA_F_InjuriesInsurance_PRO_590(?,?,?,?,?,?)}";
        }else{
        	sql = "{CALL PA_F_InjuriesInsurance_PRO(?,?,?,?,?,?)}";
        }
        
        //String sql = "{CALL PA_F_InjuriesInsurance_PRO(?,?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  FactoriesInjuriesInsurance59B(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql =  "{CALL PA_F_InjuriesInsurance_PRO_59B(?,?,?,?,?,?)}";

        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  FactoriesInjuriesInsurance59Y(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql =  "{CALL PA_F_InjuriesInsurance_PRO_59Y(?,?,?,?,?,?)}";

        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  FactoriesInjuriesInsurance_G(HttpServletRequest request,AdminBean admin) {
    	String s ="";
    	Connection con = null;
    	CallableStatement cs = null;
    	
    	String sql = "";
    	String cpnyId = admin.getCpnyId();
    	if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
    		sql = "{CALL PA_F_InjuriesInsurance_PRO_590(?,?,?,?,?,?)}";
    	}else{
    		sql = "{CALL PA_F_InjuriesInsurance_PRO_G(?,?,?,?,?,?)}";
    	}
    	
    	//String sql = "{CALL PA_F_InjuriesInsurance_PRO(?,?,?,?,?,?)}";
    	try {
    		con = services.getConnection();
    		cs = con.prepareCall(sql);
    		cs.setString(1, request.getParameter("AnnualPaymentsYear"));
    		cs.setString(2, request.getParameter("OnPayMonth"));
    		cs.setString(3, request.getParameter("PaymentDates"));
    		cs.setString(4, request.getParameter("InputBase"));
    		cs.setString(5, admin.getCpnyId());
    		cs.registerOutParameter(6,Types.VARCHAR);
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
    		Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
    		cs.executeUpdate();
    		s =cs.getString(6);
    		
    	} catch (ServiceLocatorException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		SqlUtil.close(cs, con);
    	}
    	request.setAttribute("alert", s);
    	request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
    	return "/inc/alertMessage.jsp";
    }
    public String  FactoriesInjuriesInsurance_Z(HttpServletRequest request,AdminBean admin) {
    	String s ="";
    	Connection con = null;
    	CallableStatement cs = null;
    	
    	String sql = "";
    	String cpnyId = admin.getCpnyId();
    	if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
    		sql = "{CALL PA_F_InjuriesInsurance_PRO_590(?,?,?,?,?,?)}";
    	}else{
    		sql = "{CALL PA_F_InjuriesInsurance_PRO_Z(?,?,?,?,?,?)}";
    	}
    	
    	//String sql = "{CALL PA_F_InjuriesInsurance_PRO(?,?,?,?,?,?)}";
    	try {
    		con = services.getConnection();
    		cs = con.prepareCall(sql);
    		cs.setString(1, request.getParameter("AnnualPaymentsYear"));
    		cs.setString(2, request.getParameter("OnPayMonth"));
    		cs.setString(3, request.getParameter("PaymentDates"));
    		cs.setString(4, request.getParameter("InputBase"));
    		cs.setString(5, admin.getCpnyId());
    		cs.registerOutParameter(6,Types.VARCHAR);
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
    		Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
    		cs.executeUpdate();
    		s =cs.getString(6);
    		
    	} catch (ServiceLocatorException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		SqlUtil.close(cs, con);
    	}
    	request.setAttribute("alert", s);
    	request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
    	return "/inc/alertMessage.jsp";
    }
    public String  FactoriesGrowthInsurance(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "";
        String cpnyId = admin.getCpnyId();
        if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
        	sql = "{CALL PA_F_GrowthInsurance_PRO_590(?,?,?,?,?,?)}";
        }else{
        	sql = "{CALL PA_F_GrowthInsurance_PRO(?,?,?,?,?,?)}";
        }
        
        //String sql = "{CALL PA_F_GrowthInsurance_PRO(?,?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  FactoriesGrowthInsurance59B(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "{CALL PA_F_GrowthInsurance_PRO_590B(?,?,?,?,?,?)}";

        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  FactoriesGrowthInsurance59Y(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "{CALL PA_F_GrowthInsurance_PRO_590Y(?,?,?,?,?,?)}";

        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  FactoriesGrowthInsurance_Z(HttpServletRequest request,AdminBean admin) {
    	String s ="";
    	Connection con = null;
    	CallableStatement cs = null;
    	
    	String sql = "";
    	String cpnyId = admin.getCpnyId();
    	if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
    		sql = "{CALL PA_F_GrowthInsurance_PRO_590(?,?,?,?,?,?)}";
    	}else{
    		sql = "{CALL PA_F_GrowthInsurance_PRO_Z(?,?,?,?,?,?)}";
    	}
    	
    	//String sql = "{CALL PA_F_GrowthInsurance_PRO(?,?,?,?,?,?)}";
    	try {
    		con = services.getConnection();
    		cs = con.prepareCall(sql);
    		cs.setString(1, request.getParameter("AnnualPaymentsYear"));
    		cs.setString(2, request.getParameter("OnPayMonth"));
    		cs.setString(3, request.getParameter("PaymentDates"));
    		cs.setString(4, request.getParameter("InputBase"));
    		cs.setString(5, admin.getCpnyId());
    		cs.registerOutParameter(6,Types.VARCHAR);
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
    		Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
    		cs.executeUpdate();
    		s =cs.getString(6);
    		
    	} catch (ServiceLocatorException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		SqlUtil.close(cs, con);
    	}
    	request.setAttribute("alert", s);
    	request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
    	return "/inc/alertMessage.jsp";
    }
    public String  FactoriesGrowthInsurance_G(HttpServletRequest request,AdminBean admin) {
    	String s ="";
    	Connection con = null;
    	CallableStatement cs = null;
    	
    	String sql = "";
    	String cpnyId = admin.getCpnyId();
    	if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
    		sql = "{CALL PA_F_GrowthInsurance_PRO_590(?,?,?,?,?,?)}";
    	}else{
    		sql = "{CALL PA_F_GrowthInsurance_PRO_G(?,?,?,?,?,?)}";
    	}
    	
    	//String sql = "{CALL PA_F_GrowthInsurance_PRO(?,?,?,?,?,?)}";
    	try {
    		con = services.getConnection();
    		cs = con.prepareCall(sql);
    		cs.setString(1, request.getParameter("AnnualPaymentsYear"));
    		cs.setString(2, request.getParameter("OnPayMonth"));
    		cs.setString(3, request.getParameter("PaymentDates"));
    		cs.setString(4, request.getParameter("InputBase"));
    		cs.setString(5, admin.getCpnyId());
    		cs.registerOutParameter(6,Types.VARCHAR);
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
    		Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
    		cs.executeUpdate();
    		s =cs.getString(6);
    		
    	} catch (ServiceLocatorException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		SqlUtil.close(cs, con);
    	}
    	request.setAttribute("alert", s);
    	request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
    	return "/inc/alertMessage.jsp";
    }
    public String  FactoriesProvidentFundInsurance(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "";
        String cpnyId = admin.getCpnyId();
        if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
        	sql = "{CALL PA_F_PFundInsurance_PRO_590(?,?,?,?,?,?)}";
        }else{
        	sql = "{CALL PA_F_PFundInsurance_PRO(?,?,?,?,?,?)}";
        }
        
        //String sql = "{CALL PA_F_PFundInsurance_PRO(?,?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  FactoriesProvidentFundInsurance59B(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "{CALL PA_F_PFundInsurance_PRO_590B(?,?,?,?,?,?)}";

        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  FactoriesProvidentFundInsurance59Y(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "{CALL PA_F_PFundInsurance_PRO_590Y(?,?,?,?,?,?)}";

        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  FactoriesProvidentFundInsurance_G(HttpServletRequest request,AdminBean admin) {
    	String s ="";
    	Connection con = null;
    	CallableStatement cs = null;
    	
    	String sql = "";
    	String cpnyId = admin.getCpnyId();
    	if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
    		sql = "{CALL PA_F_PFundInsurance_PRO_590(?,?,?,?,?,?)}";
    	}else{
    		sql = "{CALL PA_F_PFundInsurance_PRO_G(?,?,?,?,?,?)}";
    	}
    	
    	//String sql = "{CALL PA_F_PFundInsurance_PRO(?,?,?,?,?,?)}";
    	try {
    		con = services.getConnection();
    		cs = con.prepareCall(sql);
    		cs.setString(1, request.getParameter("AnnualPaymentsYear"));
    		cs.setString(2, request.getParameter("OnPayMonth"));
    		cs.setString(3, request.getParameter("PaymentDates"));
    		cs.setString(4, request.getParameter("InputBase"));
    		cs.setString(5, admin.getCpnyId());
    		cs.registerOutParameter(6,Types.VARCHAR);
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
    		Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
    		cs.executeUpdate();
    		s =cs.getString(6);
    		
    	} catch (ServiceLocatorException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		SqlUtil.close(cs, con);
    	}
    	request.setAttribute("alert", s);
    	request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
    	return "/inc/alertMessage.jsp";
    }
    public String  FactoriesProvidentFundInsurance_Z(HttpServletRequest request,AdminBean admin) {
    	String s ="";
    	Connection con = null;
    	CallableStatement cs = null;
    	
    	String sql = "";
    	String cpnyId = admin.getCpnyId();
    	if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
    		sql = "{CALL PA_F_PFundInsurance_PRO_590(?,?,?,?,?,?)}";
    	}else{
    		sql = "{CALL PA_F_PFundInsurance_PRO_Z(?,?,?,?,?,?)}";
    	}
    	
    	//String sql = "{CALL PA_F_PFundInsurance_PRO(?,?,?,?,?,?)}";
    	try {
    		con = services.getConnection();
    		cs = con.prepareCall(sql);
    		cs.setString(1, request.getParameter("AnnualPaymentsYear"));
    		cs.setString(2, request.getParameter("OnPayMonth"));
    		cs.setString(3, request.getParameter("PaymentDates"));
    		cs.setString(4, request.getParameter("InputBase"));
    		cs.setString(5, admin.getCpnyId());
    		cs.registerOutParameter(6,Types.VARCHAR);
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
    		Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
    		cs.executeUpdate();
    		s =cs.getString(6);
    		
    	} catch (ServiceLocatorException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		SqlUtil.close(cs, con);
    	}
    	request.setAttribute("alert", s);
    	request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
    	return "/inc/alertMessage.jsp";
    }
    public String  FactoriesProvidentFundInsurance_61_G(HttpServletRequest request,AdminBean admin) {
    	String s ="";
    	Connection con = null;
    	CallableStatement cs = null;
    	
    	String sql = "";
    	String cpnyId = admin.getCpnyId();
    	if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
    		sql = "{CALL PA_F_PFundInsurance_PRO_590(?,?,?,?,?,?)}";
    	}else{
    		sql = "{CALL PA_F_PFundInsurance_PRO_61_G(?,?,?,?,?,?)}";
    	}
    	
    	//String sql = "{CALL PA_F_PFundInsurance_PRO(?,?,?,?,?,?)}";
    	try {
    		con = services.getConnection();
    		cs = con.prepareCall(sql);
    		cs.setString(1, request.getParameter("AnnualPaymentsYear"));
    		cs.setString(2, request.getParameter("OnPayMonth"));
    		cs.setString(3, request.getParameter("PaymentDates"));
    		cs.setString(4, request.getParameter("InputBase"));
    		cs.setString(5, admin.getCpnyId());
    		cs.registerOutParameter(6,Types.VARCHAR);
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
    		Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
    		cs.executeUpdate();
    		s =cs.getString(6);
    		
    	} catch (ServiceLocatorException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		SqlUtil.close(cs, con);
    	}
    	request.setAttribute("alert", s);
    	request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
    	return "/inc/alertMessage.jsp";
    }
    public String  FactoriesProvidentFundInsurance_61_Z(HttpServletRequest request,AdminBean admin) {
    	String s ="";
    	Connection con = null;
    	CallableStatement cs = null;
    	
    	String sql = "";
    	String cpnyId = admin.getCpnyId();
    	if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
    		sql = "{CALL PA_F_PFundInsurance_PRO_590(?,?,?,?,?,?)}";
    	}else{
    		sql = "{CALL PA_F_PFundInsurance_PRO_61_Z(?,?,?,?,?,?)}";
    	}
    	
    	//String sql = "{CALL PA_F_PFundInsurance_PRO(?,?,?,?,?,?)}";
    	try {
    		con = services.getConnection();
    		cs = con.prepareCall(sql);
    		cs.setString(1, request.getParameter("AnnualPaymentsYear"));
    		cs.setString(2, request.getParameter("OnPayMonth"));
    		cs.setString(3, request.getParameter("PaymentDates"));
    		cs.setString(4, request.getParameter("InputBase"));
    		cs.setString(5, admin.getCpnyId());
    		cs.registerOutParameter(6,Types.VARCHAR);
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
    		Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
    		cs.executeUpdate();
    		s =cs.getString(6);
    		
    	} catch (ServiceLocatorException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		SqlUtil.close(cs, con);
    	}
    	request.setAttribute("alert", s);
    	request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
    	return "/inc/alertMessage.jsp";
    }
    
    public String  BranchProvidentFundInsurance_61(HttpServletRequest request,AdminBean admin) {
    	String s ="";
    	Connection con = null;
    	CallableStatement cs = null;
    	

    	String sql = "{CALL PA_B_PFundInsurance_PRO_61(?,?,?,?,?,?)}";
    	try {
    		con = services.getConnection();
    		cs = con.prepareCall(sql);
    		cs.setString(1, request.getParameter("AnnualPaymentsYear"));
    		cs.setString(2, request.getParameter("OnPayMonth"));
    		cs.setString(3, request.getParameter("PaymentDates"));
    		cs.setString(4, request.getParameter("InputBase"));
    		cs.setString(5, admin.getCpnyId());
    		cs.registerOutParameter(6,Types.VARCHAR);
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
    		Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
    		cs.executeUpdate();
    		s =cs.getString(6);
    		
    	} catch (ServiceLocatorException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		SqlUtil.close(cs, con);
    	}
    	request.setAttribute("alert", s);
    	request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
    	return "/inc/alertMessage.jsp";
    }
    
    public String  BranchPayProvision(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{CALL PA_B_PayProvision_PRO(?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));   
            cs.setString(4, admin.getCpnyId()); 
            cs.registerOutParameter(5,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(5);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  BranchPayProvision_61(HttpServletRequest request,AdminBean admin) {
    	String s ="";
    	Connection con = null;
    	CallableStatement cs = null;
    	String sql = "{CALL PA_B_PayProvision_PRO_61(?,?,?,?,?)}";
    	try {
    		con = services.getConnection();
    		cs = con.prepareCall(sql);
    		cs.setString(1, request.getParameter("AnnualPaymentsYear"));
    		cs.setString(2, request.getParameter("OnPayMonth"));
    		cs.setString(3, request.getParameter("PaymentDates"));   
    		cs.setString(4, admin.getCpnyId()); 
    		cs.registerOutParameter(5,Types.VARCHAR);
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
    		Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
    		Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
    		cs.executeUpdate();
    		s =cs.getString(5);
    		
    	} catch (ServiceLocatorException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		SqlUtil.close(cs, con);
    	}
    	request.setAttribute("alert", s);
    	request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
    	return "/inc/alertMessage.jsp";
    }
    public String  BranchProvisionLaborInsurance(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{CALL PA_B_LaborInsurance_PRO(?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));   
            cs.setString(4, admin.getCpnyId()); 
            cs.registerOutParameter(5,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(5);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  BranchBurdenManagementFees(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "";
        String cpnyId = admin.getCpnyId();
        if(cpnyId == "59000000" || "59000000".equals(cpnyId)){
        	sql = "{CALL PA_B_ManagementFees_PRO_590(?,?,?,?,?)}";
        }else{
        	sql = "{CALL PA_B_ManagementFees_PRO(?,?,?,?,?)}";
        }
        
        //String sql = "{CALL PA_B_ManagementFees_PRO(?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));    
            cs.setString(4, admin.getCpnyId()); 
            cs.registerOutParameter(5,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(5);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  BranchBurdenManagementFees59B(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "{CALL PA_B_ManagementFees_PRO_590B(?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));    
            cs.setString(4, admin.getCpnyId()); 
            cs.registerOutParameter(5,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(5);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  BranchBurdenManagementFees59Y(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        
        String sql = "{CALL PA_B_ManagementFees_PRO_590Y(?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));    
            cs.setString(4, admin.getCpnyId()); 
            cs.registerOutParameter(5,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(5);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    public String  BranchFixedBonusesProvision(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
       
        String sql = "{CALL PA_B_FixedBonuses_PRO(?,?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    
    public String  BranchFixedBonusesProvisionA(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
       
        String sql = "{CALL PA_B_FixedBonuses_PRO_A(?,?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    
    
    public String  BranchFixedBonusesProvisionB(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
       
        String sql = "{CALL PA_B_FixedBonuses_PRO_B(?,?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.setString(5, admin.getCpnyId());
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    
    public String  BranchPerformanceResultsBonusesProvision(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{CALL PA_B_ResultsBonuses_PRO(?,?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));     
            cs.setInt(4, NumberUtil.parseInt(request.getParameter("BonuseNo")));   
            cs.setString(5, admin.getCpnyId());    
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    
    public String  BranchLegalRoomBonusesProvision(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{CALL PA_B_LegalBonuses_PRO(?,?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));   
            cs.setInt(4, NumberUtil.parseInt(request.getParameter("BonuseNo")));  
            cs.setString(5, admin.getCpnyId());  
            cs.registerOutParameter(6,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(6);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    
    public String  RetirementCompensation(HttpServletRequest request) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{CALL PA_RetirementCompensation_PRO(?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));            
            cs.registerOutParameter(4,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(4);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        request.setAttribute("alert", s);
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("AnnualPaymentsYear")+"&month="+request.getParameter("OnPayMonth")+"&Project="+request.getParameter("content"));
		return "/inc/alertMessage.jsp";
    }
    
    public String  outExcel(HttpServletRequest request,AdminBean admin) throws ServiceLocatorException {
    	String s ="";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs =null;
        List list = new ArrayList();
        String sql = "select * from pa_finance_interface t where t.gjahr=? and t.monat=? and t.pay_type=?  and t.bukrs =(select max(t.bukrs) from pa_interface_mapping t where t.cpny_id=? ) order by t.pay_type,t.item_type ";
        try {
            con =ConnBean.getConn();
            pst = con.prepareStatement(sql);
            pst.setString(1, request.getParameter("AnnualPaymentsYear"));
            pst.setString(2, request.getParameter("OnPayMonth"));           
            if (request.getParameter("Project").equals("FactoriesPayProvision")) {// 工厂工资计提
				pst.setString(3, "AA");
			} else if (request.getParameter("Project").equals(
					"FactoriesPayProvision59B")) {// DICI北京奖金计提
				pst.setString(3, "AA");
			} else if (request.getParameter("Project").equals(
					"FactoriesPayProvision59Y")) {// DICI烟台奖金计提
				pst.setString(3, "BA");
			} else if (request.getParameter("Project").equals(
					"FactoriesBonusesProvision")) {// 工厂奖金计提
				pst.setString(3, "AI");
			} else if (request.getParameter("Project").equals(
					"FactoriesBonusesProvision59B")) {// DICI北京奖金计提
				pst.setString(3, "AI");
			} else if (request.getParameter("Project").equals(
					"FactoriesBonusesProvision59Y")) {// DICI烟台奖金计提
				pst.setString(3, "BI");
			} else if (request.getParameter("Project").equals(
			"FactoriesPayProvision_G")) {// 工厂工人工资计提
				pst.setString(3, "AY");
			} else if (request.getParameter("Project").equals(
			"FactoriesPayProvision_Z")) {// 工厂职员工资计提
				pst.setString(3, "AR");
			} else if (request.getParameter("Project").equals(
			"FactoriesPayProvision_61_G")) {// disc工厂工人工资计提
				pst.setString(3, "AC");
			} else if (request.getParameter("Project").equals(
			"FactoriesPayProvision_61_Z")) {// disc工厂职员工资计提
				pst.setString(3, "AE");
			} else if (request.getParameter("Project").equals(
			"FactoriesProvidentFundInsurance_61_G")) {// disc工厂gongren 公积金计提
				pst.setString(3, "AH");
			} else if (request.getParameter("Project").equals(
			"FactoriesProvidentFundInsurance_61_Z")) {// disc工厂zhiyuan 公积金计提
				pst.setString(3, "AF");
			} else if (request.getParameter("Project").equals(
					"FactoriesBonusesProvisionA")) {// DIY工厂A职员奖金计提
				pst.setString(3, "AI");
			} else if (request.getParameter("Project").equals(
					"FactoriesBonusesProvisionB")) {// DIY工厂B职员奖金计提
				pst.setString(3, "AI");
			} else if (request.getParameter("Project").equals(
					"FactoriesPayProvisionalProvision")) {// 工厂临时工工资计提
				pst.setString(3, "AB");
			} else if (request.getParameter("Project").equals(
			"FactoriesPayProvisionalProvision_G")) {// 工厂临时工工资计提
				pst.setString(3, "AZ");

			} else if (request.getParameter("Project").equals(
					"FactoriesEndowmentInsurance")) {// 工厂养老保险
				pst.setString(3, "AC");
			} else if (request.getParameter("Project").equals(
					"FactoriesEndowmentInsurance59B")) {// DICI北京养老保险
				pst.setString(3, "AC");
			} else if (request.getParameter("Project").equals(
					"FactoriesEndowmentInsurance59Y")) {// DICI烟台养老保险
				pst.setString(3, "BC");
			} else if (request.getParameter("Project").equals(
			"FactoriesEndowmentInsurance_G")) {// 工厂工人养老保险
				pst.setString(3, "BA");
			} else if (request.getParameter("Project").equals(
			"FactoriesEndowmentInsurance_Z")) {// 工厂职员养老保险
				pst.setString(3, "AS");

			} else if (request.getParameter("Project").equals(
					"FactoriesMedicalInsurance")) {// 工厂医疗保险
				pst.setString(3, "AD");
			} else if (request.getParameter("Project").equals(
					"FactoriesMedicalInsurance59B")) {// DICI北京医疗保险
				pst.setString(3, "AD");
			} else if (request.getParameter("Project").equals(
					"FactoriesMedicalInsurance59Y")) {// DICI烟台医疗保险
				pst.setString(3, "BD");
			} else if (request.getParameter("Project").equals(
			"FactoriesMedicalInsurance_G")) {// 工厂工人医疗保险
				pst.setString(3, "BB");
			} else if (request.getParameter("Project").equals(
			"FactoriesMedicalInsurance_Z")) {// 工厂职员医疗保险
				pst.setString(3, "AT");

			} else if (request.getParameter("Project").equals(
					"FactoriesUnemployedInsurance")) {// 工厂待业保险
				pst.setString(3, "AE");
			} else if (request.getParameter("Project").equals(
					"FactoriesUnemployedInsurance59B")) {//  DICI北京待业保险
				pst.setString(3, "AE");
			} else if (request.getParameter("Project").equals(
					"FactoriesUnemployedInsurance59Y")) {//  DICI烟台待业保险
				pst.setString(3, "BE");
			} else if (request.getParameter("Project").equals(
			"FactoriesUnemployedInsurance_G")) {// 工厂工人待业保险
				pst.setString(3, "BC");
			} else if (request.getParameter("Project").equals(
			"FactoriesUnemployedInsurance_Z")) {// 工厂职员待业保险
				pst.setString(3, "AU");

			} else if (request.getParameter("Project").equals(
					"FactoriesInjuriesInsurance")) {// 工厂工伤保险
				pst.setString(3, "AF");
			} else if (request.getParameter("Project").equals(
					"FactoriesInjuriesInsurance59B")) {// DICI北京工伤保险
				pst.setString(3, "AF");
			} else if (request.getParameter("Project").equals(
					"FactoriesInjuriesInsurance59Y")) {// DICI烟台工伤保险
				pst.setString(3, "BF");
			} else if (request.getParameter("Project").equals(
			"FactoriesInjuriesInsurance_G")) {// 工厂工人工伤保险
				pst.setString(3, "BD");
			} else if (request.getParameter("Project").equals(
			"FactoriesInjuriesInsurance_Z")) {// 工厂职员工伤保险
				pst.setString(3, "AV");

			} else if (request.getParameter("Project").equals(
					"FactoriesGrowthInsurance")) {// 工厂生育保险
				pst.setString(3, "AH");
			} else if (request.getParameter("Project").equals(
					"FactoriesGrowthInsurance59B")) {// DICI北京生育保险
				pst.setString(3, "AH");
			} else if (request.getParameter("Project").equals(
					"FactoriesGrowthInsurance59Y")) {// DICI烟台生育保险
				pst.setString(3, "BH");
			} else if (request.getParameter("Project").equals(
			"FactoriesGrowthInsurance_G")) {// 工厂工人生育保险
				pst.setString(3, "BF");
			} else if (request.getParameter("Project").equals(
			"FactoriesGrowthInsurance_Z")) {// 工厂职员生育保险
				pst.setString(3, "AX");

			} else if (request.getParameter("Project").equals(
					"FactoriesProvidentFundInsurance")) {// 工厂住房公积金
				pst.setString(3, "AG");
			} else if (request.getParameter("Project").equals(
					"FactoriesProvidentFundInsurance59B")) {// DICI北京住房公积金
				pst.setString(3, "AG");
			} else if (request.getParameter("Project").equals(
					"FactoriesProvidentFundInsurance59Y")) {// DICI烟台住房公积金
				pst.setString(3, "BG");
			} else if (request.getParameter("Project").equals(
			"FactoriesProvidentFundInsurance_G")) {// 工厂工人住房公积金
				pst.setString(3, "BE");
			} else if (request.getParameter("Project").equals(
			"FactoriesProvidentFundInsurance_Z")) {// 工厂职员住房公积金
				pst.setString(3, "AW");

			} else if (request.getParameter("Project").equals(
					"BranchPayProvision")) {// 支社工资计提
				pst.setString(3, "AJ");
			} else if (request.getParameter("Project").equals(
			"BranchPayProvision_61")) {// 61支社工资计提
				pst.setString(3, "AD");

			} else if (request.getParameter("Project").equals(
					"BranchProvidentFundInsurance_61")) {// 支社住房公积金
				     pst.setString(3, "AJ");
			}else if (request.getParameter("Project").equals(
					"BranchProvisionLaborInsurance")) {// 支社计提公司负担劳动保险
				pst.setString(3, "AL");

			} else if (request.getParameter("Project").equals(
					"BranchBurdenManagementFees")) {// 支社负担国企管理费
				pst.setString(3, "AM");

			} else if (request.getParameter("Project").equals(
					"BranchBurdenManagementFees59B")) {// DICI北京负担国企管理费
				pst.setString(3, "AM");

			} else if (request.getParameter("Project").equals(
					"BranchBurdenManagementFees59Y")) {// DICI烟台负担国企管理费
				pst.setString(3, "BM");

			} else if (request.getParameter("Project").equals(
					"BranchFixedBonusesProvision")) {// 支社固定奖金计提
				pst.setString(3, "AN");

			} else if (request.getParameter("Project").equals(
					"BranchFixedBonusesProvisionA")) {// 支社A职员固定奖金计提
				pst.setString(3, "AN");

			} else if (request.getParameter("Project").equals(
					"BranchFixedBonusesProvisionB")) {// 支社B职员固定奖金计提
				pst.setString(3, "AN");

			} else if (request.getParameter("Project").equals(
					"BranchPerformanceResultsBonusesProvision")) {// 支社成果业绩奖金计提
				pst.setString(3, "AO");

			} else if (request.getParameter("Project").equals(
					"BranchLegalRoomBonusesProvision")) {// 法律事务室诉讼奖金计提
				pst.setString(3, "AP");

			} else if (request.getParameter("Project").equals(
					"RetirementCompensation")) {// 退职补偿金
				pst.setString(3, "AQ");
			}
            pst.setString(4, admin.getCpnyId());
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));   
            Logger.getLogger(getClass()).debug("SQL:" + sql);   
            rs=pst.executeQuery();
            while(rs.next()){
            	SimpleMap sm = new SimpleMap();
            	sm.set("BUKRS", rs.getString("BUKRS"));            	
            	sm.set("GJAHR", rs.getString("GJAHR"));
            	sm.set("MONAT", rs.getString("MONAT"));
            	sm.set("PAY_TYPE", rs.getString("PAY_TYPE"));
            	sm.set("ITEM_TYPE", rs.getString("ITEM_TYPE"));
            	sm.set("DEP_CODE", rs.getString("DEP_CODE"));
            	sm.set("N_OR_C", rs.getString("N_OR_C"));
            	sm.set("DEP_NAME", rs.getString("DEP_NAME"));
            	sm.set("WRBTR", rs.getString("WRBTR"));
            	sm.set("EMP_CNT", rs.getString("EMP_CNT"));            	
            	list.add(sm);
            }
         // 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			 SimpleMap columns = new SimpleMap();
			 columns.setString("公司代码", "BUKRS");
			 columns.setString("年", "GJAHR");
			 columns.setString("月", "MONAT");
			 columns.setString("类别", "PAY_TYPE");
			 columns.setString("项目", "ITEM_TYPE");
			 columns.setString("部门代码", "DEP_CODE");
			 columns.setString("标示", "N_OR_C");			 
			 columns.setString("部门名称", "DEP_NAME");
			 columns.setString("金额", "WRBTR");			 
			 columns.setString("人数", "EMP_CNT");
			
			 
			 // 定义列类型
			 SimpleMap columnType = new SimpleMap();
			 columnType.setInt("MONAT", ReportConstant.CELL_TYPE_TEXT);
			 columnType.setInt("DEP_CODE", ReportConstant.CELL_TYPE_TEXT);
			 
			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("PA_FINANCE_INTERFACE.xls");
			paramBean.setSheetname("PA_FINANCE_INTERFACE");
			paramBean.setReportData(list);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 设置页眉
			//paramBean.setHeadContent("LSFC考勤刷卡记录表");
			// 设置内嵌表头
			// LSFC考勤刷卡记录表
			paramBean.setInLineHeadContent("");
			paramBean.setInLineHeadMergeSize(columns.size());
			// 设置EXCEL表头的显示方向
			//paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
			
			// make attendance record report
			ReportUtil.makeReport(request, paramBean); 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(pst, con);
        }
        return "/inc/commonReport.jsp";
    }
    
	public String inputExcel(HttpServletRequest request,AdminBean admin) {
		// TODO Auto-generated method stub
		String message;

		SimpleMap parameters = new SimpleMap();	

		ExcelBatchProcessor processor = new ExcelBatchProcessor();

		try {
			String payear=request.getParameter("AnnualPaymentsYear");
            String pamonth=request.getParameter("OnPayMonth");  
            String paytype="";
            if (request.getParameter("Project").equals("FactoriesPayProvision")) {// 工厂工资计提
				paytype="AA";
			} else if (request.getParameter("Project").equals(
					"FactoriesPayProvision59B")) {// DICI北京工资计提
				paytype="AA";
			} else if (request.getParameter("Project").equals(
					"FactoriesPayProvision59Y")) {// DICI烟台工资计提
				paytype="BA";
			} else if (request.getParameter("Project").equals(
					"FactoriesBonusesProvision")) {// 工厂奖金计提
				paytype="AI";
			} else if (request.getParameter("Project").equals(
					"FactoriesBonusesProvision59B")) {// DICI北京奖金计提
				paytype="AI";
			} else if (request.getParameter("Project").equals(
					"FactoriesBonusesProvision59Y")) {// DICI烟台奖金计提
				paytype="BI";
			} else if (request.getParameter("Project").equals(
			"FactoriesPayProvision_G")) {// 工厂工人工资计提
				paytype="AY";
			} else if (request.getParameter("Project").equals(
			"FactoriesPayProvision_Z")) {// 工厂职员工资计提
				paytype="AR";
			} else if (request.getParameter("Project").equals(
			"FactoriesPayProvision_61_G")) {// disc工厂工人工资计提
				paytype="AC";
			} else if (request.getParameter("Project").equals(
			"FactoriesPayProvision_61_Z")) {// disc工厂职员工资计提
				paytype="AE";
			} else if (request.getParameter("Project").equals(
			"FactoriesProvidentFundInsurance_61_G")) {// disc工厂gongren 公积金计提
				paytype="AH";
			} else if (request.getParameter("Project").equals(
			"FactoriesProvidentFundInsurance_61_Z")) {// disc工厂zhiyuan 公积金计提
				paytype="AF";
			} else if (request.getParameter("Project").equals(
					"FactoriesBonusesProvisionA")) {// DIY工厂A职员奖金计提
				paytype="AI";
			} else if (request.getParameter("Project").equals(
					"FactoriesBonusesProvisionB")) {// DIY工厂B职员奖金计提
				paytype="AI";
			} else if (request.getParameter("Project").equals(
					"FactoriesPayProvisionalProvision")) {// 工厂临时工工资计提
				paytype="AB";
			} else if (request.getParameter("Project").equals(
			"FactoriesPayProvisionalProvision_G")) {// 工厂临时工工资计提
				paytype="AZ";

			} else if (request.getParameter("Project").equals(
					"FactoriesEndowmentInsurance")) {// 工厂养老保险
				paytype="AC";
			} else if (request.getParameter("Project").equals(
					"FactoriesEndowmentInsurance59B")) {// DICI北京养老保险
				paytype="AC";
			} else if (request.getParameter("Project").equals(
					"FactoriesEndowmentInsurance59Y")) {// DICI烟台养老保险
				paytype="BC";
			} else if (request.getParameter("Project").equals(
			"FactoriesEndowmentInsurance_G")) {// 工厂工人养老保险
				paytype="BA";
			} else if (request.getParameter("Project").equals(
			"FactoriesEndowmentInsurance_Z")) {// 工厂职员养老保险
				paytype="AS";

			} else if (request.getParameter("Project").equals(
					"FactoriesMedicalInsurance")) {// 工厂医疗保险
				paytype="AD";
			} else if (request.getParameter("Project").equals(
					"FactoriesMedicalInsurance59B")) {// DICI北京医疗保险
				paytype="AD";
			} else if (request.getParameter("Project").equals(
					"FactoriesMedicalInsurance59Y")) {// DICI烟台医疗保险
				paytype="BD";
			} else if (request.getParameter("Project").equals(
			"FactoriesMedicalInsurance_G")) {// 工厂工人医疗保险
				paytype="BB";
			} else if (request.getParameter("Project").equals(
			"FactoriesMedicalInsurance_Z")) {// 工厂职员医疗保险
				paytype="AT";

			} else if (request.getParameter("Project").equals(
					"FactoriesUnemployedInsurance")) {// 工厂待业保险
				paytype="AE";
			} else if (request.getParameter("Project").equals(
					"FactoriesUnemployedInsurance59B")) {// DICI北京待业保险
				paytype="AE";
			} else if (request.getParameter("Project").equals(
					"FactoriesUnemployedInsurance59Y")) {// DICI烟台待业保险
				paytype="BE";
			} else if (request.getParameter("Project").equals(
			"FactoriesUnemployedInsurance_G")) {// 工厂工人待业保险
				paytype="BC";
			} else if (request.getParameter("Project").equals(
			"FactoriesUnemployedInsurance_Z")) {// 工厂职员待业保险
				paytype="AU";

			} else if (request.getParameter("Project").equals(
					"FactoriesInjuriesInsurance")) {// 工厂工伤保险
				paytype="AF";
			} else if (request.getParameter("Project").equals(
					"FactoriesInjuriesInsurance59B")) {// DICI北京工伤保险
				paytype="AF";
			} else if (request.getParameter("Project").equals(
					"FactoriesInjuriesInsurance59Y")) {// DICI烟台工伤保险
				paytype="BF";
			} else if (request.getParameter("Project").equals(
			"FactoriesInjuriesInsurance_G")) {// 工厂工人工伤保险
				paytype="BD";
			} else if (request.getParameter("Project").equals(
			"FactoriesInjuriesInsurance_Z")) {// 工厂职员工伤保险
				paytype="AV";

			} else if (request.getParameter("Project").equals(
					"FactoriesGrowthInsurance")) {// 工厂生育保险
				paytype="AH";
			} else if (request.getParameter("Project").equals(
					"FactoriesGrowthInsurance59B")) {// DICI北京生育保险
				paytype="AH";
			} else if (request.getParameter("Project").equals(
					"FactoriesGrowthInsurance59Y")) {// DICI烟台生育保险
				paytype="BH";
			} else if (request.getParameter("Project").equals(
			"FactoriesGrowthInsurance_G")) {// 工厂工人生育保险
				paytype="BF";
			} else if (request.getParameter("Project").equals(
			"FactoriesGrowthInsurance_Z")) {// 工厂职员生育保险
				paytype="AX";

			} else if (request.getParameter("Project").equals(
					"FactoriesProvidentFundInsurance")) {// 工厂住房公积金
				paytype="AG";
			} else if (request.getParameter("Project").equals(
					"FactoriesProvidentFundInsurance59B")) {// DICI北京住房公积金
				paytype="AG";
			} else if (request.getParameter("Project").equals(
					"FactoriesProvidentFundInsurance59Y")) {// DICI烟台住房公积金
				paytype="BG";
			} else if (request.getParameter("Project").equals(
			"FactoriesProvidentFundInsurance_G")) {// 工厂工人住房公积金
				paytype="BE";
			} else if (request.getParameter("Project").equals(
			"FactoriesProvidentFundInsurance_Z")) {// 工厂职员住房公积金
				paytype="AW";

			} else if (request.getParameter("Project").equals(
					"BranchPayProvision")) {// 支社工资计提
				paytype="AJ";
			} else if (request.getParameter("Project").equals(
			"BranchPayProvision_61")) {// 61支社工资计提
				paytype="AD";

			}else if (request.getParameter("Project").equals(
					"BranchProvidentFundInsurance_61")) {// 支社住房公积金
				paytype="AJ";
			} else if (request.getParameter("Project").equals(
					"BranchProvisionLaborInsurance")) {// 支社计提公司负担劳动保险
				paytype="AL";

			} else if (request.getParameter("Project").equals(
					"BranchBurdenManagementFees")) {// 支社负担国企管理费
				paytype="AM";

			} else if (request.getParameter("Project").equals(
					"BranchBurdenManagementFees59B")) {// DICI北京负担国企管理费
				paytype="AM";

			} else if (request.getParameter("Project").equals(
					"BranchBurdenManagementFees59Y")) {// DICI烟台负担国企管理费
				paytype="BM";

			} else if (request.getParameter("Project").equals(
					"BranchFixedBonusesProvision")) {// 支社固定奖金计提
				paytype="AN";

			} else if (request.getParameter("Project").equals(
					"BranchFixedBonusesProvisionA")) {// 支社A职员固定奖金计提
				paytype="AN";

			} else if (request.getParameter("Project").equals(
					"BranchFixedBonusesProvisionB")) {// 支社B职员固定奖金计提
				paytype="AN";

			} else if (request.getParameter("Project").equals(
					"BranchPerformanceResultsBonusesProvision")) {// 支社成果业绩奖金计提
				paytype="AO";

			} else if (request.getParameter("Project").equals(
					"BranchLegalRoomBonusesProvision")) {// 法律事务室诉讼奖金计提
				paytype="AP";

			} else if (request.getParameter("Project").equals(
					"RetirementCompensation")) {// 退职补偿金
				paytype="AQ";
			}
			// Excel列映射的数据库字段数组
			SimpleMap mapColumn = new SimpleMap();
			mapColumn.set("BUKRS", ExcelBatchProcessor.CHAR);
			mapColumn.set("GJAHR", ExcelBatchProcessor.CHAR);
			mapColumn.set("MONAT", ExcelBatchProcessor.CHAR);
			mapColumn.set("PAY_TYPE", ExcelBatchProcessor.CHAR);
			mapColumn.set("ITEM_TYPE", ExcelBatchProcessor.CHAR);
			mapColumn.set("DEP_CODE", ExcelBatchProcessor.CHAR);
			mapColumn.set("N_OR_C", ExcelBatchProcessor.CHAR);
			mapColumn.set("DEP_NAME", ExcelBatchProcessor.CHAR);
			mapColumn.set("WRBTR", ExcelBatchProcessor.NUMBER);
			mapColumn.set("EMP_CNT", ExcelBatchProcessor.NUMBER);

//			 附加表字段
			SimpleMap appendColumn = new SimpleMap();
			
			appendColumn.set("FILE_NO", "");
			appendColumn.set("ZFBDT", "");
			appendColumn.set("CPUDT", "");
			int fileno=this.getSequence("PA_FINANCE_INTERFACE_SEQ");
			
			// 定义插入语句的子查询语句
			String sqlContent = " SELECT #BUKRS#,#GJAHR#,#MONAT#,#PAY_TYPE#,#ITEM_TYPE#,#DEP_CODE#,#N_OR_C#,#DEP_NAME#,#WRBTR#,#EMP_CNT#," 
							  + fileno+",to_char(SYSDATE,'yyyy-mm-dd'),SYSDATE " 
							  + " FROM DUAL " ;			
			String pkStr = "GJAHR,MONAT,PAY_TYPE,ITEM_TYPE,DEP_CODE";
			
			// 添加参数
			parameters.set("tableName", "PA_FINANCE_INTERFACE");
			parameters.set("sheetName", "PA_FINANCE_INTERFACE");
			parameters.set("mappingColumn", mapColumn);
			parameters.set("appendColumn", appendColumn);
			parameters.set("sqlContent", sqlContent);
			parameters.set("pk_str", pkStr);
			
			// 插入导入数据到临时表
			message = processor.process(request, parameters, ExcelBatchProcessor.INVOLUTE);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error("Import PA_PARAM_DATA data fail. " + e.toString());
			throw new GlRuntimeException("Import PA_PARAM_DATA data fail.", e);
		}

		request.setAttribute("alert", message);
		request.setAttribute("reload","parent.opener.location.reload();parent.close()");

		return "/inc/alertMessage.jsp";
	}
    public String  interfaceOutExcel(HttpServletRequest request,AdminBean admin) {
    	String s ="";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs =null;
        List list = new ArrayList();
        String sql = "select t.*,to_char(t.cpudt,'YYYY-MM-DD') TOCPUDT from pa_finance_interface t where t.gjahr=? and t.monat=?  and t.bukrs =(select max(t.bukrs) from pa_interface_mapping t where t.cpny_id=? ) order by t.pay_type,t.item_type ";
        try {
            con =ConnBean.getConn();
            pst = con.prepareStatement(sql);
            pst.setString(1, request.getParameter("payear"));
            pst.setString(2, request.getParameter("pamonth"));   
            pst.setString(3, admin.getCpnyId());  
            Logger.getLogger(getClass()).debug("payear:" + request.getParameter("payear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("pamonth"));    
            Logger.getLogger(getClass()).debug("sql:" +sql);
            rs=pst.executeQuery();            
            while(rs.next()){
            	SimpleMap sm = new SimpleMap();
            	sm.set("BUKRS", rs.getString("BUKRS"));  
            	sm.set("FILE_NO", rs.getString("FILE_NO"));              
            	sm.set("GJAHR", rs.getString("GJAHR"));
            	sm.set("MONAT", rs.getString("MONAT"));
            	sm.set("PAY_TYPE", rs.getString("PAY_TYPE"));
            	sm.set("ITEM_TYPE", rs.getString("ITEM_TYPE"));
            	sm.set("DEP_CODE", rs.getString("DEP_CODE"));
            	sm.set("N_OR_C", rs.getString("N_OR_C"));
            	sm.set("DEP_NAME", rs.getString("DEP_NAME"));
            	sm.set("WRBTR", rs.getString("WRBTR"));
            	sm.set("EMP_CNT", rs.getString("EMP_CNT"));
            	sm.set("ZFBDT", rs.getString("ZFBDT")); 
            	sm.set("CPUDT", rs.getString("TOCPUDT"));
            	Logger.getLogger(getClass()).debug("CPUDT:" +rs.getString("CPUDT"));
            	list.add(sm);
            }
         // 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			 SimpleMap columns = new SimpleMap();
			 columns.setString("公司代码", "BUKRS");
			 columns.setString("文件编号", "FILE_NO");
			 columns.setString("年", "GJAHR");
			 columns.setString("月", "MONAT");
			 columns.setString("类别", "PAY_TYPE");
			 columns.setString("项目", "ITEM_TYPE");
			 columns.setString("部门代码", "DEP_CODE");
			 columns.setString("标示", "N_OR_C");			 
			 columns.setString("部门名称", "DEP_NAME");
			 columns.setString("金额", "WRBTR");			 
			 columns.setString("人数", "EMP_CNT");
			 columns.setString("支付日期", "ZFBDT");			 
			 columns.setString("生成日期", "CPUDT");
			
			 
			 // 定义列类型
			 SimpleMap columnType = new SimpleMap();
			 columnType.setInt("MONAT", ReportConstant.CELL_TYPE_TEXT);
			 columnType.setInt("DEP_CODE", ReportConstant.CELL_TYPE_TEXT);			 
			 
			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("PA_FINANCE_INTERFACE.xls");
			paramBean.setSheetname("PA_FINANCE_INTERFACE");
			paramBean.setReportData(list);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);

			paramBean.setInLineHeadContent("");
			paramBean.setInLineHeadMergeSize(columns.size());

			ReportUtil.makeReport(request, paramBean); 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(rs, pst, con);
        }
        return "/inc/commonReport.jsp";	
        
        
    }
    public String  deleteInterfaceData(HttpServletRequest request){
    
        Connection con = null;
        PreparedStatement pst = null;         
        String sql = "delete from pa_finance_interface t where t.gjahr=? and t.monat=? and t.pay_type=? and t.file_no=? ";
        try {
            con =ConnBean.getConn();
            pst = con.prepareStatement(sql);
            pst.setString(1, request.getParameter("payear"));
            pst.setString(2, request.getParameter("pamonth"));           
             if (request.getParameter("deleteProject").equals(
					"BranchPerformanceResultsBonusesProvision")) {// 支社成果业绩奖金计提
				pst.setString(3, "AO");

			} else if (request.getParameter("deleteProject").equals(
					"BranchLegalRoomBonusesProvision")) {// 法律事务室诉讼奖金计提
				pst.setString(3, "AP");

			}
             pst.setInt(4, NumberUtil.parseInt(request.getParameter("fileno")));   
             pst.executeUpdate();
         
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(pst, con);
        }
        request.setAttribute("alert", "删除数据成功！");
		request.setAttribute("url", "/pa/pa0506.jsp?menu_code=pa0506&year="+request.getParameter("payear")+"&month="+request.getParameter("pamonth"));
		return "/inc/alertMessage.jsp";
    }
	/*得到序列号*/
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
}
