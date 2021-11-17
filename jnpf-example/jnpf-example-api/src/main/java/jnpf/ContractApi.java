package jnpf;

import jnpf.fallback.ContractApiFallback;
import jnpf.model.ContractForm;
import jnpf.utils.FeignName;
import jnpf.base.ActionResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = FeignName.EXAMPLE_SERVER_NAME , fallback = ContractApiFallback.class, path = "/Contract")
public interface ContractApi {
    @PutMapping("/{id}")
    ActionResult update(@PathVariable("id") String id, @RequestBody @Valid ContractForm contractForm);
}
