package group.cc.df.model;

import javax.persistence.*;

@Table(name = "df_form_field")
public class DfFormField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dynamic_form_id")
    private Integer dynamicFormId;

    private String label;

    private String name;

    @Column(name = "form_type_id")
    private Integer formTypeId;

    private String value;

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
     * @return dynamic_form_id
     */
    public Integer getDynamicFormId() {
        return dynamicFormId;
    }

    /**
     * @param dynamicFormId
     */
    public void setDynamicFormId(Integer dynamicFormId) {
        this.dynamicFormId = dynamicFormId;
    }

    /**
     * @return label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return form_type_id
     */
    public Integer getFormTypeId() {
        return formTypeId;
    }

    /**
     * @param formTypeId
     */
    public void setFormTypeId(Integer formTypeId) {
        this.formTypeId = formTypeId;
    }

    /**
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }
}