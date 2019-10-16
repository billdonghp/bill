package com.ait.ar.content;

import com.ait.web.ContentFactory;   

public class ArContentFactory extends ContentFactory {
    public ArContentFactory() {                                       
        super();                                                   
        contents.put("deptrollsearch", new DeptRollSearch());// 部门别考勤搜索
        contents.put("deptrollinfosearch", new DeptRollInfoSearch());// 部门别查看
        contents.put("emprollsearch", new EmpRollSearch());// 个人别考勤搜索 修改用
        contents.put("emprollinfosearch", new EmpRollInfoSearch());// 个 人别查看搜索
        contents.put("deptrolladd", new DeptRollAdd());// 部门别查看 添加请假
        contents.put("emprollupdateview", new EmpRollUpdateView());// 个人别查看修改页面显示过程
        contents.put("emprolladd", new EmpRollAdd());// 个人别添加考勤
        contents.put("emprollinfo", new EmpRollInfo());// 个人别查看详细信息
//        contents.put("annualsearch", new AnnualSearch());// 年假查看
//        contents.put("annualinfo", new AnnualInfoView());// 年假详细查看
        contents.put("empgatherinfo", new EmpGatnerInfo());// 月考勤汇总数据
        contents.put("ar_supervisor", new SupervisorDel());// 删除考勤员
    }
    public static ContentFactory getInstance() {    
        return new ArContentFactory();            
    }
}
         