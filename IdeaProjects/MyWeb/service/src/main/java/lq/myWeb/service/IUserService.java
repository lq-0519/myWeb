package lq.myWeb.service;

import lq.myWeb.domain.Role;
import lq.myWeb.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<UserInfo> findAll(Integer page, Integer size);

    void save(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findOtherRoles(String userId);

    void addRoleToUser(String userId, String[] roleIds);

    List<UserInfo> findByFuzzy(String condition, Integer page, Integer size);
}
