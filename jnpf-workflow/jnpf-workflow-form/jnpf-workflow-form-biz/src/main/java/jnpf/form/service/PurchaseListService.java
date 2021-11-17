package jnpf.form.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.PurchaseListEntity;
import jnpf.form.entity.PurchaseListEntryEntity;

import java.util.List;

/**
 * 日常物品采购清单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
public interface PurchaseListService extends IService<PurchaseListEntity> {

    /**
     * 列表
     *
     * @param id 主键值
     * @return
     */
    List<PurchaseListEntryEntity> getPurchaseEntryList(String id);

    /**
     * 信息
     *
     * @param id 主键值
     * @return
     */
    PurchaseListEntity getInfo(String id);

    /**
     * 保存
     *
     * @param id                          主键值
     * @param entity                      实体对象
     * @param purchaseListEntryEntityList 子表
     * @throws WorkFlowException 异常
     */
    void save(String id, PurchaseListEntity entity, List<PurchaseListEntryEntity> purchaseListEntryEntityList) throws WorkFlowException;

    /**
     * 提交
     *
     * @param id                          主键值
     * @param entity                      实体对象
     * @param purchaseListEntryEntityList 子表
     * @throws WorkFlowException 异常
     */
    void submit(String id, PurchaseListEntity entity, List<PurchaseListEntryEntity> purchaseListEntryEntityList) throws WorkFlowException;

    /**
     * 更改数据
     *
     * @param id   主键值
     * @param data 实体对象
     */
    void data(String id, String data);
}
