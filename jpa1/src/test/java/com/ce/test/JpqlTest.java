package com.ce.test;

import com.ce.domain.Customer;
import com.ce.utils.JpaUtils;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * 测试jpql查询
 */
public class JpqlTest {

    /**
     * <pre>
     * 查询全部
     *      jpql: from com.ce.domain.Customer
     *      sql: SELECT * FROM cst_customer
     * </pre>
     */
    @Test
    public void testFindAll() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // 创建Query对象，Query对象才是执行jpql的对象
        String jpql = "from Customer";
        Query query = entityManager.createQuery(jpql);

        //发送查询，并封装结果集
        List list = query.getResultList();
        list.forEach(System.out::println);
        transaction.commit();
        entityManager.close();
    }

    /**
     * <pre>
     * 排序查询：根据id倒序排列
     *      jpql: from Customer order by custId desc
     *      sql:  SELECT * FROM cst_customer ORDER BY cust_id DESC
     * </pre>
     */
    @Test
    public void testOrder() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // 创建Query对象，Query对象才是执行jpql的对象
        String jpql = "from Customer order by custId desc";
        Query query = entityManager.createQuery(jpql);

        //发送查询，并封装结果集
        List list = query.getResultList();
        list.forEach(System.out::println);
        transaction.commit();
        entityManager.close();
    }

    /**
     * <pre>
     * 统计客户的总数
     *      jpql: select count(custId) from Customer
     *      sql:  SELECT COUNT(cust_id) from cst_customer
     * </pre>
     */
    @Test
    public void testCount() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // 创建Query对象，Query对象才是执行jpql的对象
        String jpql = "select count(custId) from Customer";
        Query query = entityManager.createQuery(jpql);

        //发送查询，并封装结果集
        Object result = query.getSingleResult();
        System.out.println(result);
        transaction.commit();
        entityManager.close();
    }

    /**
     * <pre>
     *     分页查询
     *          jpql: from Customer
     *          sql:  SELECT * from cst_customer LIMIT ?,?
     * </pre>
     */
    @Test
    public void testPaged() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // 创建Query对象，Query对象才是执行jpql的对象
        String jpql = "from Customer";
        Query query = entityManager.createQuery(jpql);

        // 对参数赋值  --分页参数
        // 起始索引
        query.setFirstResult(0);
        // 每页查询的条数
        query.setMaxResults(1);
        //发送查询，并封装结果集
        List list = query.getResultList();
        list.forEach(System.out::println);

        transaction.commit();
        entityManager.close();
    }

    /**
     * <pre>
     *     条件查询
     *          查询客户名称以“张”开头的客户
     *          jpql: from Customer where custName like ?
     *          sql:  SELECT * from cst_customer WHERE cust_name LIKE '张%'
     * </pre>
     */
    @Test
    public void testCondition() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // 创建Query对象，Query对象才是执行jpql的对象
        String jpql = "from Customer where custName like ?1";
        Query query = entityManager.createQuery(jpql);

        // 对参数赋值  --占位符参数
        query.setParameter(1, "张%");

        //发送查询，并封装结果集
        List list = query.getResultList();
        list.forEach(System.out::println);

        transaction.commit();
        entityManager.close();
    }
}
