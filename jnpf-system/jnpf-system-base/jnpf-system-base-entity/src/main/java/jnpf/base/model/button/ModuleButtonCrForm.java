package jnpf.base.model.button;

import lombok.Data;

/**
 * @author 引迈
 */
@Data
public class ModuleButtonCrForm {
    private String enCode;

    private Integer enabledMark;

    private String icon;

    private String fullName;

    private String description;

    private String parentId;
    private String moduleId;
    private Long sortCode;
}
