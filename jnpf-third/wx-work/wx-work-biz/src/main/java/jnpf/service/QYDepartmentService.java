package jnpf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jnpf.exception.WxErrorException;
import jnpf.QYDepartmentEntity;
import jnpf.permission.entity.OrganizeEntity;

import java.util.List;

/**
 * 企业号部门
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
public interface QYDepartmentService extends IService<QYDepartmentEntity> {

    /**
     * 列表
     *
     * @return
     */
    List<QYDepartmentEntity> getList();

    /**
     * 部门列表
     *
     * @return
     */
    List<QYDepartmentEntity> getSyncList();

    /**
     * 列表
     *
     * @param userId
     * @return
     */
    List<QYDepartmentEntity> getListByUserId(String userId);

    /**
     * 同步数据
     * @param organizelist          部门列表
     * @throws WxErrorException
     */
    void synchronization(List<OrganizeEntity> organizelist) throws WxErrorException;
}
