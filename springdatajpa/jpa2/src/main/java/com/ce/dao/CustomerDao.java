package com.ce.dao;

import com.ce.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <pre>
 *   需要继承两个接口
 *       JpaRepository<操作的实体类类型,实体类中主键属性的类型>
 *       JpaSpecificationExecutor<操作的实体类类型>
 * </pre>
 */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    /**
     * 根据客户名称查询客户
     * 使用jpql的形式
     */
    @Query(value = "from Customer where custName = ?1")
    Customer findByCustName(String custName);

    /**
     * 根据客户名称和客户id查询客户
     * 占位符 赋值的时候 和方法参数的位置保持一致
     * 可以指定占位符参数的位置
     * ?1 ?2 ?后面加索引的方式 指定此占位符的取值来源
     */
    @Query("from Customer where custName =?1 and custId = ?2")
    Customer findByCustNameAndId(String custName, Long id);

    /**
     * jpql根据id更新客户名称
     * 注解Query  代表的是进行查询
     * 注解Modifying   当前执行的是一个更新操作
     */
    @Query("update Customer set custName = ?2 where custId = ?1")
    @Modifying
    void updateCustNameById(long id, String custName);

    /**
     * 使用sql的形式查询
     * 查询全部
     */
    @Query(value = "select * from cst_customer", nativeQuery = true)
    List<Customer> sqlFindAll();

    /**
     * 条件查询全部
     */
    @Query(nativeQuery = true, value = "select  * from cst_customer where cust_name like ?1")
    List<Customer> sqlFindAll(String name);

    /**
     * 方法名称规则查询
     */
    List<Customer> findByCustNameLike(String name);


    /**
     * 使用客户名称模糊匹配和酷虎行业精准匹配的查询
     */
    Customer findByCustNameLikeAndCustIndustry(String name, String industry);
}