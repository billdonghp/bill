package com.ait.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.contrib.HSSFCellUtil;

/**
 * @author yangxiaohui
 * @date 2009-06-29
 */

public class XlsUtil {
	
	private static final Logger logger = Logger.getLogger(XlsUtil.class);
	
	public XlsUtil(){
		 new XlsUtil();
		
	}
	
	public  static void setCellValue(int row, int col, String value,HSSFSheet sheet) {
		if (StringUtils.isNotEmpty(value))		
			HSSFCellUtil.getCell(HSSFCellUtil.getRow(row,sheet), col).setCellValue(new HSSFRichTextString(StringUtils.trimToEmpty(value)));
	}

	public  static void setPhoto(int left, int top, int width, int height, File photo,HSSFSheet sheet,HSSFWorkbook workBook) {
		if (photo.exists())
			try {
				HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) left, top, (short) (left + width), top + height);
				anchor.setAnchorType(0);
				InputStream stream = new FileInputStream(photo);
				byte[] bytes = new byte[stream.available()];
				stream.read(bytes);
				stream.close();
				HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
				patriarch.createPicture(anchor, workBook.addPicture(bytes, HSSFWorkbook.PICTURE_TYPE_JPEG));
			} catch (IOException e) {
				logger.error(e.toString(), e);
			}
	}
	
	

}
