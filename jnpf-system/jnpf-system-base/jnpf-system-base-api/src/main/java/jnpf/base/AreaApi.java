package jnpf.base;

import jnpf.base.entity.ProvinceEntity;
import jnpf.base.fallback.AreaApiFallback;
import jnpf.base.ActionResult;
import jnpf.utils.FeignName;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 获取行政区划Api
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@FeignClient(name = FeignName.SYSTEM_SERVER_NAME , fallback = AreaApiFallback.class, path = "/Base/Area")
public interface AreaApi {
    /**
     * 获取行政区划列表
     * @param id
     * @return
     */
    @GetMapping("/getList/{id}")
    ActionResult<List<ProvinceEntity>> getList(@PathVariable("id") String id);
}
