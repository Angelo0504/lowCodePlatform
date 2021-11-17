package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.util.RegexUtils;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.BatchPackEntity;
import jnpf.form.model.batchpack.BatchPackForm;
import jnpf.form.model.batchpack.BatchPackInfoVO;
import jnpf.form.service.BatchPackService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import jnpf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 批包装指令
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Api(tags = "批包装指令", value = "BatchPack")
@RestController
@RequestMapping("/Form/BatchPack")
public class BatchPackController {

    @Autowired
    private BatchPackService batchPackService;

    /**
     * 获取批包装指令信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取批包装指令信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        BatchPackEntity entity = batchPackService.getInfo(id);
        BatchPackInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, BatchPackInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建批包装指令
     *
     * @param batchPackForm 表单对象
     * @return
     */
    @ApiOperation("新建批包装指令")
    @PostMapping
    public ActionResult create(@RequestBody @Valid BatchPackForm batchPackForm) throws WorkFlowException {
        if (batchPackForm.getProductionQuty() != null && StringUtil.isNotEmpty(batchPackForm.getProductionQuty()) && !RegexUtils.checkDigit2(batchPackForm.getProductionQuty())) {
            return ActionResult.fail("批产数量只能输入正整数");
        }
        BatchPackEntity entity = JsonUtil.getJsonToBean(batchPackForm, BatchPackEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(batchPackForm.getStatus())) {
            batchPackService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        batchPackService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改批包装指令
     *
     * @param batchPackForm 表单对象
     * @param id            主键
     * @return
     */
    @ApiOperation("修改批包装指令")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody @Valid BatchPackForm batchPackForm, @PathVariable("id") String id) throws WorkFlowException {
        if (batchPackForm.getProductionQuty() != null && StringUtil.isNotEmpty(batchPackForm.getProductionQuty()) && !RegexUtils.checkDigit2(batchPackForm.getProductionQuty())) {
            return ActionResult.fail("批产数量只能输入正整数");
        }
        BatchPackEntity entity = JsonUtil.getJsonToBean(batchPackForm, BatchPackEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(batchPackForm.getStatus())) {
            batchPackService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        batchPackService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
