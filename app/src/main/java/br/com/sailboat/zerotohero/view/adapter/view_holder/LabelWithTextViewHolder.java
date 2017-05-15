package br.com.sailboat.zerotohero.view.adapter.view_holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.sailboat.canoe.base.BaseViewHolder;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.model.view.LabelWithTextView;

public class LabelWithTextViewHolder extends BaseViewHolder {

    private TextView tvLabel;
    private TextView tvText;


    public static LabelWithTextViewHolder newInstance(ViewGroup parent) {
        View view = inflateLayout(parent, R.layout.vh_label_with_text);
        return new LabelWithTextViewHolder(view);
    }


    public LabelWithTextViewHolder(View itemView) {
        super(itemView);
        initViews(itemView);
    }

    public void onBindViewHolder(LabelWithTextView textDetailView) {
        tvLabel.setText(textDetailView.getLabel());
        tvText.setText(textDetailView.getText());
    }

    private void initViews(View view) {
        tvLabel = (TextView) view.findViewById(R.id.vh_label_with_text__tv__label);
        tvText = (TextView) view.findViewById(R.id.vh_label_with_text__tv__text);
    }
}
