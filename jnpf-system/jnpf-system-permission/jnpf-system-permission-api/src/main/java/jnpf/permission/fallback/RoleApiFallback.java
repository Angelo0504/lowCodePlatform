package jnpf.permission.fallback;

import jnpf.base.ActionResult;
import jnpf.permission.RoleApi;
import jnpf.permission.entity.RoleEntity;
import org.springframework.stereotype.Component;

/**
 * 获取角色信息Api降级处理
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@Component
public class RoleApiFallback implements RoleApi {
    @Override
    public ActionResult<RoleEntity> getInfoByRole(String roleId) {
        return null;
    }
}
