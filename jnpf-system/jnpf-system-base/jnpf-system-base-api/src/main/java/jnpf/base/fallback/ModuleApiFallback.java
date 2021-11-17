package jnpf.base.fallback;

import jnpf.base.ModuleApi;
import jnpf.base.entity.ModuleEntity;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * 调用系统菜单Api降级处理
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@Component
public class ModuleApiFallback implements ModuleApi {
    @Override
    public List<ModuleEntity> getList() {
        return null;
    }
}
