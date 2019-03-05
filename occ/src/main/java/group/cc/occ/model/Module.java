package group.cc.occ.model;

import javax.persistence.*;
import java.io.Serializable;

public class Module implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "moduleKey")
    private String modulekey;

    private Integer parent;

    private String url;

    @Column(name = "isSystem")
    private String issystem;

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
     * @return moduleKey
     */
    public String getModulekey() {
        return modulekey;
    }

    /**
     * @param modulekey
     */
    public void setModulekey(String modulekey) {
        this.modulekey = modulekey;
    }

    /**
     * @return parent
     */
    public Integer getParent() {
        return parent;
    }

    /**
     * @param parent
     */
    public void setParent(Integer parent) {
        this.parent = parent;
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
     * @return isSystem
     */
    public String getIssystem() {
        return issystem;
    }

    /**
     * @param issystem
     */
    public void setIssystem(String issystem) {
        this.issystem = issystem;
    }
}