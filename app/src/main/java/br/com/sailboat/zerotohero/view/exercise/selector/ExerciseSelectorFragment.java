package br.com.sailboat.zerotohero.view.exercise.selector;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.Extras;
import br.com.sailboat.zerotohero.model.view.ExerciseView;
import br.com.sailboat.zerotohero.view.adapter.ExerciseChooserAdapter;

public class ExerciseSelectorFragment extends BaseFragment<ExerciseSelectorPresenter> implements ExerciseSelectorPresenter.View {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private View emptyList;
    private FloatingActionButton fab;

    public static ExerciseSelectorFragment newInstance(List<ExerciseView> exercises) {
        Bundle args = new Bundle();
        Extras.putExerciseViewList(exercises, args);
        ExerciseSelectorFragment fragment = new ExerciseSelectorFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_exercise_selector;
    }

    @Override
    protected ExerciseSelectorPresenter newPresenterInstance() {
        return new ExerciseSelectorPresenter(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_exercise_selector, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    protected void initViews(View view) {
        inflateViews(view);
        initToolbar();
        initRecyclerView();
        initEmptyView();
        initFab();
    }

    private void initFab() {
        fab.setImageResource(R.drawable.ic_check_white_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().onClickFabSave();
            }
        });
    }

    private void initToolbar() {
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().onClickNavigationIcon();
            }
        });
        toolbar.setTitle(R.string.app_name);
    }

    @Override
    public void updateExerciseListView() {
        recyclerView.getAdapter().notifyDataSetChanged();
        updateVisibilityOfViews();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void updateVisibilityOfViews() {
        boolean isEmpty = getPresenter().getExerciseList().isEmpty();

        if (isEmpty) {
            recyclerView.setVisibility(View.GONE);
            emptyList.setVisibility(View.VISIBLE);

        } else {
            emptyList.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void updateExerciseView(int position) {
        recyclerView.getAdapter().notifyItemChanged(position);
    }

    @Override
    public void closeActivityResultCanceled() {
        getActivity().setResult(Activity.RESULT_CANCELED);
        getActivity().finish();
    }

    @Override
    public void closeActivityResultOk(List<ExerciseView> exercises) {
        Intent data = new Intent();
        Extras.putExerciseViewList(exercises, data);
        getActivity().setResult(Activity.RESULT_OK, data);
        getActivity().finish();
    }

    private void inflateViews(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        emptyList = view.findViewById(R.id.emptyList);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ExerciseChooserAdapter adapter = new ExerciseChooserAdapter(getPresenter());
        recyclerView.setAdapter(adapter);
    }

    private void initEmptyView() {
        ImageView imgEmpty = (ImageView) emptyList.findViewById(R.id.imgEmptyList);
        imgEmpty.setColorFilter(ContextCompat.getColor(getActivity(), R.color.md_blue_300), PorterDuff.Mode.SRC_ATOP);

        TextView tvTitle = (TextView) emptyList.findViewById(R.id.tvEmptyListTitle);
        tvTitle.setText(R.string.no_exercises);

        TextView tvMessage = (TextView) emptyList.findViewById(R.id.tvEmptyListMessage);
        tvMessage.setVisibility(View.GONE);

        emptyList.setVisibility(View.GONE);
    }

}
