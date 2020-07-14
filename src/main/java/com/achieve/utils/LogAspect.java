package com.achieve.utils;



import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class LogAspect {

    /*
    * slf4j日志
    * */
    private final static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /**
     * Pointcut 切入点
     * 匹配com.achieve.controller包下所有方法
     */
    //@Pointcut("execution(public * com.achieve.controller.*.*(..))")
    public void weblog(){}

    //@Around(value = "weblog()")
    public Object arround(ProceedingJoinPoint point){
        Object o =null;
        try {
            logger.info("方法环绕开始");
            o = point.proceed();
            logger.info("方法环绕结束 内容是："+o);

        } catch (Throwable e) {
            logger.error(point.getSignature() + " 出现异常： ", e);
            e.printStackTrace();
        }
        return o;

    }


}
