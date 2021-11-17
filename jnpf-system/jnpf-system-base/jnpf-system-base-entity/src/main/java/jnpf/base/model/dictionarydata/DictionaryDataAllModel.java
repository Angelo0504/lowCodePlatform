package jnpf.base.model.dictionarydata;

import jnpf.util.treeutil.SumTree;
import lombok.Data;

@Data
public class DictionaryDataAllModel extends SumTree {
    private String  fullName;
    private String  enCode;
}
