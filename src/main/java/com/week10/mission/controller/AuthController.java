package com.week10.mission.controller;

import com.week10.mission.dto.SignupRequest;
import com.week10.mission.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {
        userService.signup(request);
        return "회원가입 성공";
    }

    @PostMapping("/login")
    public String login(){
        return "로그인 성공";
    }

    @PostMapping("/logout")
    public String logout(){
        return "로그아웃 성공";
    }

    @GetMapping("/home")
    public String home(Authentication authentication) {
        return authentication.getName() + " 로그인 상태입니다.";
    }
}

