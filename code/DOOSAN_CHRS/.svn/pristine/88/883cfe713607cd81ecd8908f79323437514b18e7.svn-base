/*
 * @(#)MenuTree.java 1.0 2007-8-22 下午01:54:54
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.utils;

import java.util.Vector;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class MenuTree {

	public MenuTree() {
	}

	public boolean createXML(String fileName, String screen_no) {
		boolean isOk = false;
		//建立document对象
		Document doc = DocumentHelper.createDocument();
		Element toproot = null;
		Element secondroot = null;
		Element thirdroot = null;
		MenuBiz menu = new MenuBiz();
		Vector vmenu_code = menu.getTopMenu(screen_no);//得到一级目录
		Element root = doc.addElement("tree");
		for (int i = 0; i < vmenu_code.size(); i++) {
			MenuEnt ent = (MenuEnt) vmenu_code.get(i);
			toproot = root.addElement("tree"); //将一级目录加入到根接点  
			toproot.addAttribute("text", ent.getMenuIntro());//设置根接点的属性
			toproot.addAttribute("target", "mainFrame");//同上
			toproot.addAttribute("action", ent.getMenuUrl());//同上
			Vector vseconde = menu.getMenuTree(ent.getMenuCode(), ent.getDepth() + 1, screen_no);//得到二级目录
			for (int j = 0; j < vseconde.size(); j++) {
				ent = (MenuEnt) vseconde.get(j);
				secondroot = toproot.addElement("tree"); //将二级目录加入到上一级接点  做为上级接点的子接点
				secondroot.addAttribute("text", ent.getMenuIntro());//设置接点的属性
				secondroot.addAttribute("target", "mainFrame");//同上
				secondroot.addAttribute("action", ent.getMenuUrl());//同上
				Vector vthird = menu.getMenuTree(ent.getMenuCode(), ent.getDepth() + 1, screen_no);//得到三级目录
				for (int k = 0; k < vthird.size(); k++) {
					ent = (MenuEnt) vthird.get(k);
					thirdroot = secondroot.addElement("tree");//将三级目录加入到上一级接点  做为上级接点的子接点
					thirdroot.addAttribute("text", ent.getMenuIntro());//设置接点的属性
					thirdroot.addAttribute("target", "mainFrame");//同上
					thirdroot.addAttribute("action", ent.getMenuUrl());//同上
				}
			}
		}
		try {
			/**如果XML文档中不包含中文，可以使用下面代码写入。否则，
			 * 包括中文字符的XML文件在稍后将无法正常读取，会抛出异常
			 * 
			 FileWriter file = new FileWriter(new File(fileName));
			 XMLWriter writer = new XMLWriter(file);
			 writer.write(doc);
			 writer.close();
			 */
			/*如果XML文档中包含中文，必须指定编码写入。
			 * 由于java.io.FileWriter默认的输出编码是ANSI编码，
			 * 而Dom4j中Document的wirte方法提供的内容实际是以UTF-8保存的
			 * 因此，不能使用简单的FileWriter，而应该是使用一个能指定具体输出
			 * 编码的Writer，可以使用java.io.OutputStreamWriter
			 * 
			 */
			java.io.Writer wr = new java.io.OutputStreamWriter(new java.io.FileOutputStream(fileName), "UTF-8");
			doc.write(wr);
			wr.close();
			isOk = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isOk;
	}
}
