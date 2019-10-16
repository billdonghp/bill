package cn.multiseafoods.crm.handle;

import cn.multiseafoods.crm.entity.Result;
import cn.multiseafoods.crm.exception.EmployeeException;
import cn.multiseafoods.crm.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {
    public final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof EmployeeException){
            EmployeeException employeeException = (EmployeeException)e;
            return ResultUtils.error(employeeException.getCode(),employeeException.getMessage());
        }
        logger.error("【系统错误】" + e);
        return ResultUtils.error(-1,"未知错误！");
    }
}
