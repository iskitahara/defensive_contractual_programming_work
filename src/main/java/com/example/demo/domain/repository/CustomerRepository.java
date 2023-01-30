package com.example.demo.domain.repository;

import com.example.demo.domain.customer.Customer;
import com.example.demo.domain.customer.CustomerModifyAttribute;
import com.example.demo.domain.customer.MutableCustomer;

public interface CustomerRepository {
    public CustomerRecord findCustomer(int uid);
    public int updateCustomer(Customer customer, CustomerModifyAttribute mod);
    public MutableCustomer findMutableCustomer(int uid);
    public int updateMutableCustomer(MutableCustomer customer);
}
