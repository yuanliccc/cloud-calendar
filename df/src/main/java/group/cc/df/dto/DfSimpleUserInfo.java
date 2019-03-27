package group.cc.df.dto;

/**
 * Created by 高旭东 on 2019/3/26.
 */
public class DfSimpleUserInfo {
    private Integer id;

    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DfSimpleUserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
