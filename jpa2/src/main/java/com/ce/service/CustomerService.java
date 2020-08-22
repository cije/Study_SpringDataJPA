package com.ce.service;


import com.ce.domain.Customer;

import java.util.List;

public interface CustomerService {
    /**
     * 通过id查询customer
     *
     * @param id id
     * @return customer
     */
    Customer findById(Long id);

    /**
     * 保存
     */
    void save(Customer customer);

    /**
     * 更新
     */
    void update(Customer customer);

    /**
     * 通过id删除
     *
     * @param id 需要删除的id
     */
    void deleteById(Long id);

    /**
     * 查询所有
     */
    List<Customer> findAll();
}
