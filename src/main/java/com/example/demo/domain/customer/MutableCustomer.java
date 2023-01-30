package com.example.demo.domain.customer;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MutableCustomer {
    private int id, uid;
    private String name;
    private Date birthday, illegalAt, updateAt;
}
