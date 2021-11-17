package jnpf.base;

import jnpf.base.fallback.UserOnlineApiFallback;
import jnpf.base.model.UserOnlineModel;
import jnpf.utils.FeignName;
import jnpf.base.ActionResult;
import jnpf.base.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
/**
 * 调用在线用户Api
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@FeignClient(name = FeignName.SYSTEM_SERVER_NAME , fallback = UserOnlineApiFallback.class, path = "/Permission/OnlineUser")
public interface UserOnlineApi {


    /**
     * 查询所有在线用户
     *
     * @param page
     * @return
     */
    @GetMapping("/getList")
    ActionResult<List<UserOnlineModel>> getList(Page page);
}
