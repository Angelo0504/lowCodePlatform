package jnpf.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司
 * @date 2021/3/16 10:51
 */
@Data
public class Pagination extends Page{
    private long pageSize=20;
    private String sort="desc";
    private String sidx="";
    private long currentPage=1;

    @JsonIgnore
    private long total;
    @JsonIgnore
    private long records;

    public <T> List<T> setData(List<T> data, long records) {
        this.total = records;
        return data;
    }
}
