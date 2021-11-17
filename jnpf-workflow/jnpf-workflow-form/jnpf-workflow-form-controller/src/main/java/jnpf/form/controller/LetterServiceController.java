package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.util.RegexUtils;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.LetterServiceEntity;
import jnpf.form.model.letterservice.LetterServiceForm;
import jnpf.form.model.letterservice.LetterServiceInfoVO;
import jnpf.form.service.LetterServiceService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import jnpf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 发文单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Api(tags = "发文单", value = "LetterService")
@RestController
@RequestMapping("/Form/LetterService")
public class LetterServiceController {

    @Autowired
    private LetterServiceService letterServiceService;

    /**
     * 获取发文单信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取发文单信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        LetterServiceEntity entity = letterServiceService.getInfo(id);
        LetterServiceInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, LetterServiceInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建发文单
     *
     * @param letterServiceForm 表单对象
     * @return
     */
    @ApiOperation("新建发文单")
    @PostMapping
    public ActionResult create(@RequestBody @Valid LetterServiceForm letterServiceForm) throws WorkFlowException {
        if (letterServiceForm.getShareNum() != null && StringUtil.isNotEmpty(letterServiceForm.getShareNum()) && !RegexUtils.checkDigit2(letterServiceForm.getShareNum())) {
            return ActionResult.fail("份数只能输入正整数");
        }
        LetterServiceEntity entity = JsonUtil.getJsonToBean(letterServiceForm, LetterServiceEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(letterServiceForm.getStatus())) {
            letterServiceService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        letterServiceService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改发文单
     *
     * @param letterServiceForm 表单对象
     * @param id                主键
     * @return
     */
    @ApiOperation("修改发文单")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody @Valid LetterServiceForm letterServiceForm, @PathVariable("id") String id) throws WorkFlowException {
        if (letterServiceForm.getShareNum() != null && StringUtil.isNotEmpty(letterServiceForm.getShareNum()) && !RegexUtils.checkDigit2(letterServiceForm.getShareNum())) {
            return ActionResult.fail("份数只能输入正整数");
        }
        LetterServiceEntity entity = JsonUtil.getJsonToBean(letterServiceForm, LetterServiceEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(letterServiceForm.getStatus())) {
            letterServiceService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        letterServiceService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
