package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.PaymentApplyEntity;
import jnpf.form.model.paymentapply.PaymentApplyForm;
import jnpf.form.model.paymentapply.PaymentApplyInfoVO;
import jnpf.form.service.PaymentApplyService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 付款申请单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Api(tags = "付款申请单", value = "PaymentApply")
@RestController
@RequestMapping("/Form/PaymentApply")
public class PaymentApplyController {

    @Autowired
    private PaymentApplyService paymentApplyService;

    /**
     * 获取付款申请单信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取付款申请单信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        PaymentApplyEntity entity = paymentApplyService.getInfo(id);
        PaymentApplyInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, PaymentApplyInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建付款申请单
     *
     * @param paymentApplyForm 表单对象
     * @return
     */
    @ApiOperation("新建付款申请单")
    @PostMapping
    public ActionResult create(@RequestBody PaymentApplyForm paymentApplyForm) throws WorkFlowException {
        PaymentApplyEntity entity = JsonUtil.getJsonToBean(paymentApplyForm, PaymentApplyEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(paymentApplyForm.getStatus())) {
            paymentApplyService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        paymentApplyService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改付款申请单
     *
     * @param paymentApplyForm 表单对象
     * @param id               主键
     * @return
     */
    @ApiOperation("修改付款申请单")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody PaymentApplyForm paymentApplyForm, @PathVariable("id") String id) throws WorkFlowException {
        PaymentApplyEntity entity = JsonUtil.getJsonToBean(paymentApplyForm, PaymentApplyEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(paymentApplyForm.getStatus())) {
            paymentApplyService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        paymentApplyService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
