package group.cc.bms.service.impl;

import group.cc.bms.dao.UserMapper;
import group.cc.bms.model.User;
import group.cc.bms.service.UserService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author yuanli
 * @date 2018/11/01
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

}
