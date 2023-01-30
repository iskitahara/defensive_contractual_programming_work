package com.example.demo.adopter.http;

import com.example.demo.adopter.http.validator.CustomerValidator;
import com.example.demo.adopter.repositoryimpl.CustomerRepositoryImpl;
import com.example.demo.domain.customer.Customer;
import com.example.demo.domain.repository.CustomerRepository;
import com.example.demo.domain.usecase.CustomerUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class CustomerController {

    @Autowired
    CustomerUsecase usecase;

    public CustomerController(CustomerUsecase usecase){
        this.usecase = usecase;
    }

    @GetMapping("/customer")
    public Customer findCustomer(@RequestParam(value = "uid", defaultValue = "0") int uid) {
        Customer ret = usecase.findCustomer(uid);
        return ret;
    }

    // ドメイン内に不審者と20際以上は存在させない要望を設計
    // エラーとか適当な箇所がたたあるので、意図しない挙動にならないような契約の部分だけ実装してます
    @PutMapping("/customer")
    public void putCustomer(@RequestParam(value = "uid", defaultValue = "0") int uid, @RequestBody CustomerValidator customer) {
        // 契約的に設計されたイメージ
        usecase.updateCustomer(uid, customer.getName(), customer.getBirthday());
    }

    @PutMapping("/mutable-customer")
    public void putMutableCustomer(@RequestParam(value = "uid", defaultValue = "0") int uid, @RequestBody CustomerValidator customer) {
        // 契約的に設計されてないイメージ
        usecase.updateMutableCustomer(uid, customer.getName(), customer.getBirthday());
    }
}