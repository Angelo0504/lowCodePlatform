package jnpf.permission.model.user;

import jnpf.base.Pagination;
import lombok.Data;

@Data
public class PaginationUser extends Pagination {
    private String organizeId;
}
