package jnpf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jnpf.entity.WorkLogEntity;
import jnpf.base.Pagination;

import java.util.List;

/**
 * 工作日志
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月26日 上午9:18
 */
public interface WorkLogService extends IService<WorkLogEntity> {

    /**
     * 列表(我发出的)
     * @param pageModel 请求参数
     * @return
     */
    List<WorkLogEntity> getSendList(Pagination pageModel);

    /**
     * 列表(我收出的)
     * @param pageModel 请求参数
     * @return
     */
    List<WorkLogEntity> getReceiveList(Pagination pageModel);

    /**
     * 信息
     *
     * @param id 主键值
     * @return
     */
    WorkLogEntity getInfo(String id);

    /**
     * 创建
     *
     * @param entity 实体对象
     * @return
     */
    void create(WorkLogEntity entity);

    /**
     * 更新
     *
     * @param id     主键值
     * @param entity 实体对象
     * @return
     */
    boolean update(String id, WorkLogEntity entity);

    /**
     * 删除
     *
     * @param entity 实体对象
     */
    void delete(WorkLogEntity entity);
}
