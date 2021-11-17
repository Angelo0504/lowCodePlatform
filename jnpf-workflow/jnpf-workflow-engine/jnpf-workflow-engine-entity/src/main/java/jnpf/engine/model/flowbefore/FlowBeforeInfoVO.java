package jnpf.engine.model.flowbefore;

import jnpf.engine.model.flowengine.shuntjson.childnode.FormOperates;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021/3/15 9:18
 */
@Data
public class FlowBeforeInfoVO {
    private Long freeApprover;
    private FlowTaskEntityInfoModel flowTaskInfo;
    private List<FlowTaskNodeEntityInfoModel> flowTaskNodeList;
    private List<FlowTaskOperatorEntityInfoModel> flowTaskOperatorList;
    private List<FlowTaskOperatorRecordEntityInfoModel> flowTaskOperatorRecordList;
    private String flowFormInfo;
    private List<FormOperates> formOperates = new ArrayList<>();
}
