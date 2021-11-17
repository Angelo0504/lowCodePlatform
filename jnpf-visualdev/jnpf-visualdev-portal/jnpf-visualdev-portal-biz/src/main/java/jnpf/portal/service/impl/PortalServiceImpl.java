package jnpf.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jnpf.util.RandomUtil;
import jnpf.util.StringUtil;
import jnpf.portal.mapper.PortalMapper;
import jnpf.portal.service.PortalService;
import jnpf.portal.model.PortalPagination;
import jnpf.portal.entity.PortalEntity;
import jnpf.util.DateUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 可视化开发功能表
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-04-02
 */
@Service
public class PortalServiceImpl extends ServiceImpl<PortalMapper, PortalEntity> implements PortalService {


    @Override
    public List<PortalEntity> getList(PortalPagination portalPagination) {
        QueryWrapper<PortalEntity> queryWrapper = new QueryWrapper<>();
        if (!StringUtil.isEmpty(portalPagination.getKeyword())) {
            queryWrapper.lambda().like(PortalEntity::getFullName, portalPagination.getKeyword());
        }
        queryWrapper.lambda().orderByDesc(PortalEntity::getCreatorTime);
        return list(queryWrapper);
    }


    @Override
    public List<PortalEntity> getList() {
        QueryWrapper<PortalEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().orderByAsc(PortalEntity::getSortCode);
        return this.list(queryWrapper);
    }

    @Override
    public PortalEntity getInfo(String id) {
        QueryWrapper<PortalEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PortalEntity::getId, id);
        return this.getOne(queryWrapper);
    }

    @Override
    public void create(PortalEntity entity) {
        entity.setId(RandomUtil.uuId());
        this.save(entity);
    }

    @Override
    public boolean update(String id, PortalEntity entity) {
        entity.setId(id);
        entity.setLastModifyTime(DateUtil.getNowDate());
        return this.updateById(entity);
    }

    @Override
    public void delete(PortalEntity entity) {
        if (entity != null) {
            this.removeById(entity.getId());
        }
    }


}
