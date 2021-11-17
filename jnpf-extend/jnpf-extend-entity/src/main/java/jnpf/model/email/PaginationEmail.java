package jnpf.model.email;

import jnpf.base.PaginationTime;
import lombok.Data;

@Data
public class PaginationEmail extends PaginationTime {
    private String type;
}
