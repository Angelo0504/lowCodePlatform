package jnpf.portal.model;

import com.alibaba.fastjson.annotation.JSONField;
import jnpf.util.treeutil.SumTree;
import lombok.Data;


@Data
public class PortalSelectModel extends SumTree {
    private String fullName;
    @JSONField(name="category")
    private String  parentId;
}
