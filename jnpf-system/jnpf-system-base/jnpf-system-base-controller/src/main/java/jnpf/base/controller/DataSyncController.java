package jnpf.base.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.base.model.dbsync.DbSyncForm;
import jnpf.base.service.DbSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据同步
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Api(tags = "数据同步", value = "DataSync")
@RestController
@RequestMapping("/Base/DataSync/Actions/Execute")
public class DataSyncController {

    @Autowired
    private DbSyncService dbSyncService;

    /**
     * 执行数据同步
     * @param dbSyncForm dto实体
     * @return
     */
    @PostMapping
    @ApiOperation("执行数据同步")
    public ActionResult execute(@RequestBody DbSyncForm dbSyncForm) {
        String data = dbSyncService.importTableData(dbSyncForm.getDbConnectionFrom(), dbSyncForm.getDbConnectionTo(), dbSyncForm.getDbTable());
        if("ok".equals(data)){
            return ActionResult.success("同步成功");
        }
        return ActionResult.success(data);
    }
}
