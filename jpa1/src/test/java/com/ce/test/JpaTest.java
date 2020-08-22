package com.ce.test;

import com.ce.domain.Customer;
import com.ce.utils.JpaUtils;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * <pre>
 *  Jpa的操作步骤
 *    1.加载配置文件创建工厂（实体管理类工厂）对象
 *    2.通过实体管理类工厂获取实体管理器
 *    3.获取事务对象，开启事务
 *    4.完成增删改查操作
 *    5.提交（回滚）事务
 *    6.释放资源
 * </pre>
 */
public class JpaTest {

    /**
     * 测试保存
     * em.persist(customer);
     */
    @Test
    public void testSave() {
        // // 1.加载配置文件创建工厂（实体管理类工厂）对象
        // EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        // // 2.通过实体管理类工厂获取实体管理器
        // EntityManager em = factory.createEntityManager();
        EntityManager em = JpaUtils.getEntityManager();
        // 3.获取事务对象，开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // 4. 完成增删改查操作
        Customer customer = new Customer();
        customer.setCustName("测试保存1");
        customer.setCustIndustry("保存1");
        // 保存
        em.persist(customer);
        // 5.提交（回滚）事务
        tx.commit();
        // 6.释放资源
        em.close();
    }

    /**
     * 根据id查询客户
     * <pre>
     * entityManager.find(class,id)
     *      class： 查询数据的结果需要包装的实体类类型的字节码
     *      id： 查询的主键的取值
     *   1. 查询的对象就是当前客户对象本身
     *   2. 在调用find方法的时候，就会发送sql语句查询数据库
     * </pre>
     */
    @Test
    public void testFind() {
        // 通过工具类获取 entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();
        // 开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        // 操作
        Customer customer = entityManager.find(Customer.class, (long) 1);
        System.out.println(customer);
        // 提交事务
        tx.commit();
        // 释放资源
        entityManager.close();
    }

    /**
     * 根据id查询客户
     * <pre>
     * entityManager.getReference(class,id)
     *      class： 查询数据的结果需要包装的实体类类型的字节码
     *      id： 查询的主键的取值
     *   1. 获取的对象是一个动态代理对象
     *   2. 调用getReference方法不会立即发送sql语句查询数据库
     *      * 当调用查询结果对象的时候，才会发送查询的sql语句，什么时候用，什么时候发送SQL语句查询数据库
     *   延迟加载（懒加载）
     * </pre>
     */
    @Test
    public void testReference() {
        // 通过工具类获取 entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();
        // 开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        // 操作
        Customer customer = entityManager.getReference(Customer.class, (long) 1);
        System.out.println(customer);
        // 提交事务
        tx.commit();
        // 释放资源
        entityManager.close();
    }

    /**
     * 删除
     */
    @Test
    public void testRemove() {
        // 通过工具类获取 entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();
        // 开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        // 操作
        Customer customer = entityManager.find(Customer.class, (long) 1);
        entityManager.remove(customer);
        // 提交事务
        tx.commit();
        // 释放资源
        entityManager.close();
    }

    /**
     * 更新
     */
    @Test
    public void testUpdate() {
        // 通过工具类获取 entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();
        // 开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        // 操作
        Customer customer = entityManager.find(Customer.class, (long) 2);
        customer.setCustName("更新测试");
        customer.setCustIndustry("更新");
        customer.setCustPhone("123456");
        Customer mergeCustomer = entityManager.merge(customer);
        System.out.println(mergeCustomer);

        // 提交事务
        tx.commit();
        // 释放资源
        entityManager.close();
    }
}
