package jnpf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jnpf.util.RandomUtil;
import jnpf.base.Pagination;
import jnpf.base.UserInfo;
import jnpf.entity.ContractEntity;
import jnpf.mapper.ContractMapper;
import jnpf.service.ContractService;
import jnpf.util.UserProvider;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 *
 * Contract
 * 版本： V3.0.0
 * 版权： 引迈信息技术有限公司(https://www.jnpfsoft.com)
 * 作者： JNPF开发平台组
 * 日期： 2020-12-31
 */
@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, ContractEntity> implements ContractService {

    @Autowired
    private UserProvider userProvider;

    @Override
    public List<ContractEntity> getlist(Pagination pagination){
        //通过UserProvider获取用户信息
        UserInfo UserProvider = userProvider.get();
        QueryWrapper<ContractEntity> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(pagination.getKeyword())) {
            queryWrapper.lambda().and(
                t -> t.like(ContractEntity::getContractName, pagination.getKeyword())
                .or().like(ContractEntity::getMytelePhone, pagination.getKeyword())
            );
        }
        //排序
        if (StringUtils.isEmpty(pagination.getSidx())) {
        } else {
            queryWrapper = "asc".equals(pagination.getSort().toLowerCase()) ? queryWrapper.orderByAsc(pagination.getSidx()) : queryWrapper.orderByDesc(pagination.getSidx());
        }
        Page<ContractEntity> page = new Page<>(pagination.getCurrentPage(), pagination.getPageSize());
        IPage<ContractEntity> userIPage = this.page(page, queryWrapper);
        return pagination.setData(userIPage.getRecords(), page.getTotal());
    }

    @Override
    public ContractEntity getInfo(String id){
        QueryWrapper<ContractEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ContractEntity::getId, id);
        return this.getOne(queryWrapper);
    }

    @Override
    @Transactional
    public void create(ContractEntity entity){
        entity.setId(RandomUtil.uuId());
        this.save(entity);
    }

    @Override
    @Transactional
    public void update(String id, ContractEntity entity){
        entity.setId(id);
        this.updateById(entity);
    }

    @Override
    public void delete(ContractEntity entity) {
        if (entity != null) {
            this.removeById(entity.getId());
        }
    }
}
