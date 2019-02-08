package group.cc.pcc.model.builder;

import group.cc.pcc.model.PccScheduleAdditionalType;

import java.util.ArrayList;
import java.util.List;

public class PccScheduleAdditionalTypeBuilder {

    public static PccScheduleAdditionalType build(Integer additionalInfoTypeId, Integer pccScheduleId) {

        PccScheduleAdditionalType pccScheduleAdditionalType = new PccScheduleAdditionalType();

        pccScheduleAdditionalType.setAdditionalInfoTypeId(additionalInfoTypeId);
        pccScheduleAdditionalType.setPccScheduleId(pccScheduleId);

        return pccScheduleAdditionalType;
    }

    public static List<PccScheduleAdditionalType> build(Integer[] additionalInfoTypeIds, Integer pccScheduleId) {

        List<PccScheduleAdditionalType> pccScheduleAdditionalTypes = new ArrayList<>(additionalInfoTypeIds.length);

        for(int i = 0; i < additionalInfoTypeIds.length; i++) {
            pccScheduleAdditionalTypes.add(build(additionalInfoTypeIds[i], pccScheduleId));
        }

        return pccScheduleAdditionalTypes;
    }
}
