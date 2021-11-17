package jnpf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jnpf.util.IpUtil;
import jnpf.util.RandomUtil;
import jnpf.util.ServletUtil;
import jnpf.service.BaseTenantLogService;
import jnpf.mapper.BaseTenantLogMapper;
import jnpf.BaseTenantLogEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * baseTenantlog
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@Service
public class BaseTenantLogServiceImpl extends ServiceImpl<BaseTenantLogMapper, BaseTenantLogEntity> implements BaseTenantLogService {

    @Override
    public BaseTenantLogEntity getInfo(String id) {
        QueryWrapper<BaseTenantLogEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BaseTenantLogEntity::getId, id);
        return this.getOne(queryWrapper);
    }

    @Override
    public void create(BaseTenantLogEntity entity) {
        entity.setId(RandomUtil.uuId());
        this.save(entity);
    }

    @Override
    public boolean update(String id, BaseTenantLogEntity entity) {
        entity.setId(id);
        return this.updateById(entity);
    }

    @Override
    public void delete(BaseTenantLogEntity entity) {
        if (entity != null) {
            this.removeById(entity.getId());
        }
    }

    @Override
    public void logAsync(String id, String encode) {
        BaseTenantLogEntity entity = new BaseTenantLogEntity();
        entity.setId(RandomUtil.uuId());
        entity.setLoginIpaddress(IpUtil.getIpAddr());
        entity.setTenantId(id);
        entity.setLoginAccount(encode);
        entity.setLoginIpaddressName(IpUtil.getIpCity(IpUtil.getIpAddr()));
        entity.setLoginTime(new Date());
        entity.setDescriPtion("java:"+ ServletUtil.getUserAgent());
        this.save(entity);
    }
}
