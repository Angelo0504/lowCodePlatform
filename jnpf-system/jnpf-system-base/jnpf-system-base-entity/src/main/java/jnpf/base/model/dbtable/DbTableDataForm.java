package jnpf.base.model.dbtable;


import jnpf.base.Pagination;
import lombok.Data;

@Data
public class DbTableDataForm extends Pagination {
     private String field;
}
