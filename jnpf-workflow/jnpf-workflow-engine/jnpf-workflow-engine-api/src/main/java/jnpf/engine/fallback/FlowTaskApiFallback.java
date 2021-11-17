package jnpf.engine.fallback;

import jnpf.engine.FlowTaskApi;
import jnpf.engine.entity.FlowTaskEntity;
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
public class FlowTaskApiFallback implements FlowTaskApi {
    @Override
    public List<FlowTaskEntity> getWaitList() {
        return null;
    }

    @Override
    public List<FlowTaskEntity> getTrialList() {
        return null;
    }

    @Override
    public List<FlowTaskEntity> getAllWaitList() {
        return null;
    }
}
