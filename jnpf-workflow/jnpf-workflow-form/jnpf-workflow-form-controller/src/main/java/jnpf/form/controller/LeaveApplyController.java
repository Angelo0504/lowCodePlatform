package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.util.RegexUtils;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.LeaveApplyEntity;
import jnpf.form.model.leaveapply.LeaveApplyForm;
import jnpf.form.model.leaveapply.LeaveApplyInfoVO;
import jnpf.form.service.LeaveApplyService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 请假申请
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Api(tags = "请假申请", value = "LeaveApply")
@RestController
@RequestMapping("/Form/LeaveApply")
public class LeaveApplyController {

    @Autowired
    private LeaveApplyService leaveApplyService;

    /**
     * 获取请假申请信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取请假申请信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        LeaveApplyEntity entity = leaveApplyService.getInfo(id);
        LeaveApplyInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, LeaveApplyInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建请假申请
     *
     * @param leaveApplyForm 表单对象
     * @return
     */
    @ApiOperation("新建请假申请")
    @PostMapping
    public ActionResult create(@RequestBody @Valid LeaveApplyForm leaveApplyForm) throws WorkFlowException {
        if (leaveApplyForm.getLeaveStartTime() > leaveApplyForm.getLeaveEndTime()) {
            return ActionResult.fail("结束时间不能小于起始时间");
        }
        if (!RegexUtils.checkLeave(leaveApplyForm.getLeaveDayCount())) {
            return ActionResult.fail("请假天数只能是0.5的倍数");
        }
        if (!RegexUtils.checkLeave(leaveApplyForm.getLeaveHour())) {
            return ActionResult.fail("请假小时只能是0.5的倍数");
        }
        LeaveApplyEntity entity = JsonUtil.getJsonToBean(leaveApplyForm, LeaveApplyEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(leaveApplyForm.getStatus())) {
            leaveApplyService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        leaveApplyService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改请假申请
     *
     * @param leaveApplyForm 表单对象
     * @param id             主键
     * @return
     */
    @ApiOperation("修改请假申请")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody @Valid LeaveApplyForm leaveApplyForm, @PathVariable("id") String id) throws WorkFlowException {
        if (leaveApplyForm.getLeaveStartTime() > leaveApplyForm.getLeaveEndTime()) {
            return ActionResult.fail("结束时间不能小于起始时间");
        }
        if (!RegexUtils.checkLeave(leaveApplyForm.getLeaveDayCount())) {
            return ActionResult.fail("请假天数只能是0.5的倍数");
        }
        if (!RegexUtils.checkLeave(leaveApplyForm.getLeaveHour())) {
            return ActionResult.fail("请假小时只能是0.5的倍数");
        }
        LeaveApplyEntity entity = JsonUtil.getJsonToBean(leaveApplyForm, LeaveApplyEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(leaveApplyForm.getStatus())) {
            leaveApplyService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        leaveApplyService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
