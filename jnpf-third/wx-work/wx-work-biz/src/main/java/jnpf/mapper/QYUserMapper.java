package jnpf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jnpf.QYUserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 企业号用户
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
public interface QYUserMapper extends BaseMapper<QYUserEntity> {

    /**
     * 获取列表
     * @param orderBy
     * @return
     */
    List<QYUserEntity> getList(@Param("orderBy") String orderBy);
}
