package lq.myWeb.service.impl;

import com.github.pagehelper.PageHelper;
import lq.myWeb.dao.IProductDao;
import lq.myWeb.domain.Product;
import lq.myWeb.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}
