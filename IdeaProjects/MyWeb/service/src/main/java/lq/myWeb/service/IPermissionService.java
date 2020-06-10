package lq.myWeb.service;

import lq.myWeb.domain.Permission;

import java.util.List;

/**
 * @author LQ
 * @create 2020-06-08 10:31
 */
public interface IPermissionService {
    List<Permission> findAll(Integer page, Integer size);

    void save(Permission permission);

    Permission findById(String id);

    void delById(String id);

    List<Permission> findByFuzzy(String condition, Integer page, Integer size);
}
