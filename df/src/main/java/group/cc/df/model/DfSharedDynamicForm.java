package group.cc.df.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "df_shared_dynamic_form")
public class DfSharedDynamicForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "holder_id")
    private Integer holderId;

    @Column(name = "form_id")
    private Integer formId;

    @Column(name = "share_time")
    private Date shareTime;

    private String state;

    @Column(name = "cloned_count")
    private Integer clonedcount;

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
     * @return holder_id
     */
    public Integer getHolderId() {
        return holderId;
    }

    /**
     * @param holderId
     */
    public void setHolderId(Integer holderId) {
        this.holderId = holderId;
    }

    /**
     * @return form_id
     */
    public Integer getFormId() {
        return formId;
    }

    /**
     * @param formId
     */
    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    /**
     * @return share_time
     */
    public Date getShareTime() {
        return shareTime;
    }

    /**
     * @param shareTime
     */
    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }

    /**
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return clonedCount
     */
    public Integer getClonedcount() {
        return clonedcount;
    }

    /**
     * @param clonedcount
     */
    public void setClonedcount(Integer clonedcount) {
        this.clonedcount = clonedcount;
    }
}