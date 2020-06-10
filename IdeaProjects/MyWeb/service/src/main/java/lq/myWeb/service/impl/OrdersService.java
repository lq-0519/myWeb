package lq.myWeb.service.impl;

import com.github.pagehelper.PageHelper;
import lq.myWeb.dao.IOrdersDao;
import lq.myWeb.domain.Order;
import lq.myWeb.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersService implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Order> findAll(int page, int size) {
        PageHelper.startPage(page, size);
        return ordersDao.findAll();
    }

    @Override
    public Order findById(String id) {
        return ordersDao.findById(id);
    }
}
