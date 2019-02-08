package group.cc.pcc.model;

import javax.persistence.*;

@Table(name = "pcc_file")
public class PccFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String url;

    @Column(name = "url_type")
    private String urlType;

    private String type;

    private Double size;

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
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return url_type
     */
    public String getUrlType() {
        return urlType;
    }

    /**
     * @param urlType
     */
    public void setUrlType(String urlType) {
        this.urlType = urlType;
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
     * @return size
     */
    public Double getSize() {
        return size;
    }

    /**
     * @param size
     */
    public void setSize(Double size) {
        this.size = size;
    }
}