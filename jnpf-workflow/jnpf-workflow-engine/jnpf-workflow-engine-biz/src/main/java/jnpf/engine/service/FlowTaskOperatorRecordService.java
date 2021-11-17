package jnpf.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jnpf.engine.entity.FlowTaskOperatorRecordEntity;

import java.util.List;

/**
 * 流程经办记录
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
public interface FlowTaskOperatorRecordService extends IService<FlowTaskOperatorRecordEntity> {

    /**
     * 列表
     *
     * @param taskId 流程实例Id
     * @return
     */
    List<FlowTaskOperatorRecordEntity> getList(String taskId);

    /**
     * 信息
     *
     * @param id 主键值
     * @return
     */
    FlowTaskOperatorRecordEntity getInfo(String id);

    /**
     * 删除
     *
     * @param entity 实体对象
     * @return
     */
    void delete(FlowTaskOperatorRecordEntity entity);

    /**
     * 创建
     *
     * @param entity 实体对象
     * @return
     */
    void create(FlowTaskOperatorRecordEntity entity);

    /**
     * 更新
     *
     * @param id     主键值
     * @param entity 实体对象
     */
    void update(String id, FlowTaskOperatorRecordEntity entity);
}
