package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.SalesSupportEntity;
import jnpf.form.model.salessupport.SalesSupportForm;
import jnpf.form.model.salessupport.SalesSupportInfoVO;
import jnpf.form.service.SalesSupportService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 销售支持表
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Api(tags = "销售支持表", value = "SalesSupport")
@RestController
@RequestMapping("/Form/SalesSupport")
public class SalesSupportController {

    @Autowired
    private SalesSupportService salesSupportService;

    /**
     * 获取销售支持表信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取销售支持表信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        SalesSupportEntity entity = salesSupportService.getInfo(id);
        SalesSupportInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, SalesSupportInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建销售支持表
     *
     * @param salesSupportForm 表单对象
     * @return
     */
    @ApiOperation("新建保存销售支持表")
    @PostMapping
    public ActionResult create(@RequestBody SalesSupportForm salesSupportForm) throws WorkFlowException {
        SalesSupportEntity entity = JsonUtil.getJsonToBean(salesSupportForm, SalesSupportEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(salesSupportForm.getStatus())) {
            salesSupportService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        salesSupportService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改销售支持表
     *
     * @param salesSupportForm 表单对象
     * @param id               主键
     * @return
     */
    @ApiOperation("修改销售支持表")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody SalesSupportForm salesSupportForm, @PathVariable("id") String id) throws WorkFlowException {
        SalesSupportEntity entity = JsonUtil.getJsonToBean(salesSupportForm, SalesSupportEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(salesSupportForm.getStatus())) {
            salesSupportService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        salesSupportService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
