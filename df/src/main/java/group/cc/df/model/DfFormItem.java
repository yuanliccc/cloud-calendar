package group.cc.df.model;

import javax.persistence.*;

@Table(name = "df_form_item")
public class DfFormItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "form_field_id")
    private Integer formFieldId;

    private String value;

    private String label;

    @Column(name = "item_index")
    private String itemIndex;

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
     * @return form_field_id
     */
    public Integer getFormFieldId() {
        return formFieldId;
    }

    /**
     * @param formFieldId
     */
    public void setFormFieldId(Integer formFieldId) {
        this.formFieldId = formFieldId;
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
     * @return item_index
     */
    public String getItemIndex() {
        return itemIndex;
    }

    /**
     * @param itemIndex
     */
    public void setItemIndex(String itemIndex) {
        this.itemIndex = itemIndex;
    }
}