package com.ce.domain;

import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    /**
     * 客户和联系人之间的关系（一对多）
     * <pre>
     *     使用注解的形式配置多表关系
     *          1.声明关系
     *              \@OneToMany：配置一对多关系
     *                  targetEntity ：对方对象的字节码对象
     *          2.配置外键（中间表）
     *              \@JoinColumn：配置外键
     *                  name：外键字段名称
     *                  referencedColumnName：参照的主表的关键字段名称
     * </pre>
     */
    // @OneToMany(targetEntity = LinkMan.class)
    // @JoinColumn(name = "lkm_cust_id", referencedColumnName = "cust_id")
    /**
     * <pre>
     * 放弃外键维护权
     *      mappedBy ：对方配置关系的属性名称
     * cascade：配置级联（可以配置到设置多表的映射关系的注解上）
     *      CascadeType.ALL     所有
     *      CascadeType.MERGE   更新
     *      CascadeType.PERSIST 保存
     *      CascadeType.REMOVE  删除
     * </pre>
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<LinkMan> linkMEN = new HashSet<>();


    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public Set<LinkMan> getLinkMEN() {
        return linkMEN;
    }

    public void setLinkMEN(Set<LinkMan> linkMEN) {
        this.linkMEN = linkMEN;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custAddress='" + custAddress + '\'' +
                '}';
    }
}
