package group.cc.pcc.model;

import javax.persistence.*;

@Table(name = "pcc_schedule_file")
public class PccScheduleFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pcc_user_id")
    private Integer pccUserId;

    @Column(name = "pcc_schedule_id")
    private Integer pccScheduleId;

    @Column(name = "pcc_file_id")
    private Integer pccFileId;

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
     * @return pcc_file_id
     */
    public Integer getPccFileId() {
        return pccFileId;
    }

    /**
     * @param pccFileId
     */
    public void setPccFileId(Integer pccFileId) {
        this.pccFileId = pccFileId;
    }
}