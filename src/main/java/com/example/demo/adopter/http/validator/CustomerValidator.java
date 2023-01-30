package com.example.demo.adopter.http.validator;

import lombok.Getter;
import lombok.NonNull;

import java.util.Date;
@Getter
public class CustomerValidator {

    @NonNull
    private String name;
    @NonNull
    private Date birthday;

    // 防御的プログラミンでリクエストのバリデーション

}
