package jnpf.base;

import jnpf.base.entity.ModuleEntity;
import jnpf.base.fallback.ModuleApiFallback;
import jnpf.utils.FeignName;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
/**
 * 调用系统菜单Api
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@FeignClient(name = FeignName.SYSTEM_SERVER_NAME , fallback = ModuleApiFallback.class, path = "/Base/Menu")
public interface ModuleApi {

    /**
     * 列表
     *
     * @return
     */
    @GetMapping("/getList")
    List<ModuleEntity> getList();

}
