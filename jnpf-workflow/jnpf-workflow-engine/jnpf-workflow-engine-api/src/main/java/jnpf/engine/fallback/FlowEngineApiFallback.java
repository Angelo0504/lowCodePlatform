package jnpf.engine.fallback;

import jnpf.engine.FlowEngineApi;
import jnpf.utils.FeignName;
import jnpf.base.ActionResult;
import jnpf.base.vo.ListVO;
import jnpf.engine.model.flowengine.FlowEngineListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * api接口
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021/3/15 11:55
 */
@Component
@Slf4j
public class FlowEngineApiFallback implements FlowEngineApi {
    @Override
    public ActionResult<ListVO<FlowEngineListVO>> listAll() {
        log.error(FeignName.WORKFLOW_SERVER_NAME+"服务未启动");
        return null;
    }
}
