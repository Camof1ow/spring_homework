package com.example.homework;

import com.example.homework.domain.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class HomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeworkApplication.class, args);
    }
    // Week02Application.java 의 main 함수 아래에 붙여주세요.
    @Bean
    public CommandLineRunner demo(PostRepository repository) {
        return (args) -> {
            };
        }
    }

