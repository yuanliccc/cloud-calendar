package group.cc.df.dao;

import group.cc.core.Mapper;
import group.cc.df.model.DfUser;

import java.util.List;

public interface DfUserMapper extends Mapper<DfUser> {
    /**
     * 根据用户名查询用户信息
     * 因为用户名唯一,所以查询出来的列表只有一个数据
     * @param userName
     * @return
     */
    List<DfUser> getUserByUserName(String userName);

    /**
     * 根据用户姓名进行模糊查询
     * @param name
     * @return
     */
    List<DfUser> findUserLikeName(String name);

}