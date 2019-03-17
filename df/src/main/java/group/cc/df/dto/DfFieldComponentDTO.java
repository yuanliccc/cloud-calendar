package group.cc.df.dto;


import group.cc.df.model.DfFormAttachment;
import group.cc.df.model.DfFormField;
import group.cc.df.model.DfFormItem;

import java.util.List;

public class DfFieldComponentDTO {
    private DfFormField dfFormField;

    private List<DfFormItem> dfFormItems;

    private List<DfFormAttachment> dfFormAttachments;

    public DfFormField getDfFormField() {
        return dfFormField;
    }

    public void setDfFormField(DfFormField dfFormField) {
        this.dfFormField = dfFormField;
    }

    public List<DfFormItem> getDfFormItems() {
        return dfFormItems;
    }

    public void setDfFormItems(List<DfFormItem> dfFormItems) {
        this.dfFormItems = dfFormItems;
    }

    public List<DfFormAttachment> getDfFormAttachments() {
        return dfFormAttachments;
    }

    public void setDfFormAttachments(List<DfFormAttachment> dfFormAttachments) {
        this.dfFormAttachments = dfFormAttachments;
    }

    @Override
    public String toString() {
        return "DfFieldComponentDTO{" +
                "dfFormField=" + dfFormField +
                ", dfFormItems=" + dfFormItems +
                ", dfFormAttachments=" + dfFormAttachments +
                '}';
    }
}
