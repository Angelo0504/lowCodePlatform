package jnpf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jnpf.MPEventContentEntity;
import jnpf.mapper.MPEventContentMapper;
import jnpf.service.MPEventContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 事件内容
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Service
public class MPEventContentServiceImpl extends ServiceImpl<MPEventContentMapper, MPEventContentEntity> implements MPEventContentService {

    @Override
    public MPEventContentEntity getInfo(String id) {
        QueryWrapper<MPEventContentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MPEventContentEntity::getEventKey, id);
        return this.getOne(queryWrapper);
    }

    @Override
    @Transactional
    public boolean delete(MPEventContentEntity entity) {
       return this.removeById(entity.getEventKey());
    }

    @Override
    public void create(MPEventContentEntity entity) {
        this.save(entity);
    }

    @Override
    public boolean update(MPEventContentEntity entity) {
        return  this.updateById(entity);
    }
}
