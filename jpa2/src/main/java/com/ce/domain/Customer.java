package com.ce.domain;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.*;

/**
 * <pre>
 *     1.实体类和表的映射关系
 *          Entity
 *          Table
 *     2. 类中属性和表中字段的映射关系
 *          Id
 *          GeneratedValue
 *          Column
 * </pre>
 *
 * @author c__e
 */
@Entity
@Table(name = "cst_customer")
@Data
public class Customer {

    /**
     * 客户的主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId;
    /**
     * 客户名称
     */
    @Column(name = "cust_name")
    private String custName;
    /**
     * 客户来源
     */
    @Column(name = "cust_source")
    private String custSource;
    /**
     * 客户级别
     */
    @Column(name = "cust_level")
    private String custLevel;
    /**
     * 客户所属行业
     */
    @Column(name = "cust_industry")
    private String custIndustry;
    /**
     * 客户电话
     */
    @Column(name = "cust_phone")
    private String custPhone;
    /**
     * 客户地址
     */
    @Column(name = "cust_address")
    private String custAddress;
}
