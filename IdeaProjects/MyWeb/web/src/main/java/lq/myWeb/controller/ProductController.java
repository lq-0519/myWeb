package lq.myWeb.controller;

import com.github.pagehelper.PageInfo;
import lq.myWeb.domain.Product;
import lq.myWeb.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/findAll")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "5") Integer size) {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(products);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("product-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Product product) {
        productService.save(product);
        return "redirect:findAll";
    }
}
