package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.StaffOvertimeEntity;
import jnpf.form.model.staffovertime.StaffOvertimeForm;
import jnpf.form.model.staffovertime.StaffOvertimeInfoVO;
import jnpf.form.service.StaffOvertimeService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 员工加班申请表
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Api(tags = "员工加班申请表", value = "StaffOvertime")
@RestController
@RequestMapping("/Form/StaffOvertime")
public class StaffOvertimeController {

    @Autowired
    private StaffOvertimeService staffOvertimeService;

    /**
     * 获取员工加班申请表信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取员工加班申请表信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        StaffOvertimeEntity entity = staffOvertimeService.getInfo(id);
        StaffOvertimeInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, StaffOvertimeInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建员工加班申请表
     *
     * @param staffOvertimeForm 表单对象
     * @return
     */
    @ApiOperation("新建员工加班申请表")
    @PostMapping
    public ActionResult create(@RequestBody StaffOvertimeForm staffOvertimeForm) throws WorkFlowException {
        if (staffOvertimeForm.getStartTime() > staffOvertimeForm.getEndTime()) {
            return ActionResult.fail("结束时间不能小于起始时间");
        }
        StaffOvertimeEntity entity = JsonUtil.getJsonToBean(staffOvertimeForm, StaffOvertimeEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(staffOvertimeForm.getStatus())) {
            staffOvertimeService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        staffOvertimeService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改
     *
     * @param staffOvertimeForm 表单对象
     * @param id                主键
     * @return
     */
    @ApiOperation("修改员工加班申请表")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody StaffOvertimeForm staffOvertimeForm, @PathVariable("id") String id) throws WorkFlowException {
        if (staffOvertimeForm.getStartTime() > staffOvertimeForm.getEndTime()) {
            return ActionResult.fail("结束时间不能小于起始时间");
        }
        StaffOvertimeEntity entity = JsonUtil.getJsonToBean(staffOvertimeForm, StaffOvertimeEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(staffOvertimeForm.getStatus())) {
            staffOvertimeService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        staffOvertimeService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
