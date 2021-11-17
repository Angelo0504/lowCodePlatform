package jnpf.util.treeutil;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司
 * @date 2021/3/16 10:51
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SumTree<T> {
    private String id;
    private String parentId;
    private Boolean hasChildren;
    private List<SumTree<T>> children;
}
