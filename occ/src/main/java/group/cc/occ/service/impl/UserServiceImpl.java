package group.cc.occ.service.impl;

import group.cc.occ.dao.UserMapper;
import group.cc.occ.model.User;
import group.cc.occ.service.UserService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author wangyuming
 * @date 2019/01/02
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String account, String password) throws Exception{
        User user = this.findBy("account", account);
        if(user == null)
            throw new Exception("账号不存在！");
        else {
            if(!password.equals(user.getPassword())
            throw new Exception("密码错误！");
        }

        return user;
    }

    @Override
    public User register(User user) throws Exception{
        if(user.getAccount() == null || user.getPassword() == null)
            return null;
        List list = userMapper.getUserByAccount(user.getAccount());

        if(list != null && list.size() != 0)
            throw new Exception("账号已存在！");

        this.save(user);
        User t = this.findBy("account", user.getAccount());
        return t;
    }
}
