package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.DocumentSigningEntity;
import jnpf.form.model.documentsigning.DocumentSigningForm;
import jnpf.form.model.documentsigning.DocumentSigningInfoVO;
import jnpf.form.service.DocumentSigningService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 文件签阅表
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Api(tags = "文件签阅表", value = "DocumentSigning")
@RestController
@RequestMapping("/Form/DocumentSigning")
public class DocumentSigningController {

    @Autowired
    private DocumentSigningService documentSigningService;

    /**
     * 获取文件签阅表信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取文件签阅表信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        DocumentSigningEntity entity = documentSigningService.getInfo(id);
        DocumentSigningInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, DocumentSigningInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建文件签阅表
     *
     * @param documentSigningForm 表单对象
     * @return
     */
    @ApiOperation("新建文件签阅表")
    @PostMapping
    public ActionResult create(@RequestBody @Valid DocumentSigningForm documentSigningForm) throws WorkFlowException {
        DocumentSigningEntity entity = JsonUtil.getJsonToBean(documentSigningForm, DocumentSigningEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(documentSigningForm.getStatus())) {
            documentSigningService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        documentSigningService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改文件签阅表
     *
     * @param documentSigningForm 表单对象
     * @param id                  主键
     * @return
     */
    @ApiOperation("修改文件签阅表")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody @Valid DocumentSigningForm documentSigningForm, @PathVariable("id") String id) throws WorkFlowException {
        DocumentSigningEntity entity = JsonUtil.getJsonToBean(documentSigningForm, DocumentSigningEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(documentSigningForm.getStatus())) {
            documentSigningService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        documentSigningService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
