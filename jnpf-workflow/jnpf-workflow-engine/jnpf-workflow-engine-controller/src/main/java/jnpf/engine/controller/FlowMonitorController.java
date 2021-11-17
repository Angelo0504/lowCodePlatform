package jnpf.engine.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.base.vo.PaginationVO;
import jnpf.engine.entity.FlowEngineEntity;
import jnpf.engine.entity.FlowTaskEntity;
import jnpf.engine.model.flowmonitor.FlowMonitorListVO;
import jnpf.engine.model.flowtask.FlowDeleteModel;
import jnpf.engine.model.flowtask.PaginationFlowTask;
import jnpf.engine.service.FlowEngineService;
import jnpf.engine.service.FlowTaskNodeService;
import jnpf.engine.service.FlowTaskService;
import jnpf.permission.UsersApi;
import jnpf.permission.model.user.UserAllModel;
import jnpf.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * 流程监控
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Api(tags = "流程监控", value = "FlowMonitor")
@RestController
@RequestMapping("/Engine/FlowMonitor")
public class FlowMonitorController {

    @Autowired
    private FlowEngineService flowEngineService;
    @Autowired
    private FlowTaskService flowTaskService;
    @Autowired
    private UsersApi usersApi;

    /**
     * 获取流程监控列表
     *
     * @param paginationFlowTask
     * @return
     */
    @ApiOperation("获取流程监控列表")
    @GetMapping
    public ActionResult list(PaginationFlowTask paginationFlowTask) {
        List<FlowEngineEntity> engineList = flowEngineService.getList();
        List<FlowTaskEntity> list = flowTaskService.getMonitorList(paginationFlowTask);
        List<UserAllModel> userList = usersApi.getAll().getData();
        List<FlowMonitorListVO> listVO = new LinkedList<>();
        for (FlowTaskEntity taskEntity : list) {
            //用户名称赋值
            FlowMonitorListVO vo = JsonUtil.getJsonToBean(taskEntity, FlowMonitorListVO.class);
            UserAllModel user = userList.stream().filter(t -> t.getId().equals(taskEntity.getCreatorUserId())).findFirst().orElse(null);
            if (user != null) {
                vo.setUserName(user.getRealName() + "/" + user.getAccount());
            } else {
                vo.setUserName("");
            }
            FlowEngineEntity engine = engineList.stream().filter(t -> t.getId().equals(taskEntity.getFlowId())).findFirst().orElse(null);
            if (engine != null) {
                vo.setFormData(engine.getFormData());
                vo.setFormType(engine.getFormType());
                listVO.add(vo);
            }
        }
        PaginationVO paginationVO = JsonUtil.getJsonToBean(paginationFlowTask, PaginationVO.class);
        return ActionResult.page(listVO, paginationVO);
    }

    /**
     * 批量删除流程监控
     *
     * @param deleteModel 主键
     * @return
     */
    @ApiOperation("批量删除流程监控")
    @DeleteMapping
    public ActionResult delete(@RequestBody FlowDeleteModel deleteModel) {
        String[] taskId = deleteModel.getIds().split(",");
        flowTaskService.delete(taskId);
        return ActionResult.success("删除成功");
    }

}
