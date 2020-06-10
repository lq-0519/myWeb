package lq.myWeb.controller;

import com.github.pagehelper.PageInfo;
import lq.myWeb.domain.Role;
import lq.myWeb.domain.UserInfo;
import lq.myWeb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "5") Integer size) {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(userList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(UserInfo userInfo) {
        userService.save(userInfo);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id") String id) {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id") String userId) {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(userId);
        List<Role> roles = userService.findOtherRoles(userId);
        mv.addObject("user", userInfo);
        mv.addObject("roleList", roles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId") String userId, @RequestParam(name = "ids") String[] roleIds) {
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findUserByIdAndAllRole?id=" + userId;
    }

    @RequestMapping("/findByFuzzy")
    public ModelAndView findByFuzzy(@RequestParam(name = "condition") String condition, @RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "5") Integer size) {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfos = userService.findByFuzzy(condition, page, size);
        PageInfo pageInfo = new PageInfo(userInfos);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("condition", condition);
        mv.setViewName("user-list-search");
        return mv;
    }
}
