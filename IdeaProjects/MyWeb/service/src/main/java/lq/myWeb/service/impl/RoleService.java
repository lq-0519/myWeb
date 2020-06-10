package lq.myWeb.service.impl;

import com.github.pagehelper.PageHelper;
import lq.myWeb.dao.IPermissionDao;
import lq.myWeb.dao.IRoleDao;
import lq.myWeb.domain.Permission;
import lq.myWeb.domain.Role;
import lq.myWeb.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LQ
 * @create 2020-06-08 10:08
 */
@Service
@Transactional
public class RoleService implements IRoleService {
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Role> findAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(String roleId) {
        return roleDao.findById(roleId);
    }

    @Override
    public void delById(String id) {
        roleDao.delFromUsers_Role(id);
        roleDao.delFromRole_Permission(id);
        roleDao.delById(id);
    }

    @Override
    public List<Permission> findOtherPermissions(String id) {
        return permissionDao.findOtherPermissions(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            permissionDao.addPermissionToRole(roleId, permissionId);
        }
    }

    @Override
    public List<Role> findByFuzzy(String condition, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return roleDao.findByFuzzy("%" + condition + "%");
    }
}
