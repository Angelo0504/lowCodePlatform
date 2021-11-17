package jnpf.impl;

import jnpf.config.ConfigValueUtil;
import jnpf.util.data.DataSourceContextHolder;
import jnpf.model.password.PassContextHolder;
import jnpf.permission.UsersApi;
import jnpf.permission.entity.UserEntity;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 验证账号密码
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-23
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersApi usersApi;
    @Autowired
    private ConfigValueUtil configValueUtil;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = null;
        UserEntity userEntity = null;
        if (Boolean.parseBoolean(configValueUtil.getMultiTenancy())){
           userEntity = usersApi.checkUser(username, DataSourceContextHolder.getDatasourceId(), DataSourceContextHolder.getDatasourceName());
        }else {
            userEntity = usersApi.checkUser(username, "1", "1");
        }
        Collection<SimpleGrantedAuthority> list = new ArrayList();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("aa");
        list.add(simpleGrantedAuthority);
        user = new User(username, userEntity.getPassword(), list);
        PassContextHolder.setUserName(username);
        return user;
    }

}
