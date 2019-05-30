package group.cc.occ.model;

import javax.persistence.*;
import java.util.List;

@Table(name = "user_work_arrange")
public class UserWorkArrange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userId")
    private Integer userid;

    @Column(name = "workArrangeId")
    private Integer workarrangeid;

    @Column(name = "isComplete")
    private String iscomplete;

    @Column(name = "isRepeat")
    private String isrepeat;

    @Column(name = "completeYear")
    private String completeyear;

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
     * @return userId
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return workArrangeId
     */
    public Integer getWorkarrangeid() {
        return workarrangeid;
    }

    /**
     * @param workarrangeid
     */
    public void setWorkarrangeid(Integer workarrangeid) {
        this.workarrangeid = workarrangeid;
    }

    /**
     * @return isComplete
     */
    public String getIscomplete() {
        return iscomplete;
    }

    /**
     * @param iscomplete
     */
    public void setIscomplete(String iscomplete) {
        this.iscomplete = iscomplete;
    }

    /**
     * @return isRepeat
     */
    public String getIsrepeat() {
        return isrepeat;
    }

    /**
     * @param isrepeat
     */
    public void setIsrepeat(String isrepeat) {
        this.isrepeat = isrepeat;
    }

    /**
     * @return completeYear
     */
    public String getCompleteyear() {
        return completeyear;
    }

    /**
     * @param completeyear
     */
    public void setCompleteyear(String completeyear) {
        this.completeyear = completeyear;
    }
}