package jnpf.base.fallback;

import jnpf.base.LogApi;
import jnpf.base.entity.LogEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
/**
 * 调用系统日志Api降级处理
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@Component
@Slf4j
public class LogApiFallback implements LogApi {

    @Override
    public void writeLogAsync(String dbId, String dbName, String userId, String userName, String account, String abstracts) {
        log.error("写入登陆日志失败");
    }

    @Override
    public void writeLogRequest(LogEntity logEntity) {

    }
}
