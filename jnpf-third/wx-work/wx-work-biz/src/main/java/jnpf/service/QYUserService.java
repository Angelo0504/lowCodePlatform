package jnpf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jnpf.base.Page;
import jnpf.QYDepartmentEntity;
import jnpf.QYUserEntity;
import jnpf.exception.WxErrorException;
import jnpf.permission.entity.UserEntity;

import java.util.List;

/**
 * 企业号用户
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
public interface QYUserService extends IService<QYUserEntity> {


    /**
     * 同步成功列表
     *
     * @return
     */
    List<QYUserEntity> getListAll();

    /**
     * 列表
     * @param page
     * @return
     */
    List<QYUserEntity> getList(Page page);

    /**
     * 同步
     *
     * @param userList 用户列表
     * @param departList 同步成功的部门
     */
    void synchronization(List<UserEntity> userList, List<QYDepartmentEntity> departList) throws WxErrorException;
}
