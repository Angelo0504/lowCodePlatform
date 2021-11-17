package jnpf.base.model.datainterface;

import jnpf.util.treeutil.SumTree;
import lombok.Data;

@Data
public class DataInterfaceTreeModel extends SumTree {
//    private String id;
//    private String parentId;
    private String fullName;
    private String categoryId;
}
