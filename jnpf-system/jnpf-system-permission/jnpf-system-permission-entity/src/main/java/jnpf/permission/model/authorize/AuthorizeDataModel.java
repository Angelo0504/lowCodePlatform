package jnpf.permission.model.authorize;

import jnpf.util.treeutil.SumTree;
import lombok.Data;

@Data
public class AuthorizeDataModel extends SumTree {
    private  String id;
    private String fullName;
    private String icon;
    private Boolean showcheck;
    private Integer checkstate;
    private String title;
    private String moduleId;
    private String type;
    private Long sortCode=9999L;
}
