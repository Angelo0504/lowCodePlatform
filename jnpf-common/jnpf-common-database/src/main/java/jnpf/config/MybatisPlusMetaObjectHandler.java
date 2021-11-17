package jnpf.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import jnpf.base.UserInfo;
import jnpf.util.DateUtil;
import jnpf.util.context.SpringContext;
import jnpf.util.UserProvider;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2020年12月22日 下午20:14
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {


    private UserProvider userProvider;

    @Override
    public void insertFill(MetaObject metaObject) {
        userProvider = SpringContext.getBean(UserProvider.class);
        UserInfo userInfo= userProvider.get();
        Object enabledMark = this.getFieldValByName("enabledMark", metaObject);
        Object creatorUserId = this.getFieldValByName("creatorUserId", metaObject);
        Object creatorTime = this.getFieldValByName("creatorTime", metaObject);
        Object creatorUser = this.getFieldValByName("creatorUser", metaObject);
        if (enabledMark == null) {
            this.setFieldValByName("enabledMark", 1, metaObject);
        }
        if (creatorUserId == null) {
            this.setFieldValByName("creatorUserId", userInfo.getUserId(), metaObject);
        }
        if (creatorTime == null) {
            this.setFieldValByName("creatorTime", DateUtil.getNowDate(), metaObject);
        }
        if (creatorUser == null) {
            this.setFieldValByName("creatorUser", userInfo.getUserId(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        userProvider = SpringContext.getBean(UserProvider.class);
        UserInfo userInfo = userProvider.get();
        this.setFieldValByName("lastModifyTime", new Date(), metaObject);
        this.setFieldValByName("lastModifyUserId", userInfo.getUserId(), metaObject);
        this.setFieldValByName("lastModifyUser", userInfo.getUserId(), metaObject);

    }


}
