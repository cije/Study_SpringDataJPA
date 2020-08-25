package com.ce.service.impl;

import com.ce.dao.CustomerDao;
import com.ce.domain.Customer;
import com.ce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public Customer findById(Long id) {
        // return customerDao.findById(id).orElse(null);
        return customerDao.getOne(id);
    }

    /**
     * save 保存或者更新
     * 根据传递的对象是否存在主键id
     * 如果没有id属性，保存
     * 如果有id 根据id查找数据 能找到数据 更新
     * 找不到数据 保存
     */
    /**
     * customer 没有id或者找不到id 保存
     */
    @Override
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    /**
     * customer 有id且能通过id找到  更新
     */
    @Override
    public void update(Customer customer) {
        Assert.notNull(customer.getCustId(), "id为空，无法更新!");
        customerDao.save(customer);
    }

    @Override
    public void deleteById(Long id) {
        customerDao.deleteById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public long findTotal() {
        return customerDao.count();
    }

    @Override
    public boolean isExistsById(long id) {
        return customerDao.existsById(id);
    }
}
