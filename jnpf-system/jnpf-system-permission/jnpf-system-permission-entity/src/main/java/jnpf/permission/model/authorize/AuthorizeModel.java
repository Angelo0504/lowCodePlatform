package jnpf.permission.model.authorize;

import jnpf.util.treeutil.SumTree;
import lombok.Data;


@Data
public class AuthorizeModel extends SumTree {
    private String id;
    private String fullName;
    private String icon;
}
