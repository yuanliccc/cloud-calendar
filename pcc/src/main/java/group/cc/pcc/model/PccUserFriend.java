package group.cc.pcc.model;

import javax.persistence.*;

@Table(name = "pcc_user_friend")
public class PccUserFriend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pcc_user_id")
    private Integer pccUserId;

    @Column(name = "friend_pcc_user_id")
    private Integer friendPccUserId;

    private String remark;

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
     * @return pcc_user_id
     */
    public Integer getPccUserId() {
        return pccUserId;
    }

    /**
     * @param pccUserId
     */
    public void setPccUserId(Integer pccUserId) {
        this.pccUserId = pccUserId;
    }

    /**
     * @return friend_pcc_user_id
     */
    public Integer getFriendPccUserId() {
        return friendPccUserId;
    }

    /**
     * @param friendPccUserId
     */
    public void setFriendPccUserId(Integer friendPccUserId) {
        this.friendPccUserId = friendPccUserId;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}