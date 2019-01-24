package group.cc.pcc.model.dto;

import group.cc.pcc.model.PccSchedule;

public class PccScheduleDto {

    private PccSchedule pccSchedule;

    private Integer[] scheduleReceivers;

    private Integer[] remindServices;

    public Integer[] getAdditionalInfoTypes() {
        return additionalInfoTypes;
    }

    public void setAdditionalInfoTypes(Integer[] additionalInfoTypes) {
        this.additionalInfoTypes = additionalInfoTypes;
    }

    private Integer[] additionalInfoTypes;


    public PccSchedule getPccSchedule() {
        return pccSchedule;
    }

    public void setPccSchedule(PccSchedule pccSchedule) {
        this.pccSchedule = pccSchedule;
    }

    public Integer[] getScheduleReceivers() {
        return scheduleReceivers;
    }

    public void setScheduleReceivers(Integer[] scheduleReceivers) {
        this.scheduleReceivers = scheduleReceivers;
    }

    public Integer[] getRemindServices() {
        return remindServices;
    }

    public void setRemindServices(Integer[] remindServices) {
        this.remindServices = remindServices;
    }
}
