package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.PayDistributionEntity;
import jnpf.form.model.paydistribution.PayDistributionForm;
import jnpf.form.model.paydistribution.PayDistributionInfoVO;
import jnpf.form.service.PayDistributionService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 薪酬发放
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Api(tags = "薪酬发放", value = "PayDistribution")
@RestController
@RequestMapping("/Form/PayDistribution")
public class PayDistributionController {

    @Autowired
    private PayDistributionService payDistributionService;

    /**
     * 获取薪酬发放信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取薪酬发放信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        PayDistributionEntity entity = payDistributionService.getInfo(id);
        PayDistributionInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, PayDistributionInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建薪酬发放
     *
     * @param payDistributionForm 表单对象
     * @return
     */
    @ApiOperation("新建薪酬发放")
    @PostMapping
    public ActionResult create(@RequestBody PayDistributionForm payDistributionForm) throws WorkFlowException {
        PayDistributionEntity entity = JsonUtil.getJsonToBean(payDistributionForm, PayDistributionEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(payDistributionForm.getStatus())) {
            payDistributionService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        payDistributionService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改薪酬发放
     *
     * @param payDistributionForm 表单对象
     * @param id                  主键
     * @return
     */
    @ApiOperation("修改薪酬发放")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody PayDistributionForm payDistributionForm, @PathVariable("id") String id) throws WorkFlowException {
        PayDistributionEntity entity = JsonUtil.getJsonToBean(payDistributionForm, PayDistributionEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(payDistributionForm.getStatus())) {
            payDistributionService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        payDistributionService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
