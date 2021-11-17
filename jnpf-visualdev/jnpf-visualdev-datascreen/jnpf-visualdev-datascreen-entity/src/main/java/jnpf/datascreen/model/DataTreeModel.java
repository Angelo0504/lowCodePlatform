package jnpf.datascreen.model;

import jnpf.util.treeutil.SumTree;
import lombok.Data;

@Data
public class DataTreeModel extends SumTree {
    private String fullName;
}
