package lq.myWeb.dao;

import lq.myWeb.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author LQ
 * @create 2020-06-10 12:04
 */
public interface ISysLogDao {
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog);

    @Select("select * from sysLog order by visitTime desc")
    List<SysLog> findAll();

    @Select("select * from syslog where id like #{condition} or username like #{condition} or ip like #{condition} or url like #{condition} or method like #{condition}")
    List<SysLog> findByFuzzy(String s);
}
