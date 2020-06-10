package lq.myWeb.service.impl;

import com.github.pagehelper.PageHelper;
import lq.myWeb.dao.ISysLogDao;
import lq.myWeb.domain.SysLog;
import lq.myWeb.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LQ
 * @create 2020-06-10 11:59
 */
@Service
@Transactional
public class SysLogService implements ISysLogService {
    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return sysLogDao.findAll();
    }

    @Override
    public List<SysLog> findByFuzzy(String condition, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return sysLogDao.findByFuzzy("%"+condition+"%");
    }
}
