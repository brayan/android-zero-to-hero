package br.com.sailboat.zerotohero.model.view;

import br.com.sailboat.canoe.recycler.RecyclerItem;
import br.com.sailboat.zerotohero.helper.ViewType;

public class LabelWithTextView implements RecyclerItem {

    private String label;
    private String text;

    public LabelWithTextView(String label, String text) {
        this.label = label;
        this.text = text;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int getViewType() {
        return ViewType.LABEL_TEXT;
    }

}
