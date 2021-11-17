package jnpf.form.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jnpf.base.BillRuleApi;
import jnpf.engine.service.FlowTaskService;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.IncomeRecognitionEntity;
import jnpf.form.mapper.IncomeRecognitionMapper;
import jnpf.form.model.incomerecognition.IncomeRecognitionForm;
import jnpf.form.service.IncomeRecognitionService;
import jnpf.util.JsonUtil;
import jnpf.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 收入确认分析表
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Service
public class IncomeRecognitionServiceImpl extends ServiceImpl<IncomeRecognitionMapper, IncomeRecognitionEntity> implements IncomeRecognitionService {

    @Autowired
    private BillRuleApi billRuleApi;
    @Autowired
    private FlowTaskService flowTaskService;

    @Override
    public IncomeRecognitionEntity getInfo(String id) {
        QueryWrapper<IncomeRecognitionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(IncomeRecognitionEntity::getId, id);
        return this.getOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = WorkFlowException.class)
    public void save(String id, IncomeRecognitionEntity entity) throws WorkFlowException {
        //表单信息
        if (id == null) {
            entity.setId(RandomUtil.uuId());
            this.save(entity);
            billRuleApi.useBillNumber("WF_IncomeRecognitionNo");
        } else {
            entity.setId(id);
            this.updateById(entity);
        }
        //流程信息
        flowTaskService.save(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo());
    }

    @Override
    @Transactional(rollbackFor = WorkFlowException.class)
    public void submit(String id, IncomeRecognitionEntity entity) throws WorkFlowException {
        //表单信息
        if (id == null) {
            entity.setId(RandomUtil.uuId());
            this.save(entity);
            billRuleApi.useBillNumber("WF_IncomeRecognitionNo");
        } else {
            entity.setId(id);
            this.updateById(entity);
        }
        //流程信息
        flowTaskService.submit(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo(), entity);
    }

    @Override
    public void data(String id, String data) {
        IncomeRecognitionForm incomeRecognitionForm = JsonUtil.getJsonToBean(data, IncomeRecognitionForm.class);
        IncomeRecognitionEntity entity = JsonUtil.getJsonToBean(incomeRecognitionForm, IncomeRecognitionEntity.class);
        entity.setId(id);
        this.updateById(entity);
    }
}
