package jnpf.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司
 * @date 2021/3/16 10:51
 */
@Slf4j
public class IpUtil {

    /**
     * 检测ip信息的网站
     */
    private final static String IP_URL = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query={ip}&resource_id=6006";
    /**
     * IP的正则
     */
    private static Pattern pattern = Pattern
            .compile("(1\\d{1,2}|2[0-4]\\d|25[0-5]|\\d{1,2})\\."
                    + "(1\\d{1,2}|2[0-4]\\d|25[0-5]|\\d{1,2})\\."
                    + "(1\\d{1,2}|2[0-4]\\d|25[0-5]|\\d{1,2})\\."
                    + "(1\\d{1,2}|2[0-4]\\d|25[0-5]|\\d{1,2})");

    /**
     * 内网IP
     *
     * @return
     */
    private static List<Pattern> ipFilterRegexList = new ArrayList<>();
    static {
        Set<String> ipFilter = new HashSet<String>();
        ipFilter.add("^10\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[0-9])"
                + "\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[0-9])" + "\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[0-9])$");
        // B类地址范围: 172.16.0.0---172.31.255.255
        ipFilter.add("^172\\.(1[6789]|2[0-9]|3[01])\\" + ".(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[0-9])\\"
                + ".(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[0-9])$");
        // C类地址范围: 192.168.0.0---192.168.255.255
        ipFilter.add("^192\\.168\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[0-9])\\"
                + ".(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[0-9])$");
        ipFilter.add("127.0.0.1");
        ipFilter.add("0.0.0.0");
        ipFilter.add("localhost");
        for (String tmp : ipFilter) {
            ipFilterRegexList.add(Pattern.compile(tmp));
        }
    }

    public static String getIpAddr() {
        HttpServletRequest request = ServletUtil.getRequest();
        String xIp = request.getHeader("X-Real-IP");
        String xFor = request.getHeader("X-Forwarded-For");
        if (StringUtil.isNotEmpty(xFor) && !"unKnown".equalsIgnoreCase(xFor)) {
            int index = xFor.indexOf(",");
            if (index != -1) {
                return xFor.substring(0, index);
            } else {
                return xFor;
            }
        }
        xFor = xIp;
        if (StringUtil.isNotEmpty(xFor) && !"unKnown".equalsIgnoreCase(xFor)) {
            return xFor;
        }
        if (StringUtil.isBlank(xFor) || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtil.isBlank(xFor) || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtil.isBlank(xFor) || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtil.isBlank(xFor) || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtil.isBlank(xFor) || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getRemoteAddr();
        }
        String ip = "0:0:0:0:0:0:0:1".equals(xFor) ? "127.0.0.1" : xFor;
        return ip;
    }

    /**
     * 检查IP是否合法
     *
     * @param ip
     * @return
     */
    public static boolean isValid(String ip) {
        Matcher m = pattern.matcher(ip);
        return m.matches();
    }

    /**
     * 获取ip信息
     */
    private static JSONObject getIpInfo(String ip) {
        JSONObject data = null;
        if (!ipIsInner(ip)) {
            long begin = System.currentTimeMillis();
            try {
                String ipUrl = IP_URL.replace("{ip}", ip);
                URL url = new URL(ipUrl);
                HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
                httpUrlConnection.setRequestMethod("GET");
                httpUrlConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
                httpUrlConnection.setDoInput(true);
                httpUrlConnection.setDoOutput(true);
                httpUrlConnection.setReadTimeout(5000);
                InputStream inputStream = httpUrlConnection.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] b = new byte[1024];
                int len = 0;
                while (true) {
                    len = inputStream.read(b);
                    if (len == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(b, 0, len);
                }
                long end = System.currentTimeMillis() - begin;
                byte[] lens = byteArrayOutputStream.toByteArray();
                String result = new String(lens, "GBK");
                data = JSONObject.parseObject(result);
                if (null == data) {
                    return data;
                }
                data = JSONObject.parseObject(data.getJSONArray("data").get(0).toString());
            } catch (Exception e) {
                log.error("ip信息获取失败，请检查ip接口工具IPUtil。");
            }
        }

        return data;
    }

    /**
     * 获取ip所在的城市和宽带属于哪一家
     */
    public static String getIpCity(String ip) {
        String ipInfo = null;
        if (ip != null) {
            JSONObject[] data = {null};
            Callable task = new Callable<JSONObject>() {
                @Override
                public JSONObject call() throws Exception {
                    data[0] = getIpInfo(ip);
                    return data[0];
                }
            };
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Future<String> future = executorService.submit(task);
            try {
                //设置超时时间
                String rst = future.get(5, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                log.error("ip信息获取超时");
            } catch (Exception e) {
                log.error("ip信息获取错误:" + e.getMessage());
            } finally {
                executorService.shutdown();
            }
            if (null == data[0]) {
                return ipInfo;
            }
            ipInfo = data[0].getString("location");
            return ipInfo;
        }
        return ipInfo;
    }

    /**
     * 判断IP是否内网IP
     * @Title: ipIsInner
     * @param ip
     * @return: boolean
     */
    public static boolean ipIsInner(String ip) {
        boolean isInnerIp = false;
        for (Pattern tmp : ipFilterRegexList) {
            Matcher matcher = tmp.matcher(ip);
            if (matcher.find()) {
                isInnerIp = true;
                break;
            }
        }
        return isInnerIp;
    }
}
