/*
 * @(#)CommandManager.java 1.0 2006-12-18 下午02:52:21
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sqlmap.util;

import java.util.HashMap;

import com.ait.web.Command;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-18 上午14:52:21
 * @version 1.0
 * 
 */
public class CommandManager {

	//	private static final Logger logger = Logger.getLogger(CommandManager.class);

	// map of store CommandManager
	private static HashMap<String, CommandManager> managerMap = new HashMap<String, CommandManager>();

	private NavigationMapper mapper;

	// map of store Command object
	private HashMap<String, Command> commandMap;

	// default construct
	private CommandManager() {
	}

	private CommandManager(String webPath, String module) {
		// construct NavigationMapper object by different module
		mapper = new NavigationMapper(webPath, null, module);
		commandMap = new HashMap<String, Command>();
	}

	/**
	 * get CommandManager object by different module
	 * 
	 * @param webPath
	 * @param module
	 * @return CommandManager
	 */
	public static synchronized CommandManager getInstance(String webPath, String module) {
		if (!managerMap.containsKey(module)) {
			managerMap.put(module, new CommandManager(webPath, module));
		}
		return managerMap.get(module);
	}

	/**
	 * get command object
	 * 
	 * @param mapParam
	 * @return Command
	 */
	public synchronized Command getCommand(String mapParam) {
		if (!commandMap.containsKey(mapParam)) {
			commandMap.put(mapParam, (Command) mapper.getMapObjectByMapParam(mapParam));
		}
		return commandMap.get(mapParam);
	}
}