package jnpf.base.model.module;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class MenuListVO {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "是否有下级菜单")
    private boolean hasChildren;
    @ApiModelProperty(value = "上级ID")
    private String parentId;
    @ApiModelProperty(value = "状态")
    private Integer enabledMark;
    @ApiModelProperty(value = "菜单名称")
    private String fullName;
    @ApiModelProperty(value = " 图标")
    private String icon;
    @ApiModelProperty(value = "链接地址")
    private String urlAddress;
    @ApiModelProperty(value = "菜单类型",example = "1")
    private Integer type;
    @ApiModelProperty(value = "下级菜单列表")
    private List<MenuListVO> children;
    private Integer isButtonAuthorize;
    private Integer isColumnAuthorize;
    private Integer isDataAuthorize;
    private Long sortCode;

}
