package com.example.homework.controller;

import com.example.homework.FormLoginSuccessHandler;
import com.example.homework.ResEntity;
import com.example.homework.UserDetailsImpl;
import com.example.homework.dto.LoginRequestDto;
import com.example.homework.dto.SignupRequestDto;
import com.example.homework.dto.UserInfoDto;
import com.example.homework.jwt.JwtTokenUtils;
import com.example.homework.model.User;
import com.example.homework.model.UserRoleEnum;
import com.example.homework.repository.UserRepository;
import com.example.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;



import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.example.homework.FormLoginSuccessHandler.AUTH_HEADER;
import static com.example.homework.FormLoginSuccessHandler.TOKEN_TYPE;
import static com.example.homework.ResEntity.StatusEnum.OK;


@RestController
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsImpl userDetails;

    private final JwtTokenUtils jwtTokenUtils;

    private final FormLoginSuccessHandler formLoginSuccessHandler;








    @Autowired
    public UserController(UserService userService, UserRepository userRepository,
                          PasswordEncoder passwordEncoder, UserDetailsImpl userDetails, JwtTokenUtils jwtTokenUtils, FormLoginSuccessHandler formLoginSuccessHandler) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetails = userDetails;
        this.jwtTokenUtils = jwtTokenUtils;
        this.formLoginSuccessHandler = formLoginSuccessHandler;
    }

    @PostMapping("/api/users/signup")
    public ResEntity registerUser(@RequestBody SignupRequestDto requestDto) {
        User user = userService.registerUser(requestDto);
        return new ResEntity(user, OK);

    }

    @PostMapping("/api/users/login")
    public ResEntity login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response,Authentication authentication) {
        User user = userRepository.findByUsername(requestDto.getNickname())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 nickname 입니다."));

        if (!passwordEncoder.matches(requestDto.getPassword(),user.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        String accessToken = JwtTokenUtils.generateJwtToken(userDetails);
        String refreshToken = JwtTokenUtils.generateRefreshToken(userDetails);
        response.setHeader("Refresh-token",refreshToken);
        response.addHeader(AUTH_HEADER, TOKEN_TYPE + " " + accessToken);

        Cookie cookie = new Cookie("Authorization",JwtTokenUtils.generateJwtToken(userDetails));
        response.addCookie(cookie);



        return new ResEntity(requestDto,OK);

    }

    // 회원 관련 정보 받기
    @PostMapping("/user/userinfo")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        UserRoleEnum role = userDetails.getUser().getRole();
        boolean isAdmin = (role == UserRoleEnum.ADMIN);

        return new UserInfoDto(username, isAdmin);
    }

}