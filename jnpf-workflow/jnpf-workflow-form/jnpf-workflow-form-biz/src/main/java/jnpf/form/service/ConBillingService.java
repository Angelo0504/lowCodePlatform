package jnpf.form.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.ConBillingEntity;

/**
 * 合同开票流程
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
public interface ConBillingService extends IService<ConBillingEntity> {

    /**
     * 信息
     *
     * @param id 主键值
     * @return
     */
    ConBillingEntity getInfo(String id);

    /**
     * 保存
     *
     * @param id     主键值
     * @param entity 实体对象
     * @throws WorkFlowException 异常
     */
    void save(String id, ConBillingEntity entity) throws WorkFlowException;

    /**
     * 提交
     *
     * @param id     主键值
     * @param entity 实体对象
     * @throws WorkFlowException 异常
     */
    void submit(String id, ConBillingEntity entity) throws WorkFlowException;

    /**
     * 更改数据
     *
     * @param id   主键值
     * @param data 实体对象
     */
    void data(String id, String data);
}
