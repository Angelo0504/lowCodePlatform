package jnpf.base.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.base.Page;
import jnpf.base.model.UserOnlineModel;
import jnpf.base.model.UserOnlineVO;
import jnpf.base.service.UserOnlineService;
import jnpf.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 在线用户
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月26日 上午9:18
 */
@Api(tags = "在线用户", value = "Online")
@RestController
@RequestMapping("/Base/OnlineUser")
public class OnlineUserController {

    @Autowired
    private UserOnlineService userOnlineService;

    /**
     * 列表
     *
     * @param page
     * @return
     */
    @ApiOperation("获取在线用户列表")
    @GetMapping
    public ActionResult list(Page page) {
        List<UserOnlineModel> data = userOnlineService.getList(page);
        List<UserOnlineVO> vo= JsonUtil.getJsonToList(data,UserOnlineVO.class);
        return ActionResult.success(vo);
    }

    /**
     * 注销
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("强制下线")
    @DeleteMapping("/{id}")
    public ActionResult delete(@PathVariable("id") String id) {
        userOnlineService.delete(id);
        return ActionResult.success("操作成功");
    }

    /**
     * 获取所有在线用户
     * @param page
     * @return
     */
    @GetMapping("/getList")
    public ActionResult getList(Page page) {
        List<UserOnlineModel> list = userOnlineService.getList(page);
        return ActionResult.success(list);
    }
}
