package jnpf.base.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.base.model.monitor.MonitorListVO;
import jnpf.util.JsonUtil;
import jnpf.base.util.MonitorUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 系统监控
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Api(tags = "系统监控", value = "Monitor")
@RestController
@RequestMapping("/Base/Monitor")
public class MonitorController {

    /**
     * 系统监控
     *
     * @param
     * @return
     */
    @ApiOperation("系统监控")
    @GetMapping
    public ActionResult list() {
        MonitorUtil monitorUtil = new MonitorUtil();
        MonitorListVO vo = JsonUtil.getJsonToBean(monitorUtil, MonitorListVO.class);
        vo.setTime(System.currentTimeMillis());
        return ActionResult.success(vo);
    }
}
