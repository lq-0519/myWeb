package lq.myWeb.controller;

import com.github.pagehelper.PageInfo;
import lq.myWeb.domain.Order;
import lq.myWeb.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "5") Integer size) {
        ModelAndView mv = new ModelAndView();
        List<Order> orders = ordersService.findAll(page, size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(orders);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String ordersId) {
        ModelAndView mv = new ModelAndView();
        Order order = ordersService.findById(ordersId);
        mv.addObject("order", order);
        mv.setViewName("orders-show");
        return mv;
    }

}
