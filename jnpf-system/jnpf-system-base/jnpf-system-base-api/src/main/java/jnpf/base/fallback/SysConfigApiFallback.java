package jnpf.base.fallback;

import jnpf.base.SysConfigApi;
import jnpf.model.BaseSystemInfo;
import jnpf.base.entity.SysConfigEntity;
import jnpf.base.model.mp.MPSavaModel;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * 调用系统配置Api降级处理
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@Component
public class SysConfigApiFallback implements SysConfigApi {

    @Override
    public BaseSystemInfo getSysInfo(String tenantId, String dbName) {
        return null;
    }

    @Override
    public List<SysConfigEntity> getSysInfo(String type) {
        return null;
    }

    @Override
    public BaseSystemInfo getWeChatInfo() {
        return null;
    }

    @Override
    public boolean saveMp(MPSavaModel mpSavaModel) {
        return false;
    }
}
