package jnpf.permission.fallback;

import jnpf.permission.OrganizeApi;
import jnpf.permission.entity.OrganizeEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 获取组织信息Api降级处理
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@Component
public class OrganizeApiFallback implements OrganizeApi {
    @Override
    public OrganizeEntity getById(String organizeId) {
        return null;
    }

    @Override
    public List<OrganizeEntity> getList() {
        return null;
    }
}
