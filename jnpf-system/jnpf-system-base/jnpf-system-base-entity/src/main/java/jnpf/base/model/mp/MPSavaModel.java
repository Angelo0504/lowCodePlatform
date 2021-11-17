package jnpf.base.model.mp;

import jnpf.base.entity.SysConfigEntity;
import lombok.Data;

import java.util.List;

@Data
public class MPSavaModel {
    private List<SysConfigEntity> entitys;

    public MPSavaModel(List<SysConfigEntity> entitys) {
        this.entitys = entitys;
    }
}
