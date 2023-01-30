package com.example.demo.domain.customer;

import lombok.Getter;
import lombok.NonNull;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

@Getter
public class CustomerModifyAttribute {
    @NonNull
    private String name;

    @NonNull
    private Date birthday;

    public CustomerModifyAttribute(String name, Date birthday) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, -20);
        if(birthday.compareTo(cal.getTime()) < 0)  throw new RuntimeException("20才以上");
        this.name = name;
        this.birthday = birthday;
    }
}