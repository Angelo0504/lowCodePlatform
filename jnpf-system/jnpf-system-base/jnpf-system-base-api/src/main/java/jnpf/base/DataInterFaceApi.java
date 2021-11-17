package jnpf.base;

import jnpf.utils.FeignName;
import jnpf.base.fallback.DataInterFaceApiFallback;
import jnpf.base.ActionResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * 调用数据接口Api
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@FeignClient(name = FeignName.SYSTEM_SERVER_NAME , fallback = DataInterFaceApiFallback.class, path = "/Base/DataInterface")
public interface DataInterFaceApi {

    /**
     * 访问接口
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}/Actions/Response")
    ActionResult infoToId(@PathVariable("id") String id);

}
