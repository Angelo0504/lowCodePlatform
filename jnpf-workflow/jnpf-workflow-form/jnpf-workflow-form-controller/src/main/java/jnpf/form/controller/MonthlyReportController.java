package jnpf.form.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.engine.enums.FlowStatusEnum;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.form.entity.MonthlyReportEntity;
import jnpf.form.model.monthlyreport.MonthlyReportForm;
import jnpf.form.model.monthlyreport.MonthlyReportInfoVO;
import jnpf.form.service.MonthlyReportService;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 月工作总结
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Api(tags = "月工作总结", value = "MonthlyReport")
@RestController
@RequestMapping("/Form/MonthlyReport")
public class MonthlyReportController {

    @Autowired
    private MonthlyReportService monthlyReportService;

    /**
     * 获取月工作总结信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取月工作总结信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        MonthlyReportEntity entity = monthlyReportService.getInfo(id);
        MonthlyReportInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, MonthlyReportInfoVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 新建月工作总结
     *
     * @param monthlyReportForm 表单对象
     * @return
     */
    @ApiOperation("新建月工作总结")
    @PostMapping
    public ActionResult create(@RequestBody MonthlyReportForm monthlyReportForm) throws WorkFlowException {
        MonthlyReportEntity entity = JsonUtil.getJsonToBean(monthlyReportForm, MonthlyReportEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(monthlyReportForm.getStatus())) {
            monthlyReportService.save(entity.getId(), entity);
            return ActionResult.success("保存成功");
        }
        monthlyReportService.submit(entity.getId(), entity);
        return ActionResult.success("提交成功，请耐心等待");
    }

    /**
     * 修改月工作总结
     *
     * @param monthlyReportForm 表单对象
     * @param id                主键
     * @return
     */
    @ApiOperation("修改月工作总结")
    @PutMapping("/{id}")
    public ActionResult update(@RequestBody MonthlyReportForm monthlyReportForm, @PathVariable("id") String id) throws WorkFlowException {
        MonthlyReportEntity entity = JsonUtil.getJsonToBean(monthlyReportForm, MonthlyReportEntity.class);
        if (FlowStatusEnum.save.getMessage().equals(monthlyReportForm.getStatus())) {
            monthlyReportService.save(id, entity);
            return ActionResult.success("保存成功");
        }
        monthlyReportService.submit(id, entity);
        return ActionResult.success("提交成功，请耐心等待");
    }
}
