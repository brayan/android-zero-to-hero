package br.com.sailboat.zerotohero.view.exercise.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.canoe.dialog.TwoOptionsDialog;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.view.adapter.ExerciseDetailsAdapter;
import br.com.sailboat.zerotohero.view.exercise.insert.InsertExerciseActivity;

public class ExerciseDetailsFragment extends BaseFragment<ExerciseDetailsPresenter> implements ExerciseDetailsPresenter.View {

    private Toolbar toolbar;
    private RecyclerView recycler;
    private FloatingActionButton fab;

    public static ExerciseDetailsFragment newInstance(long exerciseId) {
        Bundle args = new Bundle();
        ExtrasHelper.putExerciseId(exerciseId, args);
        ExerciseDetailsFragment fragment = new ExerciseDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_exercise_details;
    }

    @Override
    protected ExerciseDetailsPresenter newPresenterInstance() {
        return new ExerciseDetailsPresenter(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_details, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_delete: {
                getPresenter().onClickMenuDelete();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    protected void postActivityResult(int requestCode, Intent data) {
        getPresenter().postActivityResult();
    }

    @Override
    protected void initViews() {
        initRecyclerView();
        initToolbar();
        initFab();
    }

    @Override
    public void startEditExerciseActivity(long exerciseId) {
        InsertExerciseActivity.start(this, exerciseId);
    }

    @Override
    public void setTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void updateList() {
        recycler.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showDialogDeleteExercise() {
        TwoOptionsDialog dialog = new TwoOptionsDialog();
        dialog.setMessage(getString(R.string.are_you_sure));
        dialog.setPositiveMsg(getString(R.string.delete));
        dialog.setPositiveCallback(new TwoOptionsDialog.PositiveCallback() {
            @Override
            public void onClickPositiveOption() {
                getPresenter().onClickDeleteExercise();
            }
        });
        dialog.show(getFragmentManager(), "DELETE_DIALOG");
    }

    private void initToolbar() {
        toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(R.string.exercise_details);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    private void initRecyclerView() {
        recycler = (RecyclerView) getView().findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(new ExerciseDetailsAdapter(getPresenter()));
    }

    private void initFab() {
        fab = (FloatingActionButton) getView().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_edit_white_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onClickEditExercise();
            }
        });
    }

}
