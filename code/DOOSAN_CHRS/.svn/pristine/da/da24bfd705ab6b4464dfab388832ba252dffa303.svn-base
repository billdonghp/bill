package com.ait.ga.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ait.ess.business.EssServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.ServiceLocatorException;
import com.ait.util.XlsUtil;

public class VisiterApply {
	private static final String sheetName = "visiterApply";

	private static final String templatePath ="reports\\template\\VisiterApplyform.xls";

	///private static final String photoPath = "images\\main\\logo.jpg";

	private String webRootPath = null;

	private HSSFWorkbook workBook = null;

	private HSSFSheet sheet = null;

	private String applyno = null;
	

	public void setApplyno(String applyno) {
		this.applyno = applyno;
	}

	public void setWebRootPath(String webRootPath) {
		this.webRootPath = webRootPath;
	}

	public void write(OutputStream out) throws IOException {
		this.workBook.write(out);
	}
	
	public void fillReport() throws FileNotFoundException, IOException, ServiceLocatorException, SQLException {
		File template = new File(webRootPath + templatePath);

		this.workBook = new HSSFWorkbook(new FileInputStream(template));
		this.sheet = this.workBook.getSheet(sheetName);

		EssServices essServices = new EssServices();
		SimpleMap parameterObject = new SimpleMap();	
		parameterObject.set("LEAVE_NO",applyno);	
		SimpleMap sm = (SimpleMap)essServices.getLeaveApplyInfo1(parameterObject);	
		/**
		 * 参观者信息
		 */
		XlsUtil.setCellValue(3, 2, sm.getString("VISIT_INTRO_DEPT"),this.sheet); //访问介绍单位
		XlsUtil.setCellValue(3, 6, sm.getString("VISITER_CONTACT_TEL"),this.sheet); //访问介绍单位
		XlsUtil.setCellValue(4, 2, sm.getString("VISITER_COMPANY"),this.sheet); //访问单位
		XlsUtil.setCellValue(4, 6, sm.getString("VISITER_COMPANY"),this.sheet); //访问地点
		XlsUtil.setCellValue(5, 2, sm.getString("VISITER_DATE")+" "+sm.getString("VISITER_COME_TIME")+"--"+sm.getString("VISITER_END_TIME"),this.sheet); //来访日期
		XlsUtil.setCellValue(5, 6, sm.getString("VISIT_COUNT"),this.sheet); //访问人数
		XlsUtil.setCellValue(6, 2, sm.getString("VISITER_NAME"),this.sheet); //主要客人姓名/职务
		XlsUtil.setCellValue(6, 6, sm.getString("VISITER_CARNUM"),this.sheet); //车牌号
		XlsUtil.setCellValue(7, 2, sm.getString("VISITER_OBJECTIVE"),this.sheet); //来访目的
		XlsUtil.setCellValue(7, 6, sm.getString("EXPECT_RESULT"),this.sheet); //期待效果
		if("参观交流".equals(sm.getString("VISIT_TYPE"))){
			XlsUtil.setCellValue(9, 2, "√",this.sheet); //访问类型--参观交流
		}
		if("商务考察".equals(sm.getString("VISIT_TYPE"))){
			XlsUtil.setCellValue(9, 4, "√",this.sheet); //访问类型--商务考察
		}
		if("政府安排".equals(sm.getString("VISIT_TYPE"))){
			XlsUtil.setCellValue(9, 6, "√",this.sheet); //访问类型--政府安排
		}
		if("政府/协会/机构".equals(sm.getString("VISITOR_TYPE"))){
			XlsUtil.setCellValue(11, 2, "√",this.sheet); //访客类型--政府/协会/机构
		}
		if("斗山集团".equals(sm.getString("VISITOR_TYPE"))){
			XlsUtil.setCellValue(11, 4, "√",this.sheet); //访客类型--斗山集团
		}
		if("代理商/外协厂".equals(sm.getString("VISITOR_TYPE"))){
			XlsUtil.setCellValue(11, 6, "√",this.sheet); //访客类型--代理商/外协厂
		}
		if("学校".equals(sm.getString("VISITOR_TYPE"))){
			XlsUtil.setCellValue(13, 2, "√",this.sheet); //访客类型--代理商/外协厂
		}
		if("竞争企业".equals(sm.getString("VISITOR_TYPE"))){
			XlsUtil.setCellValue(13, 4, "√",this.sheet); //访客类型--竞争企业
		}
		if("非竞争企业".equals(sm.getString("VISITOR_TYPE"))){
			XlsUtil.setCellValue(13, 6, "√",this.sheet); //访客类型--非竞争企业
		}
		
		/**
		 * 管理部协助内容
		 */
		if("欢迎屏".equals(sm.getString("WELCOME_SCR"))){
			XlsUtil.setCellValue(16, 0, sm.getString("WELCOME_SCR"),this.sheet); //欢迎屏
		}
		
			XlsUtil.setCellValue(16, 2, sm.getString("RECEPTION_OFFICE"),this.sheet); //VIP接待室
		
		if("茶".equals(sm.getString("TEA"))){
			XlsUtil.setCellValue(16, 4, "√",this.sheet); //茶
		}
		if("咖啡".equals(sm.getString("COFFEE"))){
			XlsUtil.setCellValue(16, 6, "√",this.sheet); //咖啡
		}
		if("矿泉水".equals(sm.getString("PRESENT"))){
			XlsUtil.setCellValue(18, 0, "√",this.sheet); //礼品
		}
		if("宣传片".equals(sm.getString("ADVERTISING_VIDEO"))){
			XlsUtil.setCellValue(18, 2, "√",this.sheet); //宣传片
		}
		if("现场防护设备".equals(sm.getString("DEFEND_FACILITY"))){
			XlsUtil.setCellValue(18, 4, "√",this.sheet); //现场防护设备
		}
		if("传译设备".equals(sm.getString("INTERPRET_FACILITY"))){
			XlsUtil.setCellValue(18, 6, "√",this.sheet); //传译设备
		}
		if("拍摄".equals(sm.getString("SHOOT"))){
			XlsUtil.setCellValue(20, 0, "√",this.sheet); //拍摄
		}
		
		if("午餐就餐".equals(sm.getString("LUNCH_REPAST"))){
			XlsUtil.setCellValue(20, 2, "√",this.sheet); //午餐就餐
		}
		if("VIP餐厅".equals(sm.getString("RESTAURANT"))){
			XlsUtil.setCellValue(20, 4, "√",this.sheet); //VIP餐厅
		}
		if("水果(VIP)".equals(sm.getString("FRUIT"))){
			XlsUtil.setCellValue(20, 6, "√",this.sheet); //水果(VIP)
		}
		
			
		
		/**
		 * 接待部门信息
		 */
		XlsUtil.setCellValue(22, 2, sm.getString("DEPTNAME"),this.sheet); //接待部门
		XlsUtil.setCellValue(22, 6, sm.getString("FRONT_EMP"),this.sheet); //接待担当
		XlsUtil.setCellValue(23, 2, sm.getString("RECEPTION_LEAD"),this.sheet); //接待领导
		XlsUtil.setCellValue(23, 6, sm.getString("RECEPTION_ACT_PHONE"),this.sheet); //接待担当电话
		XlsUtil.setCellValue(24, 2, sm.getString("NOTE"),this.sheet); //特记事项
		/**
		 * 照片
		 */
		///XlsUtil.setPhoto(4, 16, 8, 3, new File(webRootPath + photoPath),this.sheet,this.workBook);
	}	

}
