package jnpf.permission;

import jnpf.permission.fallback.PositionApiFallback;
import jnpf.permission.entity.PositionEntity;
import jnpf.permission.model.position.PositionInfoVO;
import jnpf.utils.FeignName;
import jnpf.base.ActionResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 获取岗位信息Api
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@FeignClient(name = FeignName.SYSTEM_SERVER_NAME , fallback = PositionApiFallback.class, path = "/Permission/Position")
public interface PositionApi {

    /**
     * 获取所有岗位
     * @return
     */
    @GetMapping("/getListAll")
    ActionResult<List<PositionEntity>> getListAll();

    /**
     * 获取岗位信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    ActionResult<PositionInfoVO> getInfo(@PathVariable("id") String id);

}
