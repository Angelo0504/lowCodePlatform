package jnpf.message.model;

import jnpf.base.Pagination;
import lombok.Data;

@Data
public class PaginationMessage extends Pagination {
    private String type;
}
