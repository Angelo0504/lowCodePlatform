package jnpf.base.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.util.JsonUtil;
import jnpf.util.StringUtil;
import jnpf.base.ActionResult;
import jnpf.base.vo.ListVO;
import jnpf.base.model.module.*;
import jnpf.base.entity.ModuleEntity;
import jnpf.exception.DataException;
import jnpf.model.UserMenuModel;
import jnpf.base.service.ModuleService;
import jnpf.util.treeutil.SumTree;
import jnpf.util.treeutil.TreeDotUtils;
import jnpf.util.CacheKeyUtil;
import jnpf.util.RedisUtil;
import jnpf.util.UserProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统功能
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Api(tags = "系统菜单", value = "menu")
@RestController
@RequestMapping("/Base/Menu")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CacheKeyUtil cacheKeyUtil;
    @Autowired
    private UserProvider userProvider;


    /**
     * 获取菜单列表
     *
     * @param paginationMenu
     * @return
     */
    @ApiOperation("获取菜单列表")
    @GetMapping
    public ActionResult list(PaginationMenu paginationMenu) {
        List<ModuleEntity> data = moduleService.getList();
        //正序排序
        data = data.stream().sorted(Comparator.comparing(ModuleEntity::getSortCode)).collect(Collectors.toList());
        if (!StringUtil.isEmpty(paginationMenu.getCategory())) {
            data = data.stream().filter(t -> paginationMenu.getCategory().equals(t.getCategory())).collect(Collectors.toList());
        }
        if (!StringUtils.isEmpty(paginationMenu.getKeyword())) {
            data = data.stream().filter(t -> t.getFullName().contains(paginationMenu.getKeyword())).collect(Collectors.toList());
        }
        List<UserMenuModel> list = JsonUtil.getJsonToList(data, UserMenuModel.class);
        List<SumTree<UserMenuModel>> menuList = TreeDotUtils.convertListToTreeDot(list);
        List<MenuListVO> menuvo = JsonUtil.getJsonToList(menuList, MenuListVO.class);
        ListVO vo = new ListVO();
        vo.setList(menuvo);
        return ActionResult.success(vo);
    }

    /**
     * 获取菜单列表(下拉框)
     *
     * @return
     */
    @ApiOperation("获取菜单列表(下拉框)")
    @GetMapping("/Selector")
    public ActionResult treeView(String category) {
        List<ModuleEntity> data = moduleService.getList().stream().filter(t -> !StringUtil.isEmpty(category) ? category.equals(String.valueOf(t.getCategory())) && "1".equals(String.valueOf(t.getType())) : "1".equals(String.valueOf(t.getType()))).collect(Collectors.toList());
        List<UserMenuModel> list = JsonUtil.getJsonToList(data, UserMenuModel.class);
        List<SumTree<UserMenuModel>> menuList = TreeDotUtils.convertListToTreeDot(list);
        List<MenuSelectVO> menuvo = JsonUtil.getJsonToList(menuList, MenuSelectVO.class);
        ListVO vo = new ListVO();
        vo.setList(menuvo);
        return ActionResult.success(vo);
    }
    /**
     * 获取菜单列表(下拉框)
     *
     * @return
     */
    @ApiOperation("获取菜单列表下拉框")
    @GetMapping("/Selector/All")
    public ActionResult menuSelect(String category) {
        List<ModuleEntity> data = moduleService.getList().stream().filter(t ->"1".equals(String.valueOf(t.getEnabledMark()))).collect(Collectors.toList());
        if(!StringUtil.isEmpty(category)){
            data.stream().filter(t ->category.equals(String.valueOf(t.getCategory()))).collect(Collectors.toList());
        }
        List<UserMenuModel> list = JsonUtil.getJsonToList(data, UserMenuModel.class);
        List<SumTree<UserMenuModel>> menuList = TreeDotUtils.convertListToTreeDot(list);
        List<MenuSelectAllVO> menuvo = JsonUtil.getJsonToList(menuList, MenuSelectAllVO.class);
        ListVO vo = new ListVO();
        vo.setList(menuvo);
        return ActionResult.success(vo);
    }


    /**
     * 获取菜单信息
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("获取菜单信息")
    @GetMapping("/{id}")
    public ActionResult info(@PathVariable("id") String id) throws DataException {
        ModuleEntity entity = moduleService.getInfo(id);
        ModuleInfoVO vo = JsonUtil.getJsonToBeanEx(entity, ModuleInfoVO.class);
        return ActionResult.success(vo);
    }


    /**
     * 新建系统功能
     *
     * @param moduleCrForm 实体对象
     * @return
     */
    @ApiOperation("新建系统功能")
    @PostMapping
    public ActionResult create(@RequestBody @Valid ModuleCrForm moduleCrForm) {
        ModuleEntity entity = JsonUtil.getJsonToBean(moduleCrForm, ModuleEntity.class);
        if (entity.getUrlAddress() != null) {
            entity.setUrlAddress(entity.getUrlAddress().trim());
        }
        if (moduleService.isExistByFullName(entity.getFullName(), entity.getId())) {
            return ActionResult.fail("名称不能重复");
        }
        if (moduleService.isExistByEnCode(entity.getEnCode(), entity.getId())) {
            return ActionResult.fail("编码不能重复");
        }
        moduleService.create(entity);
        return ActionResult.success("新建成功");
    }

    /**
     * 更新系统功能
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("更新系统功能")
    @PutMapping("/{id}")
    public ActionResult update(@PathVariable("id") String id, @RequestBody @Valid ModuleUpForm moduleUpForm) {
        ModuleEntity entity = JsonUtil.getJsonToBean(moduleUpForm, ModuleEntity.class);
        if (entity.getUrlAddress() != null) {
            entity.setUrlAddress(entity.getUrlAddress().trim());
        }
        if (moduleService.isExistByFullName(entity.getFullName(), id)) {
            return ActionResult.fail("名称不能重复");
        }
        if (moduleService.isExistByEnCode(entity.getEnCode(), id)) {
            return ActionResult.fail("编码不能重复");
        }
        boolean flag = moduleService.update(id, entity);
        if (flag == false) {
            return ActionResult.fail("更新失败，数据不存在");
        }
        return ActionResult.success("更新成功");
    }

    /**
     * 删除系统功能
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("删除系统功能")
    @DeleteMapping("/{id}")
    public ActionResult delete(@PathVariable("id") String id) {
        ModuleEntity entity = moduleService.getInfo(id);
        if (entity != null) {
            List<ModuleEntity> list=moduleService.getList().stream().filter(t->t.getParentId().equals(entity.getId())).collect(Collectors.toList());
            if(list.size()>0){
                return ActionResult.fail("删除失败，请先删除子菜单。");
            }
            moduleService.delete(entity);
            return ActionResult.success("删除成功");
        }
        return ActionResult.fail("删除失败，数据不存在");
    }

    /**
     * 更新菜单状态
     *
     * @param id 主键值
     * @return
     */
    @ApiOperation("更新菜单状态")
    @PutMapping("/{id}/Actions/State")
    public ActionResult upState(@PathVariable("id") String id) {
        ModuleEntity entity = moduleService.getInfo(id);
        if (entity != null) {
            if (entity.getEnabledMark() == null || entity.getEnabledMark() == 1) {
                entity.setEnabledMark(0);
            } else {
                entity.setEnabledMark(1);
            }
            moduleService.update(id, entity);
            //清除redis权限
            String cacheKey = cacheKeyUtil.getUserAuthorize() + userProvider.get().getUserId();
            if (redisUtil.exists(cacheKey)) {
                redisUtil.remove(cacheKey);
            }
            return ActionResult.success("更新成功");
        }
        return ActionResult.fail("更新失败，数据不存在");
    }

    /**
     * 列表
     *
     * @return
     */
    @GetMapping("/getList")
    public List<ModuleEntity> getList() {
        List<ModuleEntity> list = moduleService.getList();
        return list;
    }
}
