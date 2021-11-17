package jnpf.permission;

import jnpf.permission.entity.UserRelationEntity;
import jnpf.permission.fallback.UserRelationApiFallback;
import jnpf.utils.FeignName;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 获取用户关系Api
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@FeignClient(name = FeignName.SYSTEM_SERVER_NAME , fallback = UserRelationApiFallback.class, path = "/Permission/UserRelation")
public interface UserRelationApi {

    /**
     * 获取岗位
     *
     * @return
     */
    @GetMapping("/getList/{userId}")
    List<UserRelationEntity> getList(@PathVariable("userId") String userId);

    /**
     * 获取岗位
     *
     * @return
     */
    @GetMapping("/getObjectList/{objectId}")
    List<UserRelationEntity> getObjectList(@PathVariable("objectId") String objectId);
}
