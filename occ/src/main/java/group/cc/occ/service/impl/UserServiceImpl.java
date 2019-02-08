package group.cc.occ.service.impl;

import group.cc.occ.dao.UserMapper;
import group.cc.occ.model.User;
import group.cc.occ.service.UserService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public User login(String account, String password) {
        List list = userMapper.getUserByAccount(account);
        System.out.println();
        return null;
    }
}
