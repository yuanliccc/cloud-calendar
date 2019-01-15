package group.cc.df.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "df_form_attachment")
public class DfFormAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "form_field_id")
    private Integer formFieldId;

    @Column(name = "attachment_name")
    private String attachmentName;

    @Column(name = "attachment_path")
    private String attachmentPath;

    @Column(name = "attachment_size")
    private Double attachmentSize;

    @Column(name = "upload_time")
    private Date uploadTime;

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
     * @return attachment_name
     */
    public String getAttachmentName() {
        return attachmentName;
    }

    /**
     * @param attachmentName
     */
    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    /**
     * @return attachment_path
     */
    public String getAttachmentPath() {
        return attachmentPath;
    }

    /**
     * @param attachmentPath
     */
    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    /**
     * @return attachment_size
     */
    public Double getAttachmentSize() {
        return attachmentSize;
    }

    /**
     * @param attachmentSize
     */
    public void setAttachmentSize(Double attachmentSize) {
        this.attachmentSize = attachmentSize;
    }

    /**
     * @return upload_time
     */
    public Date getUploadTime() {
        return uploadTime;
    }

    /**
     * @param uploadTime
     */
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}