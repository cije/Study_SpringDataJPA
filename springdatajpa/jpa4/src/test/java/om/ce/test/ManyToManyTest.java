package om.ce.test;

import com.ce.dao.RoleDao;
import com.ce.dao.UserDao;
import com.ce.domain.Role;
import com.ce.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToManyTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    /**
     * 保存一个用户，保存一个角色
     * 多对多放弃维护权 被动的一方放弃
     */
    @Test
    public void testAdd() {
        User user = new User();
        user.setUsername("小明");

        Role role = new Role();
        role.setRoleName("java程序员");

        // 配置用户到角色关系，可以对中间表进行维护
        user.getRoles().add(role);
        // 配置角色到用户关系，可以对中间表进行维护
        role.getUsers().add(user);

        roleDao.save(role);
        userDao.save(user);
    }

    /**
     * .
     * 级联添加：保存一个用户的同时保存用户的关联角色
     */
    @Test
    public void testCascadeAdd() {
        User user = new User();
        user.setUsername("小明1");

        Role role = new Role();
        role.setRoleName("java程序员1");

        // 配置用户到角色关系，可以对中间表进行维护
        user.getRoles().add(role);

        userDao.save(user);
    }

    /**
     * 级联删除
     */
    @Test
    public void testCascadeDelete() {
        Optional<User> optional = userDao.findById(5L);
        optional.ifPresent(user -> userDao.delete(user));
    }
}
