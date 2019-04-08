package group.cc.occ.service;
import group.cc.occ.model.User;
import group.cc.core.Service;
import group.cc.occ.model.dto.LoginUserDto;
import sun.rmi.runtime.Log;

import java.util.List;


/**
 * @author wangyuming
 * @date 2019/03/01
 */
public interface UserService extends Service<User> {
    public LoginUserDto login(String account, String password) throws Exception;
    public LoginUserDto register(User user)throws Exception;
    public List<User> listByKey(String key, String value, LoginUserDto login);
    public List<User>findUserByIdOrName(String value);
    public List<User> findAllByLoginOrg(LoginUserDto login);

    public void saveRole(Integer userId, Integer roleId);

    public LoginUserDto switchOrg(Integer orgId, LoginUserDto login) throws Exception;

    public List<User> getUserByLoginOrgId(LoginUserDto loginUserDto);
}
