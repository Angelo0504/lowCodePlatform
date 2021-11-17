package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.ProcurementEntryEntity;
import jnpf.form.entity.ProcurementMaterialEntity;
import jnpf.form.model.procurementmaterial.ProcurementEntryEntityInfoModel;
import jnpf.form.model.procurementmaterial.ProcurementMaterialForm;
import jnpf.form.model.procurementmaterial.ProcurementMaterialInfoVO;
import jnpf.form.service.ProcurementMaterialService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 采购原材料
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Api(tags = "采购原材料", value = "ProcurementMaterial")
@RestController
@RequestMapping("/Form/ProcurementMaterial")
public class ProcurementMaterialController {

    @Autowired
    private ProcurementMaterialService procurementMaterialService;

    /**
     * 获取采购原材料信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取采购原材料信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        ProcurementMaterialEntity entity = procurementMaterialService.getInfo(id);
        List<ProcurementEntryEntity> entityList = procurementMaterialService.getProcurementEntryList(id);
        ProcurementMaterialInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, ProcurementMaterialInfoVO.class);
        vo.setEntryList(JsonUtil.getJsonToList(entityList, ProcurementEntryEntityInfoModel.class));
        return ActionResult.success(vo);
    }

    /**
     * 新建采购原材料
     *
     * @param procurementMaterialForm 表单对象
     * @return
     * @throws WorkFlowException
     */
    @ApiOperation("新建采购原材料")
    @PostMapping
    public ActionResult create(@RequestBody ProcurementMaterialForm procurementMaterialForm) throws WorkFlowException {
        ProcurementMaterialEntity procurement = JsonUtil.getJsonToBean(procurementMaterialForm, ProcurementMaterialEntity.class);
        List<ProcurementEntryEntity> procurementEntryList = JsonUtil.getJsonToList(procurementMaterialForm.getEntryList(), ProcurementEntryEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(procurementMaterialForm.getStatus())) {
            procurementMaterialService.save(procurement.getId(), procurement, procurementEntryList);
            return ActionResult.success("保存成功");
        }
        procurementMaterialService.submit(procurement.getId(), procurement, procurementEntryList);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改采购原材料
     *
     * @param procurementMaterialForm 表单对象
     * @param id                      主键
     * @return
     * @throws WorkFlowException
     */
    @ApiOperation("修改采购原材料")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody ProcurementMaterialForm procurementMaterialForm, @PathVariable("id") String id) throws WorkFlowException {
        ProcurementMaterialEntity procurement = JsonUtil.getJsonToBean(procurementMaterialForm, ProcurementMaterialEntity.class);
        List<ProcurementEntryEntity> procurementEntryList = JsonUtil.getJsonToList(procurementMaterialForm.getEntryList(), ProcurementEntryEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(procurementMaterialForm.getStatus())) {
            procurementMaterialService.save(id, procurement, procurementEntryList);
            return ActionResult.success("保存成功");
        }
        procurementMaterialService.submit(id, procurement, procurementEntryList);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
