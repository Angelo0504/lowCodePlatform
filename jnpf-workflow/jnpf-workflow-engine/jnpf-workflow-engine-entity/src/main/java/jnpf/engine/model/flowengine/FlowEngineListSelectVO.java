package jnpf.engine.model.flowengine;

import lombok.Data;

import java.util.List;

/**
 *
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021/3/15 9:17
 */
@Data
public class FlowEngineListSelectVO {

    private String id;
    private String fullName;
    private Boolean hasChildren;
    private List<FlowEngineListSelectVO> children;
}
