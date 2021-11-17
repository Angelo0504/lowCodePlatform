package jnpf.base.fallback;

import jnpf.base.VisualDevelopmentApi;
import jnpf.base.VisualdevEntity;
import org.springframework.stereotype.Component;

@Component
public class VisualDevelopmentApiFallback implements VisualDevelopmentApi {

    @Override
    public VisualdevEntity getInfo(String id) {
        return null;
    }
}
