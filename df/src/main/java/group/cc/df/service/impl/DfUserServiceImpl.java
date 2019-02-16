package group.cc.df.service.impl;

import group.cc.df.dao.DfUserMapper;
import group.cc.df.model.DfUser;
import group.cc.df.service.DfUserService;
import group.cc.core.AbstractService;
import group.cc.df.utils.FormatValidateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;


/**
 * @author gxd
 * @date 2019/02/16
 */
@Service
@Transactional
public class DfUserServiceImpl extends AbstractService<DfUser> implements DfUserService {
    @Resource
    private DfUserMapper dfUserMapper;

    @Override
    public DfUser get(DfUser dfUser) {
        return dfUserMapper.selectOne(dfUser);
    }

    @Override
    public DfUser verifyUser(String identityCode, String verifyPassword) {
        DfUser user = new DfUser();

        if (FormatValidateUtil.validateEmail(identityCode)) {
            user.setEmail(identityCode);
        } else if (FormatValidateUtil.validatePhone(identityCode)) {
            user.setPhone(identityCode);
        } else {
            return null;
        }

        user.setPassword(verifyPassword);

        user = this.get(user);

        return user;
    }
}
