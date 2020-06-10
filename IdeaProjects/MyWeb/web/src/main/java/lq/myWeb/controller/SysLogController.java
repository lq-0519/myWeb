package lq.myWeb.controller;

import com.github.pagehelper.PageInfo;
import lq.myWeb.domain.SysLog;
import lq.myWeb.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author LQ
 * @create 2020-06-10 12:25
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    ISysLogService sysLogService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer size) {
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogs = sysLogService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(sysLogs);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }

    @RequestMapping("/findByFuzzy")
    public ModelAndView findByFuzzy(@RequestParam(name = "condition") String condition, @RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer size) {
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogs = sysLogService.findByFuzzy(condition, page, size);
        PageInfo pageInfo = new PageInfo(sysLogs);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("condition", condition);
        mv.setViewName("syslog-list-search");
        return mv;
    }
}
