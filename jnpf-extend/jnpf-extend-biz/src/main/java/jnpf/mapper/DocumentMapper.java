package jnpf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jnpf.entity.DocumentEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 知识文档
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月26日 上午9:18
 */
public interface DocumentMapper extends BaseMapper<DocumentEntity> {

    /**
     * 列表（共享给我）
     * @param userId
     * @return
     */
   List<DocumentEntity> getShareTomeList(@Param("userId") String userId);

    /**
     * 列表（全部下级数据）
     * @param folderId
     * @return
     */
    List<DocumentEntity> getChildList(@Param("folderId") String folderId);

    int trashRecovery(@Param("id") String id);
}
