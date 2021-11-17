package jnpf.model.document;

import jnpf.base.Page;
import lombok.Data;

@Data
public class PageDocument extends Page {
    private String parentId;
}
