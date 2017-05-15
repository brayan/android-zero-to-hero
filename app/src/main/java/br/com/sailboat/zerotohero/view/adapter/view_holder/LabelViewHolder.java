package br.com.sailboat.zerotohero.view.adapter.view_holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.sailboat.canoe.base.BaseViewHolder;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.model.view.LabelView;

public class LabelViewHolder extends BaseViewHolder {

    private TextView tvLabel;

    public static LabelViewHolder newInstance(ViewGroup parent) {
        View view = inflateLayout(parent, R.layout.vh_label);
        return new LabelViewHolder(view);
    }

    public LabelViewHolder(View itemView) {
        super(itemView);
        initViews(itemView);
    }


    public void onBindViewHolder(LabelView label) {
        tvLabel.setText(label.getLabel());
    }

    private void initViews(View view) {
        tvLabel = (TextView) view.findViewById(R.id.vh_label__tv__label);
    }
}
