package jnpf.white;

import java.util.ArrayList;
import java.util.List;

/**
 * 放行的url
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
public class GatewayWhite {

    public List<String> whiteUrl = new ArrayList<>();

    public List<String> getWhiteUrl() {
        excludePath();
        return whiteUrl;
    }

    private void excludePath() {
        List<String> list = new ArrayList<>();

        //oauth
        list.add("/oauth/Login");
        list.add("/oauth/RSA/publicKey");

        //websocket
        list.add("/api/system/Message/websocket");
        //大屏图片
        list.add("/api/visualdev/DataScreen/**");
        //数据地图
        list.add("/api/system/Base/DataMap/**");
        //代码下载接口
        list.add("/api/visualdev/Generater/DownloadVisCode");
        //多租户
        list.add("/api/tenant");
        list.add("/api/tenant/**");

        //file模块不拦截
        list.add("/api/file/**");

        //swagger3
        list.add("/*/v2/api-docs");
        list.add("/csrf");
        whiteUrl = list;
    }
}
