package jnpf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jnpf.base.Pagination;
import jnpf.entity.ContractEntity;

import java.util.*;

/**
 *
 * Contract
 * 版本： V3.0.0
 * 版权： 引迈信息技术有限公司(https://www.jnpfsoft.com)
 * 作者： JNPF开发平台组
 * 日期： 2020-12-31
 */
public interface ContractService extends IService<ContractEntity> {

    List<ContractEntity> getlist(Pagination pagination);

    ContractEntity getInfo(String id);

    void create(ContractEntity entity);

    void update(String id, ContractEntity entity);

    void delete(ContractEntity entity);
}
