package lq.myWeb.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author LQ
 * @create 2020-06-10 16:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class IOrdersDao {
    @Autowired
//    private IOrdersDao ordersDao;
    @Test
    public void findById() {

    }
}
