package jnpf.base.fallback;

import jnpf.base.BillRuleApi;
import jnpf.base.ActionResult;
import org.springframework.stereotype.Component;

/**
 * 获取单据规则Api降级处理
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@Component
public class BillRuleApiFallback implements BillRuleApi {

    @Override
    public ActionResult useBillNumber(String enCode) {
        return null;
    }

    @Override
    public ActionResult<String> getBillNumber(String enCode) {
        return null;
    }
}
