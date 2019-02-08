package group.cc.pcc.model.dto;

public class PccScheduleComplete {

    private Integer pccScheduleId;

    private Integer pccUserId;

    private Integer[] fileIds;

    private String text;

    public Integer getPccScheduleId() {
        return pccScheduleId;
    }

    public void setPccScheduleId(Integer pccScheduleId) {
        this.pccScheduleId = pccScheduleId;
    }

    public Integer getPccUserId() {
        return pccUserId;
    }

    public void setPccUserId(Integer pccUserId) {
        this.pccUserId = pccUserId;
    }

    public Integer[] getFileIds() {
        return fileIds;
    }

    public void setFileIds(Integer[] fileIds) {
        this.fileIds = fileIds;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
