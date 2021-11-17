package jnpf.message.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jnpf.message.entity.MessageEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 消息实例
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
public interface MessageMapper extends BaseMapper<MessageEntity> {

    List<MessageEntity> getMessageList(@Param("map") Map<String, String> map);

    int getUnreadNoticeCount(@Param("userId") String userId);

    int getUnreadMessageCount(@Param("userId") String userId);

    List<MessageEntity> getInfoDefault(@Param("type") int type);
}
