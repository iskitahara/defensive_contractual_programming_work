package com.example.demo.domain.usecase;

import com.example.demo.adopter.repositoryimpl.CustomerRepositoryImpl;
import com.example.demo.domain.customer.Customer;
import com.example.demo.domain.customer.CustomerModifyAttribute;
import com.example.demo.domain.customer.MutableCustomer;
import com.example.demo.domain.repository.CustomerRecord;
import com.example.demo.domain.repository.CustomerRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;


public class CustomerUsecase {

    @Autowired
    CustomerRepository repository;

    public CustomerUsecase(CustomerRepositoryImpl repository) {
        this.repository = repository;
    }

    public Customer findCustomer(int uid){
        CustomerRecord rec = repository.findCustomer(uid);
        System.out.println(rec);
        Customer customer = new Customer(rec);
        System.out.println(rec);
        return customer;
    }

    // 呼び出し箇所や実装者でも契約によって紛れがない実装のイメージ
    // ドメイン内はドメインルールに従ったオブジェクトしか存在させない契約
    // イミュータブルなオブジェクトなので、生成後に不整値が入ることは基本的にはない
    public void updateCustomer(int uid, String name, Date birthday){
        CustomerRecord rec = repository.findCustomer(uid);
        Customer customer = new Customer(rec);
        CustomerModifyAttribute att = new CustomerModifyAttribute(name, birthday);
        int ret = repository.updateCustomer(customer, att);
        if(ret == 0) throw new RuntimeException("更新失敗");
    }

    // 呼び出し箇所や実装者によって紛れのある実装のイメージ
    public void updateMutableCustomer(int uid, String name, Date birthday){
        MutableCustomer customer = repository.findMutableCustomer(uid);
        // 不審者はNG
        if(customer.getIllegalAt() != null) throw new RuntimeException("不審者");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, -20);
        // 20際以上はNG
        if(customer.getBirthday().compareTo(cal.getTime()) < 0)  throw new RuntimeException("20才以上");

        customer.setName(name);
        customer.setBirthday(birthday);

        int ret = repository.updateMutableCustomer(customer);
        if(ret == 0) throw new RuntimeException("更新失敗");
    }
}
