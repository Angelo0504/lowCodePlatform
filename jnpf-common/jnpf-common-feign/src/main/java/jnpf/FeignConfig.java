package jnpf;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jnpf.util.ServletUtil;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司
 * @date 2021/3/16 10:51
 */
@Configuration
public class FeignConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        //添加token
        HttpServletRequest request = ServletUtil.getRequest();
        template.header("User-Agent", request.getHeader("User-Agent"));
        template.header("Authorization", request.getHeader("Authorization"));
        template.header("X-Real-IP",request.getHeader("X-Real-IP"));
        template.header("X-Forwarded-For",request.getHeader("X-Forwarded-For"));
    }
}
