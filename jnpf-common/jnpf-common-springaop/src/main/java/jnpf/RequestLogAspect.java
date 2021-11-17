package jnpf;

import jnpf.base.LogApi;
import jnpf.base.LogSortEnum;
import jnpf.base.UserInfo;
import jnpf.base.entity.LogEntity;
import jnpf.util.*;
import jnpf.util.jwt.JwtUtil;
import jnpf.utils.LogWriteUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 日志记录
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司
 * @date 2021年3月13日 上午9:18
 */

@Slf4j
@Aspect
@Component
@Order(2)
public class RequestLogAspect {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private LogApi logApi;
    @Autowired
    private UserProvider userProvider;

    @Pointcut("!execution(* jnpf.*.LoginController.Login(..)) && (execution(* jnpf.controller.*.*(..))  || execution(* jnpf.*.controller.*.*(..)))")
    public void requestLog() {

    }

    @Around("requestLog()")
    public Object doAroundService(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long costTime = System.currentTimeMillis() - startTime;
        if (!ServletUtil.getRequest().getRequestURI().endsWith(LogWriteUtil.NOTWRITE)) {
            printLog(costTime);
        }
        return obj;
    }

    private void printLog(long costTime) {
        //无需记载日志
        if (LogWriteUtil.NOTWRITETWO.equals(ServletUtil.getRequest().getRequestURI())) {
            return;
        }
        String authorization = ServletUtil.getRequest().getHeader("Authorization");
        try {
            String realToken = JwtUtil.getRealToken(authorization);
            if (realToken==null){
                return;
            }
            String token = String.valueOf(redisUtil.getString(realToken));
            UserInfo info = JsonUtil.getJsonToBean(token, UserInfo.class);
            LogEntity entity = new LogEntity();
            entity.setId(RandomUtil.uuId());
            entity.setCategory(LogSortEnum.Operate.getCode());
            entity.setUserId(info.getUserId());
            entity.setUserName(info.getUserName() + "/" + info.getUserAccount());
            //请求耗时
            entity.setRequestduration((int) costTime);
            entity.setRequestURL(ServletUtil.getRequest().getServletPath());
            entity.setRequestMethod(ServletUtil.getRequest().getMethod());
            entity.setCategory(5);
            entity.setUserId(info.getUserId());
            entity.setIPAddress(IpUtil.getIpAddr());
            entity.setCreatorTime(new Date());
            entity.setPlatForm(ServletUtil.getUserAgent());
            logApi.writeLogRequest(entity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }finally {
            //判断是否记录日志后清空redis
            if (LogWriteUtil.WRITELOG.equals(ServletUtil.getRequest().getRequestURI())){
                userProvider.removeCurrent();
            }
        }
    }
}

