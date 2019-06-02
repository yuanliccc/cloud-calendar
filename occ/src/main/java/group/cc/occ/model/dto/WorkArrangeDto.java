package group.cc.occ.model.dto;

import java.io.Serializable;
import java.util.List;

public class WorkArrangeDto implements Serializable {
    private Integer workarrangeid;
    private List<Integer> userlist;

    public Integer getWorkarrangeid() {
        return workarrangeid;
    }

    public void setWorkarrangeid(Integer workarrangeid) {
        this.workarrangeid = workarrangeid;
    }

    public List<Integer> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<Integer> userlist) {
        this.userlist = userlist;
    }
}
