package jnpf.engine;

import jnpf.engine.entity.FlowDelegateEntity;
import jnpf.engine.fallback.FlowDelegateApiFallback;
import jnpf.utils.FeignName;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * api接口
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021/3/15 11:55
 */
@FeignClient(name = FeignName.WORKFLOW_SERVER_NAME , fallback = FlowDelegateApiFallback.class, path = "/Engine/FlowDelegate")
public interface FlowDelegateApi {
    /**
     * 列表
     *
     * @return
     */
    @GetMapping("/getList")
    List<FlowDelegateEntity> getList();

}
