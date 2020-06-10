package lq.myWeb.dao;

import lq.myWeb.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IRoleDao {

    @Select(" select * from role where id in ( select roleId from users_role where userId = #{userId} ) ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "lq.myWeb.dao.IPermissionDao.findPermissionByRoleId", fetchType = FetchType.LAZY)),
    })
    public List<Role> findRoleByUserId(String userId);

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(roleName, roleDesc) values(#{roleName}, #{roleDesc})")
    void save(Role role);

    @Select("select * from role where id = #{roleId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "lq.myWeb.dao.IPermissionDao.findPermissionByRoleId", fetchType = FetchType.LAZY)),
    })
    Role findById(String roleId);

    @Select(" select * from role where id in ( select roleId from role_permission where permissionId = #{permissionId} ) ")
    List<Role> findRoleByPermissionId(String permissionId);

    @Delete("delete from users_role where roleId=#{id}")
    void delFromUsers_Role(String id);

    @Delete("delete from role_permission where roleId=#{id}")
    void delFromRole_Permission(String id);

    @Delete("delete from role where id = #{id}")
    void delById(String id);

    @Select("select * from role where id not in (select roleId from users_role where userId=#{userId})")
    List<Role> findOtherRoles(String userId);
    @Select("select * from role where id like #{condition} or roleName like #{condition} or roleDesc like #{condition}")
    List<Role> findByFuzzy(String condition);
}
