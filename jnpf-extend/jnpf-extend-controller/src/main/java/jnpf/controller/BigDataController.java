package jnpf.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.util.JsonUtil;
import jnpf.service.BigDataService;
import jnpf.entity.BigDataEntity;
import jnpf.model.bidata.BigBigDataListVO;
import jnpf.base.ActionResult;
import jnpf.base.Pagination;
import jnpf.base.vo.PaginationVO;
import jnpf.exception.WorkFlowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 大数据测试
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月26日 上午9:18
 */
@Api(tags = "大数据测试", value = "BigData")
@RestController
@RequestMapping("/BigData")
public class BigDataController {

    @Autowired
    private BigDataService bigDataService;

    /**
     * 列表
     *
     * @param pagination
     * @return
     */
    @ApiOperation("获取大数据测试列表分页")
    @GetMapping
    public ActionResult list(Pagination pagination) {
        List<BigDataEntity> data = bigDataService.getList(pagination);
        List<BigBigDataListVO> list= JsonUtil.getJsonToList(data,BigBigDataListVO.class);
        PaginationVO paginationVO  = JsonUtil.getJsonToBean(pagination,PaginationVO.class);
        return ActionResult.page(list,paginationVO);
    }

    /**
     * 新建
     *
     * @return
     */
    @ApiOperation("添加大数据测试")
    @PostMapping
    public ActionResult create() throws WorkFlowException {
        bigDataService.create(10000);
        return ActionResult.success("新建成功10000条数据");
    }
}
