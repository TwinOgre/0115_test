package com.article.test0115.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {
    @NotEmpty
    private String username;

    @NotEmpty
    private String nickname;

    @NotEmpty
    private String password1;

    @NotEmpty
    private String password2;
}
