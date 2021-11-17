package jnpf.form.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jnpf.base.BillRuleApi;
import jnpf.engine.service.FlowTaskService;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.WarehouseEntryEntity;
import jnpf.form.entity.WarehouseReceiptEntity;
import jnpf.form.mapper.WarehouseReceiptMapper;
import jnpf.form.model.warehousereceipt.WarehouseReceiptForm;
import jnpf.form.service.WarehouseEntryService;
import jnpf.form.service.WarehouseReceiptService;
import jnpf.util.JsonUtil;
import jnpf.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 入库申请单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Service
public class WarehouseReceiptServiceImpl extends ServiceImpl<WarehouseReceiptMapper, WarehouseReceiptEntity> implements WarehouseReceiptService {

    @Autowired
    private BillRuleApi billRuleApi;
    @Autowired
    private WarehouseEntryService warehouseEntryService;
    @Autowired
    private FlowTaskService flowTaskService;

    @Override
    public List<WarehouseEntryEntity> getWarehouseEntryList(String id) {
        QueryWrapper<WarehouseEntryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(WarehouseEntryEntity::getWarehouseId, id).orderByDesc(WarehouseEntryEntity::getSortCode);
        return warehouseEntryService.list(queryWrapper);
    }

    @Override
    public WarehouseReceiptEntity getInfo(String id) {
        QueryWrapper<WarehouseReceiptEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(WarehouseReceiptEntity::getId, id);
        return this.getOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = WorkFlowException.class)
    public void save(String id, WarehouseReceiptEntity entity, List<WarehouseEntryEntity> warehouseEntryEntityList) throws WorkFlowException {
        //表单信息
        if (id == null) {
            entity.setId(RandomUtil.uuId());
            for (int i = 0; i < warehouseEntryEntityList.size(); i++) {
                warehouseEntryEntityList.get(i).setId(RandomUtil.uuId());
                warehouseEntryEntityList.get(i).setWarehouseId(entity.getId());
                warehouseEntryEntityList.get(i).setSortCode(Long.parseLong(i + ""));
                warehouseEntryService.save(warehouseEntryEntityList.get(i));
            }
            //创建
            this.save(entity);
            billRuleApi.useBillNumber("WF_WarehouseReceiptNo");
        } else {
            entity.setId(id);
            QueryWrapper<WarehouseEntryEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(WarehouseEntryEntity::getWarehouseId, entity.getId());
            warehouseEntryService.remove(queryWrapper);
            for (int i = 0; i < warehouseEntryEntityList.size(); i++) {
                warehouseEntryEntityList.get(i).setId(RandomUtil.uuId());
                warehouseEntryEntityList.get(i).setWarehouseId(entity.getId());
                warehouseEntryEntityList.get(i).setSortCode(Long.parseLong(i + ""));
                warehouseEntryService.save(warehouseEntryEntityList.get(i));
            }
            //编辑
            this.updateById(entity);
        }
        //流程信息
        flowTaskService.save(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo());
    }

    @Override
    @Transactional(rollbackFor = WorkFlowException.class)
    public void submit(String id, WarehouseReceiptEntity entity, List<WarehouseEntryEntity> warehouseEntryEntityList) throws WorkFlowException {
        //表单信息
        if (id == null) {
            entity.setId(RandomUtil.uuId());
            for (int i = 0; i < warehouseEntryEntityList.size(); i++) {
                warehouseEntryEntityList.get(i).setId(RandomUtil.uuId());
                warehouseEntryEntityList.get(i).setWarehouseId(entity.getId());
                warehouseEntryEntityList.get(i).setSortCode(Long.parseLong(i + ""));
                warehouseEntryService.save(warehouseEntryEntityList.get(i));
            }
            //创建
            this.save(entity);
            billRuleApi.useBillNumber("WF_WarehouseReceiptNo");
        } else {
            entity.setId(id);
            QueryWrapper<WarehouseEntryEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(WarehouseEntryEntity::getWarehouseId, entity.getId());
            warehouseEntryService.remove(queryWrapper);
            for (int i = 0; i < warehouseEntryEntityList.size(); i++) {
                warehouseEntryEntityList.get(i).setId(RandomUtil.uuId());
                warehouseEntryEntityList.get(i).setWarehouseId(entity.getId());
                warehouseEntryEntityList.get(i).setSortCode(Long.parseLong(i + ""));
                warehouseEntryService.save(warehouseEntryEntityList.get(i));
            }
            //编辑
            this.updateById(entity);
        }
        //流程信息
        flowTaskService.submit(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo(), entity);
    }

    @Override
    public void data(String id, String data) {
        WarehouseReceiptForm warehouseReceiptForm = JsonUtil.getJsonToBean(data, WarehouseReceiptForm.class);
        WarehouseReceiptEntity entity = JsonUtil.getJsonToBean(warehouseReceiptForm, WarehouseReceiptEntity.class);
        List<WarehouseEntryEntity> warehouseEntryEntityList = JsonUtil.getJsonToList(warehouseReceiptForm.getEntryList(), WarehouseEntryEntity.class);
        entity.setId(id);
        QueryWrapper<WarehouseEntryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(WarehouseEntryEntity::getWarehouseId, entity.getId());
        warehouseEntryService.remove(queryWrapper);
        for (int i = 0; i < warehouseEntryEntityList.size(); i++) {
            warehouseEntryEntityList.get(i).setId(RandomUtil.uuId());
            warehouseEntryEntityList.get(i).setWarehouseId(entity.getId());
            warehouseEntryEntityList.get(i).setSortCode(Long.parseLong(i + ""));
            warehouseEntryService.save(warehouseEntryEntityList.get(i));
        }
        //编辑
        this.updateById(entity);
    }
}
