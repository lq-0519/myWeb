package lq.myWeb.service;

import lq.myWeb.domain.Product;

import java.util.List;

public interface IProductService {
    public List<Product> findAll(Integer page, Integer size);

    void save(Product product);
}
