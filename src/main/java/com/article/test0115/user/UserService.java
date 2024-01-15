package com.article.test0115.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(String username, String nickname, String password1) {
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(username);
        siteUser.setNickname(nickname);
        siteUser.setPassword(passwordEncoder.encode(password1));
        siteUser.setCreateDate(LocalDateTime.now());

        this.userRepository.save(siteUser);
    }

    public SiteUser getUser(String name) {
        Optional<SiteUser> os = this.userRepository.findByusername(name);
        if (os.isEmpty()) {
            throw new RuntimeException();
        }
        return os.get();
    }
}
