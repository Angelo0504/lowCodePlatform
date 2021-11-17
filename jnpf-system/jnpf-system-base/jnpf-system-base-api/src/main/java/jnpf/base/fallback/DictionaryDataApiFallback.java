package jnpf.base.fallback;

import jnpf.base.DictionaryDataApi;
import jnpf.base.ActionResult;
import jnpf.base.entity.DictionaryDataEntity;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * 调用数据字典Api降级处理
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@Component
public class DictionaryDataApiFallback implements DictionaryDataApi {
    @Override
    public ActionResult<List<DictionaryDataEntity>> getList(String dictionary) {
        return null;
    }

    @Override
    public ActionResult<List<DictionaryDataEntity>> getListAll() {
        return null;
    }

    @Override
    public ActionResult<DictionaryDataEntity> getInfo(String id) {
        return null;
    }
}
