package com.week10.mission.service;

import com.week10.mission.domain.Role;
import com.week10.mission.domain.User;
import com.week10.mission.dto.SignupRequest;
import com.week10.mission.jwt.JwtProvider;
import com.week10.mission.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public void signup(SignupRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);
    }

    public String login(String loginId, String password) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자"));

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }

        return jwtProvider.createToken(
                user.getLoginId(),
                user.getRole().name()
        );
    }
}

