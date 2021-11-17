package jnpf.message.fallback;

import jnpf.base.ActionResult;
import jnpf.message.NoticeApi;
import jnpf.message.entity.MessageEntity;
import jnpf.message.model.MessageFlowForm;
import jnpf.message.model.SentMessageModel;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * 调用系统消息Api降级处理
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@Component
public class NoticeApiFallback implements NoticeApi {


    @Override
    public ActionResult<String> sentMessage(MessageFlowForm messageFlowForm) {
        return null;
    }

    @Override
    public List<MessageEntity> getNoticeList() {
        return null;
    }

    @Override
    public void sentMessage(SentMessageModel sentMessageModel) {

    }
}

