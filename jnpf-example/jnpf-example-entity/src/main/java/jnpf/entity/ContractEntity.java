package jnpf.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *
 * Contract
 * 版本： V3.0.0
 * 版权： 引迈信息技术有限公司(https://www.jnpfsoft.com)
 * 作者： JNPF开发平台组
 * 日期： 2020-12-31
 */
@Data
@TableName("test_contract")
public class ContractEntity  {

    @TableId("F_ID")
    private String id;

    @TableField("F_CONTRACTNAME")
    private String contractName;


    @TableField("F_MYTELEPHONE")
    private String mytelePhone;

    @TableField("F_FILEJSON")
    private String fileJson;

}
