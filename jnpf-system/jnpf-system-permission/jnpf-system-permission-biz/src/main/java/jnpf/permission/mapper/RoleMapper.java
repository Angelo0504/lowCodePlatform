package jnpf.permission.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jnpf.permission.entity.RoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统角色
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月26日 上午9:18
 */
public interface RoleMapper extends BaseMapper<RoleEntity> {

    List<RoleEntity> getListByUserId(@Param("userId") String userId);
}
