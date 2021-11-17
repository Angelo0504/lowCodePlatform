package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.QuotationApprovalEntity;
import jnpf.form.model.quotationapproval.QuotationApprovalForm;
import jnpf.form.model.quotationapproval.QuotationApprovalInfoVO;
import jnpf.form.service.QuotationApprovalService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 报价审批表
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Api(tags = "报价审批表", value = "QuotationApproval")
@RestController
@RequestMapping("/Form/QuotationApproval")
public class QuotationApprovalController {


    @Autowired
    private QuotationApprovalService quotationApprovalService;

    /**
     * 获取报价审批表信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取报价审批表信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        QuotationApprovalEntity entity = quotationApprovalService.getInfo(id);
        QuotationApprovalInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, QuotationApprovalInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建报价审批表
     *
     * @param quotationApprovalForm 表单对象
     * @return
     */
    @ApiOperation("新建报价审批表")
    @PostMapping
    public ActionResult create(@RequestBody QuotationApprovalForm quotationApprovalForm) throws WorkFlowException {
        QuotationApprovalEntity entity = JsonUtil.getJsonToBean(quotationApprovalForm, QuotationApprovalEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(quotationApprovalForm.getStatus())) {
            quotationApprovalService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        quotationApprovalService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改报价审批表
     *
     * @param quotationApprovalForm 表单对象
     * @param id                    主键
     * @return
     */
    @ApiOperation("修改报价审批表")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody QuotationApprovalForm quotationApprovalForm, @PathVariable("id") String id) throws WorkFlowException {
        QuotationApprovalEntity entity = JsonUtil.getJsonToBean(quotationApprovalForm, QuotationApprovalEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(quotationApprovalForm.getStatus())) {
            quotationApprovalService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        quotationApprovalService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
