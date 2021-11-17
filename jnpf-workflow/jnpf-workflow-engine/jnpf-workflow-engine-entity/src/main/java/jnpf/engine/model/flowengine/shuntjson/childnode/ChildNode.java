package jnpf.engine.model.flowengine.shuntjson.childnode;

import lombok.Data;

import java.util.List;

/**
 * 解析引擎
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021/3/15 9:12
 */
@Data
public class ChildNode {
    private String type;
    private String content;
    private Properties properties;
    private String nodeId;
    private String prevId;
    private ChildNode childNode;
    private String conditionType;
    private List<ChildNode> conditionNodes;
    private Boolean isInterflow;

}
