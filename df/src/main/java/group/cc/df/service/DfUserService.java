package group.cc.df.service;
import group.cc.df.dto.DfLoginUserInfoDTO;
import group.cc.df.model.DfUser;
import group.cc.core.Service;

import java.util.List;


/**
 * @author gxd
 * @date 2019/03/25
 */
public interface DfUserService extends Service<DfUser> {

    /**
     * 根据用户名查询用户信息
     * 返回的用户信息只有一个,因此直接去list列表中的第一个元素即可
     * @param userName
     * @return
     */
    List<DfUser> getUserByUserName(String userName);

    /**
     *  根据用户姓名进行模糊查询
     * @param name
     * @return
     */
    List<DfUser> findUserLikeName(String name);
}
