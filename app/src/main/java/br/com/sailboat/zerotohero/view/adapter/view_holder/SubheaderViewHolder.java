package br.com.sailboat.zerotohero.view.adapter.view_holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.sailboat.canoe.base.BaseViewHolder;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.model.view.SubheaderView;

public class SubheaderViewHolder extends BaseViewHolder {

    private TextView tvSubheader;


    public static SubheaderViewHolder newInstance(ViewGroup parent) {
        View view = inflateLayout(parent, R.layout.vh_subheader);
        return new SubheaderViewHolder(view);
    }


    public SubheaderViewHolder(View itemView) {
        super(itemView);
        initViews(itemView);
    }

    public void onBindViewHolder(SubheaderView subheader) {
        tvSubheader.setText(subheader.getStringRes());
    }

    private void initViews(View view) {
        tvSubheader = (TextView) view.findViewById(R.id.tvHolderSubheader);
    }
}
