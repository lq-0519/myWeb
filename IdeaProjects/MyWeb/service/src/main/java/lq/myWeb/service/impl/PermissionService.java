package lq.myWeb.service.impl;

import com.github.pagehelper.PageHelper;
import lq.myWeb.dao.IPermissionDao;
import lq.myWeb.domain.Permission;
import lq.myWeb.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LQ
 * @create 2020-06-08 10:31
 */
@Service
@Transactional
public class PermissionService implements IPermissionService {
    @Autowired
    IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(String id) {
        return permissionDao.findById(id);
    }

    @Override
    public void delById(String id) {
        permissionDao.delFromRole_Permission(id);
        permissionDao.delById(id);
    }

    @Override
    public List<Permission> findByFuzzy(String condition, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return permissionDao.findByFuzzy("%" + condition + "%");
    }
}
