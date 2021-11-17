package jnpf.permission.fallback;

import jnpf.base.ActionResult;
import jnpf.permission.PositionApi;
import jnpf.permission.entity.PositionEntity;
import jnpf.permission.model.position.PositionInfoVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 获取岗位信息Api降级处理
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@Component
public class PositionApiFallback implements PositionApi {

    @Override
    public ActionResult<List<PositionEntity>> getListAll() {
        return null;
    }

    @Override
    public ActionResult<PositionInfoVO> getInfo(String id) {
        return null;
    }

}
