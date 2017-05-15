package br.com.sailboat.zerotohero.model.view;

import br.com.sailboat.canoe.recycler.RecyclerItem;
import br.com.sailboat.zerotohero.helper.ViewType;

public class PaddingView implements RecyclerItem{

    private int padding;

    public PaddingView(int padding) {
        this.padding = padding;
    }

    @Override
    public int getViewType() {
        return ViewType.PADDING;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

}
