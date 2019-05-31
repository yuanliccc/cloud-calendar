package group.cc.occ.model;

import javax.persistence.*;

@Table(name = "system_config")
public class SystemConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "systemKey")
    private String systemkey;

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
     * @return systemKey
     */
    public String getSystemkey() {
        return systemkey;
    }

    /**
     * @param systemkey
     */
    public void setSystemkey(String systemkey) {
        this.systemkey = systemkey;
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