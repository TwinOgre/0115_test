package com.article.test0115.user;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public String register() {
        return "user_form";
    }

    @PostMapping("/register")
    public String register(@Valid UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_form";
        }
        if (!userForm.getPassword1().equals(userForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInvalid", "비밀번호가 일치하지 않습니다.");
        }
        this.userService.register(userForm.getUsername(), userForm.getNickname(), userForm.getPassword1());
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {

        return "user_login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
