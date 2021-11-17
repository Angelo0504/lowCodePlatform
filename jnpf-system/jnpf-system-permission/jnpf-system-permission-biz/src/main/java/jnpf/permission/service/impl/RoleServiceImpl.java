package jnpf.permission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jnpf.util.RandomUtil;
import jnpf.util.StringUtil;
import jnpf.base.Page;
import jnpf.permission.entity.AuthorizeEntity;
import jnpf.permission.entity.RoleEntity;
import jnpf.permission.entity.UserRelationEntity;
import jnpf.permission.mapper.RoleMapper;
import jnpf.permission.service.AuthorizeService;
import jnpf.permission.service.RoleService;
import jnpf.permission.service.UserRelationService;
import jnpf.util.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统角色
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月26日 上午9:18
 */
@Service
@Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    @Autowired
    private UserProvider userProvider;
    @Autowired
    private UserRelationService userRelationService;
    @Autowired
    private AuthorizeService authorizeService;

    @Override
    public List<RoleEntity> getList() {
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().orderByAsc(RoleEntity::getSortCode);
        return this.list(queryWrapper);
    }

    @Override
    public List<RoleEntity> getList(Page page) {
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        if (page.getKeyword() != null) {
            queryWrapper.lambda().and(
                    t -> t.like(RoleEntity::getFullName, page.getKeyword())
                            .or().like(RoleEntity::getEnCode, page.getKeyword())
            );
        }
        queryWrapper.lambda().orderByAsc(RoleEntity::getSortCode);
        return this.list(queryWrapper);
    }

    @Override
    public List<RoleEntity> getListByUserId(String userId) {
        return this.baseMapper.getListByUserId(userId);
    }

    @Override
    public RoleEntity getInfo(String id) {
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleEntity::getId, id);
        return this.getOne(queryWrapper);
    }

    @Override
    public boolean isExistByFullName(String fullName, String id) {
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleEntity::getFullName, fullName);
        if (!StringUtil.isEmpty(id)) {
            queryWrapper.lambda().ne(RoleEntity::getId, id);
        }
        return this.count(queryWrapper) > 0 ? true : false;
    }

    @Override
    public boolean isExistByEnCode(String enCode, String id) {
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleEntity::getEnCode, enCode);
        if (!StringUtil.isEmpty(id)) {
            queryWrapper.lambda().ne(RoleEntity::getId, id);
        }
        return this.count(queryWrapper) > 0 ? true : false;
    }

    @Override
    public void create(RoleEntity entity) {
        entity.setId(RandomUtil.uuId());
        entity.setCreatorUserId(userProvider.get().getUserId());
        this.save(entity);
    }

    @Override
    public boolean update(String id, RoleEntity entity) {
        entity.setId(id);
        entity.setLastModifyTime(new Date());
        entity.setLastModifyUserId(userProvider.get().getUserId());
        return this.updateById(entity);
    }

    @Override
    @Transactional
    public void delete(RoleEntity entity) {
        if (entity != null) {
            this.removeById(entity.getId());
            QueryWrapper<AuthorizeEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(AuthorizeEntity::getObjectId, entity.getId());
            authorizeService.remove(queryWrapper);
            QueryWrapper<UserRelationEntity> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(UserRelationEntity::getObjectId, entity.getId());
            userRelationService.remove(wrapper);
        }
    }
    @Override
    public List<RoleEntity> getRoleName(List<String> id) {
        List<RoleEntity> roleList = new ArrayList<>();
        if (id.size() > 0) {
            QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().in(RoleEntity::getId, id);
            roleList = this.list(queryWrapper);
        }
        return roleList;
    }
}
