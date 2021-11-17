package jnpf.form.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jnpf.base.BillRuleApi;
import jnpf.engine.service.FlowTaskService;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.StaffOvertimeEntity;
import jnpf.form.mapper.StaffOvertimeMapper;
import jnpf.form.model.staffovertime.StaffOvertimeForm;
import jnpf.form.service.StaffOvertimeService;
import jnpf.util.JsonUtil;
import jnpf.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 员工加班申请表
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Service
public class StaffOvertimeServiceImpl extends ServiceImpl<StaffOvertimeMapper, StaffOvertimeEntity> implements StaffOvertimeService {

    @Autowired
    private BillRuleApi billRuleApi;
    @Autowired
    private FlowTaskService flowTaskService;

    @Override
    public StaffOvertimeEntity getInfo(String id) {
        QueryWrapper<StaffOvertimeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StaffOvertimeEntity::getId, id);
        return this.getOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = WorkFlowException.class)
    public void save(String id, StaffOvertimeEntity entity) throws WorkFlowException {
        //表单信息
        if (id == null) {
            entity.setId(RandomUtil.uuId());
            this.save(entity);
            billRuleApi.useBillNumber("WF_StaffOvertimeNo");
        } else {
            entity.setId(id);
            this.updateById(entity);
        }
        //流程信息
        flowTaskService.save(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo());
    }

    @Override
    @Transactional(rollbackFor = WorkFlowException.class)
    public void submit(String id, StaffOvertimeEntity entity) throws WorkFlowException {
        //表单信息
        if (id == null) {
            entity.setId(RandomUtil.uuId());
            this.save(entity);
            billRuleApi.useBillNumber("WF_StaffOvertimeNo");
        } else {
            entity.setId(id);
            this.updateById(entity);
        }
        //流程信息
        flowTaskService.submit(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo(), entity);
    }

    @Override
    public void data(String id, String data) {
        StaffOvertimeForm staffOvertimeForm = JsonUtil.getJsonToBean(data, StaffOvertimeForm.class);
        StaffOvertimeEntity entity = JsonUtil.getJsonToBean(staffOvertimeForm, StaffOvertimeEntity.class);
        entity.setId(id);
        this.updateById(entity);
    }
}
