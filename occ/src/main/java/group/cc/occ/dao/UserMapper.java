package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.User;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    public List<User> getUserByAccount(String account);
}