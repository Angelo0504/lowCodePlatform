package jnpf.base.model.dictionarydata;

import jnpf.util.treeutil.SumTree;
import lombok.Data;

@Data
public class DictionaryDataModel extends SumTree {
    private String  id;
    private String parentId;
    private String  fullName;
    private String  enCode;
    private Integer  enabledMark;
    private String icon;
    private long sortCode;
}
