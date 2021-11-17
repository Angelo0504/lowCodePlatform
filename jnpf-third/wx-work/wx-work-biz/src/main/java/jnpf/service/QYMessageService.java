package jnpf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jnpf.exception.WxErrorException;
import jnpf.model.qymessage.PaginationQYMessage;
import jnpf.QYMessageEntity;

import java.util.List;

/**
 * 消息发送
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
public interface QYMessageService extends IService<QYMessageEntity> {

    /**
     * 列表
     *
     * @param paginationQyMessage
     * @return
     */
    List<QYMessageEntity> getList(PaginationQYMessage paginationQyMessage);

    /**
     * 发送
     *
     * @param entity
     */
    void sent(QYMessageEntity entity) throws WxErrorException;
}
