package jnpf.permission.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jnpf.base.model.button.ButtonModel;
import jnpf.base.model.column.ColumnModel;
import jnpf.base.model.module.ModuleModel;
import jnpf.base.model.resource.ResourceModel;
import jnpf.permission.entity.AuthorizeEntity;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作权限
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月26日 上午9:18
 */
public interface AuthorizeMapper extends BaseMapper<AuthorizeEntity> {

    List<ModuleModel> findModule(@Param("objectId") String objectId);

    List<ButtonModel> findButton(@Param("objectId") String objectId);

    List<ColumnModel> findColumn(@Param("objectId") String objectId);

    List<ResourceModel> findResource(@Param("objectId") String objectId);

    List<ModuleModel> findModuleAdmin(@Param("mark") String mark);

    List<ButtonModel> findButtonAdmin(@Param("mark") String mark);

    List<ColumnModel> findColumnAdmin(@Param("mark") String mark);

    List<ResourceModel> findResourceAdmin(@Param("mark") String mark);

    void saveBatch(@Param("values") String values);

    void savaBatchList(@Param("list") List<AuthorizeEntity> list);
}
