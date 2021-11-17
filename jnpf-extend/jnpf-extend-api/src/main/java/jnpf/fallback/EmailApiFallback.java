package jnpf.fallback;

import jnpf.EmailApi;
import jnpf.entity.EmailReceiveEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailApiFallback implements EmailApi {
    @Override
    public List<EmailReceiveEntity> getReceiveList() {
        return null;
    }
}
