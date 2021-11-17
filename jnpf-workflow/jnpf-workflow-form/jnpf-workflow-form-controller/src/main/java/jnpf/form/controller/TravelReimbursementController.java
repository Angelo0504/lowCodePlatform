package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.TravelReimbursementEntity;
import jnpf.form.model.travelreimbursement.TravelReimbursementForm;
import jnpf.form.model.travelreimbursement.TravelReimbursementInfoVO;
import jnpf.form.service.TravelReimbursementService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 差旅报销申请表
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Api(tags = "差旅报销申请表", value = "TravelReimbursement")
@RestController
@RequestMapping("/Form/TravelReimbursement")
public class TravelReimbursementController {

    @Autowired
    private TravelReimbursementService travelReimbursementService;

    /**
     * 获取差旅报销申请表信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取差旅报销申请表信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        TravelReimbursementEntity entity = travelReimbursementService.getInfo(id);
        TravelReimbursementInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, TravelReimbursementInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建差旅报销申请表
     *
     * @param travelReimbursementForm 表单对象
     * @return
     */
    @ApiOperation("新建差旅报销申请表")
    @PostMapping
    public ActionResult create(@RequestBody TravelReimbursementForm travelReimbursementForm) throws WorkFlowException {
        if (travelReimbursementForm.getSetOutDate() > travelReimbursementForm.getReturnDate()) {
            return ActionResult.fail("结束时间不能小于起始时间");
        }
        TravelReimbursementEntity entity = JsonUtil.getJsonToBean(travelReimbursementForm, TravelReimbursementEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(travelReimbursementForm.getStatus())) {
            travelReimbursementService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        travelReimbursementService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改差旅报销申请表
     *
     * @param travelReimbursementForm 表单对象
     * @param id                      主键
     * @return
     */
    @ApiOperation("修改差旅报销申请表")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody TravelReimbursementForm travelReimbursementForm, @PathVariable("id") String id) throws WorkFlowException {
        if (travelReimbursementForm.getSetOutDate() > travelReimbursementForm.getReturnDate()) {
            return ActionResult.fail("结束时间不能小于起始时间");
        }
        TravelReimbursementEntity entity = JsonUtil.getJsonToBean(travelReimbursementForm, TravelReimbursementEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(travelReimbursementForm.getStatus())) {
            travelReimbursementService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        travelReimbursementService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
