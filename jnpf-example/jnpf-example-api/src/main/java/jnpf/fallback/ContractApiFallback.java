package jnpf.fallback;


import jnpf.ContractApi;
import jnpf.base.ActionResult;
import jnpf.model.ContractForm;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class ContractApiFallback implements ContractApi {
    @Override
    public ActionResult update(String id, @Valid ContractForm contractForm) {
        return null;
    }
}
