package jnpf.base.model.Template7;


import jnpf.onlinedev.model.fields.FieLdsModel;
import lombok.Data;

import java.util.List;

@Data
public class MastModel {

    //主表的属性
    private List<FieLdsModel> mastList;
    //系统自带的赋值
    private List<KeyModel> keyMastList;
}
