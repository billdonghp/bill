package cn.multiseafoods.crm.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * cn.multiseafoods.crm.controller.CustomerController.*(..))")
    public void log(){}
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        //System.out.println("before");
        logger.info("before");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //url
        logger.info("url={}",request.getRequestURL());
        //method
        logger.info("method={}",request.getMethod());
        //ip
        logger.info("ip={}",request.getRemoteAddr());
        //类名
        logger.info("class={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        //class Args
        logger.info("classArgs={}",joinPoint.getArgs());
    }
    @After("log()")
    public void doAfter(){
        //System.out.println("after");
        logger.info("after");
    }

    @AfterReturning(returning="object",pointcut="log()")
    public void doAfterReturning(Object object){
        logger.info("response={}",object.toString());
    }

}
