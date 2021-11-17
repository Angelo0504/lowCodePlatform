package jnpf.listener;

import jnpf.config.ConfigValueUtil;
import jnpf.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class JnpfListener implements ApplicationRunner {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ConfigValueUtil configValueUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if ("false".equals(configValueUtil.getTestVersion())) {
            redisUtil.removeAll();
        }
    }
}
