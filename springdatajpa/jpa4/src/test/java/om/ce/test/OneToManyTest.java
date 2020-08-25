package om.ce.test;

import com.ce.dao.CustomerDao;
import com.ce.dao.LinkManDao;
import com.ce.domain.Customer;
import com.ce.domain.LinkMan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;

    /**
     * 保存一个客户。保存一个联系人
     */
    @Test
    public void testAdd() {
        // 创建一个客户，一个联系人
        Customer customer = new Customer();
        customer.setCustName("百度");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李");

        /**
         * 从客户的角度 发送两次insert 然后 update
         * 由于 一 的一方可以维护外键，会发送update语句
         * 放弃外键维护权
         */
        customer.getLinkMEN().add(linkMan);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    @Test
    public void testAdd1() {
        // 创建一个客户，一个联系人
        Customer customer = new Customer();
        customer.setCustName("百度1");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李1");

        /**
         * 从联系人的角度 只发送两次insert
         */
        linkMan.setCustomer(customer);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    /**
     * 级联添加：保存一个客户的同时，保存客户中的所有人
     * 需要在操作主体的实体类上，配置cascade
     */
    @Test
    public void testAdd2() {
        // 创建一个客户，一个联系人
        Customer customer = new Customer();
        customer.setCustName("百度2");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李2");

        linkMan.setCustomer(customer);
        customer.getLinkMEN().add(linkMan);

        customerDao.save(customer);
    }

    /**
     * 级联删除：
     * 删除2号客户的同时，删除2号客户的所有联系人
     */
    @Test
    public void testCascadeRemove() {
        // 1.查询客户
        Optional<Customer> optional = customerDao.findById(2L);
        optional.ifPresent(customer -> customerDao.delete(customer));
    }
}
