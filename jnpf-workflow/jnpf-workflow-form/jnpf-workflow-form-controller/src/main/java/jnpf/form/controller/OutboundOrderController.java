package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.OutboundEntryEntity;
import jnpf.form.entity.OutboundOrderEntity;
import jnpf.form.model.outboundorder.OutboundEntryEntityInfoModel;
import jnpf.form.model.outboundorder.OutboundOrderForm;
import jnpf.form.model.outboundorder.OutboundOrderInfoVO;
import jnpf.form.service.OutboundOrderService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 出库单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Api(tags = "出库单", value = "OutboundOrder")
@RestController
@RequestMapping("/Form/OutboundOrder")
public class OutboundOrderController {

    @Autowired
    private OutboundOrderService outboundOrderService;

    /**
     * 获取出库单信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取出库单信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        OutboundOrderEntity entity = outboundOrderService.getInfo(id);
        List<OutboundEntryEntity> entityList = outboundOrderService.getOutboundEntryList(id);
        OutboundOrderInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, OutboundOrderInfoVO.class);
        vo.setEntryList(JsonUtil.getJsonToList(entityList, OutboundEntryEntityInfoModel.class));
        return ActionResult.success(vo);
    }

    /**
     * 新建出库单
     *
     * @param outboundOrderForm 表单对象
     * @return
     * @throws WorkFlowException
     */
    @ApiOperation("新建出库单")
    @PostMapping
    public ActionResult create(@RequestBody OutboundOrderForm outboundOrderForm) throws WorkFlowException {
        OutboundOrderEntity outbound = JsonUtil.getJsonToBean(outboundOrderForm, OutboundOrderEntity.class);
        List<OutboundEntryEntity> outboundEntryList = JsonUtil.getJsonToList(outboundOrderForm.getEntryList(), OutboundEntryEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(outboundOrderForm.getStatus())) {
            outboundOrderService.save(outbound.getId(), outbound, outboundEntryList);
            return ActionResult.success("保存成功");
        }
        outboundOrderService.submit(outbound.getId(), outbound, outboundEntryList);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改出库单
     *
     * @param outboundOrderForm 表单对象
     * @param id                主键
     * @return
     * @throws WorkFlowException
     */
    @ApiOperation("修改出库单")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody OutboundOrderForm outboundOrderForm, @PathVariable("id") String id) throws WorkFlowException {
        OutboundOrderEntity outbound = JsonUtil.getJsonToBean(outboundOrderForm, OutboundOrderEntity.class);
        List<OutboundEntryEntity> outboundEntryList = JsonUtil.getJsonToList(outboundOrderForm.getEntryList(), OutboundEntryEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(outboundOrderForm.getStatus())) {
            outboundOrderService.save(id, outbound, outboundEntryList);
            return ActionResult.success("保存成功");
        }
        outboundOrderService.submit(id, outbound, outboundEntryList);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
