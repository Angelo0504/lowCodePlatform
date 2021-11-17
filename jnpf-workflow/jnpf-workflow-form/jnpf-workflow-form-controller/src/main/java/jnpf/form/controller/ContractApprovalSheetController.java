package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.util.RegexUtils;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.ContractApprovalSheetEntity;
import jnpf.form.model.contractapprovalsheet.ContractApprovalSheetForm;
import jnpf.form.model.contractapprovalsheet.ContractApprovalSheetInfoVO;
import jnpf.form.service.ContractApprovalSheetService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 合同申请单表
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Api(tags = "合同申请单表", value = "ContractApprovalSheet")
@RestController
@RequestMapping("/Form/ContractApprovalSheet")
public class ContractApprovalSheetController {

    @Autowired
    private ContractApprovalSheetService contractApprovalSheetService;

    /**
     * 获取合同申请单表信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取合同申请单表信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        ContractApprovalSheetEntity entity = contractApprovalSheetService.getInfo(id);
        ContractApprovalSheetInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, ContractApprovalSheetInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建合同申请单表
     *
     * @param contractApprovalSheetForm 表单对象
     * @return
     */
    @ApiOperation("新建合同申请单表")
    @PostMapping
    public ActionResult create(@RequestBody @Valid ContractApprovalSheetForm contractApprovalSheetForm) throws WorkFlowException {
        if (contractApprovalSheetForm.getStartContractDate() > contractApprovalSheetForm.getEndContractDate()) {
            return ActionResult.fail("结束时间不能小于开始时间");
        }
        if (contractApprovalSheetForm.getIncomeAmount() != null && !"".equals(String.valueOf(contractApprovalSheetForm.getIncomeAmount())) && !RegexUtils.checkDecimals2(String.valueOf(contractApprovalSheetForm.getIncomeAmount()))) {
            return ActionResult.fail("收入金额必须大于0，最多可以输入两位小数点");
        }
        if (contractApprovalSheetForm.getTotalExpenditure() != null && !"".equals(String.valueOf(contractApprovalSheetForm.getIncomeAmount())) && !RegexUtils.checkDecimals2(String.valueOf(contractApprovalSheetForm.getTotalExpenditure()))) {
            return ActionResult.fail("支出金额必须大于0，最多可以输入两位小数点");
        }
        ContractApprovalSheetEntity entity = JsonUtil.getJsonToBean(contractApprovalSheetForm, ContractApprovalSheetEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(contractApprovalSheetForm.getStatus())) {
            contractApprovalSheetService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        contractApprovalSheetService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改合同申请单表
     *
     * @param contractApprovalSheetForm 表单对象
     * @param id                        主键
     * @return
     */
    @ApiOperation("修改合同申请单表")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody @Valid ContractApprovalSheetForm contractApprovalSheetForm, @PathVariable("id") String id) throws WorkFlowException {
        if (contractApprovalSheetForm.getStartContractDate() > contractApprovalSheetForm.getEndContractDate()) {
            return ActionResult.fail("结束时间不能小于开始时间");
        }
        if (contractApprovalSheetForm.getIncomeAmount() != null && !"".equals(String.valueOf(contractApprovalSheetForm.getIncomeAmount())) && !RegexUtils.checkDecimals2(String.valueOf(contractApprovalSheetForm.getIncomeAmount()))) {
            return ActionResult.fail("收入金额必须大于0，最多可以输入两位小数点");
        }
        if (contractApprovalSheetForm.getTotalExpenditure() != null && !"".equals(String.valueOf(contractApprovalSheetForm.getTotalExpenditure())) && !RegexUtils.checkDecimals2(String.valueOf(contractApprovalSheetForm.getTotalExpenditure()))) {
            return ActionResult.fail("支出金额必须大于0，最多可以输入两位小数点");
        }
        ContractApprovalSheetEntity entity = JsonUtil.getJsonToBean(contractApprovalSheetForm, ContractApprovalSheetEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(contractApprovalSheetForm.getStatus())) {
            contractApprovalSheetService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        contractApprovalSheetService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
