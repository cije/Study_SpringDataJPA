package om.ce.test;

import com.ce.dao.CustomerDao;
import com.ce.domain.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testSpec() {
        Specification<Customer> spec = new Specification<Customer>() {
            /**
             * 匿名内部类
             * <pre>
             * 自定义查询条件
             *      1.实现Specification接口（提供泛型，查询的对象类型）
             *      2.实现toPredicate方法（构造查询条件）
             *      3.需要借助方法参数中的两个参数
             *          root：获取需要查询的对象属性
             *          CriteriaBuilder：构造查询条件的，内部封装了很多的查询条件
             *  案例：根据客户名称查询，查询客户名为传智播客的客户
             *      查询条件
             *          1.查询方式
             *              CriteriaBuilder对象
             *          2.比较的属性名称
             *              root对象
             * </pre>
             */
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //1.获取比较的属性
                Path<Object> custName = root.get("custName");
                //2.构造查询条件
                // 进行精准匹配 (比较的属性，比较的属性的取值）
                /**
                 * 第一个参数：需要比较的属性
                 * 第二个参数：当前需要比较的取值
                 */
                return criteriaBuilder.equal(custName, "张三丰");
            }
        };
        Optional<Customer> optional = customerDao.findOne(spec);
        System.out.println(optional.orElse(null));
    }

    /**
     * 多条件查询
     * 案例：根据客户名（传智播客）和客户所属行业查询
     */
    @Test
    public void testSpec1() {
        Optional<Customer> optional = customerDao.findOne(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");
                Predicate pred1 = criteriaBuilder.equal(custName, "张三丰");
                Predicate pred2 = criteriaBuilder.equal(custIndustry, "张");
                return criteriaBuilder.and(pred1, pred2);
            }
        });
        System.out.println(optional.orElse(null));
    }

    /**
     * 模糊匹配
     */
    @Test
    public void testSpec3() {
        List<Customer> list = customerDao.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                // 需要制定比较的参数类型，再去比较 path.as(Object.class)
                return criteriaBuilder.like(custName.as(String.class), "张%");

            }
        });
        list.forEach(System.out::println);
    }

    /**
     * 模糊匹配+排序
     */
    @Test
    public void testSpec4() {
        // 创建排序对象
        // 第一个参数：排序的顺序 升序ASC 倒叙DESC
        Sort sort = Sort.by(Sort.Direction.ASC, "custId");
        List<Customer> list = customerDao.findAll((Specification<Customer>) (root, criteriaQuery, criteriaBuilder) -> {
            Path<Object> custName = root.get("custName");
            return criteriaBuilder.like(custName.as(String.class), "张%");
        }, sort);
        list.forEach(System.out::println);
    }

    /**
     * 分页查询
     * 参数Pageable：分页参数
     * 查询的页码，每页的条数
     * 返回：Page
     */
    @Test
    public void testSpec5() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Customer> page = customerDao.findAll(pageable);
        page.forEach(System.out::println);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
    }
}
