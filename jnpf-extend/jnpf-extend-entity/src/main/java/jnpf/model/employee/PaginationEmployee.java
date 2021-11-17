package jnpf.model.employee;

import jnpf.base.PaginationTime;
import lombok.Data;

@Data
public class PaginationEmployee extends PaginationTime {
    private String condition;
}
