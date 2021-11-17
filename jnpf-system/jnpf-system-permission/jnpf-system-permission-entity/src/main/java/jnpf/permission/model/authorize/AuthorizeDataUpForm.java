package jnpf.permission.model.authorize;

import lombok.Data;

@Data
public class AuthorizeDataUpForm {
    private String objectType;
    private String[] button;
    private String[] column;
    private String[] module;
    private String[] resource;

}
