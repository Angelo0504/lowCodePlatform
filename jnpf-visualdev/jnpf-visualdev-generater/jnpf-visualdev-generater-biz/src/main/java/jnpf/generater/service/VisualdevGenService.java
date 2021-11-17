package jnpf.generater.service;


import com.baomidou.mybatisplus.extension.service.IService;
import jnpf.base.VisualdevEntity;
import jnpf.base.model.DownloadCodeForm;
import jnpf.base.model.FormDataModel;
import jnpf.util.DataSourceUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * 可视化开发功能表
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-04-02
 */
public interface VisualdevGenService extends IService<VisualdevEntity> {


    void  htmlTemplates(String fileName, VisualdevEntity entity, FormDataModel model, FormDataModel htmlModel, String templatePath, List<String> childTable, String pKeyName) throws SQLException;

    void  modelTemplates(String fileName, VisualdevEntity entity,FormDataModel model, String templatePath,List<String> childTable,String pKeyName) throws SQLException;

    void generate(VisualdevEntity entity, FormDataModel model, DataSourceUtil dataSourceUtil, String templateCodePath, String fileName, DownloadCodeForm downloadCodeForm, List<String> childTable, String pKeyName, Map<String,Object> childPKeyMap) throws SQLException;

    String codeGengerate(String id, DownloadCodeForm downloadCodeForm) throws SQLException;
}
