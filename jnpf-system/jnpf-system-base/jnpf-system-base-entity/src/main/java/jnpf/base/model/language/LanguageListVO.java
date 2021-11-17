package jnpf.base.model.language;

import jnpf.base.Pagination;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LanguageListVO {

    private Pagination pagination;

    private List<LanguageListDTO> list;
}
