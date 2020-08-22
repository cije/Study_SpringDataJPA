package com.ce.dao;

import com.ce.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <pre>
 *   需要继承两个接口
 *       JpaRepository<操作的实体类类型,实体类中主键属性的类型>
 *       JpaSpecificationExecutor<操作的实体类类型>
 * </pre>
 */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
}
