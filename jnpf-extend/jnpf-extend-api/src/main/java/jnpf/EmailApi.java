package jnpf;

import jnpf.utils.FeignName;
import jnpf.fallback.EmailApiFallback;
import jnpf.entity.EmailReceiveEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = FeignName.EXTEND_SERVER_NAME, fallback = EmailApiFallback.class, path = "/Email")
public interface EmailApi {
    /**
     * 列表（收件箱）
     *
     * @param
     * @return
     */
    @GetMapping("/GetReceiveList")
    List<EmailReceiveEntity> getReceiveList();

}
