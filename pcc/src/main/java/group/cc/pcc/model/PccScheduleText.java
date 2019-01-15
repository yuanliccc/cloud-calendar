package group.cc.pcc.model;

import javax.persistence.*;

@Table(name = "pcc_schedule_text")
public class PccScheduleText {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pcc_schedule_id")
    private Integer pccScheduleId;

    @Column(name = "pcc_user_id")
    private Integer pccUserId;

    @Column(name = "pcc_text_id")
    private Integer pccTextId;

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
     * @return pcc_user_id
     */
    public Integer getPccUserId() {
        return pccUserId;
    }

    /**
     * @param pccUserId
     */
    public void setPccUserId(Integer pccUserId) {
        this.pccUserId = pccUserId;
    }

    /**
     * @return pcc_text_id
     */
    public Integer getPccTextId() {
        return pccTextId;
    }

    /**
     * @param pccTextId
     */
    public void setPccTextId(Integer pccTextId) {
        this.pccTextId = pccTextId;
    }
}