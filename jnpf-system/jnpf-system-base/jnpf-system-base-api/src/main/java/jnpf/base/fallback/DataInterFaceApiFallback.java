package jnpf.base.fallback;

import jnpf.base.DataInterFaceApi;
import jnpf.base.ActionResult;
import org.springframework.stereotype.Component;
/**
 * 调用数据接口Api降级处理
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@Component
public class DataInterFaceApiFallback implements DataInterFaceApi {

    @Override
    public ActionResult infoToId(String id) {
        return null;
    }
}
