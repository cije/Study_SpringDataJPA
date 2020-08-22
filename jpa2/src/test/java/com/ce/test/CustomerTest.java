package com.ce.test;

import com.ce.domain.Customer;
import com.ce.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerTest {

    @Autowired
    private CustomerService service;

    @Test
    public void testFindOne() {
        Customer customer = service.findById(5L);
        System.out.println(customer);
    }

    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setCustIndustry("c__e");
        customer.setCustName("c__e");
        service.save(customer);
    }

    @Test
    public void testUpdate() {
        Customer customer = new Customer();
        customer.setCustId(6L);
        customer.setCustIndustry("update");
        // customer.setCustName("update");
        service.update(customer);
    }

    @Test
    public void testDelete() {
        service.deleteById(6L);
    }

    @Test
    public void testFindAll() {
        List<Customer> customers = service.findAll();
        customers.forEach(System.out::println);
    }
}
