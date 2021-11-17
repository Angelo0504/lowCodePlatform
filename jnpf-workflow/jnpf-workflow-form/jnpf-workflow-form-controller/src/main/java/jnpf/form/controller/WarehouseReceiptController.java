package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.WarehouseEntryEntity;
import jnpf.form.entity.WarehouseReceiptEntity;
import jnpf.form.model.warehousereceipt.WarehouseReceiptEntityInfoModel;
import jnpf.form.model.warehousereceipt.WarehouseReceiptForm;
import jnpf.form.model.warehousereceipt.WarehouseReceiptInfoVO;
import jnpf.form.service.WarehouseReceiptService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 入库申请单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Api(tags = "入库申请单", value = "WarehouseReceipt")
@RestController
@RequestMapping("/Form/WarehouseReceipt")
public class WarehouseReceiptController {

    @Autowired
    private WarehouseReceiptService warehouseReceiptService;

    /**
     * 获取入库申请单信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取入库申请单信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        WarehouseReceiptEntity entity = warehouseReceiptService.getInfo(id);
        List<WarehouseEntryEntity> entityList = warehouseReceiptService.getWarehouseEntryList(id);
        WarehouseReceiptInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, WarehouseReceiptInfoVO.class);
        vo.setEntryList(JsonUtil.getJsonToList(entityList, WarehouseReceiptEntityInfoModel.class));
        return ActionResult.success(vo);
    }

    /**
     * 新建入库申请单
     *
     * @param warehouseReceiptForm 表单对象
     * @return
     * @throws WorkFlowException
     */
    @ApiOperation("新建入库申请单")
    @PostMapping
    public ActionResult create(@RequestBody WarehouseReceiptForm warehouseReceiptForm) throws WorkFlowException {
        WarehouseReceiptEntity warehouse = JsonUtil.getJsonToBean(warehouseReceiptForm, WarehouseReceiptEntity.class);
        List<WarehouseEntryEntity> warehouseEntryList = JsonUtil.getJsonToList(warehouseReceiptForm.getEntryList(), WarehouseEntryEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(warehouseReceiptForm.getStatus())) {
            warehouseReceiptService.save(warehouse.getId(), warehouse, warehouseEntryList);
            return ActionResult.success("保存成功");
        }
        warehouseReceiptService.submit(warehouse.getId(), warehouse, warehouseEntryList);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改入库申请单
     *
     * @param warehouseReceiptForm 表单对象
     * @param id                   主键
     * @return
     * @throws WorkFlowException
     */
    @ApiOperation("修改入库申请单")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody WarehouseReceiptForm warehouseReceiptForm, @PathVariable("id") String id) throws WorkFlowException {
        WarehouseReceiptEntity warehouse = JsonUtil.getJsonToBean(warehouseReceiptForm, WarehouseReceiptEntity.class);
        List<WarehouseEntryEntity> warehouseEntryList = JsonUtil.getJsonToList(warehouseReceiptForm.getEntryList(), WarehouseEntryEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(warehouseReceiptForm.getStatus())) {
            warehouseReceiptService.save(id, warehouse, warehouseEntryList);
            return ActionResult.success("保存成功");
        }
        warehouseReceiptService.submit(id, warehouse, warehouseEntryList);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
