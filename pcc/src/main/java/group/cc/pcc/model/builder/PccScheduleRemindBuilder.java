package group.cc.pcc.model.builder;

import group.cc.pcc.model.PccScheduleRemind;

import java.util.ArrayList;
import java.util.List;

public class PccScheduleRemindBuilder {

    public static PccScheduleRemind build(Integer pccRemindServiceId, Integer pccScheduleId) {

        PccScheduleRemind pccScheduleRemind = new PccScheduleRemind();

        pccScheduleRemind.setPccRemindServiceId(pccRemindServiceId);
        pccScheduleRemind.setPccScheduleId(pccScheduleId);

        return pccScheduleRemind;
    }

    public static List<PccScheduleRemind> build(Integer[] pccRemindServiceIds, Integer pccScheduleId) {

        List<PccScheduleRemind> pccScheduleReminds = new ArrayList<>(pccRemindServiceIds.length);

        for(int i = 0; i < pccRemindServiceIds.length; i++) {
            pccScheduleReminds.add(build(pccRemindServiceIds[i], pccScheduleId));
        }

        return pccScheduleReminds;
    }

}
