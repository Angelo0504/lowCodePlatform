package jnpf.file.fallback;

import jnpf.file.FileApi;
import org.springframework.stereotype.Component;
/**
 * 降级处理
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@Component
public class FileApiFallback implements FileApi {
    @Override
    public String getPath(String type) {
        return null;
    }
}
