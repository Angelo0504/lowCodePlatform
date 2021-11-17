package jnpf.base;

import jnpf.utils.FeignName;
import jnpf.base.fallback.BillRuleApiFallback;
import jnpf.base.ActionResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 获取单据规则Api
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@FeignClient(name = FeignName.SYSTEM_SERVER_NAME , fallback = BillRuleApiFallback.class, path = "/Base/BillRule")
public interface BillRuleApi {

    /**
     * 获取单据缓存
     * @param enCode
     * @return
     */
    @GetMapping("/useBillNumber/{enCode}")
    ActionResult useBillNumber(@PathVariable("enCode") String enCode);


    /**
     * 删除单据缓存
     * @param enCode
     * @return
     */
    @GetMapping("/getBillNumber/{enCode}")
    ActionResult<String> getBillNumber(@PathVariable("enCode") String enCode);

}
