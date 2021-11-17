package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.ReceiptSignEntity;
import jnpf.form.model.receiptsign.ReceiptSignForm;
import jnpf.form.model.receiptsign.ReceiptSignInfoVO;
import jnpf.form.service.ReceiptSignService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 收文签呈单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Api(tags = "收文签呈单", value = "ReceiptSign")
@RestController
@RequestMapping("/Form/ReceiptSign")
public class ReceiptSignController {

    @Autowired
    private ReceiptSignService receiptSignService;

    /**
     * 获取收文签呈单信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取收文签呈单信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        ReceiptSignEntity entity = receiptSignService.getInfo(id);
        ReceiptSignInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, ReceiptSignInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建收文签呈单
     *
     * @param receiptSignForm 表单对象
     * @return
     */
    @ApiOperation("新建收文签呈单")
    @PostMapping
    public ActionResult create(@RequestBody ReceiptSignForm receiptSignForm) throws WorkFlowException {
        ReceiptSignEntity entity = JsonUtil.getJsonToBean(receiptSignForm, ReceiptSignEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(receiptSignForm.getStatus())) {
            receiptSignService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        receiptSignService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改收文签呈单
     *
     * @param receiptSignForm 表单对象
     * @param id              主键
     * @return
     */
    @ApiOperation("修改收文签呈单")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody ReceiptSignForm receiptSignForm, @PathVariable("id") String id) throws WorkFlowException {
        ReceiptSignEntity entity = JsonUtil.getJsonToBean(receiptSignForm, ReceiptSignEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(receiptSignForm.getStatus())) {
            receiptSignService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        receiptSignService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
