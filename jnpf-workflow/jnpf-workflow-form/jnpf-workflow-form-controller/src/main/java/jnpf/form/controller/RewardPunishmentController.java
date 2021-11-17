package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.RewardPunishmentEntity;
import jnpf.form.model.rewardpunishment.RewardPunishmentForm;
import jnpf.form.model.rewardpunishment.RewardPunishmentInfoVO;
import jnpf.form.service.RewardPunishmentService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 行政赏罚单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Api(tags = "行政赏罚单", value = "RewardPunishment")
@RestController
@RequestMapping("/Form/RewardPunishment")
public class RewardPunishmentController {

    @Autowired
    private RewardPunishmentService rewardPunishmentService;

    /**
     * 获取行政赏罚单信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取行政赏罚单信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        RewardPunishmentEntity entity = rewardPunishmentService.getInfo(id);
        RewardPunishmentInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, RewardPunishmentInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建行政赏罚单
     *
     * @param rewardPunishmentForm 表单对象
     * @return
     */
    @ApiOperation("新建行政赏罚单")
    @PostMapping
    public ActionResult create(@RequestBody RewardPunishmentForm rewardPunishmentForm) throws WorkFlowException {
        RewardPunishmentEntity entity = JsonUtil.getJsonToBean(rewardPunishmentForm, RewardPunishmentEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(rewardPunishmentForm.getStatus())) {
            rewardPunishmentService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        rewardPunishmentService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改行政赏罚单
     *
     * @param rewardPunishmentForm 表单对象
     * @param id                   主键
     * @return
     */
    @ApiOperation("修改行政赏罚单")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody RewardPunishmentForm rewardPunishmentForm, @PathVariable("id") String id) throws WorkFlowException {
        RewardPunishmentEntity entity = JsonUtil.getJsonToBean(rewardPunishmentForm, RewardPunishmentEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(rewardPunishmentForm.getStatus())) {
            rewardPunishmentService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        rewardPunishmentService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
