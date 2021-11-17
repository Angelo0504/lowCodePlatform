package jnpf.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司
 * @date 2021/3/16 10:51
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DownloadVO {
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "请求接口")
    private String url;
}
