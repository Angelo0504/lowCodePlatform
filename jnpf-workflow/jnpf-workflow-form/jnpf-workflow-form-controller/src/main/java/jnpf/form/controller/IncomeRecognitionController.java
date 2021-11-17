package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.IncomeRecognitionEntity;
import jnpf.form.model.incomerecognition.IncomeRecognitionForm;
import jnpf.form.model.incomerecognition.IncomeRecognitionInfoVO;
import jnpf.form.service.IncomeRecognitionService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 收入确认分析表
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Api(tags = "收入确认分析表", value = "IncomeRecognition")
@RestController
@RequestMapping("/Form/IncomeRecognition")
public class IncomeRecognitionController {

    @Autowired
    private IncomeRecognitionService incomeRecognitionService;

    /**
     * 获取收入确认分析表信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取收入确认分析表信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        IncomeRecognitionEntity entity = incomeRecognitionService.getInfo(id);
        IncomeRecognitionInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, IncomeRecognitionInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建收入确认分析表
     *
     * @param incomeRecognitionForm 表单对象
     * @return
     */
    @ApiOperation("新建收入确认分析表")
    @PostMapping
    public ActionResult create(@RequestBody @Valid IncomeRecognitionForm incomeRecognitionForm) throws WorkFlowException {
        IncomeRecognitionEntity entity = JsonUtil.getJsonToBean(incomeRecognitionForm, IncomeRecognitionEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(incomeRecognitionForm.getStatus())) {
            incomeRecognitionService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        incomeRecognitionService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改收入确认分析表
     *
     * @param incomeRecognitionForm 表单对象
     * @param id                    主键
     * @return
     */
    @ApiOperation("修改收入确认分析表")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody @Valid IncomeRecognitionForm incomeRecognitionForm, @PathVariable("id") String id) throws WorkFlowException {
        IncomeRecognitionEntity entity = JsonUtil.getJsonToBean(incomeRecognitionForm, IncomeRecognitionEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(incomeRecognitionForm.getStatus())) {
            incomeRecognitionService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        incomeRecognitionService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
