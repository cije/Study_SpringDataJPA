package com.ce.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 解决实体管理器工厂的浪费资源和耗时问题
 * 通过静态代码块的形式，当程序第一次访问此工具类时，创建一个公共的`EntityManagerFactory`对象
 * <p>
 * 第一次访问getEntityManager方法，经过静态代码块创建一个EntityManagerFactory对象，在调用方法创建一个EntityManagerF对象
 * 第二次getEntityManager方法，直接通过已经创建好的EntityManagerFactory对象，创建EntityManager对象
 */
public class JpaUtils {
    private static final String PERSISTENCE_UNIT_NAME = "myJpa";

    private static final EntityManagerFactory factory;

    static {
        //1.加载配置文件，创建`EntityManagerFactory`对象
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);


    }

    /**
     * 获取EntityManager对象
     */
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
