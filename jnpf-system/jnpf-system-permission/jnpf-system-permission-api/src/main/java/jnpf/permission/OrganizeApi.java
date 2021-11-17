package jnpf.permission;

import jnpf.permission.fallback.OrganizeApiFallback;
import jnpf.permission.entity.OrganizeEntity;
import jnpf.utils.FeignName;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 获取组织信息Api
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@FeignClient(name = FeignName.SYSTEM_SERVER_NAME ,fallback = OrganizeApiFallback.class,path = "/Permission/Organize")
public interface OrganizeApi {
    /**
     * 通过id获取
     * @param organizeId
     * @return
     */
    @GetMapping("/getById/{organizeId}")
    OrganizeEntity getById(@PathVariable("organizeId") String organizeId) ;

    /**
     * 获取组织列表
     *
     * @return
     */
    @GetMapping("/getList")
    List<OrganizeEntity> getList();
}
