package jnpf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jnpf.base.entity.EmailConfigEntity;
import jnpf.mapper.EmailConfigMapper;
import jnpf.service.EmailConfigService;
import org.springframework.stereotype.Service;


/**
 * 邮件配置
 *
 * @copyright 引迈信息技术有限公司
 * @author JNPF开发平台组
 * @date 2019年9月26日 上午9:18
 */
@Service
public class EmailConfigServiceImpl extends ServiceImpl<EmailConfigMapper, EmailConfigEntity> implements EmailConfigService {

}
