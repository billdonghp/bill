package com.ait.kpa.servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sy.bean.AdminBean;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
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
		String adminid = admin.getAdminID();
		String content = request.getParameter("content");
		String returnPage = null;
		if(!content.equals("") && content.equals("FactoriesPayProvision")){//工厂工资计提
			String flag=this.FactoriesPayProvision(request);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa0205.jsp?menu_code=pa0205");
			returnPage="/inc/alertMessage.jsp";
		}else if(!content.equals("") && content.equals("FactoriesBonusesProvision")){//工厂奖金计提
			String flag=this.FactoriesBonusesProvision(request);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa0205.jsp?menu_code=pa0205");
			returnPage="/inc/alertMessage.jsp";
		}else if(!content.equals("") && content.equals("FactoriesPayProvisionalProvision")){//工厂临时工工资计提
			String flag=this.FactoriesPayProvisionalProvision(request);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa0205.jsp?menu_code=pa0205");
			returnPage="/inc/alertMessage.jsp";
		}else if(!content.equals("") && content.equals("FactoriesEndowmentInsurance")){//工厂养老保险
			String flag=this.FactoriesEndowmentInsurance(request);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa0205.jsp?menu_code=pa0205");
			returnPage="/inc/alertMessage.jsp";
		}else if(!content.equals("") && content.equals("FactoriesMedicalInsurance")){//工厂医疗保险
			String flag=this.FactoriesMedicalInsurance(request);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa0205.jsp?menu_code=pa0205");
			returnPage="/inc/alertMessage.jsp";
		}else if(!content.equals("") && content.equals("FactoriesUnemployedInsurance")){//工厂待业保险
			String flag=this.FactoriesUnemployedInsurance(request);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa0205.jsp?menu_code=pa0205");
			returnPage="/inc/alertMessage.jsp";
		}else if(!content.equals("") && content.equals("FactoriesInjuriesInsurance")){//工厂工伤保险
			String flag=this.FactoriesInjuriesInsurance(request);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa0205.jsp?menu_code=pa0205");
			returnPage="/inc/alertMessage.jsp";
		}else if(!content.equals("") && content.equals("FactoriesGrowthInsurance")){//工厂生育保险
			String flag=this.FactoriesGrowthInsurance(request);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa0205.jsp?menu_code=pa0205");
			returnPage="/inc/alertMessage.jsp";
		}else if(!content.equals("") && content.equals("FactoriesProvidentFundInsurance")){//工厂住房公积金
			String flag=this.FactoriesProvidentFundInsurance(request);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa0205.jsp?menu_code=pa0205");
			returnPage="/inc/alertMessage.jsp";
		}else if(!content.equals("") && content.equals("BranchPayProvision")){//支社工资计提
			String flag=this.BranchPayProvision(request);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa0205.jsp?menu_code=pa0205");
			returnPage="/inc/alertMessage.jsp";
		}else if(!content.equals("") && content.equals("BranchProvisionLaborInsurance")){//支社计提公司负担劳动保险
			String flag=this.BranchProvisionLaborInsurance(request);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa0205.jsp?menu_code=pa0205");
			returnPage="/inc/alertMessage.jsp";
		}else if(!content.equals("") && content.equals("BranchBurdenManagementFees")){//支社负担国企管理费
			String flag=this.BranchBurdenManagementFees(request);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa0205.jsp?menu_code=pa0205");
			returnPage="/inc/alertMessage.jsp";
		}else if(!content.equals("") && content.equals("BranchFixedBonusesProvision")){//支社固定奖金计提
			String flag=this.BranchFixedBonusesProvision(request);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa0205.jsp?menu_code=pa0205");
			returnPage="/inc/alertMessage.jsp";
		}else if(!content.equals("") && content.equals("BranchPerformanceResultsBonusesProvision")){//支社成果业绩奖金计提
			String flag=this.BranchPerformanceResultsBonusesProvision(request);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa0205.jsp?menu_code=pa0205");
			returnPage="/inc/alertMessage.jsp";
		}else if(!content.equals("") && content.equals("BranchLegalRoomBonusesProvision")){//法律事务室诉讼奖金计提
			String flag=this.BranchLegalRoomBonusesProvision(request);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa0205.jsp?menu_code=pa0205");
			returnPage="/inc/alertMessage.jsp";
		}else if(!content.equals("") && content.equals("RetirementCompensation")){//退职补偿金
			String flag=this.RetirementCompensation(request);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa0205.jsp?menu_code=pa0205");
			returnPage="/inc/alertMessage.jsp";
		}else{
			return "error.jsp";
		}
		return returnPage;
	}
	

    public String  FactoriesPayProvision(HttpServletRequest request) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{CALL PA_F_PayProvision_PRO(?,?,?,?)}";
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
        return s;
    }
    
    public String  FactoriesBonusesProvision(HttpServletRequest request) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{CALL PA_F_BonusesProvision_PRO(?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.registerOutParameter(5,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(5);
           System.out.println(s =cs.getString(5));
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        return s;
    }
    
    public String  FactoriesPayProvisionalProvision(HttpServletRequest request) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{CALL PA_F_ProvisionalProvision_PRO(?,?,?,?)}";
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
           System.out.println(s =cs.getString(4));
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        return s;
    }
    
    public String  FactoriesEndowmentInsurance(HttpServletRequest request) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{CALL PA_F_EndowmentInsurance_PRO(?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.registerOutParameter(5,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(5);
           System.out.println(s =cs.getString(5));
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        return s;
    }
    public String  FactoriesMedicalInsurance(HttpServletRequest request) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{CALL PA_F_MedicalInsurance_PRO(?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.registerOutParameter(5,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(5);
           System.out.println(s =cs.getString(5));
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        return s;
    }
    public String  FactoriesUnemployedInsurance(HttpServletRequest request) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{CALL PA_F_UnemployedInsurance_PRO(?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.registerOutParameter(5,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(5);
           System.out.println(s =cs.getString(5));
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        return s;
    }
    public String  FactoriesInjuriesInsurance(HttpServletRequest request) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{CALL PA_F_InjuriesInsurance_PRO(?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.registerOutParameter(5,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(5);
           System.out.println(s =cs.getString(5));
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        return s;
    }
    public String  FactoriesGrowthInsurance(HttpServletRequest request) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{CALL PA_F_GrowthInsurance_PRO(?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.registerOutParameter(5,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(5);
           System.out.println(s =cs.getString(5));
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        return s;
    }
    public String  FactoriesProvidentFundInsurance(HttpServletRequest request) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{CALL PA_F_PFundInsurance_PRO(?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.registerOutParameter(5,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(5);
           System.out.println(s =cs.getString(5));
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        return s;
    }
    public String  BranchPayProvision(HttpServletRequest request) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{CALL PA_B_PayProvision_PRO(?,?,?,?)}";
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
           System.out.println(s =cs.getString(4));
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        return s;
    }
    public String  BranchProvisionLaborInsurance(HttpServletRequest request) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{CALL PA_B_LaborInsurance_PRO(?,?,?,?)}";
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
           System.out.println(s =cs.getString(4));
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        return s;
    }
    public String  BranchBurdenManagementFees(HttpServletRequest request) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{CALL PA_B_ManagementFees_PRO(?,?,?,?)}";
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
           System.out.println(s =cs.getString(4));
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        return s;
    }
    public String  BranchFixedBonusesProvision(HttpServletRequest request) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{CALL PA_B_FixedBonuses_PRO(?,?,?,?,?)}";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, request.getParameter("AnnualPaymentsYear"));
            cs.setString(2, request.getParameter("OnPayMonth"));
            cs.setString(3, request.getParameter("PaymentDates"));
            cs.setString(4, request.getParameter("InputBase"));
            cs.registerOutParameter(5,Types.VARCHAR);
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("AnnualPaymentsYear"));
            Logger.getLogger(getClass()).debug("pamonth:" + request.getParameter("OnPayMonth"));
            Logger.getLogger(getClass()).debug("company:" + request.getParameter("PaymentDates"));
            cs.executeUpdate();
           s =cs.getString(5);
           System.out.println(s =cs.getString(5));
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        return s;
    }
    
    public String  BranchPerformanceResultsBonusesProvision(HttpServletRequest request) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{CALL PA_B_ResultsBonuses_PRO(?,?,?,?)}";
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
           System.out.println(s =cs.getString(4));
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        return s;
    }
    
    public String  BranchLegalRoomBonusesProvision(HttpServletRequest request) {
    	String s ="";
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{CALL PA_B_LegalBonuses_PRO(?,?,?,?)}";
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
           System.out.println(s =cs.getString(4));
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        return s;
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
           System.out.println(s =cs.getString(4));
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, con);
        }
        return s;
    }
}
