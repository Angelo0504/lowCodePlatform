package jnpf.base.model.datainterface;

import jnpf.base.Pagination;
import lombok.Data;

@Data
public class PaginationDataInterface extends Pagination {
    private String categoryId;
}
