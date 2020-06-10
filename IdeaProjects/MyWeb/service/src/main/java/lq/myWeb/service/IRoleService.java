package lq.myWeb.service;

import lq.myWeb.domain.Permission;
import lq.myWeb.domain.Role;

import java.util.List;

/**
 * @author LQ
 * @create 2020-06-08 10:08
 */
public interface IRoleService {
    List<Role> findAll(Integer page, Integer size);

    void save(Role role);


    Role findById(String roleId);

    void delById(String id);

    List<Permission> findOtherPermissions(String id);

    void addPermissionToRole(String roleId, String[] permissionIds);

    List<Role> findByFuzzy(String condition, Integer page, Integer size);
}
