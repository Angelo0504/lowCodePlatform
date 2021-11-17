package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.ReceiptProcessingEntity;
import jnpf.form.model.receiptprocessing.ReceiptProcessingForm;
import jnpf.form.model.receiptprocessing.ReceiptProcessingInfoVO;
import jnpf.form.service.ReceiptProcessingService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 收文处理表
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Api(tags = "收文处理表", value = "ReceiptProcessing")
@RestController
@RequestMapping("/Form/ReceiptProcessing")
public class ReceiptProcessingController {

    @Autowired
    private ReceiptProcessingService receiptProcessingService;

    /**
     * 获取收文处理表信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取收文处理表信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        ReceiptProcessingEntity entity = receiptProcessingService.getInfo(id);
        ReceiptProcessingInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, ReceiptProcessingInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建收文处理表
     *
     * @param receiptProcessingForm 表单对象
     * @return
     */
    @ApiOperation("新建收文处理表")
    @PostMapping
    public ActionResult create(@RequestBody ReceiptProcessingForm receiptProcessingForm) throws WorkFlowException {
        ReceiptProcessingEntity entity = JsonUtil.getJsonToBean(receiptProcessingForm, ReceiptProcessingEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(receiptProcessingForm.getStatus())) {
            receiptProcessingService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        receiptProcessingService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改收文处理表
     *
     * @param receiptProcessingForm 表单对象
     * @param id                    主键
     * @return
     */
    @ApiOperation("修改收文处理表")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody ReceiptProcessingForm receiptProcessingForm, @PathVariable("id") String id) throws WorkFlowException {
        ReceiptProcessingEntity entity = JsonUtil.getJsonToBean(receiptProcessingForm, ReceiptProcessingEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(receiptProcessingForm.getStatus())) {
            receiptProcessingService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        receiptProcessingService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
