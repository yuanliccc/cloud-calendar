package group.cc.df.service;
import group.cc.df.model.DfUser;
import group.cc.core.Service;

import java.util.Map;


/**
 * @author gxd
 * @date 2019/02/16
 */
public interface DfUserService extends Service<DfUser> {
    DfUser get(DfUser dfUser);

    DfUser verifyUser(String identityCode, String verifyPassword);
}
