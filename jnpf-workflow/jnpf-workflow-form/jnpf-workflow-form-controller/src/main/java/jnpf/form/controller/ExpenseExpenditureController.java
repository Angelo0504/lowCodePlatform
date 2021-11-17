package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.ExpenseExpenditureEntity;
import jnpf.form.model.expenseexpenditure.ExpenseExpenditureForm;
import jnpf.form.model.expenseexpenditure.ExpenseExpenditureInfoVO;
import jnpf.form.service.ExpenseExpenditureService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 费用支出单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Api(tags = "费用支出单", value = "ExpenseExpenditure")
@RestController
@RequestMapping("/Form/ExpenseExpenditure")
public class ExpenseExpenditureController {

    @Autowired
    private ExpenseExpenditureService expenseExpenditureService;

    /**
     * 获取费用支出单信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取费用支出单信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        ExpenseExpenditureEntity entity = expenseExpenditureService.getInfo(id);
        ExpenseExpenditureInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, ExpenseExpenditureInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建费用支出单
     *
     * @param expenseExpenditureForm 表单对象
     * @return
     */
    @ApiOperation("新建费用支出单")
    @PostMapping
    public ActionResult create(@RequestBody @Valid ExpenseExpenditureForm expenseExpenditureForm) throws WorkFlowException {
        ExpenseExpenditureEntity entity = JsonUtil.getJsonToBean(expenseExpenditureForm, ExpenseExpenditureEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(expenseExpenditureForm.getStatus())) {
            expenseExpenditureService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        expenseExpenditureService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改费用支出单
     *
     * @param expenseExpenditureForm 表单对象
     * @param id                     主键
     * @return
     */
    @ApiOperation("修改费用支出单")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody @Valid ExpenseExpenditureForm expenseExpenditureForm, @PathVariable("id") String id) throws WorkFlowException {
        ExpenseExpenditureEntity entity = JsonUtil.getJsonToBean(expenseExpenditureForm, ExpenseExpenditureEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(expenseExpenditureForm.getStatus())) {
            expenseExpenditureService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        expenseExpenditureService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
