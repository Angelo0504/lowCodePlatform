package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.TravelApplyEntity;
import jnpf.form.model.travelapply.TravelApplyForm;
import jnpf.form.model.travelapply.TravelApplyInfoVO;
import jnpf.form.service.TravelApplyService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 出差预支申请单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Api(tags = "出差预支申请单", value = "TravelApply")
@RestController
@RequestMapping("/Form/TravelApply")
public class TravelApplyController {

    @Autowired
    private TravelApplyService travelApplyService;

    /**
     * 获取出差预支申请单信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取出差预支申请单信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        TravelApplyEntity entity = travelApplyService.getInfo(id);
        TravelApplyInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, TravelApplyInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建出差预支申请单
     *
     * @param travelApplyForm 表单对象
     * @return
     */
    @ApiOperation("新建出差预支申请单")
    @PostMapping
    public ActionResult create(@RequestBody TravelApplyForm travelApplyForm) throws WorkFlowException {
        TravelApplyEntity entity = JsonUtil.getJsonToBean(travelApplyForm, TravelApplyEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(travelApplyForm.getStatus())) {
            travelApplyService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        travelApplyService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改出差预支申请单
     *
     * @param travelApplyForm 表单对象
     * @param id              主键
     * @return
     */
    @ApiOperation("修改出差预支申请单")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody TravelApplyForm travelApplyForm, @PathVariable("id") String id) throws WorkFlowException {
        TravelApplyEntity entity = JsonUtil.getJsonToBean(travelApplyForm, TravelApplyEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(travelApplyForm.getStatus())) {
            travelApplyService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        travelApplyService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
