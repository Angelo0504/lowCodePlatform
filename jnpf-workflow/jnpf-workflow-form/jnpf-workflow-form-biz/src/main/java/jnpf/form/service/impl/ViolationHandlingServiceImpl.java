package jnpf.form.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jnpf.base.BillRuleApi;
import jnpf.engine.service.FlowTaskService;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.ViolationHandlingEntity;
import jnpf.form.mapper.ViolationHandlingMapper;
import jnpf.form.model.violationhandling.ViolationHandlingForm;
import jnpf.form.service.ViolationHandlingService;
import jnpf.util.JsonUtil;
import jnpf.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 违章处理申请表
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Service
public class ViolationHandlingServiceImpl extends ServiceImpl<ViolationHandlingMapper, ViolationHandlingEntity> implements ViolationHandlingService {

    @Autowired
    private BillRuleApi billRuleApi;
    @Autowired
    private FlowTaskService flowTaskService;

    @Override
    public ViolationHandlingEntity getInfo(String id) {
        QueryWrapper<ViolationHandlingEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ViolationHandlingEntity::getId, id);
        return this.getOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = WorkFlowException.class)
    public void save(String id, ViolationHandlingEntity entity) throws WorkFlowException {
        //表单信息
        if (id == null) {
            entity.setId(RandomUtil.uuId());
            this.save(entity);
            billRuleApi.useBillNumber("WF_ViolationHandlingNo");
        } else {
            entity.setId(id);
            this.updateById(entity);
        }
        //流程信息
        flowTaskService.save(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo());
    }

    @Override
    @Transactional(rollbackFor = WorkFlowException.class)
    public void submit(String id, ViolationHandlingEntity entity) throws WorkFlowException {
        //表单信息
        if (id == null) {
            entity.setId(RandomUtil.uuId());
            this.save(entity);
            billRuleApi.useBillNumber("WF_ViolationHandlingNo");
        } else {
            entity.setId(id);
            this.updateById(entity);
        }
        //流程信息
        flowTaskService.submit(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo(), entity);
    }

    @Override
    public void data(String id, String data) {
        ViolationHandlingForm violationHandlingForm = JsonUtil.getJsonToBean(data, ViolationHandlingForm.class);
        ViolationHandlingEntity entity = JsonUtil.getJsonToBean(violationHandlingForm, ViolationHandlingEntity.class);
        entity.setId(id);
        this.updateById(entity);
    }
}
