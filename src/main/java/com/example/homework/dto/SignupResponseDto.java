package com.example.homework.dto;


import com.example.homework.model.Timestamped;
import com.example.homework.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SignupResponseDto extends Timestamped {
    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public SignupResponseDto(String nickname, String password){
        this.nickname = nickname;
        this.password = password;
    }

    public SignupResponseDto(User user){
        this(user.getUsername(), user.getPassword());
    }


}
