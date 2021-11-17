package jnpf.onlinedev.model;


import jnpf.base.Pagination;
import lombok.Data;

@Data
public class PaginationModelExport extends Pagination {
    private String selectKey;
    private String json;
    private String dataType;
}
