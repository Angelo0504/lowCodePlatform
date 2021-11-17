package jnpf.base.fallback;

import jnpf.base.AreaApi;
import jnpf.base.ActionResult;
import jnpf.base.entity.ProvinceEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 获取行政区划降级处理
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@Component
public class AreaApiFallback implements AreaApi {
    @Override
    public ActionResult<List<ProvinceEntity>> getList(String id) {
        return null;
    }
}
