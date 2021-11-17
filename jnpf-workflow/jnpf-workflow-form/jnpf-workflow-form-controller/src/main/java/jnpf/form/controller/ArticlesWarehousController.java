package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.ArticlesWarehousEntity;
import jnpf.form.model.articleswarehous.ArticlesWarehousForm;
import jnpf.form.model.articleswarehous.ArticlesWarehousInfoVO;
import jnpf.form.service.ArticlesWarehousService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import jnpf.util.RegexUtils;
import jnpf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用品入库申请表
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Api(tags = "用品入库申请表", value = "ArticlesWarehous")
@RestController
@RequestMapping("/Form/ArticlesWarehous")
public class ArticlesWarehousController {

    @Autowired
    private ArticlesWarehousService articlesWarehousService;

    /**
     * 获取用品入库申请表信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取用品入库申请表信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        ArticlesWarehousEntity entity = articlesWarehousService.getInfo(id);
        ArticlesWarehousInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, ArticlesWarehousInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建用品入库申请表
     *
     * @param articlesWarehousForm 表单对象
     * @return
     */
    @ApiOperation("新建用品入库申请表")
    @PostMapping
    public ActionResult create(@RequestBody @Valid ArticlesWarehousForm articlesWarehousForm) throws WorkFlowException {
        if (articlesWarehousForm.getEstimatePeople() != null && StringUtil.isNotEmpty(articlesWarehousForm.getEstimatePeople()) && !RegexUtils.checkDigit2(articlesWarehousForm.getEstimatePeople())) {
            return ActionResult.fail("数量只能输入正整数");
        }
        ArticlesWarehousEntity entity = JsonUtil.getJsonToBean(articlesWarehousForm, ArticlesWarehousEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(articlesWarehousForm.getStatus())) {
            articlesWarehousService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        articlesWarehousService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改用品入库申请表
     *
     * @param articlesWarehousForm 表单对象
     * @param id                   主键
     * @return
     */
    @ApiOperation("修改用品入库申请表")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody @Valid ArticlesWarehousForm articlesWarehousForm, @PathVariable("id") String id) throws WorkFlowException {
        if (articlesWarehousForm.getEstimatePeople() != null && StringUtil.isNotEmpty(articlesWarehousForm.getEstimatePeople()) && !RegexUtils.checkDigit2(articlesWarehousForm.getEstimatePeople())) {
            return ActionResult.fail("数量只能输入正整数");
        }
        ArticlesWarehousEntity entity = JsonUtil.getJsonToBean(articlesWarehousForm, ArticlesWarehousEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(articlesWarehousForm.getStatus())) {
            articlesWarehousService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        articlesWarehousService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
