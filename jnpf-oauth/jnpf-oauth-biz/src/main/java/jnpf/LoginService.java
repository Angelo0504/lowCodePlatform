package jnpf;

import jnpf.model.LoginForm;
import jnpf.model.currenuser.PCUserVO;
import jnpf.base.UserInfo;
import jnpf.exception.LoginException;
import jnpf.permission.entity.UserEntity;
/**
 * 登陆业务层
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-23
 */
public interface LoginService {

    /**
     * 租戶登录验证
     * @param loginForm
     * @return
     * @throws LoginException
     */
    UserInfo checkTenant(LoginForm loginForm) throws LoginException;

    /**
     * 获取用户登陆信息
     * @return
     */
    PCUserVO getCurrentUser();

}
