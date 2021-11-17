package jnpf.form.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jnpf.base.BillRuleApi;
import jnpf.engine.service.FlowTaskService;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.MaterialEntryEntity;
import jnpf.form.entity.MaterialRequisitionEntity;
import jnpf.form.mapper.MaterialRequisitionMapper;
import jnpf.form.model.materialrequisition.MaterialRequisitionForm;
import jnpf.form.service.MaterialEntryService;
import jnpf.form.service.MaterialRequisitionService;
import jnpf.util.JsonUtil;
import jnpf.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 领料单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Service
public class MaterialRequisitionServiceImpl extends ServiceImpl<MaterialRequisitionMapper, MaterialRequisitionEntity> implements MaterialRequisitionService {

    @Autowired
    private BillRuleApi billRuleApi;
    @Autowired
    private MaterialEntryService materialEntryService;
    @Autowired
    private FlowTaskService flowTaskService;

    @Override
    public List<MaterialEntryEntity> getMaterialEntryList(String id) {
        QueryWrapper<MaterialEntryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MaterialEntryEntity::getLeadeId, id).orderByDesc(MaterialEntryEntity::getSortCode);
        return materialEntryService.list(queryWrapper);
    }

    @Override
    public MaterialRequisitionEntity getInfo(String id) {
        QueryWrapper<MaterialRequisitionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MaterialRequisitionEntity::getId, id);
        return this.getOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = WorkFlowException.class)
    public void save(String id, MaterialRequisitionEntity entity, List<MaterialEntryEntity> materialEntryEntityList) throws WorkFlowException {
        //表单信息
        if (id == null) {
            entity.setId(RandomUtil.uuId());
            for (int i = 0; i < materialEntryEntityList.size(); i++) {
                materialEntryEntityList.get(i).setId(RandomUtil.uuId());
                materialEntryEntityList.get(i).setLeadeId(entity.getId());
                materialEntryEntityList.get(i).setSortCode(Long.parseLong(i + ""));
                materialEntryService.save(materialEntryEntityList.get(i));
            }
            //创建
            this.save(entity);
            billRuleApi.useBillNumber("WF_MaterialRequisitionNo");
        } else {
            entity.setId(id);
            QueryWrapper<MaterialEntryEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(MaterialEntryEntity::getLeadeId, entity.getId());
            materialEntryService.remove(queryWrapper);
            for (int i = 0; i < materialEntryEntityList.size(); i++) {
                materialEntryEntityList.get(i).setId(RandomUtil.uuId());
                materialEntryEntityList.get(i).setLeadeId(entity.getId());
                materialEntryEntityList.get(i).setSortCode(Long.parseLong(i + ""));
                materialEntryService.save(materialEntryEntityList.get(i));
            }
            //编辑
            this.updateById(entity);
        }
        //流程信息
        flowTaskService.save(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo());
    }

    @Override
    @Transactional(rollbackFor = WorkFlowException.class)
    public void submit(String id, MaterialRequisitionEntity entity, List<MaterialEntryEntity> materialEntryEntityList) throws WorkFlowException {
        //表单信息
        if (id == null) {
            entity.setId(RandomUtil.uuId());
            for (int i = 0; i < materialEntryEntityList.size(); i++) {
                materialEntryEntityList.get(i).setId(RandomUtil.uuId());
                materialEntryEntityList.get(i).setLeadeId(entity.getId());
                materialEntryEntityList.get(i).setSortCode(Long.parseLong(i + ""));
                materialEntryService.save(materialEntryEntityList.get(i));
            }
            //创建
            this.save(entity);
            billRuleApi.useBillNumber("WF_MaterialRequisitionNo");
        } else {
            entity.setId(id);
            QueryWrapper<MaterialEntryEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(MaterialEntryEntity::getLeadeId, entity.getId());
            materialEntryService.remove(queryWrapper);
            for (int i = 0; i < materialEntryEntityList.size(); i++) {
                materialEntryEntityList.get(i).setId(RandomUtil.uuId());
                materialEntryEntityList.get(i).setLeadeId(entity.getId());
                materialEntryEntityList.get(i).setSortCode(Long.parseLong(i + ""));
                materialEntryService.save(materialEntryEntityList.get(i));
            }
            //编辑
            this.updateById(entity);
        }
        //流程信息
        flowTaskService.submit(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo(), entity);
    }

    @Override
    public void data(String id, String data) {
        MaterialRequisitionForm materialRequisitionForm = JsonUtil.getJsonToBean(data, MaterialRequisitionForm.class);
        MaterialRequisitionEntity entity = JsonUtil.getJsonToBean(materialRequisitionForm, MaterialRequisitionEntity.class);
        List<MaterialEntryEntity> materialEntryEntityList = JsonUtil.getJsonToList(materialRequisitionForm.getEntryList(), MaterialEntryEntity.class);
        entity.setId(id);
        QueryWrapper<MaterialEntryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MaterialEntryEntity::getLeadeId, entity.getId());
        materialEntryService.remove(queryWrapper);
        for (int i = 0; i < materialEntryEntityList.size(); i++) {
            materialEntryEntityList.get(i).setId(RandomUtil.uuId());
            materialEntryEntityList.get(i).setLeadeId(entity.getId());
            materialEntryEntityList.get(i).setSortCode(Long.parseLong(i + ""));
            materialEntryService.save(materialEntryEntityList.get(i));
        }
        this.updateById(entity);
    }
}
