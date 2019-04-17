package group.cc.df.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "df_cloned_dynamic_form")
public class DfClonedDynamicForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "clone_time")
    private Date cloneTime;

    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "form_id")
    private Integer formId;

    @Column(name = "new_form_id")
    private Integer newFormId;

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
     * @return clone_time
     */
    public Date getCloneTime() {
        return cloneTime;
    }

    /**
     * @param cloneTime
     */
    public void setCloneTime(Date cloneTime) {
        this.cloneTime = cloneTime;
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
     * @return new_form_id
     */
    public Integer getNewFormId() {
        return newFormId;
    }

    /**
     * @param newFormId
     */
    public void setNewFormId(Integer newFormId) {
        this.newFormId = newFormId;
    }
}