package com.example.homework.model;

import com.example.homework.dto.SignupRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@Table(name = "users")
public class User extends Timestamped {

    @Id
    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @JsonIgnore
    private String passwordConfirm;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @JsonIgnore
    private UserRoleEnum role;


    @Builder
    public User(String username, String password,String passwordConfirm, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.role = role;

    }

    public static User toEntity(SignupRequestDto dto, UserRoleEnum role){
        return User.builder()
                .role(role)
                .username(dto.getUsername())
                .password(dto.getPassword())
                .passwordConfirm(dto.getPasswordConfirm())
                .build();
    }

}