package group.cc.df.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "df_collect_form")
public class DfCollectForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "form_id")
    private Integer formId;

    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "submit_time")
    private Date submitTime;

    @Column(name = "state")
    private String state;

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
     * @return employee_id
     */
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId
     */
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return submit_time
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * @param submitTime
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}