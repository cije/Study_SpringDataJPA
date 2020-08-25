package com.ce.test;

import com.ce.domain.Customer;
import com.ce.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerTest {

    @Autowired
    private CustomerService service;

    /**
     * 通过id查找
     */
    @Test
    @Transactional
    public void testFindOne() {
        Customer customer = service.findById(5L);
        System.out.println(customer);
    }

    /**
     * 保存操作
     */
    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setCustIndustry("c__e");
        customer.setCustName("c__e");
        service.save(customer);
    }

    /**
     * 更新操作
     */
    @Test
    public void testUpdate() {
        Customer customer = new Customer();
        customer.setCustId(6L);
        customer.setCustIndustry("update");
        customer.setCustName("update");
        service.update(customer);
    }

    /**
     * 通过id删除
     */
    @Test
    public void testDelete() {
        service.deleteById(6L);
    }

    /**
     * 查询所有
     */
    @Test
    public void testFindAll() {
        List<Customer> customers = service.findAll();
        customers.forEach(System.out::println);
    }

    /**
     * 查询总条数
     */
    @Test
    public void testCount() {
        long count = service.findTotal();
        System.out.println(count);
    }

    /**
     * 判断该id 是否存在
     */
    @Test
    public void testExist() {
        boolean flag = service.isExistsById(4L);
        System.out.println(flag);
    }
}
