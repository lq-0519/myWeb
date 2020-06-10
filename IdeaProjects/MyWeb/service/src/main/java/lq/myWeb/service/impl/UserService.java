package lq.myWeb.service.impl;

import com.github.pagehelper.PageHelper;
import lq.myWeb.dao.IRoleDao;
import lq.myWeb.dao.IUserDao;
import lq.myWeb.domain.Role;
import lq.myWeb.domain.UserInfo;
import lq.myWeb.service.IUserService;
import lq.myWeb.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<UserInfo> findAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfo.setPassword(PasswordUtils.encoder(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(String userId) {
        return roleDao.findOtherRoles(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for (String roleId : roleIds) {
            userDao.addRoleToUser(userId, roleId);
        }
    }

    @Override
    public List<UserInfo> findByFuzzy(String condition, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return userDao.findByFuzzy("%"+condition+"%");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByUsername(username);
        //处理自己的用户对象封装成UserDetails
        //  User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
//        User user = new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(), userInfo.getStatus() == 1, true, true, true, getAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus() == 1, true, true, true, getAuthority(userInfo.getRoles()));
        return user;
    }

    //作用就是返回一个List集合，集合中装入的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }
}
