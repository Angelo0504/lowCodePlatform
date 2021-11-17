package jnpf.model.projectgantt;

import jnpf.util.treeutil.SumTree;
import lombok.Data;

@Data
public class ProjectGanttTaskTreeModel extends SumTree {
    private String fullName;
}
