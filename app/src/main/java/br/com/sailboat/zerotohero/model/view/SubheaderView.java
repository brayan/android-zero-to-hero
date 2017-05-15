package br.com.sailboat.zerotohero.model.view;

import android.support.annotation.StringRes;

import br.com.sailboat.canoe.recycler.RecyclerItem;
import br.com.sailboat.zerotohero.helper.ViewType;

public class SubheaderView implements RecyclerItem {

    private @StringRes int stringRes;

    public SubheaderView(int stringRes) {
        this.stringRes = stringRes;
    }

    @Override
    public int getViewType() {
        return ViewType.SUBHEADER;
    }

    public int getStringRes() {
        return stringRes;
    }

    public void setStringRes(int stringRes) {
        this.stringRes = stringRes;
    }

}
