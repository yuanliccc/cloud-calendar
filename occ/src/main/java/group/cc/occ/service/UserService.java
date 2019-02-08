package group.cc.occ.service;
import group.cc.occ.model.User;
import group.cc.core.Service;


/**
 * @author wangyuming
 * @date 2019/01/02
 */
public interface UserService extends Service<User> {
    public User login(String account, String password);
}
