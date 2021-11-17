package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.ArchivalBorrowEntity;
import jnpf.form.model.archivalborrow.ArchivalBorrowForm;
import jnpf.form.model.archivalborrow.ArchivalBorrowInfoVO;
import jnpf.form.service.ArchivalBorrowService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 档案借阅申请
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Api(tags = "档案借阅申请", value = "ArchivalBorrow")
@RestController
@RequestMapping("/Form/ArchivalBorrow")
public class ArchivalBorrowController {

    @Autowired
    private ArchivalBorrowService archivalBorrowService;

    /**
     * 获取档案借阅申请信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取档案借阅申请信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        ArchivalBorrowEntity entity = archivalBorrowService.getInfo(id);
        ArchivalBorrowInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, ArchivalBorrowInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建档案借阅申请
     *
     * @param archivalBorrowForm 表单对象
     * @return
     */
    @ApiOperation("新建档案借阅申请")
    @PostMapping
    public ActionResult create(@RequestBody @Valid ArchivalBorrowForm archivalBorrowForm) throws WorkFlowException {
        if (archivalBorrowForm.getBorrowingDate() > archivalBorrowForm.getReturnDate()) {
            return ActionResult.fail("归还时间不能小于借阅时间");
        }
        ArchivalBorrowEntity entity = JsonUtil.getJsonToBean(archivalBorrowForm, ArchivalBorrowEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(archivalBorrowForm.getStatus())) {
            archivalBorrowService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        archivalBorrowService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改档案借阅申请
     *
     * @param archivalBorrowForm 表单对象
     * @param id                 主键
     * @return
     */
    @ApiOperation("修改档案借阅申请")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody @Valid ArchivalBorrowForm archivalBorrowForm, @PathVariable("id") String id) throws WorkFlowException {
        if (archivalBorrowForm.getBorrowingDate() > archivalBorrowForm.getReturnDate()) {
            return ActionResult.fail("归还时间不能小于借阅时间");
        }
        ArchivalBorrowEntity entity = JsonUtil.getJsonToBean(archivalBorrowForm, ArchivalBorrowEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(archivalBorrowForm.getStatus())) {
            archivalBorrowService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        archivalBorrowService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
