package jnpf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jnpf.emnus.DbType;
import jnpf.mapper.BigDataMapper;
import jnpf.service.BigDataService;
import jnpf.entity.BigDataEntity;
import jnpf.util.DateUtil;
import jnpf.util.JdbcUtil;
import jnpf.util.RandomUtil;
import jnpf.base.Pagination;
import jnpf.exception.WorkFlowException;
import jnpf.config.ConfigValueUtil;
import jnpf.util.DataSourceUtil;
import jnpf.util.UserProvider;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 大数据测试
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月26日 上午9:18
 */
@Slf4j
@Service
public class BigDataServiceImpl extends ServiceImpl<BigDataMapper, BigDataEntity> implements BigDataService {

    @Autowired
    private DataSourceUtil dataSourceUtils;
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private ConfigValueUtil configValueUtil;


    @Override
    public List<BigDataEntity> getList(Pagination pagination) {
        QueryWrapper<BigDataEntity> queryWrapper = new QueryWrapper<>();
        if (pagination.getKeyword() != null) {
            queryWrapper.lambda().and(
                    t -> t.like(BigDataEntity::getFullName, pagination.getKeyword())
                            .or().like(BigDataEntity::getEnCode, pagination.getKeyword())
            );
        }
        //排序
        if (StringUtils.isEmpty(pagination.getSidx())) {
            queryWrapper.lambda().orderByDesc(BigDataEntity::getCreatorTime);
        } else {
            queryWrapper = "asc".equals(pagination.getSort().toLowerCase()) ? queryWrapper.orderByAsc(pagination.getSidx()) : queryWrapper.orderByDesc(pagination.getSidx());
        }
        Page<BigDataEntity> page = new Page<>(pagination.getCurrentPage(), pagination.getPageSize());
        IPage<BigDataEntity> userIPage = page(page, queryWrapper);
        return pagination.setData(userIPage.getRecords(), page.getTotal());
    }

    @Override
    public void create(int insertCount) throws WorkFlowException {
        Integer code = this.baseMapper.maxCode();
        if (code == null) {
            code = 0;
        }
        int index = code == 0 ? 10000001 : code;
        if (index > 11500001) {
            throw new WorkFlowException("防止恶意创建过多数据");
        }
        try {
            String dbName = dataSourceUtils.getDbName();
            String urll = dataSourceUtils.getUrl().replace("{dbName}", dbName);
            @Cleanup Connection conn = null;
            if ("true".equals(configValueUtil.getMultiTenancy())) {
                urll = dataSourceUtils.getUrl().replace("{dbName}", userProvider.get().getTenantDbConnectionString());
                conn = JdbcUtil.getConn(dataSourceUtils.getUserName(), dataSourceUtils.getPassword(), urll);
            } else {
                conn = JdbcUtil.getConn(dataSourceUtils.getUserName(), dataSourceUtils.getPassword(), urll);
            }
            @Cleanup PreparedStatement pstm = null;
            String sql = "";
            if (DbType.ORACLE.getMessage().equals(dataSourceUtils.getDataType().toLowerCase())) {
                sql = "INSERT INTO ext_bigdata(F_ID,F_ENCODE,F_FULLNAME,F_CREATORTIME)  VALUES (?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'))";
            } else {
                sql = "INSERT INTO ext_bigdata(F_ID,F_ENCODE,F_FULLNAME,F_CREATORTIME)  VALUES (?,?,?,?)";
            }
            pstm = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            Date date = new Date();
            SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=sf.format(date);
            String preTime = DateUtil.getPreTime(time, "-480");
            for (int i = 0; i < insertCount; i++) {
                pstm.setString(1, RandomUtil.uuId());
                pstm.setInt(2, index);
                pstm.setString(3, "测试大数据" + index);
                pstm.setString(4, preTime);
                pstm.addBatch();
                index++;
            }
            pstm.executeBatch();
            conn.commit();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
