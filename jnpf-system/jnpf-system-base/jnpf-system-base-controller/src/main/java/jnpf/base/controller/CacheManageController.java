package jnpf.base.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.base.ActionResult;
import jnpf.base.vo.ListVO;
import jnpf.base.Page;
import jnpf.base.model.cacheManage.CacheManageInfoVO;
import jnpf.base.model.cacheManage.CacheManageListVO;
import jnpf.util.DateUtil;
import jnpf.util.StringUtil;
import jnpf.util.RedisUtil;
import jnpf.util.UserProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 缓存管理
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Api(tags = "缓存管理", value = "CacheManage")
@RestController
@RequestMapping("/Base/CacheManage")
public class CacheManageController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserProvider userProvider;

    @ApiOperation("获取缓存列表")
    @GetMapping
    public ActionResult getList(Page page) {
        String tenantId = userProvider.get().getTenantId();
        List<CacheManageListVO> list = new ArrayList<>();
        Set<String> data = redisUtil.getAllKeys();
        for (String key : data) {
            if (!StringUtils.isEmpty(tenantId) && key.contains(tenantId)) {
                CacheManageListVO model = new CacheManageListVO();
                model.setName(key);
                model.setOverdueTime(new Date((DateUtil.getTime(new Date()) + redisUtil.getLiveTime(key)) * 1000).getTime());
                list.add(model);
            } else if (StringUtils.isEmpty(tenantId)) {
                CacheManageListVO model = new CacheManageListVO();
                model.setName(key);
                model.setOverdueTime(new Date((DateUtil.getTime(new Date()) + redisUtil.getLiveTime(key)) * 1000).getTime());
                list.add(model);
            }
        }
        list = list.stream().sorted(Comparator.comparing(CacheManageListVO::getOverdueTime)).collect(Collectors.toList());
        if(StringUtil.isNotEmpty(page.getKeyword())){
            list= list.stream().filter(t->t.getName().contains(page.getKeyword())).collect(Collectors.toList());
        }
        ListVO vo = new ListVO();
        vo.setList(list);
        return ActionResult.success(vo);
    }

    /**
     * 获取缓存信息
     *
     * @param name 主键值
     * @return
     */
    @ApiOperation("获取缓存信息")
    @GetMapping("/{name}")
    public ActionResult info(@PathVariable("name") String name) {
        String json = String.valueOf(redisUtil.getString(name));
        CacheManageInfoVO vo=new CacheManageInfoVO();
        vo.setName(name);
        vo.setValue(json);
        return ActionResult.success(vo);
    }

    /**
     * 清空所有缓存
     *
     * @return
     */
    @ApiOperation("清空所有缓存")
    @PostMapping("/Actions/ClearAll")
    public ActionResult clearAll() {
        String tenantId = userProvider.get().getTenantId();
        if ("".equals(tenantId)) {
            Set<String> keys = redisUtil.getAllKeys();
            for (String key : keys) {
                redisUtil.remove(key);
            }
        } else {
            Set<String> data = redisUtil.getAllKeys();
            String clientKey = UserProvider.getToken();
            System.out.println(clientKey);
            for (String key : data) {
                if (key.contains(tenantId)) {
                    redisUtil.remove(key);
                }
            }
        }
        return ActionResult.success("清理成功");
    }

    /**
     * 清空单个缓存
     *
     * @param name 主键值
     * @return
     */
    @ApiOperation("清空单个缓存")
    @DeleteMapping("/{name}")
    public ActionResult clear(@PathVariable("name") String name) {
        redisUtil.remove(name);
        return ActionResult.success("清空成功");
    }
}
