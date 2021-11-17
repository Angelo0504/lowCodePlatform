package jnpf.permission.fallback;

import jnpf.permission.UserRelationApi;
import jnpf.permission.entity.UserRelationEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 获取用户关系Api降级处理
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@Component
public class UserRelationApiFallback implements UserRelationApi {
    @Override
    public List<UserRelationEntity> getList(String userId) {
        return null;
    }

    @Override
    public List<UserRelationEntity> getObjectList(String objectId) {
        return null;
    }



}
