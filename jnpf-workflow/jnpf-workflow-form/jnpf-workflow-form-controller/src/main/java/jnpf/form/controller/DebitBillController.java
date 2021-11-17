package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.DebitBillEntity;
import jnpf.form.model.debitbill.DebitBillForm;
import jnpf.form.model.debitbill.DebitBillInfoVO;
import jnpf.form.service.DebitBillService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import jnpf.util.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 借支单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Api(tags = "借支单", value = "DebitBill")
@RestController
@RequestMapping("/Form/DebitBill")
public class DebitBillController {

    @Autowired
    private DebitBillService debitBillService;

    /**
     * 获取借支单信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取借支单信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        DebitBillEntity entity = debitBillService.getInfo(id);
        DebitBillInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, DebitBillInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建借支单
     *
     * @param debitBillForm 表单对象
     * @return
     */
    @ApiOperation("新建借支单")
    @PostMapping
    public ActionResult create(@RequestBody @Valid DebitBillForm debitBillForm) throws WorkFlowException {
        if (debitBillForm.getAmountDebit() != null && !"".equals(String.valueOf(debitBillForm.getAmountDebit())) && !RegexUtils.checkDecimals2(String.valueOf(debitBillForm.getAmountDebit()))) {
            return ActionResult.fail("借支金额必须大于0，最多可以输入两位小数点");
        }
        DebitBillEntity entity = JsonUtil.getJsonToBean(debitBillForm, DebitBillEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(debitBillForm.getStatus())) {
            debitBillService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        debitBillService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改借支单
     *
     * @param debitBillForm 表单对象
     * @param id            主键
     * @return
     */
    @ApiOperation("修改借支单")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody @Valid DebitBillForm debitBillForm, @PathVariable("id") String id) throws WorkFlowException {
        if (debitBillForm.getAmountDebit() != null && !"".equals(String.valueOf(debitBillForm.getAmountDebit())) && !RegexUtils.checkDecimals2(String.valueOf(debitBillForm.getAmountDebit()))) {
            return ActionResult.fail("借支金额必须大于0，最多可以输入两位小数点");
        }
        DebitBillEntity entity = JsonUtil.getJsonToBean(debitBillForm, DebitBillEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(debitBillForm.getStatus())) {
            debitBillService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        debitBillService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
