package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.PurchaseListEntity;
import jnpf.form.entity.PurchaseListEntryEntity;
import jnpf.form.model.purchaselist.PurchaseListEntryEntityInfoModel;
import jnpf.form.model.purchaselist.PurchaseListForm;
import jnpf.form.model.purchaselist.PurchaseListInfoVO;
import jnpf.form.service.PurchaseListService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 日常物品采购清单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Api(tags = "日常物品采购清单", value = "PurchaseList")
@RestController
@RequestMapping("/Form/PurchaseList")
public class PurchaseListController {

    @Autowired
    private PurchaseListService purchaseListService;

    /**
     * 获取日常物品采购清单信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取日常物品采购清单信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        PurchaseListEntity entity = purchaseListService.getInfo(id);
        List<PurchaseListEntryEntity> entityList = purchaseListService.getPurchaseEntryList(id);
        PurchaseListInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, PurchaseListInfoVO.class);
        vo.setEntryList(JsonUtil.getJsonToList(entityList, PurchaseListEntryEntityInfoModel.class));
        return ActionResult.success(vo);
    }

    /**
     * 新建日常物品采购清单
     *
     * @param purchaseListForm 表单对象
     * @return
     * @throws WorkFlowException
     */
    @ApiOperation("新建日常物品采购清单")
    @PostMapping
    public ActionResult create(@RequestBody PurchaseListForm purchaseListForm) throws WorkFlowException {
        PurchaseListEntity procurement = JsonUtil.getJsonToBean(purchaseListForm, PurchaseListEntity.class);
        List<PurchaseListEntryEntity> procurementEntryList = JsonUtil.getJsonToList(purchaseListForm.getEntryList(), PurchaseListEntryEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(purchaseListForm.getStatus())) {
            purchaseListService.save(procurement.getId(), procurement, procurementEntryList);
            return ActionResult.success("保存成功");
        }
        purchaseListService.submit(procurement.getId(), procurement, procurementEntryList);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改日常物品采购清单
     *
     * @param purchaseListForm 表单对象
     * @param id               主键
     * @return
     * @throws WorkFlowException
     */
    @ApiOperation("修改日常物品采购清单")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody PurchaseListForm purchaseListForm, @PathVariable("id") String id) throws WorkFlowException {
        PurchaseListEntity procurement = JsonUtil.getJsonToBean(purchaseListForm, PurchaseListEntity.class);
        List<PurchaseListEntryEntity> procurementEntryList = JsonUtil.getJsonToList(purchaseListForm.getEntryList(), PurchaseListEntryEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(purchaseListForm.getStatus())) {
            purchaseListService.save(id, procurement, procurementEntryList);
            return ActionResult.success("保存成功");
        }
        purchaseListService.submit(id, procurement, procurementEntryList);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
