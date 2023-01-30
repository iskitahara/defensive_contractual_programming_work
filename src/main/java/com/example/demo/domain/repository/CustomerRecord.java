package com.example.demo.domain.repository;

import java.util.Date;

public record CustomerRecord(int id, int uid, String name, Date birthday, Date illegalAt, Date updateAt) { }
