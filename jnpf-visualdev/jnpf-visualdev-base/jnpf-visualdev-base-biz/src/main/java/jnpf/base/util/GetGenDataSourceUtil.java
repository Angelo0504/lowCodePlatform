package jnpf.base.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import jnpf.base.UserInfo;
import jnpf.base.entity.DbLinkEntity;
import jnpf.base.service.DblinkService;
import jnpf.permission.service.UserService;
import jnpf.util.DataSourceUtil;
import jnpf.util.UserProvider;
import jnpf.util.context.SpringContext;

/**
 * 可视化开发功能表
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-04-07
 */

public class GetGenDataSourceUtil {
    private static DblinkService dblinkService;
    private static UserProvider userProvider;
    private static DataSourceUtil dataSourceUtil;

    public static DataSourceConfig getGenDataSource(String dataSourceId){

        // 数据源配置
        SourceUtil sourceUtil = new SourceUtil();

        dblinkService = SpringContext.getBean(DblinkService.class);
        DataSourceConfig dsc;
        DbLinkEntity linkEntity = dblinkService.getInfo(dataSourceId);
        if (linkEntity != null) {
            return sourceUtil.dbConfig(linkEntity);
        }

        userProvider=SpringContext.getBean(UserProvider.class);
        dataSourceUtil=SpringContext.getBean(DataSourceUtil.class);
        UserInfo userInfo=userProvider.get();
        dsc = sourceUtil.dbConfig(userInfo.getTenantDbConnectionString());
        if (dataSourceUtil.getUrl().contains(DbType.ORACLE.getDb())) {
            String schema = dataSourceUtil.getUserName();
            //oracle 默认 schema=username
            dsc.setSchemaName(schema.toUpperCase());
        }
        return dsc;
    }

}
