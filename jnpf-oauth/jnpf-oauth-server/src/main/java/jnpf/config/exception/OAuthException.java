package jnpf.config.exception;

import jnpf.base.ActionResult;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 自定义异常
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-23
 */
@ControllerAdvice
public class OAuthException {
    /**
     * 用户名和密码错误
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(OAuth2Exception.class)
    public ActionResult handleInvalidGrantException(OAuth2Exception e) {
        return ActionResult.fail("用户名或密码错误");
    }
}
