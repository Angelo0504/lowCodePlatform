package jnpf.controller;


import jnpf.base.ActionResult;
import jnpf.base.Pagination;
import jnpf.base.vo.PaginationVO;
import jnpf.entity.ContractEntity;
import jnpf.model.ContractForm;
import jnpf.model.ContractInfoVO;
import jnpf.model.ContractListVO;
import jnpf.service.ContractService;
import jnpf.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * Contract
 * 版本： V3.0.0
 * 版权： 引迈信息技术有限公司(https://www.jnpfsoft.com)
 * 作者： JNPF开发平台组
 * 日期： 2020-12-31
 */
@RestController
@RequestMapping("/Contract")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @GetMapping("/List")
    public ActionResult list(Pagination pagination) {
        List<ContractEntity> entity = contractService.getlist(pagination);
        List<ContractListVO> listVo = JsonUtil.getJsonToList(JsonUtil.getObjectToStringDateFormat(entity, "yyyy-MM-dd HH:mm:ss"),ContractListVO.class );
        PaginationVO vo = JsonUtil.getJsonToBean(pagination,PaginationVO.class);
        return ActionResult.page(listVo,vo);
    }

    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) {
        ContractEntity entity = contractService.getInfo(id);
        ContractInfoVO vo = JsonUtil.getJsonToBean(entity, ContractInfoVO.class);
        return ActionResult.success(vo);
    }

    @PostMapping
    public ActionResult create(@RequestBody @Valid ContractForm contractForm) {
        ContractEntity entity = JsonUtil.getJsonToBean(contractForm, ContractEntity.class);
        contractService.create(entity);
        return ActionResult.success("保存成功");
    }

    @PutMapping("/{id}")
    public ActionResult update(@PathVariable("id") String id,@RequestBody @Valid ContractForm contractForm) {
        ContractEntity entity = JsonUtil.getJsonToBean(contractForm, ContractEntity.class);
        contractService.update(id,entity);
        return ActionResult.success("修改成功");
    }

    @DeleteMapping("/{id}")
    public ActionResult delete(@PathVariable("id") String id) {
        ContractEntity entity = contractService.getInfo(id);
        contractService.delete(entity);
        return ActionResult.success("删除成功");
    }

}
