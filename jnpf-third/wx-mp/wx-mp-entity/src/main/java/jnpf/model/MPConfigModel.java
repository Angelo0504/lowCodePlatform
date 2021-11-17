package jnpf.model;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

public class MPConfigModel {
    @NotBlank(message = "必填")
    @ApiModelProperty(value = "公众号App")
    private String wx_GZH_APPID;
    @NotBlank(message = "必填")
    @ApiModelProperty(value = "公众号A应用凭证")
    private String wx_GZH_APPSECRET;
    @NotBlank(message = "必填")
    @ApiModelProperty(value = "公众号服务器地址")
    private String wx_GZH_URL;
    @NotBlank(message = "必填")
    @ApiModelProperty(value = "令牌token")
    private String wx_GZH_TOKEN;
}
