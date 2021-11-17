package jnpf.base;


import jnpf.utils.FeignName;
import jnpf.base.fallback.VisualDevelopmentApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = FeignName.VUSUALDEV_SERVER_NAME , fallback = VisualDevelopmentApiFallback.class, path = "/VisualDevelopment")
public interface VisualDevelopmentApi {

    /**
     * 列表
     *
     * @return
     */
    @GetMapping("/getInfo/{id}")
    VisualdevEntity getInfo(@PathVariable("id") String id);

}
