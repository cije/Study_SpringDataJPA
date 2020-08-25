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
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ObjectQueryTest {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;

    /**
     * 测试对象导航查询
     * 查询一个对象的同时，通过此对象查询他的关联对象
     * 一 查 多  默认使用的是 延迟加载 的形式查询
     * 可以 fetch 配置关联对象的加载方式
     */
    @Test
    @Transactional
    public void testQuery1() {
        Customer customer = customerDao.getOne(3L);
        Set<LinkMan> linkMEN = customer.getLinkMEN();
        linkMEN.forEach(System.out::println);
    }

    /**
     * 由 多 方 查 一 方 默认 立即加载
     */
    @Test
    @Transactional
    public void testQuery2() {
        LinkMan linkMan = linkManDao.getOne(5L);
        Customer customer = linkMan.getCustomer();
        System.out.println(customer);
    }
}
