package group.cc.occ.service;
import group.cc.occ.model.User;
import group.cc.core.Service;
import group.cc.occ.model.dto.LoginUserDto;


/**
 * @author wangyuming
 * @date 2019/03/01
 */
public interface UserService extends Service<User> {
    public LoginUserDto login(String account, String password) throws Exception;
    public LoginUserDto register(User user)throws Exception;
}
