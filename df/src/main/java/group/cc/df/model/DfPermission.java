package group.cc.df.model;

import javax.persistence.*;

@Table(name = "df_permission")
public class DfPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "permission_name")
    private String permissionName;

    private String description;

    @Column(name = "module_id")
    private Integer moduleId;

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
     * @return permission_name
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * @param permissionName
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return module_id
     */
    public Integer getModuleId() {
        return moduleId;
    }

    /**
     * @param moduleId
     */
    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }
}