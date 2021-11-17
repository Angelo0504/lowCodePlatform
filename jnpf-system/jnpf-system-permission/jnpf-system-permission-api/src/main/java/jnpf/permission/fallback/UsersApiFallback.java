package jnpf.permission.fallback;

import jnpf.exception.LoginException;
import jnpf.permission.UsersApi;
import jnpf.base.ActionResult;
import jnpf.permission.entity.UserEntity;
import jnpf.permission.model.user.UserAllModel;
import jnpf.permission.model.user.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 获取用户信息Api降级处理
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@Component
@Slf4j
public class UsersApiFallback implements UsersApi {
    @Override
    public ActionResult<List<UserAllModel>> getAll() {
        return null;
    }

    @Override
    public UserEntity checkUser(String account, String dbId, String dbName) {
        log.error("用户信息获取失败");
        return null;
    }

    @Override
    public List<UserEntity> getListByManagerId(String userId) {
        return null;
    }

    @Override
    public ActionResult<UserInfoVO> getInfo(String id) {
        return null;
    }

    @Override
    public List<UserEntity> getListByPositionId(String userId) {
        return null;
    }

    @Override
    public List<UserEntity> getUserList() {
        return null;
    }

    @Override
    public List<UserAllModel> getDbUserAll() {
        return null;
    }

    @Override
    public UserEntity getInfoById(String id) {
        return null;
    }

    @Override
    public void updateById(UserEntity userEntity) {

    }

    @Override
    public UserEntity isExistUser(String account, String password, String tenantId, String dbName) throws LoginException {
        return null;
    }

}
