package group.cc.occ.model;

import java.io.Serializable;

/**
 * Created by wangyuming on 2019/3/19.
 */
public class Message implements Serializable{
    private Integer type; //0为通知，1为私信
    private Object obj;

    public Message(){}

    public Message(Integer type, Object obj){
        setType(type);
        setObj(obj);
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
