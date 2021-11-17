package jnpf.base.fallback;

import jnpf.base.UserOnlineApi;
import jnpf.base.ActionResult;
import jnpf.base.Page;
import jnpf.base.model.UserOnlineModel;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * 调用在线用户Api降级处理
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@Component
public class UserOnlineApiFallback implements UserOnlineApi {
    @Override
    public ActionResult<List<UserOnlineModel>> getList(Page page) {
        return null;
    }
}
