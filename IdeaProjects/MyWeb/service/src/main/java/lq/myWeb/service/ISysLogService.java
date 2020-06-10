package lq.myWeb.service;

import lq.myWeb.domain.SysLog;

import java.util.List;

/**
 * @author LQ
 * @create 2020-06-10 11:59
 */
public interface ISysLogService {
    void save(SysLog sysLog);

    List<SysLog> findAll(Integer page, Integer size);

    List<SysLog> findByFuzzy(String condition, Integer page, Integer size);
}
