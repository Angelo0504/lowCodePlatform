package jnpf.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jnpf.base.Pagination;
import jnpf.base.entity.DbBackupEntity;

import java.util.List;

/**
 * 数据备份
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
public interface DbBackupService extends IService<DbBackupEntity> {

    /**
     * 列表
     *
     * @param pagination 条件
     * @return
     */
    List<DbBackupEntity> getList(Pagination pagination);

    /**
     * 信息
     *
     * @param id 主键值
     * @return
     */
    DbBackupEntity getInfo(String id);

    /**
     * 删除
     *
     * @param entity 实体对象
     */
    void delete(DbBackupEntity entity);

    /**
     * 创建
     *
     * @param entity 实体对象
     */
    void create(DbBackupEntity entity);

    /**
     * 备份
     * @return
     */
    boolean dbBackup();
}
