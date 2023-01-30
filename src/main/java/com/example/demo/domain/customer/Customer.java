package com.example.demo.domain.customer;

import com.example.demo.domain.repository.CustomerRecord;
import lombok.Getter;

import java.util.Calendar;
import java.util.Date;

@Getter
public class Customer {
    private int id, uid;
    private String name;
    private Date birthday;

    /** ドメイン内では必ず20才以下で不審者ではないイミュータブルなオブジェクトしか存在しない */
    public Customer(CustomerRecord rec) {
        // ドメイン内に不審者はいない
        if(rec.illegalAt() != null) throw new RuntimeException("不審者");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, -20);
        // ドメイン内に20際以上はいない
        if(rec.birthday().compareTo(cal.getTime()) < 0)  throw new RuntimeException("20才以上");

        this.id = rec.id();
        this.uid = rec.uid();
        this.name = rec.name();
        this.birthday = rec.birthday();
    }
}