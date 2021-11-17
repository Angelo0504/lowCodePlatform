package jnpf.datascreen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jnpf.util.FileUtil;
import jnpf.util.RandomUtil;
import jnpf.util.StringUtil;
import jnpf.datascreen.VisualDataEntity;
import jnpf.datascreen.mapper.VisualDataMapper;
import jnpf.datascreen.model.PaginationData;
import jnpf.datascreen.service.VisualDataService;
import jnpf.config.ConfigValueUtil;
import jnpf.util.DateUtil;
import jnpf.util.UserProvider;
import jnpf.datascreen.util.VisualImageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * 大屏数据
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月26日 上午9:18
 */
@Service
public class VisualDataServiceImpl extends ServiceImpl<VisualDataMapper, VisualDataEntity> implements VisualDataService {

    @Autowired
    private UserProvider userProvider;
    @Autowired
    private ConfigValueUtil configValueUtil;

    @Override
    public List<VisualDataEntity> getList(PaginationData pagination) {
        QueryWrapper<VisualDataEntity> queryWrapper = new QueryWrapper<>();
        if (StringUtil.isNotEmpty(pagination.getCategoryId())) {
            queryWrapper.lambda().eq(VisualDataEntity::getCategoryId, pagination.getCategoryId());
        }
        if (StringUtil.isNotEmpty(pagination.getKeyword())) {
            queryWrapper.lambda().and(
                    t -> t.like(VisualDataEntity::getFullName, pagination.getKeyword())
                            .or().like(VisualDataEntity::getEnCode, pagination.getKeyword())
            );
        }
        //排序
        queryWrapper.lambda().orderByDesc(VisualDataEntity::getCreatorTime);
        Page page = new Page(pagination.getCurrentPage(), pagination.getPageSize());
        IPage<VisualDataEntity> iPages = this.page(page, queryWrapper);
        return pagination.setData(iPages.getRecords(), page.getTotal());
    }

    @Override
    public List<VisualDataEntity> getList() {
        QueryWrapper<VisualDataEntity> queryWrapper = new QueryWrapper<>();
        return this.list(queryWrapper);
    }

    @Override
    public VisualDataEntity getInfo(String id) {
        QueryWrapper<VisualDataEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(VisualDataEntity::getId, id);
        return this.getOne(queryWrapper);
    }

    @Override
    public void create(VisualDataEntity entity) {
        entity.setId(RandomUtil.uuId());
        entity.setSortCode(RandomUtil.parses());
        entity.setCreatorTime(DateUtil.getNowDate());
        entity.setCreatorUserId(userProvider.get().getUserId());
        this.save(entity);
    }

    @Override
    public boolean update(String id, VisualDataEntity entity) {
        entity.setId(id);
        entity.setLastModifyTime(DateUtil.getNowDate());
        entity.setLastModifyUserId(userProvider.get().getUserId());
        return this.updateById(entity);
    }

    @Override
    public void delete(VisualDataEntity entity) {
        if (entity != null) {
            String fileName = entity.getScreenShot();
            String path = configValueUtil.getBiVisualPath() + File.separator + VisualImageEnum.SCREENSHOT.getMessage() + File.separator + fileName;
            this.removeById(entity.getId());
            FileUtil.deleteFile(path);
        }
    }

    @Override
    public boolean isExistByName(String id, String name) {
        QueryWrapper<VisualDataEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(VisualDataEntity::getFullName, name);
        if (!StringUtil.isEmpty(id)) {
            queryWrapper.lambda().ne(VisualDataEntity::getId, id);
        }
        return this.count(queryWrapper) > 0 ? true : false;
    }

}
