package group.cc.df.model;

import javax.persistence.*;

@Table(name = "df_form_field")
public class DfFormField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "form_id")
    private Integer formId;

    private String label;

    private String name;

    private String type;

    private String value;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "display_index")
    private Integer displayIndex;

    private String key;

    private String model;

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
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
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

    /**
     * @return parent_id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return display_index
     */
    public Integer getDisplayIndex() {
        return displayIndex;
    }

    /**
     * @param displayIndex
     */
    public void setDisplayIndex(Integer displayIndex) {
        this.displayIndex = displayIndex;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "DfFormField{" +
                "id=" + id +
                ", formId=" + formId +
                ", label='" + label + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", parentId=" + parentId +
                ", displayIndex=" + displayIndex +
                ", key='" + key + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}