package br.com.sailboat.zerotohero.model.view;

import br.com.sailboat.canoe.recycler.RecyclerItem;
import br.com.sailboat.zerotohero.helper.ViewType;

public class LabelView implements RecyclerItem {

    private String label;

    public LabelView(String label) {
        this.label = label;
    }

    @Override
    public int getViewType() {
        return ViewType.LABEL;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
