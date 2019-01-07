package group.cc.df.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "df_form_attachement")
public class DfFormAttachement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "form_field_id")
    private Integer formFieldId;

    @Column(name = "attachementName")
    private String attachementname;

    @Column(name = "attachementPath")
    private String attachementpath;

    @Column(name = "attachementSize")
    private Double attachementsize;

    @Column(name = "uploadTime")
    private Date uploadtime;

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
     * @return attachementName
     */
    public String getAttachementname() {
        return attachementname;
    }

    /**
     * @param attachementname
     */
    public void setAttachementname(String attachementname) {
        this.attachementname = attachementname;
    }

    /**
     * @return attachementPath
     */
    public String getAttachementpath() {
        return attachementpath;
    }

    /**
     * @param attachementpath
     */
    public void setAttachementpath(String attachementpath) {
        this.attachementpath = attachementpath;
    }

    /**
     * @return attachementSize
     */
    public Double getAttachementsize() {
        return attachementsize;
    }

    /**
     * @param attachementsize
     */
    public void setAttachementsize(Double attachementsize) {
        this.attachementsize = attachementsize;
    }

    /**
     * @return uploadTime
     */
    public Date getUploadtime() {
        return uploadtime;
    }

    /**
     * @param uploadtime
     */
    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }
}