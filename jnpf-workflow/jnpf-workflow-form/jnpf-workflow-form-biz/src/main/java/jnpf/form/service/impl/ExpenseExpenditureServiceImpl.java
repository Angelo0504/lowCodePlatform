package jnpf.form.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jnpf.base.BillRuleApi;
import jnpf.engine.service.FlowTaskService;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.ExpenseExpenditureEntity;
import jnpf.form.mapper.ExpenseExpenditureMapper;
import jnpf.form.model.expenseexpenditure.ExpenseExpenditureForm;
import jnpf.form.service.ExpenseExpenditureService;
import jnpf.util.JsonUtil;
import jnpf.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 费用支出单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Service
public class ExpenseExpenditureServiceImpl extends ServiceImpl<ExpenseExpenditureMapper, ExpenseExpenditureEntity> implements ExpenseExpenditureService {

    @Autowired
    private BillRuleApi billRuleApi;
    @Autowired
    private FlowTaskService flowTaskService;

    @Override
    public ExpenseExpenditureEntity getInfo(String id) {
        QueryWrapper<ExpenseExpenditureEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ExpenseExpenditureEntity::getId, id);
        return this.getOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = WorkFlowException.class)
    public void save(String id, ExpenseExpenditureEntity entity) throws WorkFlowException {
        //表单信息
        if (id == null) {
            entity.setId(RandomUtil.uuId());
            this.save(entity);
            billRuleApi.useBillNumber("WF_ExpenseExpenditureNo");
        } else {
            entity.setId(id);
            this.updateById(entity);
        }
        //流程信息
        flowTaskService.save(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo());
    }

    @Override
    @Transactional(rollbackFor = WorkFlowException.class)
    public void submit(String id, ExpenseExpenditureEntity entity) throws WorkFlowException {
        //表单信息
        if (id == null) {
            entity.setId(RandomUtil.uuId());
            this.save(entity);
            billRuleApi.useBillNumber("WF_ExpenseExpenditureNo");
        } else {
            entity.setId(id);
            this.updateById(entity);
        }
        //流程信息
        flowTaskService.submit(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo(), entity);
    }

    @Override
    public void data(String id, String data) {
        ExpenseExpenditureForm expenseExpenditureForm = JsonUtil.getJsonToBean(data, ExpenseExpenditureForm.class);
        ExpenseExpenditureEntity entity = JsonUtil.getJsonToBean(expenseExpenditureForm, ExpenseExpenditureEntity.class);
        entity.setId(id);
        this.updateById(entity);
    }
}
