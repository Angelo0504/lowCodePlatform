package jnpf.model.projectgantt;

import jnpf.util.treeutil.SumTree;
import lombok.Data;

@Data
public class ProjectGanttTreeModel extends SumTree {

    private Integer schedule;

    private String fullName;

    private long startTime;

    private long endTime;

    private String signColor;
    private String sign;
}
