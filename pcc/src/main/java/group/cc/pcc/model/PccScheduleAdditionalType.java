package group.cc.pcc.model;

import javax.persistence.*;

@Table(name = "pcc_schedule_additional_type")
public class PccScheduleAdditionalType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pcc_schedule_id")
    private Integer pccScheduleId;

    @Column(name = "additional_info_type_id")
    private Integer additionalInfoTypeId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return pcc_schedule_id
     */
    public Integer getPccScheduleId() {
        return pccScheduleId;
    }

    /**
     * @param pccScheduleId
     */
    public void setPccScheduleId(Integer pccScheduleId) {
        this.pccScheduleId = pccScheduleId;
    }

    /**
     * @return additional_info_type_id
     */
    public Integer getAdditionalInfoTypeId() {
        return additionalInfoTypeId;
    }

    /**
     * @param additionalInfoTypeId
     */
    public void setAdditionalInfoTypeId(Integer additionalInfoTypeId) {
        this.additionalInfoTypeId = additionalInfoTypeId;
    }
}