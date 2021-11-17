package jnpf.file;

import jnpf.file.fallback.FileApiFallback;
import jnpf.utils.FeignName;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 通过api调用文件路径
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@FeignClient(name = FeignName.FILE_SERVER_NAME, fallback = FileApiFallback.class)
public interface FileApi {
    /**
     * 通过type获取路径
     *
     * @param type 类型
     * @return
     */
    @GetMapping("/getPath/{type}")
    String getPath(@PathVariable("type") String type);
}
