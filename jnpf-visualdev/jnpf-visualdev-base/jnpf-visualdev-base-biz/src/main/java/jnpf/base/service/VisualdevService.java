package jnpf.base.service;


import com.baomidou.mybatisplus.extension.service.IService;
import jnpf.base.VisualdevEntity;
import jnpf.base.model.PaginationVisualdev;

import java.util.List;

/**
 *
 * 可视化开发功能表
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-04-02
 */
public interface VisualdevService extends IService<VisualdevEntity> {

    List<VisualdevEntity> getList(PaginationVisualdev paginationVisualdev);

    List<VisualdevEntity> getList();

    VisualdevEntity getInfo(String id);


    void create(VisualdevEntity entity);

    boolean update(String id, VisualdevEntity entity);

    void delete(VisualdevEntity entity);
}
