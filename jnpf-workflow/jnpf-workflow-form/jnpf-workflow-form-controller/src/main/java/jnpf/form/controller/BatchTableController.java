package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.util.RegexUtils;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.BatchTableEntity;
import jnpf.form.model.batchtable.BatchTableForm;
import jnpf.form.model.batchtable.BatchTableInfoVO;
import jnpf.form.service.BatchTableService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import jnpf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 行文呈批表
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Api(tags = "行文呈批表", value = "BatchTable")
@RestController
@RequestMapping("/Form/BatchTable")
public class BatchTableController {

    @Autowired
    private BatchTableService batchTableService;

    /**
     * 获取行文呈批表信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取行文呈批表信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        BatchTableEntity entity = batchTableService.getInfo(id);
        BatchTableInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, BatchTableInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建行文呈批表
     *
     * @param batchTableForm 表单对象
     * @return
     */
    @ApiOperation("新建行文呈批表")
    @PostMapping
    public ActionResult create(@RequestBody @Valid BatchTableForm batchTableForm) throws WorkFlowException {
        if (batchTableForm.getShareNum() != null && StringUtil.isNotEmpty(batchTableForm.getShareNum()) && !RegexUtils.checkDigit2(batchTableForm.getShareNum())) {
            return ActionResult.fail("份数只能输入正整数");
        }
        BatchTableEntity entity = JsonUtil.getJsonToBean(batchTableForm, BatchTableEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(batchTableForm.getStatus())) {
            batchTableService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        batchTableService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改行文呈批表
     *
     * @param batchTableForm 表单对象
     * @param id             主键
     * @return
     */
    @ApiOperation("修改行文呈批表")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody @Valid BatchTableForm batchTableForm, @PathVariable("id") String id) throws WorkFlowException {
        if (batchTableForm.getShareNum() != null && StringUtil.isNotEmpty(batchTableForm.getShareNum()) && !RegexUtils.checkDigit2(batchTableForm.getShareNum())) {
            return ActionResult.fail("份数只能输入正整数");
        }
        BatchTableEntity entity = JsonUtil.getJsonToBean(batchTableForm, BatchTableEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(batchTableForm.getStatus())) {
            batchTableService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        batchTableService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
