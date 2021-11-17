package jnpf.base.service;


import jnpf.base.Page;
import jnpf.base.model.UserOnlineModel;

import java.util.List;

/**
 * 在线用户
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月26日 上午9:18
 */
public interface UserOnlineService {

    /**
     * 列表
     *
     * @return
     */
    List<UserOnlineModel> getList(Page page);

    /**
     * 删除
     *
     * @param id 主键值
     */
    void delete(String id);
}
