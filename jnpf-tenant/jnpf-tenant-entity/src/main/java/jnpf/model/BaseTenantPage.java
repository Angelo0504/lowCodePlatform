package jnpf.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * BaseTenantPage
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@Data
public class BaseTenantPage {

    private String startTime;
    private String endTime;
    private String keyword;
    private long pageSize=20;
    @ApiModelProperty(hidden = true)
    private String sort="desc";
    @ApiModelProperty(hidden = true)
    private String sidx="";
    private long currentPage=1;
    @ApiModelProperty(hidden = true)
    private long total;

    public <T> List<T> setData(List<T> data, long records) {
        this.total = records;
        return data;
    }

}
