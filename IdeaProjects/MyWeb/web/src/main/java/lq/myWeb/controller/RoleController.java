package lq.myWeb.controller;

import com.github.pagehelper.PageInfo;
import lq.myWeb.domain.Permission;
import lq.myWeb.domain.Role;
import lq.myWeb.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author LQ
 * @create 2020-06-08 10:06
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "5") Integer size) {
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(roles);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id") String roleId) {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        mv.addObject("role", role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("delById")
    public String delById(@RequestParam(name = "id") String id) {
        roleService.delById(id);
        return "redirect:findAll";
    }

    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id") String id) {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);
        List<Permission> permissions = roleService.findOtherPermissions(id);
        mv.addObject("role", role);
        mv.addObject("permissions", permissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId") String roleId, @RequestParam(name = "ids") String[] permissionIds) {
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findRoleByIdAndAllPermission?id=" + roleId;
    }

    @RequestMapping("/findByFuzzy")
    public ModelAndView findByFuzzy(@RequestParam(name = "condition") String condition, @RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "5") Integer size) {
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findByFuzzy(condition, page, size);
        PageInfo pageInfo = new PageInfo(roles);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("condition", condition);
        mv.setViewName("role-list-search");
        return mv;
    }
}
