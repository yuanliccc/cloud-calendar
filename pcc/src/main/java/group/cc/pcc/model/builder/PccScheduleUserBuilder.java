package group.cc.pcc.model.builder;

import group.cc.pcc.model.PccScheduleUser;

import java.util.ArrayList;
import java.util.List;

public class PccScheduleUserBuilder {

    public static PccScheduleUser build(Integer pccUserId, Integer pccScheduleId) {

        PccScheduleUser pccScheduleUser = new PccScheduleUser();

        pccScheduleUser.setPccUserId(pccUserId);
        pccScheduleUser.setPccScheduleId(pccScheduleId);

        return pccScheduleUser;
    }

    public static List<PccScheduleUser> build(Integer[] pccUserIds, Integer pccScheduleId) {

        List<PccScheduleUser> pccScheduleUsers = new ArrayList<>(pccUserIds.length);

        for(int i = 0; i < pccUserIds.length; i++) {
            pccScheduleUsers.add(build(pccUserIds[i], pccScheduleId));
        }

        return pccScheduleUsers;
    }
}
