package group.cc.pcc.model.builder;

import group.cc.pcc.model.PccScheduleText;

import java.util.ArrayList;
import java.util.List;

public class PccScheduleTextBuilder {

    public static PccScheduleText build(Integer pccScheduleId, Integer pccUserId, Integer pccTextId) {

        PccScheduleText pccScheduleText = new PccScheduleText();

        pccScheduleText.setPccTextId(pccTextId);
        pccScheduleText.setPccScheduleId(pccScheduleId);
        pccScheduleText.setPccUserId(pccUserId);

        return pccScheduleText;
    }

    public static List<PccScheduleText> build(Integer[] pccTextIds, Integer pccScheduleId, Integer pccUserId) {

        List<PccScheduleText> pccScheduleTexts = new ArrayList<>(pccTextIds.length);

        for(int i = 0; i < pccTextIds.length; i++) {
            pccScheduleTexts.add(build(pccScheduleId, pccUserId, pccTextIds[i]));
        }

        return pccScheduleTexts;
    }
}
