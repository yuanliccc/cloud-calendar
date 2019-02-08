package group.cc.pcc.model.builder;

import group.cc.pcc.model.PccText;

public class PccTextBuilder {

    public static PccText build(String text) {
        PccText pccText = new PccText();

        pccText.setText(text);

        return pccText;
    }

}
