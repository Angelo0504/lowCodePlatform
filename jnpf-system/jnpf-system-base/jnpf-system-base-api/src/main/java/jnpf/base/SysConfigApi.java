package jnpf.base;

import jnpf.model.BaseSystemInfo;
import jnpf.base.entity.SysConfigEntity;
import jnpf.base.fallback.SysConfigApiFallback;
import jnpf.base.model.mp.MPSavaModel;
import jnpf.utils.FeignName;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
/**
 * 调用系统配置Api
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@FeignClient(name = FeignName.SYSTEM_SERVER_NAME , fallback = SysConfigApiFallback.class, path = "/Base/SysConfig")
public interface SysConfigApi {

    /**
     * 获取BaseSystemInfo
     * @param tenantId
     * @param dbName
     * @return
     */
    @GetMapping("/getInfo/{tenantId}/{dbName}")
    BaseSystemInfo getSysInfo(@PathVariable("tenantId") String tenantId, @PathVariable("dbName") String dbName);

    /**
     * 获取微信配置列表
     * @param type
     * @return
     */
    @GetMapping("/getSysConfigInfo/{type}")
    List<SysConfigEntity> getSysInfo(@PathVariable("type") String type);

    /**
     * 获取微信配置列表
     * @return
     */
    @GetMapping("/getWeChatInfo")
    BaseSystemInfo getWeChatInfo();

    /**
     * 保存公众号配置
     * @param mpSavaModel
     * @return
     */
    @PostMapping("/SaveMp")
    boolean saveMp(@RequestBody MPSavaModel mpSavaModel);


}
