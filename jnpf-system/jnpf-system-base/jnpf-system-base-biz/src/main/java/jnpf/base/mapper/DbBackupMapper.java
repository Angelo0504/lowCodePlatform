package jnpf.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jnpf.base.entity.DbBackupEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 数据备份
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
public interface DbBackupMapper extends BaseMapper<DbBackupEntity> {

    List<Map<String, Object>> getList(@Param("sql") String sql);

}
