package com.ce.test;

import com.ce.dao.CustomerDao;
import com.ce.domain.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpqlTest {

    @Autowired
    private CustomerDao dao;

    @Test
    public void testFindByCustName() {
        Customer customer = dao.findByCustName("张三丰");
        System.out.println(customer);
    }

    @Test
    public void testFindByCustNameAndId() {
        Customer customer = dao.findByCustNameAndId("张三丰", 4L);
        System.out.println(customer);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdate() {
        dao.updateCustNameById(5L, "张张张");
    }

    @Test
    public void testSqlFindAll() {
        List<Customer> lists = dao.sqlFindAll("张%");
        lists.forEach(System.out::println);
    }

    /**
     * 测试方法命名规则的查询
     */
    @Test
    public void testNameFind() {
        List<Customer> lists = dao.findByCustNameLike("张%");
        lists.forEach(System.out::println);
    }
    @Test
    public void testNameFind1(){
        Customer customer = dao.findByCustNameLikeAndCustIndustry("张%", "张");
        System.out.println(customer);
    }
}
