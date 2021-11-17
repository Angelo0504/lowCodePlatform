package jnpf.engine.fallback;

import jnpf.engine.FlowDelegateApi;
import jnpf.engine.entity.FlowDelegateEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * api接口
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021/3/15 11:55
 */
@Component
public class FlowDelegateApiFallback implements FlowDelegateApi {
    @Override
    public List<FlowDelegateEntity> getList() {
        return null;
    }
}
