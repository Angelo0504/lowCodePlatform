package jnpf.permission.model.user;

import lombok.Data;

@Data
public class UserPasswordForm {
    private String oldPassword;
    private String password;
    private String code;
}
