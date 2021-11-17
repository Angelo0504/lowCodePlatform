package jnpf.model.employee;

import jnpf.model.EmployeeModel;
import lombok.Data;

import java.util.List;

/**
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司
 *
 */
@Data
public class EmployeeImportVO {
    /**
     * 导入成功条数
     */
    private int snum;
    /**
     * 导入失败条数
     */
    private int fnum;
    /**
     * 导入结果状态(0,成功  1，失败)
     */
    private int resultType;
    /**
     * 失败结果
     */
    private List<EmployeeModel> failResult;

}
