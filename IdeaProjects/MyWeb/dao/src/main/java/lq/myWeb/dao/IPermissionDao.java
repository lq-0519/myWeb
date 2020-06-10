package lq.myWeb.dao;

import lq.myWeb.domain.Permission;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author LQ
 * @create 2020-06-07 17:57
 */
public interface IPermissionDao {
    @Select(" select * from permission where id in ( select permissionId from role_permission where roleId = #{roleId} ) ")
    public List<Permission> findPermissionByRoleId(String roleId);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(permissionName, url) values(#{permissionName}, #{url})")
    void save(Permission permission);

    @Select("select * from permission where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "permissionName", column = "permissionName"),
            @Result(property = "url", column = "url"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "lq.myWeb.dao.IRoleDao.findRoleByPermissionId", fetchType = FetchType.LAZY))
    })
    Permission findById(String id);

    @Delete("delete from role_permission where permissionId=#{id}")
    void delFromRole_Permission(String id);

    @Delete("delete from permission where id=#{id}")
    void delById(String id);

    @Select(" select * from permission where id not in ( select permissionId from role_permission where roleId=#{id} ) ")
    List<Permission> findOtherPermissions(String id);

    @Insert("insert into role_permission values(#{permissionId}, #{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);

    @Select("select * from permission where id like #{condition} or permissionName like #{condition} or url like #{condition}")
    List<Permission> findByFuzzy(String condition);
}
