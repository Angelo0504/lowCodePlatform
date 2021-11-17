package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.ApplyDeliverGoodsEntity;
import jnpf.form.entity.ApplyDeliverGoodsEntryEntity;
import jnpf.form.model.applydelivergoods.ApplyDeliverGoodsEntryInfoModel;
import jnpf.form.model.applydelivergoods.ApplyDeliverGoodsForm;
import jnpf.form.model.applydelivergoods.ApplyDeliverGoodsInfoVO;
import jnpf.form.service.ApplyDeliverGoodsService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 发货申请单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Api(tags = "发货申请单", value = "ApplyDeliverGoods")
@RestController
@RequestMapping("/Form/ApplyDeliverGoods")
public class ApplyDeliverGoodsController {

    @Autowired
    private ApplyDeliverGoodsService applyDeliverGoodsService;

    /**
     * 获取发货申请单信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取发货申请单信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        ApplyDeliverGoodsEntity entity = applyDeliverGoodsService.getInfo(id);
        List<ApplyDeliverGoodsEntryEntity> entityList = applyDeliverGoodsService.getDeliverEntryList(id);
        ApplyDeliverGoodsInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, ApplyDeliverGoodsInfoVO.class);
        vo.setEntryList(JsonUtil.getJsonToList(entityList, ApplyDeliverGoodsEntryInfoModel.class));
        return ActionResult.success(vo);
    }

    /**
     * 新建发货申请单
     *
     * @param applyDeliverGoodsForm 表单对象
     * @return
     * @throws WorkFlowException
     */
    @ApiOperation("新建发货申请单")
    @PostMapping
    public ActionResult create(@RequestBody @Valid ApplyDeliverGoodsForm applyDeliverGoodsForm) throws WorkFlowException {
        ApplyDeliverGoodsEntity deliver = JsonUtil.getJsonToBean(applyDeliverGoodsForm, ApplyDeliverGoodsEntity.class);
        List<ApplyDeliverGoodsEntryEntity> deliverEntryList = JsonUtil.getJsonToList(applyDeliverGoodsForm.getEntryList(), ApplyDeliverGoodsEntryEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(applyDeliverGoodsForm.getStatus())) {
            applyDeliverGoodsService.save(deliver.getId(), deliver, deliverEntryList);
            return ActionResult.success("保存成功");
        }
        applyDeliverGoodsService.submit(deliver.getId(), deliver, deliverEntryList);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改发货申请单
     *
     * @param applyDeliverGoodsForm 表单对象
     * @param id                    主键
     * @return
     * @throws WorkFlowException
     */
    @ApiOperation("修改发货申请单")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody @Valid ApplyDeliverGoodsForm applyDeliverGoodsForm, @PathVariable("id") String id) throws WorkFlowException {
        ApplyDeliverGoodsEntity deliver = JsonUtil.getJsonToBean(applyDeliverGoodsForm, ApplyDeliverGoodsEntity.class);
        List<ApplyDeliverGoodsEntryEntity> deliverEntryList = JsonUtil.getJsonToList(applyDeliverGoodsForm.getEntryList(), ApplyDeliverGoodsEntryEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(applyDeliverGoodsForm.getStatus())) {
            applyDeliverGoodsService.save(id, deliver, deliverEntryList);
            return ActionResult.success("保存成功");
        }
        applyDeliverGoodsService.submit(id, deliver, deliverEntryList);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
