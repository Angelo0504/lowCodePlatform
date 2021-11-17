package jnpf.permission.fallback;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jnpf.permission.AuthorizeApi;
import jnpf.permission.entity.AuthorizeEntity;
import jnpf.permission.model.authorize.AuthorizeVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 获取权限信息Api降级处理
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@Component
public class AuthorizeApiFallback implements AuthorizeApi {
    @Override
    public AuthorizeVO getEntity(boolean isCache) {
        return null;
    }

    @Override
    public List<AuthorizeEntity> getListByObjectId(String objectId) {
        return null;
    }

    @Override
    public void remove(QueryWrapper<AuthorizeEntity> queryWrapper) {

    }
}
