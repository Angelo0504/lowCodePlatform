package jnpf.engine;

import jnpf.engine.fallback.FlowEngineApiFallback;
import jnpf.engine.model.flowengine.FlowEngineListVO;
import jnpf.utils.FeignName;
import jnpf.base.ActionResult;
import jnpf.base.vo.ListVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * api接口
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021/3/15 11:55
 */
@FeignClient(name = FeignName.WORKFLOW_SERVER_NAME , fallback = FlowEngineApiFallback.class, path = "/Engine/FlowEngine")
public interface FlowEngineApi {
    /**
     * 列表
     *
     * @return
     */
    @GetMapping("/ListAll")
    ActionResult<ListVO<FlowEngineListVO>> listAll();

}
