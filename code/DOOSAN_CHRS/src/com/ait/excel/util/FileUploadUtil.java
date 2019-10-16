/*
 * @(#)FileUploadUtil.java 1.0 2007-1-30 上午11:50:22
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.excel.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.SQLMapConfigManager;
import com.ait.sqlmap.util.UserConfiguration;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2007-1-30 上午11:50:22
 * @version 1.0
 * 
 */

public class FileUploadUtil {

	private UserConfiguration userConfig;

	private DiskFileUpload fileUpload;

	private String targetDirectory;

	private String temporaryDirectory;

	private int maxsize;

	private int size_threshold;

	private String header_encoding;

	private final String defaultSysFile = "/system.properties";

	private final String defaultTargetDirectoryConfig = "upload.policy.common.target-directory";

	private final String defaultTemporaryDirectoryConfig = "upload.policy.common.temporary-directory";

	private final String defaultMaxsizeConfig = "upload.policy.common.maxsize";

	private final String defaultSizeThresholdConfig = "upload.policy.common.size-threshold";

	private final String defaultHeaderEncodingConfig = "upload.policy.common.header-encoding";

	private static final Logger logger = Logger
			.getLogger(SQLMapConfigManager.class);

	/**
	 * load file upload configuration
	 * 
	 */
	public FileUploadUtil() {

		fileUpload = new DiskFileUpload();
		init(null, null, null, null, null, null, null);
	}

	/**
	 * load file upload configuration
	 * 
	 * @param webPath
	 */
	public FileUploadUtil(String webPath) {

		fileUpload = new DiskFileUpload();
		init(webPath, null, null, null, null, null, null);
	}

	/**
	 * load file upload configuration
	 * 
	 * @param webPath
	 * @param sysFilePath
	 */
	public FileUploadUtil(String webPath, String sysFilePath) {

		fileUpload = new DiskFileUpload();
		init(webPath, sysFilePath, null, null, null, null, null);
	}

	/**
	 * load file upload configuration
	 * 
	 * @param webPath
	 * @param sysFilePath
	 * @param targetDirectoryConfig
	 * @param temporaryDirectoryConfig
	 * @param maxsizeConfig
	 * @param sizeThresholdConfig
	 * @param headerEncodingConfig
	 */
	public FileUploadUtil(String webPath, String sysFilePath,
			String targetDirectoryConfig, String temporaryDirectoryConfig,
			String maxsizeConfig, String sizeThresholdConfig,
			String headerEncodingConfig) {

		fileUpload = new DiskFileUpload();
		init(webPath, sysFilePath, targetDirectoryConfig,
				temporaryDirectoryConfig, maxsizeConfig, sizeThresholdConfig,
				headerEncodingConfig);
	}

	/**
	 * load file upload configuration
	 * 
	 * @param webPath
	 * @param sysFilePath
	 * @param targetDirectory
	 * @param temporaryDirectory
	 * @param maxsize
	 * @param sizeThreshold
	 * @param headerEncoding
	 */
	private void init(String webPath, String sysFilePath,
			String targetDirectoryConfig, String temporaryDirectoryConfig,
			String maxsizeConfig, String sizeThresholdConfig,
			String headerEncodingConfig) {

		String segmentTargetPath;

		String segmentTempPath;

		try {
			// load system properties file
			if (sysFilePath == null || sysFilePath.equals("")) {

				userConfig = UserConfiguration.getInstance(defaultSysFile);
			} else {

				userConfig = UserConfiguration.getInstance(sysFilePath);
			}

			// get upload target directory segment
			if (targetDirectoryConfig == null
					|| targetDirectoryConfig.equals("")) {

				segmentTargetPath = userConfig
						.getString(defaultTargetDirectoryConfig);
			} else {

				segmentTargetPath = userConfig.getString(targetDirectoryConfig);
			}

			// get upload temp directory segment
			if (temporaryDirectoryConfig == null
					|| temporaryDirectoryConfig.equals("")) {

				segmentTempPath = userConfig
						.getString(defaultTemporaryDirectoryConfig);
			} else {

				segmentTempPath = userConfig
						.getString(temporaryDirectoryConfig);
			}

			// construct target directory and temp directory
			if (webPath == null || webPath.equals("")) {

				targetDirectory = segmentTargetPath;
				temporaryDirectory = segmentTempPath;
			} else {

				targetDirectory = this.getFullPath(webPath, segmentTargetPath);
				temporaryDirectory = this.getFullPath(webPath, segmentTempPath);
			}

			// get upload file max size
			if (maxsizeConfig == null || maxsizeConfig.equals("")) {

				maxsize = Integer.parseInt(userConfig
						.getString(defaultMaxsizeConfig));
			} else {

				maxsize = Integer.parseInt(userConfig.getString(maxsizeConfig));
			}

			// maximum size that will be stored in memory
			if (sizeThresholdConfig == null || sizeThresholdConfig.equals("")) {

				size_threshold = Integer.parseInt(userConfig
						.getString(defaultSizeThresholdConfig));
			} else {

				size_threshold = Integer.parseInt(userConfig
						.getString(sizeThresholdConfig));
			}

			// maximum size that will be stored in memory
			if (headerEncodingConfig == null || headerEncodingConfig.equals("")) {

				header_encoding = userConfig
						.getString(defaultHeaderEncodingConfig);
			} else {

				header_encoding = userConfig.getString(headerEncodingConfig);
			}

		} catch (Exception e) {

			logger.error("load file upload configuration fail.");
			throw new GlRuntimeException(
					"load file upload configuration fail.", e);
		}
	}

	/**
	 * upload multi file 
	 * 
	 * @param request
	 * @return List contain File object
	 * @throws GlRuntimeException
	 */
	public List uploadFile(HttpServletRequest request)
			throws GlRuntimeException {

		List list = new ArrayList();

		try {

			// maximum size of upload file unit: byte
			fileUpload.setSizeMax(maxsize);

			// maximum size that will be stored in memory unit: byte
			fileUpload.setSizeThreshold(size_threshold);

			// set upload file save path while file size overstep memory maximum
			// size
			fileUpload.setRepositoryPath(temporaryDirectory);

			// set encoding
			fileUpload.setHeaderEncoding(header_encoding);	

			// read upload file data
			List fileItems = fileUpload.parseRequest(request);
			logger.debug("fileItems size: " + fileItems.size());

			// process upload file all
			Iterator iter = fileItems.iterator();

			while (iter.hasNext()) {

				FileItem item = (FileItem) iter.next();

				// 忽略其他不是文件域的所有表单信息
				if (!item.isFormField()) {

					// get file name
					String fileName = item.getName();
					
					// get file size
					long fileSize = item.getSize();

					if ((fileName == null || fileName.equals(""))
							&& fileSize == 0)
						continue;

					if (fileName.lastIndexOf("\\") != -1)
						fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
					
					// save file to target directory
					item.write(new File(targetDirectory + fileName));

					// add File object to list
					list.add(new File(targetDirectory + fileName));
				}
			}
			logger.debug("file list size: " + list.size());

		} catch (Exception e) {

			logger.error("file upload fail.");
			throw new GlRuntimeException("file upload fail.", e);
		}

		return list;
	}

	/**
	 * get full path
	 * 
	 * @param webPath
	 * @param segmentPath
	 * @return String
	 */
	private String getFullPath(String webPath, String segmentPath) {

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
}
