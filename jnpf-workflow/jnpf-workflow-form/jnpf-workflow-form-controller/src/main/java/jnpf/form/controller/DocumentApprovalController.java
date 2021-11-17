package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.DocumentApprovalEntity;
import jnpf.form.model.documentapproval.DocumentApprovalForm;
import jnpf.form.model.documentapproval.DocumentApprovalInfoVO;
import jnpf.form.service.DocumentApprovalService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 文件签批意见表
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Api(tags = "文件签批意见表", value = "DocumentApproval")
@RestController
@RequestMapping("/Form/DocumentApproval")
public class DocumentApprovalController {

    @Autowired
    private DocumentApprovalService documentApprovalService;

    /**
     * 获取文件签批意见表信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取文件签批意见表信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        DocumentApprovalEntity entity = documentApprovalService.getInfo(id);
        DocumentApprovalInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, DocumentApprovalInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建文件签批意见表
     *
     * @param documentApprovalForm 表单对象
     * @return
     */
    @ApiOperation("新建文件签批意见表")
    @PostMapping
    public ActionResult create(@RequestBody @Valid DocumentApprovalForm documentApprovalForm) throws WorkFlowException {
        DocumentApprovalEntity entity = JsonUtil.getJsonToBean(documentApprovalForm, DocumentApprovalEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(documentApprovalForm.getStatus())) {
            documentApprovalService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        documentApprovalService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改文件签批意见表
     *
     * @param documentApprovalForm 表单对象
     * @param id                   主键
     * @return
     */
    @ApiOperation("修改文件签批意见表")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody @Valid DocumentApprovalForm documentApprovalForm, @PathVariable("id") String id) throws WorkFlowException {
        DocumentApprovalEntity entity = JsonUtil.getJsonToBean(documentApprovalForm, DocumentApprovalEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(documentApprovalForm.getStatus())) {
            documentApprovalService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        documentApprovalService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
