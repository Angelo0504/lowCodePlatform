package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.SalesOrderEntity;
import jnpf.form.entity.SalesOrderEntryEntity;
import jnpf.form.model.salesorder.SalesOrderEntryEntityInfoModel;
import jnpf.form.model.salesorder.SalesOrderForm;
import jnpf.form.model.salesorder.SalesOrderInfoVO;
import jnpf.form.service.SalesOrderService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 销售订单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Api(tags = "销售订单", value = "SalesOrder")
@RestController
@RequestMapping("/Form/SalesOrder")
public class SalesOrderController {

    @Autowired
    private SalesOrderService salesOrderService;

    /**
     * 获取销售订单信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取销售订单信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        SalesOrderEntity entity = salesOrderService.getInfo(id);
        List<SalesOrderEntryEntity> entityList = salesOrderService.getSalesEntryList(id);
        SalesOrderInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, SalesOrderInfoVO.class);
        vo.setEntryList(JsonUtil.getJsonToList(entityList, SalesOrderEntryEntityInfoModel.class));
        return ActionResult.success(vo);
    }

    /**
     * 新建销售订单
     *
     * @param salesOrderForm 表单对象
     * @return
     * @throws WorkFlowException
     */
    @ApiOperation("新建销售订单")
    @PostMapping
    public ActionResult create(@RequestBody SalesOrderForm salesOrderForm) throws WorkFlowException {
        SalesOrderEntity sales = JsonUtil.getJsonToBean(salesOrderForm, SalesOrderEntity.class);
        List<SalesOrderEntryEntity> salesEntryList = JsonUtil.getJsonToList(salesOrderForm.getEntryList(), SalesOrderEntryEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(salesOrderForm.getStatus())) {
            salesOrderService.save(sales.getId(), sales, salesEntryList);
            return ActionResult.success("保存成功");
        }
        salesOrderService.submit(sales.getId(), sales, salesEntryList);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改销售订单
     *
     * @param salesOrderForm 表单对象
     * @param id             主键
     * @return
     * @throws WorkFlowException
     */
    @ApiOperation("修改销售订单")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody SalesOrderForm salesOrderForm, @PathVariable("id") String id) throws WorkFlowException {
        SalesOrderEntity sales = JsonUtil.getJsonToBean(salesOrderForm, SalesOrderEntity.class);
        List<SalesOrderEntryEntity> salesEntryList = JsonUtil.getJsonToList(salesOrderForm.getEntryList(), SalesOrderEntryEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(salesOrderForm.getStatus())) {
            salesOrderService.save(id, sales, salesEntryList);
            return ActionResult.success("保存成功");
        }
        salesOrderService.submit(id, sales, salesEntryList);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
