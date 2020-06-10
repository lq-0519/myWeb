package lq.myWeb.dao;

import lq.myWeb.domain.Member;
import lq.myWeb.domain.Order;
import lq.myWeb.domain.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class,
                    one = @One(select = "lq.myWeb.dao.IProductDao.findById", fetchType = FetchType.LAZY))
    })
    public List<Order> findAll();

    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class,
                    one = @One(select = "lq.myWeb.dao.IProductDao.findById", fetchType = FetchType.LAZY)),
            @Result(property = "member", column = "memberId", javaType = Member.class,
                    one = @One(select = "lq.myWeb.dao.IMemberDao.findById", fetchType = FetchType.LAZY)),
            @Result(property = "travellers", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "lq.myWeb.dao.ITravellerDao.findById", fetchType = FetchType.LAZY)),
    })
    Order findById(String id);
}
