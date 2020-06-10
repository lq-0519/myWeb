package lq.myWeb.dao;

import lq.myWeb.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {
    @Select(" select * from traveller where id in ( select travellerId from order_traveller where orderId = #{id} )")
    public List<Traveller> findById(String id);
}
