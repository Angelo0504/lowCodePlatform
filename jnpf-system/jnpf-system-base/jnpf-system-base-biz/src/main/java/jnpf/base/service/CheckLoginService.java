package jnpf.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jnpf.base.entity.EmailConfigEntity;
/**
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-23
 */
public interface CheckLoginService extends IService<EmailConfigEntity> {
    /**
     * 邮箱验证
     *
     * @param configEntity
     * @return
     */
    String checkLogin(EmailConfigEntity configEntity);
}
