package br.com.sailboat.zerotohero.view.adapter.view_holder;

import android.view.View;
import android.view.ViewGroup;

import br.com.sailboat.canoe.base.BaseViewHolder;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.model.view.PaddingView;

public class PaddingViewHolder extends BaseViewHolder {


    public static PaddingViewHolder newInstance(ViewGroup parent) {
        View view = inflateLayout(parent, R.layout.vh_padding);
        return new PaddingViewHolder(view);
    }

    public PaddingViewHolder(View itemView) {
        super(itemView);
    }

    public void bindToView(PaddingView padding) {
        itemView.getLayoutParams().height = padding.getPadding();
    }

}
