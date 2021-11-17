package jnpf.base.service;


import com.baomidou.mybatisplus.extension.service.IService;
import jnpf.base.entity.ComFieldsEntity;

import java.util.List;

/**
 *
 * 常用字段表
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-23
 */
public interface ComFieldsService extends IService<ComFieldsEntity> {

    List<ComFieldsEntity> getList();

    ComFieldsEntity getInfo(String id);

    void create(ComFieldsEntity entity);

    boolean update(String id, ComFieldsEntity entity);

    /**
     * 验证名称
     *
     * @param fullName 名称
     * @param id       主键值
     * @return
     */
    boolean isExistByFullName(String fullName, String id);

    void delete(ComFieldsEntity entity);
}
