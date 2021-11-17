package jnpf.utils;

import lombok.Data;

/**
 * 日志分类
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司
 * @date 2021年3月13日 上午9:18
 */
@Data
public class LogWriteUtil {
    public static final String NOTWRITE = "/Base/Log/writeLogRequest";

    public static final String NOTWRITETWO = "/Base/SysConfig/getInfo";

    public static final String WRITELOG = "/Logout";

}
