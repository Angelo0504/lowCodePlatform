package jnpf.message;

import jnpf.message.fallback.NoticeApiFallback;
import jnpf.message.entity.MessageEntity;
import jnpf.message.model.SentMessageModel;
import jnpf.utils.FeignName;
import jnpf.base.ActionResult;
import jnpf.message.model.MessageFlowForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
/**
 * 调用系统消息Api
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@FeignClient(name = FeignName.SYSTEM_SERVER_NAME, fallback = NoticeApiFallback.class, path = "/Message")
public interface NoticeApi {

    /**
     * 工作流发送消息
     * @param messageFlowForm
     * @return
     */
    @GetMapping("/flow/sentMessage")
    ActionResult<String> sentMessage(MessageFlowForm messageFlowForm);

    /**
     * 列表（通知公告）
     *
     * @param
     * @return
     */
    @GetMapping("/GetNoticeList")
    List<MessageEntity> getNoticeList();

    /**
     * 发送消息
     * @param sentMessageModel
     * @return
     */
    @PostMapping("/SentMessage")
    void sentMessage(@RequestBody SentMessageModel sentMessageModel);
}
