package com.example.demo.adopter.repositoryimpl;


import com.example.demo.domain.customer.Customer;
import com.example.demo.domain.customer.CustomerModifyAttribute;
import com.example.demo.domain.repository.CustomerRecord;
import com.example.demo.domain.customer.MutableCustomer;
import com.example.demo.domain.repository.CustomerRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@NoArgsConstructor
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public CustomerRecord findCustomer(int uid){
        CustomerRecord rec = jdbcTemplate.queryForObject(
                "SELECT ID, UID, NAME, BIRTHDAY, ILLEGAL_AT FROM CUSTOMERS WHERE UID = ?",
                CustomerRecord.class, uid);
        return rec;
    }

    public int updateCustomer(Customer customer, CustomerModifyAttribute attr){
        // ILLEGAL_ATは重要な条件なので不正なデータが入らないよう厳密にSQLでも判定する
        int ret = jdbcTemplate.update(
                "UPDATE FROM CUSTOMERS SET NAME = ?, BIRTHDAY =?, UPDATE_AT = NOW() WHERE ID = ? AND ILLEGAL_AT = null",
                attr.getName(), attr.getBirthday(), customer.getId());
        return ret;
    }

    public MutableCustomer findMutableCustomer(int uid){
        CustomerRecord rec = jdbcTemplate.queryForObject(
                "SELECT ID, UID, NAME, BIRTHDAY, ILLEGAL_AT FROM CUSTOMERS WHERE UID = ?",
                CustomerRecord.class, uid);
        MutableCustomer customer = new MutableCustomer();
        customer.setId(rec.id());
        customer.setUid(rec.uid());
        customer.setName(rec.name());
        customer.setBirthday(rec.birthday());
        customer.setIllegalAt(rec.illegalAt());
        customer.setUpdateAt(rec.updateAt());
        return customer;
    }

    public int updateMutableCustomer(MutableCustomer customer){
        // MutableCustomerはミュータブル
        int ret = jdbcTemplate.update(
                "UPDATE FROM CUSTOMERS SET NAME = ?, BIRTHDAY, UPDATE_AT = NOW() WHERE ID = ?",
                customer.getId(), customer.getName(), customer.getBirthday());
        return ret;
    }
}
