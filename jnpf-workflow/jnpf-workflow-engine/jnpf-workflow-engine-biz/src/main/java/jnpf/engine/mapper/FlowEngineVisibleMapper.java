package jnpf.engine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jnpf.engine.entity.FlowEngineVisibleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程可见
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
public interface FlowEngineVisibleMapper extends BaseMapper<FlowEngineVisibleEntity> {

    /**
     * 部分看见(岗位和用户)
     * @param sql sql语句
     * @return
     */
    List<FlowEngineVisibleEntity> getVisibleFlowList(@Param("sql") String sql);
}
