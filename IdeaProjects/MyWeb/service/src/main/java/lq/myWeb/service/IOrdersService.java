package lq.myWeb.service;

import lq.myWeb.domain.Order;

import java.util.List;

public interface IOrdersService {
    public List<Order> findAll(int page, int size);

    public Order findById(String id);
}
