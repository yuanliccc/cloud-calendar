package group.cc.occ.model.dto;

import com.sun.org.apache.xpath.internal.operations.Bool;
import group.cc.occ.model.Module;

import java.util.List;

/**
 * Created by Administrator on 2019/2/28.
 */
public class ModuleDto {
    private Module module;

    private List<Module> children;

    private Boolean showChild = false;

    public Boolean getShowChild() {
        return showChild;
    }

    public void setShowChild(Boolean showChild) {
        this.showChild = showChild;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<Module> getChildren() {
        return children;
    }

    public void setChildren(List<Module> children) {
        this.children = children;
    }
}
