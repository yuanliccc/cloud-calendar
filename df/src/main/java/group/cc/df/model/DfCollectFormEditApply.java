package group.cc.df.model;

import javax.persistence.*;

@Table(name = "df_collect_form_edit_apply")
public class DfCollectFormEditApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "collect_form_id")
    private Integer collectFormId;

    @Column(name = "employee_id")
    private Integer employeeId;

    private String state;

    private String message;

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
     * @return collect_form_id
     */
    public Integer getCollectFormId() {
        return collectFormId;
    }

    /**
     * @param collectFormId
     */
    public void setCollectFormId(Integer collectFormId) {
        this.collectFormId = collectFormId;
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
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}