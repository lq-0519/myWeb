package lq.myWeb.controller;

import lq.myWeb.domain.SysLog;
import lq.myWeb.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author LQ
 * @create 2020-06-09 20:08
 */
@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;
    private Date visitTime; //开始时间
    private Class aClass; //访问的类
    private Method method;//访问的方法

    /**
     * 前置通知
     * 主要获取开始时间 执行的类 执行的方法
     * 获取类和方法对象的原因是为了再一次获取类和方法上面添加的注解
     *
     * @param joinPoint
     */
    @Before("execution(* lq.myWeb.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        aClass = joinPoint.getTarget().getClass();//访问的类
        if (aClass != LogAop.class && aClass != SysLogController.class) {
            //如果执行的是LogAop类就直接跳过就行
            visitTime = new Date();//开始时间
            Object[] args = joinPoint.getArgs();//获取访问时的方法参数
            String methodName = joinPoint.getSignature().getName();
            //获取具体执行的方法对象Method
            if (args == null || args.length == 0) {
                method = aClass.getMethod(methodName);
            } else {
                Class[] classArgs = new Class[args.length];
                for (int i = 0; i < args.length; i++) {
                    classArgs[i] = args[i].getClass();
                }
                method = aClass.getMethod(methodName, classArgs);
            }
        }
    }

    /**
     * 后置通知
     * 获取操作者
     * 获取访问的ip
     * 获取访问的url
     * 获取访问的时长
     *
     * @param joinPoint
     */
    @After("execution(* lq.myWeb.controller.*.*(..))")
    public void doAfter(JoinPoint joinPoint) {
        if (aClass != null && aClass != LogAop.class && method != null && aClass != SysLogController.class) {
            //获取访问的时长
            long time = new Date().getTime() - visitTime.getTime();
            //获取url, 获取url需要获取类和方法上面的注解对象
            String url = null;
            RequestMapping classAnnotation = (RequestMapping) aClass.getAnnotation(RequestMapping.class);
            RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
            if (classAnnotation != null && methodAnnotation != null) {
                url = classAnnotation.value()[0] + methodAnnotation.value()[0];
            }
            //获取访问的ip地址
            String ip = request.getRemoteAddr();
            //获取用户名
            SecurityContext context = SecurityContextHolder.getContext();
            User user = (User) context.getAuthentication().getPrincipal();
            String username = user.getUsername();
            //将这些信息封装到SysLog里
            SysLog sysLog = new SysLog(null, visitTime, null, username, ip, url,
                    time, "[类名]:" + aClass.getName() + "[方法名]:" + method.getName());
            //调用service
            sysLogService.save(sysLog);
        }
    }
}
