package lq.myWeb.controller;

import com.github.pagehelper.PageInfo;
import lq.myWeb.domain.Permission;
import lq.myWeb.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author LQ
 * @create 2020-06-08 10:32
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "5") Integer size) {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = permissionService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(permissions);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Permission permission) {
        permissionService.save(permission);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id) {
        ModelAndView mv = new ModelAndView();
        Permission permission = permissionService.findById(id);
        mv.addObject("permission", permission);
        mv.setViewName("permission-show");
        return mv;
    }

    @RequestMapping("/delById")
    public String delById(@RequestParam(name = "id") String id) {
        permissionService.delById(id);
        return "redirect:findAll";
    }

    @RequestMapping("/findByFuzzy")
    public ModelAndView findByFuzzy(@RequestParam(name = "condition") String condition, @RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "5") Integer size) {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = permissionService.findByFuzzy(condition, page, size);
        PageInfo pageInfo = new PageInfo(permissions);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("condition", condition);
        mv.setViewName("permission-list-search");
        return mv;
    }
}
