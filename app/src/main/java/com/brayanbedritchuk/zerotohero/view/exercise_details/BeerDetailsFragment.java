package com.brayanbedritchuk.zerotohero.view.exercise_details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brayanbedritchuk.sailbeer.R;
import com.brayanbedritchuk.sailbeer.model.Beer;
import com.brayanbedritchuk.sailbeer.presenter.BeerDetailsPresenter;
import com.brayanbedritchuk.sailbeer.presenter.view.BeerDetailsView;

public class BeerDetailsFragment extends Fragment implements BeerDetailsView {

    private BeerDetailsPresenter presenter;

    private Toolbar toolbar;
    private TextView tvToolbarTitle;
    private TextView tvCountry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        initPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_details, container, false);
        initViews(contentView);
        return contentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override
    public Beer getBeerFromIntent() {
        Intent intent = getActivity().getIntent();
        return (Beer) intent.getSerializableExtra(BeerDetailsActivity.EXTRA_BEER);
    }

    @Override
    public void updateContentViews(Beer beer) {
        tvToolbarTitle.setText(beer.getName());
        tvCountry.setText(beer.getCountry());
    }

    private void initPresenter() {
        setPresenter(new BeerDetailsPresenter(this));
    }

    private void initViews(View contentView) {
        inflateViews(contentView);
        initToolbar();
        initListeners();
    }

    private void inflateViews(View contentView) {
        tvToolbarTitle = (TextView) contentView.findViewById(R.id.toolbar_title);
        toolbar = (Toolbar) contentView.findViewById(R.id.toolbar);
        tvCountry = (TextView) contentView.findViewById(R.id.fragment_details__tv__country);
    }

    private void initToolbar() {
        initAppCompatActivity();
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    }

    protected void initAppCompatActivity() {
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initListeners() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    public BeerDetailsPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(BeerDetailsPresenter presenter) {
        this.presenter = presenter;
    }

}
