package jnpf.permission.model.authorize;



import jnpf.base.model.button.ButtonModel;
import jnpf.base.model.column.ColumnModel;
import jnpf.base.model.module.ModuleModel;
import jnpf.base.model.resource.ResourceModel;
import lombok.Data;


import java.util.List;

/**
 * 权限集合模型
 */
@Data
public class AuthorizeVO {
    // 菜单
//    private List<MenuModel> menuList;
    // 功能
    private List<ModuleModel> moduleList;
    // 按钮
    private List<ButtonModel> buttonList;
    // 视图
    private List<ColumnModel> columnList;
    // 资源
    private List<ResourceModel> resourceList;

    public AuthorizeVO() {
    }

    public AuthorizeVO(List<ModuleModel> moduleList, List<ButtonModel> buttonList, List<ColumnModel> columnList, List<ResourceModel> resourceList){
//        this.menuList = menuList;
        this.moduleList = moduleList;
        this.buttonList = buttonList;
        this.columnList = columnList;
        this.resourceList = resourceList;
    }
}
