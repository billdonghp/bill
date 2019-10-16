/*
 * @(#)ReportUtil.java 1.0 2007-1-29 上午11:51:06
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.excel.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.HeaderFooter;
import jxl.Workbook;
import jxl.biff.DisplayFormat;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.Orientation;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.SQLMapConfigManager;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2007-1-29 上午11:51:06
 * @version 1.0
 * 
 */
public class ReportUtil {
	
	private static final int defaultOrientation = 1;

	private static final String defaultSysFile = "/system.properties";

	private static final String defaultReportTemplateConfig = "report.template.folder";

	private static final String defaultReportPathConfig = "report.jasper.folder";

	private static final Logger logger = Logger.getLogger(SQLMapConfigManager.class);

	/**
	 * generate report template file name
	 * 
	 * @return String
	 */
	public static String generateFileName() {
		return generateFileName(null, null, null);
	}

	/**
	 * generate report template file name
	 * 
	 * @param webPath
	 * @return String
	 */
	public static String generateFileName(String webPath) {
		return generateFileName(webPath, null, null);
	}

	/**
	 * generate report template file name
	 * 
	 * @param sysFilePath
	 * @return String
	 */
	public static String generateFileName(String webPath, String sysFilePath, String reportTemplateConfig) {
		String tmpFile = "";
		String templateFilePath;
		String segmentPath;
		UserConfiguration userConfig;
		try {
			// load system properties file
			userConfig = getUserConfiguration(sysFilePath);
			// get template file path
			if (reportTemplateConfig == null || reportTemplateConfig.equals("")) {
				segmentPath = userConfig.getString(defaultReportTemplateConfig);
			} else {
				segmentPath = userConfig.getString(reportTemplateConfig);
			}
			// construct template file path
			if (webPath == null || webPath.equals("")) {
				templateFilePath = segmentPath;
			} else {
				templateFilePath = getFullPath(webPath, segmentPath);
			}
			tmpFile = templateFilePath + System.currentTimeMillis();
		} catch (Exception e) {
			logger.error("generate report template file name fail.");
			throw new GlRuntimeException("generate report template file name fail.", e);
		}
		return tmpFile;
	}
	
	/**
	 * make report
	 * 
	 * @deprecated 
	 * @param request
	 * @param columns
	 *            column name(key: report column name, value: mapping SQL field
	 *            name)
	 * @param fileName
	 *            report file name
	 * @param sheetName
	 *            work sheet name
	 * @param fileType
	 *            template file type(0:pdf,1:excel,2:html)
	 * @param reportType
	 *            report type(0: no attachment,1: return attachment)
	 */
	public static void makeReport(HttpServletRequest request, Map columns, String fileName, String sheetName, int fileType, int reportType) {
		makeReport(request, columns, null, fileName, sheetName, fileType, reportType);
	}

	/**
	 * make report
	 * 
	 * @deprecated
	 * @param request
	 * @param reportData
	 *            fill report data
	 * @param fileName
	 *            report file name
	 * @param sheetName
	 *            work sheet name
	 * @param fileType
	 *            template file type(0:pdf,1:excel,2:html)
	 * @param reportType
	 *            report type(0: no attachment,1: return attachment)
	 */
	public static void makeReport(HttpServletRequest request, List reportData, String fileName, String sheetName, int fileType, int reportType) {
		makeReport(request, null, reportData, fileName, sheetName, fileType, reportType);
	}

	/**
	 * make report file
	 * 
	 * @deprecated
	 * @param request      
	 * @param columns
	 *            column name(key: report column name, value: mapping SQL field
	 *            name)
	 * @param reportData
	 *            fill report data
	 * @param fileName
	 *            report file name
	 * @param sheetName
	 *            work sheet name
	 * @param fileType
	 *            template file type(0:pdf,1:excel,2:html)
	 * @param reportType
	 *            report type(0: no attachment,1: return attachment)
	 * 
	 */
	@SuppressWarnings("deprecation")
	public static void makeReport(HttpServletRequest request, Map columns, List reportData, String fileName, String sheetName, int fileType, int reportType) {
		makeReport(request, columns, null, reportData, fileName, sheetName, fileType, reportType);
	}
	
	/**
	 * make report file
	 * 
	 * @deprecated
	 * @param request      
	 * @param columns
	 *            column name(key: report column name, value: mapping SQL field
	 *            name)
	 * @param columnTypes
	 * 			  type(1:number,2:text)
	 * @param reportData
	 *            fill report data
	 * @param fileName
	 *            report file name
	 * @param sheetName
	 *            work sheet name
	 * @param fileType
	 *            template file type(0:pdf,1:excel,2:html)
	 * @param reportType
	 *            report type(0: no attachment,1: return attachment)
	 * 
	 */
	@SuppressWarnings("deprecation")
	public static void makeReport(HttpServletRequest request, Map columns, Map columnTypes, List reportData, String fileName, String sheetName, int fileType, int reportType) {
		FileOutputStream os = null;
		BufferedInputStream buff;
		List fieldList = new ArrayList();
		String webPath = request.getRealPath("/").replace("\\", "/");
		// 取得临时文件的全路径名称
		String tmpFileName = generateFileName(webPath);
		try {
			os = new FileOutputStream(new File(tmpFileName));
			
			// 打开文件
			jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(os);
			// 生成指定名称的工作表，参数0表示这是第一页
			jxl.write.WritableSheet ws = wwb.createSheet(sheetName, 0);
			// 如果存在列名的映射集合，取映射的列名为报表的列名称
			if (columns != null && columns.size() != 0) {
				Iterator columnNames = columns.keySet().iterator();
				int i = 0;
				// 循环映射的"列名"集合
				while (columnNames.hasNext()) {
					String key = (String) columnNames.next();
					// 在Label对象的构造子中指名单元格位置是第几列第一行(i,0),以及单元格内容为"列名"
					jxl.write.Label labelC = new jxl.write.Label(i, 0, key,getHeader(defaultOrientation));
					fieldList.add(columns.get(key));
					// 将Label对象添加到工作表对象
					ws.addCell(labelC);
					i++;
				}
			}
			// 如果存在报表数据
			if (reportData != null && reportData.size() != 0) {
				// 如果不存在列名的映射集合
				if (columns == null || columns.size() == 0) {
					// 取报表数据map的键名为报表文件的列名
					SimpleMap map = (SimpleMap) reportData.get(0);
					Iterator columnNames = map.keySet().iterator();
					int i = 0;
					while (columnNames.hasNext()) {
						String name = (String) columnNames.next();
						fieldList.add(name);
						jxl.write.Label labelC = new jxl.write.Label(i, 0, name, getHeader(defaultOrientation));
						// 添加列名称到sheet
						ws.addCell(labelC);
						i++;
					}
				}
				
				// 如果存在列类型定义
				if (columnTypes != null) {

					// 添加报表的内容            
					int lineNumber = 0;
					Iterator rowIterator = reportData.iterator();
					while (rowIterator.hasNext()) {
						// 取得一行数据
						SimpleMap oneResult = (SimpleMap) rowIterator.next();
						// 将该行数据插入
						int k = 0;  
						for (int j = 0; j < fieldList.size(); j++) {
							String colName = (String) fieldList.get(j);

							if (columnTypes.containsKey(colName) 
									&& Integer.parseInt(columnTypes.get(colName).toString()) == ReportConstant.CELL_TYPE_NUMBER) {

								// 数字格式的内容
								jxl.write.Number labelN = new jxl.write.Number(k, lineNumber + 1, Double.valueOf(oneResult.getString(colName) == null ? "" : oneResult.getString(colName)).doubleValue(), showByNumber());
								// 添加单元格内容到sheet
								//labelN.setCellFormat(new CellFormat(new jxl.format.Format));
								ws.addCell(labelN);
							} else if (columnTypes.containsKey(colName) 
									&& Integer.parseInt(columnTypes.get(colName).toString()) == ReportConstant.CELL_TYPE_TEXT) {

								// 字符格式的内容
								jxl.write.Label labelC = new jxl.write.Label(k, lineNumber + 1, oneResult.getString(colName) == null ? "" : oneResult.getString(colName), showByText());
								// 添加单元格内容到sheet
								ws.addCell(labelC);
							} else {

								try {
									// 数字格式的内容
									jxl.write.Number labelN = new jxl.write.Number(k, lineNumber + 1, Double.valueOf(oneResult.getString(colName) == null ? "" : oneResult.getString(colName)).doubleValue(), showByNumber());
									// 添加单元格内容到sheet
									//labelN.setCellFormat(new CellFormat(new jxl.format.Format));
									ws.addCell(labelN);
								} catch (NumberFormatException e) {
									// 字符格式的内容
									jxl.write.Label labelC = new jxl.write.Label(k, lineNumber + 1, oneResult.getString(colName) == null ? "" : oneResult.getString(colName), showByText());
									// 添加单元格内容到sheet
									ws.addCell(labelC);
								}
							}

							k++;
						}
						lineNumber++;
					}
				
				} else {
					
					// 添加报表的内容            
					int lineNumber = 0;
					Iterator rowIterator = reportData.iterator();
					while (rowIterator.hasNext()) {
						// 取得一行数据
						SimpleMap oneResult = (SimpleMap) rowIterator.next();
						// 将该行数据插入
						int k = 0;  
						for (int j = 0; j < fieldList.size(); j++) {
							String colName = (String) fieldList.get(j);
						
							try {
								// 数字格式的内容
								jxl.write.Number labelN = new jxl.write.Number(k, lineNumber + 1, Double.valueOf(oneResult.getString(colName) == null ? "" : oneResult.getString(colName)).doubleValue(), showByNumber());
								// 添加单元格内容到sheet
								//labelN.setCellFormat(new CellFormat(new jxl.format.Format));
								ws.addCell(labelN);
							} catch (NumberFormatException e) {
								// 字符格式的内容
								jxl.write.Label labelC = new jxl.write.Label(k, lineNumber + 1, oneResult.getString(colName) == null ? "" : oneResult.getString(colName), showByText());
								// 添加单元格内容到sheet
								ws.addCell(labelC);
							}
							k++;
						}
						lineNumber++;
					}
				}
					
			}
			// 写入文件
			wwb.write();
			wwb.close();
			buff = new BufferedInputStream(new FileInputStream(tmpFileName));
		} catch (ClassCastException e) {
			logger.debug("make report Exception, result list is SimpleMap must." + e.toString());
			throw new GlRuntimeException("make report Exception. ", e);
		} catch (Exception e) {
			logger.debug(e.toString());
			throw new GlRuntimeException("make report Exception. ", e);
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				logger.debug(e.toString());
			}
		}
		request.setAttribute("stream", buff);
		request.setAttribute("fileType", new Integer(fileType));
		request.setAttribute("reportType", new Integer(reportType));
		request.setAttribute("tmpFilePath", tmpFileName);
		request.setAttribute("reportID", fileName);
	}

	/**
	 * make report file 
	 * 
	 * @param request
	 * @param paramBean
	 */
	@SuppressWarnings("deprecation")
	public static void makeReport(HttpServletRequest request, ExcelParameterBean paramBean) {

		FileOutputStream os = null;
		BufferedInputStream buff;
		List fieldList = new ArrayList();
		String webPath = request.getRealPath("/").replace("\\", "/");
		// 取得临时文件的全路径名称
		String tmpFileName = generateFileName(webPath);
		try {
			os = new FileOutputStream(new File(tmpFileName));
			// 打开文件
			jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(os);
			// 生成指定名称的工作表，参数0表示这是第一页
			jxl.write.WritableSheet ws = wwb.createSheet(paramBean.getSheetname(), 0);
			// 如果存在列名的映射集合，取映射的列名为报表的列名称
			if (paramBean.getColumns() != null && paramBean.getColumns().size() != 0) {
				Iterator columnNames = paramBean.getColumns().keySet().iterator();
				int i = 0;
				// 循环映射的"列名"集合
				while (columnNames.hasNext()) {
					String key = (String) columnNames.next();
					// 在Label对象的构造子中指名单元格位置是第几列第一行(i,0),以及单元格内容为"列名"
					jxl.write.Label labelC = new jxl.write.Label(i, 0, key,getHeader(paramBean.getOrientation()==0?defaultOrientation:paramBean.getOrientation()));
					fieldList.add(paramBean.getColumns().get(key));
					// 将Label对象添加到工作表对象
					ws.addCell(labelC);
					i++;
				}
			}
			// 如果存在报表数据
			if (paramBean.getReportData() != null && paramBean.getReportData().size() != 0) {
				// 如果不存在列名的映射集合
				if (paramBean.getColumns() == null || paramBean.getColumns().size() == 0) {
					// 取报表数据map的键名为报表文件的列名
					SimpleMap map = (SimpleMap) paramBean.getReportData().get(0);
					Iterator columnNames = map.keySet().iterator();
					int i = 0;
					while (columnNames.hasNext()) {
						String name = (String) columnNames.next();
						fieldList.add(name);
						jxl.write.Label labelC = new jxl.write.Label(i, 0, name, getHeader(paramBean.getOrientation()==0?defaultOrientation:paramBean.getOrientation()));
						// 添加列名称到sheet
						ws.addCell(labelC);
						i++;
					}
				}
				
				// 如果存在列类型定义
				if (paramBean.getColumnTypes() != null) {

					// 添加报表的内容            
					int lineNumber = 0;
					Iterator rowIterator = paramBean.getReportData().iterator();
					while (rowIterator.hasNext()) {
						// 取得一行数据
						SimpleMap oneResult = (SimpleMap) rowIterator.next();
						// 将该行数据插入
						int k = 0;  
						for (int j = 0; j < fieldList.size(); j++) {
							String colName = (String) fieldList.get(j);

							if (paramBean.getColumnTypes().containsKey(colName) 
									&& Integer.parseInt(paramBean.getColumnTypes().get(colName).toString()) == ReportConstant.CELL_TYPE_NUMBER) {

								// 数字格式的内容
								jxl.write.Number labelN = new jxl.write.Number(k, lineNumber + 1, Double.valueOf(oneResult.getString(colName) == null ? "" : oneResult.getString(colName)).doubleValue(), showByNumber());
								// 添加单元格内容到sheet
								//labelN.setCellFormat(new CellFormat(new jxl.format.Format));
								ws.addCell(labelN);
							} else if (paramBean.getColumnTypes().containsKey(colName) 
									&& Integer.parseInt(paramBean.getColumnTypes().get(colName).toString()) == ReportConstant.CELL_TYPE_TEXT) {

								// 字符格式的内容
								jxl.write.Label labelC = new jxl.write.Label(k, lineNumber + 1, oneResult.getString(colName) == null ? "" : oneResult.getString(colName), showByText());
								// 添加单元格内容到sheet
								ws.addCell(labelC);
							} else {

								try {
									// 数字格式的内容
									jxl.write.Number labelN = new jxl.write.Number(k, lineNumber + 1, Double.valueOf(oneResult.getString(colName) == null ? "" : oneResult.getString(colName)).doubleValue(), showByNumber());
									// 添加单元格内容到sheet
									//labelN.setCellFormat(new CellFormat(new jxl.format.Format));
									ws.addCell(labelN);
								} catch (NumberFormatException e) {
									// 字符格式的内容
									jxl.write.Label labelC = new jxl.write.Label(k, lineNumber + 1, oneResult.getString(colName) == null ? "" : oneResult.getString(colName), showByText());
									// 添加单元格内容到sheet
									ws.addCell(labelC);
								}
							}

							k++;
						}
						lineNumber++;
					}
				
				} else {
					
					// 添加报表的内容            
					int lineNumber = 0;
					Iterator rowIterator = paramBean.getReportData().iterator();
					while (rowIterator.hasNext()) {
						// 取得一行数据
						SimpleMap oneResult = (SimpleMap) rowIterator.next();
						// 将该行数据插入
						int k = 0;  
						for (int j = 0; j < fieldList.size(); j++) {
							String colName = (String) fieldList.get(j);
						
							try {
								// 数字格式的内容
								jxl.write.Number labelN = new jxl.write.Number(k, lineNumber + 1, Double.valueOf(oneResult.getString(colName) == null ? "" : oneResult.getString(colName)).doubleValue(), showByNumber());
								// 添加单元格内容到sheet
								//labelN.setCellFormat(new CellFormat(new jxl.format.Format));
								ws.addCell(labelN);
							} catch (NumberFormatException e) {
								// 字符格式的内容
								jxl.write.Label labelC = new jxl.write.Label(k, lineNumber + 1, oneResult.getString(colName) == null ? "" : oneResult.getString(colName), showByText());
								// 添加单元格内容到sheet
								ws.addCell(labelC);
							}
							k++;
						}
						lineNumber++;
					}
				}
					
			}
			
			// 插入图片
			insertImg(ws, paramBean.getImageCol(), paramBean.getImageRow(), 
					paramBean.getImageWidth(), paramBean.getImageHeight(), 
					paramBean.getImageFile());
			
			// 设置页眉
			setHeaderFooter(ws, paramBean.getHeadContent(), paramBean.getHeadFontSize());
			
			// 设置内嵌表头
			setInLineHeader(ws, paramBean.getInLineHeadContent(), paramBean.getInLineHeadMergeSize());

			// 写入文件
			wwb.write();
			wwb.close();
			buff = new BufferedInputStream(new FileInputStream(tmpFileName));
		} catch (ClassCastException e) {
			logger.debug("make report Exception, result list is SimpleMap must." + e.toString());
			throw new GlRuntimeException("make report Exception. ", e);
		} catch (Exception e) {
			logger.debug(e.toString());
			throw new GlRuntimeException("make report Exception. ", e);
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				logger.debug(e.toString());
			}
		}
		request.setAttribute("stream", buff);
		request.setAttribute("fileType", new Integer(paramBean.getFileType()));
		request.setAttribute("reportType", new Integer(paramBean.getReportType()));
		request.setAttribute("tmpFilePath", tmpFileName);
		request.setAttribute("reportID", paramBean.getFileName());
	}
	
	/**
	 * @deprecated
	 * 
	 * @param request
	 * @param reportData
	 * @param columnnames
	 * @param fileName
	 * @param sheetName
	 * @param fileType
	 * @param reportType
	 */
	public static void makeReport(HttpServletRequest request, Vector reportData, String columnnames[], String fileName, String sheetName, int fileType, int reportType) {
		FileOutputStream os = null;
		BufferedInputStream buff;
		String webPath = request.getRealPath("/").replace("\\", "/");
		// 取得临时文件的全路径名称
		String tmpFileName = generateFileName(webPath);
		try {
			os = new FileOutputStream(new File(tmpFileName));
			// 打开文件
			jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(os);
			// 生成指定名称的工作表，参数0表示这是第一页
			jxl.write.WritableSheet ws = wwb.createSheet(sheetName, 0);
			// 如果存在列名的映射集合，取映射的列名为报表的列名称
			if (columnnames.length > 0) {
				for (int i = 0; i < columnnames.length; i++) {
					String name = columnnames[i];
					//在Label对象的构造子中指名单元格位置是第几列第一行(i,0),以及单元格内容为"列名"
					jxl.write.Label labelC = new jxl.write.Label(i, 0, name, getHeader(defaultOrientation));
					ws.addCell(labelC);
				}
			}
			/**
			 *   根据列类型 添加报表的内容 
			 * 
			 */
			Vector colSqlType = (Vector) reportData.lastElement();
			for (int i = 0; i < reportData.size() - 1; i++) {
				Vector vrow = (Vector) reportData.get(i);
				for (int j = 0; j < columnnames.length; j++) {
					String type = (String) colSqlType.get(j);
					if (type.equals("VARCHAR2") || type.equals("VARCHAR")) {
						String colvalue = (String) vrow.get(j);
						if (colvalue != null) {
							jxl.write.Label cellnum = new jxl.write.Label(j, i + 1, colvalue, showByText());
							ws.addCell(cellnum);
						}
					} else if (type.equals("NUMBER")) {
						String colvalue = (String) vrow.get(j);
						if (!colvalue.equals("") && colvalue != null) {
							Double num = Double.valueOf(colvalue);
							double doublenum = num.doubleValue();
							jxl.write.Number cellnum = new jxl.write.Number(j, i + 1, doublenum, showByNumber());
							ws.addCell(cellnum);
						} else if (colvalue != null) {
							jxl.write.Label cellnum = new jxl.write.Label(j, i + 1, colvalue, showByText());
						}
					}
				}
			}
			/**
			 * 设置报表列宽为30
			 */
			for (int i = 0; i < columnnames.length; i++) {
				ws.setColumnView(i, 30);//设置列宽  
			}
			wwb.write();// 写入文件
			wwb.close();//关闭工作表
			buff = new BufferedInputStream(new FileInputStream(tmpFileName));
		} catch (ClassCastException e) {
			logger.debug("make report Exception, result list is SimpleMap must." + e.toString());
			throw new GlRuntimeException("make report Exception. ", e);
		} catch (Exception e) {
			logger.debug(e.toString());
			throw new GlRuntimeException("make report Exception. ", e);
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				logger.debug(e.toString());
			}
		}
		request.setAttribute("stream", buff);
		request.setAttribute("fileType", new Integer(fileType));
		request.setAttribute("reportType", new Integer(reportType));
		request.setAttribute("tmpFilePath", tmpFileName);
		request.setAttribute("reportID", fileName);
	}

	/**
	 * @deprecated
	 * 
	 * @param request
	 * @param vdata
	 * @param fileName
	 * @param sheetName
	 * @param fileType
	 * @param reportType
	 */
	public static void makeReport(HttpServletRequest request, Vector vdata, String fileName, String sheetName, int fileType, int reportType) {
		FileOutputStream os = null;
		BufferedInputStream buff;
		String webPath = request.getRealPath("/").replace("\\", "/");
		// 取得临时文件的全路径名称
		String tmpFileName = generateFileName(webPath);
		try {
			os = new FileOutputStream(new File(tmpFileName));
			// 打开文件
			jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(os);
			// 生成指定名称的工作表，参数0表示这是第一页
			jxl.write.WritableSheet ws = wwb.createSheet(sheetName, 0);
			// 如果存在列名的映射集合，取映射的列名为报表的列名称
			Vector columnnames = (Vector) vdata.get(0);
			if (columnnames.size() > 0) {
				for (int i = 0; i < columnnames.size(); i++) {
					String name = (String) columnnames.get(i);
					//在Label对象的构造子中指名单元格位置是第几列第一行(i,0),以及单元格内容为"列名"
					jxl.write.Label labelC = new jxl.write.Label(i, 0, name, getHeader(defaultOrientation));
					ws.addCell(labelC);
				}
			}
			Vector reportData = new Vector();
			for (int j = 1; j < vdata.size() - 1; j++) {
				reportData.addElement(vdata.get(j));
			}
			/**
			 *   根据列类型 添加报表的内容 
			 * 
			 */
			Vector colSqlType = (Vector) vdata.lastElement();
			for (int i = 0; i < reportData.size(); i++) {
				Vector vrow = (Vector) reportData.get(i);
				for (int j = 0; j < columnnames.size(); j++) {
					String type = (String) colSqlType.get(j);
					if (type.equals("NUMBER")) {
						String colvalue = (String) vrow.get(j);
						if (!colvalue.equals("") && colvalue != null) {
							Double num = Double.valueOf(colvalue);
							double doublenum = num.doubleValue();
							jxl.write.Number cellnum = new jxl.write.Number(j, i + 1, doublenum, showByNumber());
							ws.addCell(cellnum);
						} else if (colvalue != null) {
							jxl.write.Label cellnum = new jxl.write.Label(j, i + 1, colvalue, showByText());
						}
					} else {
						String colvalue = (String) vrow.get(j);
						if (colvalue != null) {
							jxl.write.Label cellnum = new jxl.write.Label(j, i + 1, colvalue, showByText());
							ws.addCell(cellnum);
						}
					}
				}
			}
			/**
			 * 设置报表列宽为30  
			 */
			for (int i = 0; i < columnnames.size(); i++) {
				ws.setColumnView(i, 20);//设置列宽  
			}
			wwb.write();// 写入文件
			wwb.close();//关闭工作表
			buff = new BufferedInputStream(new FileInputStream(tmpFileName));
		} catch (ClassCastException e) {
			logger.debug("make report Exception, result list is SimpleMap must." + e.toString());
			throw new GlRuntimeException("make report Exception. ", e);
		} catch (Exception e) {
			logger.debug(e.toString());
			throw new GlRuntimeException("make report Exception. ", e);
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				logger.debug(e.toString());
			}
		}
		request.setAttribute("stream", buff);
		request.setAttribute("fileType", new Integer(fileType));
		request.setAttribute("reportType", new Integer(reportType));
		request.setAttribute("tmpFilePath", tmpFileName);
		request.setAttribute("reportID", fileName);
	}

	/**
	 * 
	 * 设置非数值类型字段的显示样式为文本
	 * @return
	 */
	public static WritableCellFormat showByText() {
		DisplayFormat fivedps = NumberFormats.TEXT;
		WritableCellFormat format = new WritableCellFormat(fivedps);
		try {
			format.setAlignment(jxl.format.Alignment.CENTRE);//左右居中
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//上下居中
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);//黑色边框
		} catch (WriteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return format;
	}

	/**
	 * 
	 * 设置数值类型字段的显示样式
	 * @return
	 */
	public static WritableCellFormat showByNumber() {
		//		    NumberFormat nf=new NumberFormat();
		WritableCellFormat format = new WritableCellFormat();
		try {
			format.setAlignment(jxl.format.Alignment.CENTRE);//左右居中
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//上下居中
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);//黑色边框
		} catch (WriteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return format;
	}

	/**
	 * 设置标题样式
	 * @return
	 */
	public static WritableCellFormat getHeader(int orientation) {
		WritableFont font = new WritableFont(WritableFont.TIMES, 9, WritableFont.BOLD);//定义字体
		try {
			font.setColour(Colour.BLACK);
		} catch (WriteException e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}
		WritableCellFormat format = new WritableCellFormat(font); 
		
		try {
			// 设置文字纵向显示
			switch (orientation) {
			case ReportConstant.ORIENTATION_HORIZONTAL:
				format.setOrientation(Orientation.HORIZONTAL);
				break;
			case ReportConstant.ORIENTATION_VERTICAL:
				format.setOrientation(Orientation.VERTICAL);
				break;
			case ReportConstant.ORIENTATION_MINUS_90:
				format.setOrientation(Orientation.MINUS_90);
				break;
			case ReportConstant.ORIENTATION_PLUS_90:
				format.setOrientation(Orientation.PLUS_90);
				break;
			default:
				break;
			}
			format.setAlignment(jxl.format.Alignment.CENTRE);//左右居中
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//上下居中
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);//黑色边框
		} catch (WriteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return format;
	}
	
	/**
	 * insert image
	 * 
	 * @param dataSheet
	 * @param col
	 * @param row
	 * @param width
	 * @param height
	 * @param imgFile
	 */
	public static void insertImg(WritableSheet dataSheet, int col, int row, int width,    
	        int height, File imgFile) throws Exception{    
			
		  if (dataSheet == null || imgFile  == null)
			  return;
		  
	      WritableImage img = new WritableImage(col, row, width, height, imgFile);
	      
	      dataSheet.addImage(img);    
	}    
	
	/**
	 * set header and footer
	 * 
	 * @param dataSheet
	 * @param content
	 * @param size
	 */
	public static void setHeaderFooter(WritableSheet dataSheet, String content, int size) {
		
		  if (content == null || content.equals(""))
			  return;
		  
		  HeaderFooter headFoot = new HeaderFooter();
		  headFoot.getCentre().append(content);
		  headFoot.getCentre().setFontSize(size);	  
		  
		  dataSheet.getSettings().setHeader(headFoot);
	}
	
	/**
	 * set in-line header
	 * 
	 * @param dataSheet
	 * @param content
	 * @param cellNum
	 */
	public static void setInLineHeader(WritableSheet dataSheet, String content, int cellNum) {
		
		try {
			
			if (content == null || content.equals(""))
				return;
			
			WritableFont font = new WritableFont(WritableFont.TIMES, 13, WritableFont.BOLD);
			font.setColour(Colour.BLACK);
			
			WritableCellFormat format = new WritableCellFormat(font); 
			format.setAlignment(jxl.format.Alignment.CENTRE);//左右居中
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//上下居中
			format.setBorder(Border.NONE, BorderLineStyle.THIN, Colour.BLACK);//黑色边框
			
			dataSheet.insertRow(0);
			dataSheet.insertRow(0);
			jxl.write.Label labelC = new jxl.write.Label(0, 0, content, format);
			dataSheet.addCell(labelC);
			dataSheet.mergeCells(0, 0, cellNum-1, 1);
				
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}


	/**
	 * send report file to client
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	public static void sendReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get generate file type
		Integer fileTypeObj = (Integer) request.getAttribute("fileType");
		int fileType = fileTypeObj.intValue();
		// set response content type
		if (fileType == 0) {
			response.setContentType("application/pdf");
		} else if (fileType == 1) {
			response.setContentType("application/vnd.ms-excel");
		} else if (fileType == 2) {
			response.setContentType("text/html");
		}
		// get report type
		Integer reportTypeObj = (Integer) request.getAttribute("reportType");
		// get report name
		String reportID = (String) request.getAttribute("reportID");
		int reportType = reportTypeObj.intValue();
		// set response head for return attachment
		if (reportType == 1) {
			response.setHeader("Content-disposition", "attachment; filename=" + reportID);
			response.setHeader("Cache-Control", "max-age=0");
		}
		// get temp file full path
		String tmpFilePath = (String) request.getAttribute("tmpFilePath");
		java.io.File file = new java.io.File(tmpFilePath);
		byte b[] = new byte[4062];
		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;
		try {
			if (file.isFile()) {
				// get input stream from temp file
				fin = new BufferedInputStream(new FileInputStream(file));
				// create output stream to Response object
				outs = new BufferedOutputStream(response.getOutputStream());
				int read = 0;
				while ((read = fin.read(b)) > 0) {
					outs.write(b, 0, read);
					outs.flush();
				}
			}
		} catch (Exception e) {
			logger.error("REPORT Exception Occured");
			throw new GlRuntimeException("generate report file fail.", e);
		} finally {
			if (fin != null)
				try {
					fin.close();
				} catch (Exception e) {
				}
			if (outs != null)
				try {
					outs.close();
				} catch (Exception e) {
				}
			file.delete();
		}
	}

	/**
	 * generate report
	 * 
	 * @param result
	 * @param reportParams
	 * @param reportID
	 * @param fileType
	 * @param fileName
	 * @return BufferedInputStream
	 * @throws Exception
	 */
	public static BufferedInputStream generateReport(List result, Map reportParams, String reportID, int fileType, String fileName) throws Exception {
		return generateReport(null, null, null, result, reportParams, reportID, fileType, fileName);
	}

	/**
	 * generate report
	 * 
	 * @param webPath
	 * @param result
	 * @param reportParams
	 * @param reportID
	 * @param fileType
	 * @param fileName
	 * @return BufferedInputStream
	 * @throws Exception
	 */
	public static BufferedInputStream generateReport(String webPath, List result, Map reportParams, String reportID, int fileType, String fileName) throws Exception {
		return generateReport(webPath, null, null, result, reportParams, reportID, fileType, fileName);
	}

	/**
	 * generate report
	 * 
	 * @param sysFilePath
	 *            system file path
	 * @param reportFilePath
	 *            report file path
	 * @param result
	 * @param reportParams
	 * @param reportID
	 *            jasper file name
	 * @param fileType
	 *            report file type
	 * @param fileName
	 *            reprot file name
	 * @return BufferedInputStream
	 * @throws Exception
	 */
	public static BufferedInputStream generateReport(String webPath, String sysFilePath, String reportPathConfig, List result, Map reportParams, String reportID, int fileType, String fileName) throws Exception {
		// make JRDataSource
		JRMapArrayDataSource jrds = new JRMapArrayDataSource(result.toArray());
		result = null;
		BufferedInputStream bis = null;
		String segmentPath;
		String jasperDir;
		// build output stream for report file
		FileOutputStream os = new FileOutputStream(new File(fileName));
		// get user configuration
		UserConfiguration userConfig = getUserConfiguration(sysFilePath);
		// get report file segetnt path
		if (reportPathConfig == null || reportPathConfig.equals("")) {
			segmentPath = userConfig.getString(defaultReportPathConfig);
		} else {
			segmentPath = userConfig.getString(reportPathConfig);
		}
		// get report file full path
		if (webPath == null || webPath.equals("")) {
			jasperDir = segmentPath;
		} else {
			jasperDir = getFullPath(webPath, segmentPath);
		}
		// get jasper report file object
		File reportFile = new File(jasperDir + reportID + ".jasper");
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
		// make JasperPrint
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportParams, jrds);
		// Prepare Export File:pdf or xls or html
		try {
			switch (fileType) {
			case ReportConstant.REPORT_TYPE_PDF:
				// make jasper report pdf exporter
				JRPdfExporter pdfExporter = new JRPdfExporter();
				// set jasper report printer parameter
				pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				// set jasper report output stream parameter
				pdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
				// export report file
				pdfExporter.exportReport();
				break;
			case ReportConstant.REPORT_TYPE_EXCEL:
				// make jasper report excel exporter
				JRXlsExporter xlsExporter = new JRXlsExporter();
				xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				xlsExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
				xlsExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
				xlsExporter.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, Boolean.FALSE);
				xlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
				xlsExporter.exportReport();
				break;
			case ReportConstant.REPORT_TYPE_HTML:
				// make jasper report html exporter
				JRHtmlExporter htmlExporter = new JRHtmlExporter();
				htmlExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				htmlExporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "common/images/");
				htmlExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
				htmlExporter.exportReport();
				break;
			default:
				break;
			}
			return bis;
		} finally {
			os.close();
		}
	}

	/**
	 * get full path
	 * 
	 * @param webPath
	 * @param segmentPath
	 * @return String
	 */
	private static String getFullPath(String webPath, String segmentPath) {
		if (!webPath.endsWith("/")) {
			if (!segmentPath.startsWith("/")) {
				return webPath + "/" + segmentPath;
			} else {
				return webPath + segmentPath;
			}
		} else {
			if (!segmentPath.startsWith("/")) {
				return webPath + segmentPath;
			} else {
				return (webPath + segmentPath).replaceAll("//", "/");
			}
		}
	}

	/**                                   
	 * get UserConfiguration object
	 * 
	 * @param sysFilePath
	 * @return UserConfiguration
	 */
	private static UserConfiguration getUserConfiguration(String sysFilePath) {
		if (sysFilePath == null || sysFilePath.equals("")) {
			return UserConfiguration.getInstance(defaultSysFile);
		} else {
			return UserConfiguration.getInstance(sysFilePath);
		}
	}

}
