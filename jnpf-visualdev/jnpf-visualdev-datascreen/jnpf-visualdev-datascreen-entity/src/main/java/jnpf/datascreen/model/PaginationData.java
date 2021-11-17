package jnpf.datascreen.model;

import jnpf.base.Pagination;
import lombok.Data;

@Data
public class PaginationData extends Pagination {
    private String categoryId;
}
