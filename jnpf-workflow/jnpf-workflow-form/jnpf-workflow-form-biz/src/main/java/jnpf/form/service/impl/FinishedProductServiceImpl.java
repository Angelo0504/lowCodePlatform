package jnpf.form.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jnpf.base.BillRuleApi;
import jnpf.engine.service.FlowTaskService;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.FinishedProductEntity;
import jnpf.form.entity.FinishedProductEntryEntity;
import jnpf.form.mapper.FinishedProductMapper;
import jnpf.form.model.finishedproduct.FinishedProductForm;
import jnpf.form.service.FinishedProductEntryService;
import jnpf.form.service.FinishedProductService;
import jnpf.util.JsonUtil;
import jnpf.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 成品入库单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Service
public class FinishedProductServiceImpl extends ServiceImpl<FinishedProductMapper, FinishedProductEntity> implements FinishedProductService {

    @Autowired
    private BillRuleApi billRuleApi;
    @Autowired
    private FinishedProductEntryService finishedProductEntryService;
    @Autowired
    private FlowTaskService flowTaskService;

    @Override
    public List<FinishedProductEntryEntity> getFinishedEntryList(String id) {
        QueryWrapper<FinishedProductEntryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(FinishedProductEntryEntity::getWarehouseId, id).orderByDesc(FinishedProductEntryEntity::getSortCode);
        return finishedProductEntryService.list(queryWrapper);
    }

    @Override
    public FinishedProductEntity getInfo(String id) {
        QueryWrapper<FinishedProductEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(FinishedProductEntity::getId, id);
        return this.getOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = WorkFlowException.class)
    public void save(String id, FinishedProductEntity entity, List<FinishedProductEntryEntity> finishedProductEntryEntityList) throws WorkFlowException {
        //表单信息
        if (id == null) {
            entity.setId(RandomUtil.uuId());
            for (int i = 0; i < finishedProductEntryEntityList.size(); i++) {
                finishedProductEntryEntityList.get(i).setId(RandomUtil.uuId());
                finishedProductEntryEntityList.get(i).setWarehouseId(entity.getId());
                finishedProductEntryEntityList.get(i).setSortCode(Long.parseLong(i + ""));
                finishedProductEntryService.save(finishedProductEntryEntityList.get(i));
            }
            //创建
            this.save(entity);
            billRuleApi.useBillNumber("WF_FinishedProductNo");
        } else {
            entity.setId(id);
            QueryWrapper<FinishedProductEntryEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(FinishedProductEntryEntity::getWarehouseId, entity.getId());
            finishedProductEntryService.remove(queryWrapper);
            for (int i = 0; i < finishedProductEntryEntityList.size(); i++) {
                finishedProductEntryEntityList.get(i).setId(RandomUtil.uuId());
                finishedProductEntryEntityList.get(i).setWarehouseId(entity.getId());
                finishedProductEntryEntityList.get(i).setSortCode(Long.parseLong(i + ""));
                finishedProductEntryService.save(finishedProductEntryEntityList.get(i));
            }
            //编辑
            this.updateById(entity);
        }
        //流程信息
        flowTaskService.save(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo());
    }

    @Override
    @Transactional(rollbackFor = WorkFlowException.class)
    public void submit(String id, FinishedProductEntity entity, List<FinishedProductEntryEntity> finishedProductEntryEntityList) throws WorkFlowException {
        //表单信息
        if (id == null) {
            entity.setId(RandomUtil.uuId());
            for (int i = 0; i < finishedProductEntryEntityList.size(); i++) {
                finishedProductEntryEntityList.get(i).setId(RandomUtil.uuId());
                finishedProductEntryEntityList.get(i).setWarehouseId(entity.getId());
                finishedProductEntryEntityList.get(i).setSortCode(Long.parseLong(i + ""));
                finishedProductEntryService.save(finishedProductEntryEntityList.get(i));
            }
            //创建
            this.save(entity);
            billRuleApi.useBillNumber("WF_FinishedProductNo");
        } else {
            entity.setId(id);
            QueryWrapper<FinishedProductEntryEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(FinishedProductEntryEntity::getWarehouseId, entity.getId());
            finishedProductEntryService.remove(queryWrapper);
            for (int i = 0; i < finishedProductEntryEntityList.size(); i++) {
                finishedProductEntryEntityList.get(i).setId(RandomUtil.uuId());
                finishedProductEntryEntityList.get(i).setWarehouseId(entity.getId());
                finishedProductEntryEntityList.get(i).setSortCode(Long.parseLong(i + ""));
                finishedProductEntryService.save(finishedProductEntryEntityList.get(i));
            }
            //编辑
            this.updateById(entity);
        }
        //流程信息
        flowTaskService.submit(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo(), entity);
    }

    @Override
    public void data(String id, String data) {
        FinishedProductForm finishedProductForm = JsonUtil.getJsonToBean(data, FinishedProductForm.class);
        FinishedProductEntity entity = JsonUtil.getJsonToBean(finishedProductForm, FinishedProductEntity.class);
        List<FinishedProductEntryEntity> finishedProductEntryEntityList = JsonUtil.getJsonToList(finishedProductForm.getEntryList(), FinishedProductEntryEntity.class);
        entity.setId(id);
        QueryWrapper<FinishedProductEntryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(FinishedProductEntryEntity::getWarehouseId, entity.getId());
        finishedProductEntryService.remove(queryWrapper);
        for (int i = 0; i < finishedProductEntryEntityList.size(); i++) {
            finishedProductEntryEntityList.get(i).setId(RandomUtil.uuId());
            finishedProductEntryEntityList.get(i).setWarehouseId(entity.getId());
            finishedProductEntryEntityList.get(i).setSortCode(Long.parseLong(i + ""));
            finishedProductEntryService.save(finishedProductEntryEntityList.get(i));
        }
        this.updateById(entity);
    }
}
