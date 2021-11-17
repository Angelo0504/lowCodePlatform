package jnpf.base.model.dictionarytype;

import jnpf.util.treeutil.SumTree;
import lombok.Data;

@Data
public class DictionaryTypeModel extends SumTree {
    private String id;
    private String parentId;
    private String fullName;
    private Integer isTree;
    private String enCode;
    private long sortCode;
}
