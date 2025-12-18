package com.week10.mission.service;

import com.week10.mission.domain.Role;
import com.week10.mission.domain.User;
import com.week10.mission.dto.SignupRequest;
import com.week10.mission.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signup(SignupRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);
    }
}

