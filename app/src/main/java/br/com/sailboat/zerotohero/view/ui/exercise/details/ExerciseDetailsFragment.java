package br.com.sailboat.zerotohero.view.ui.exercise.details;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.canoe.dialog.TwoOptionsDialog;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.view.ui.exercise.insert.InsertExerciseActivity;

public class ExerciseDetailsFragment extends BaseFragment<ExerciseDetailsPresenter> implements ExerciseDetailsPresenter.View {

    private static final int REQUEST_EDIT_EXERCISE = 0;

    private Toolbar toolbar;
    private TextView tvName;
    private TextView tvWeight;
    private TextView tvSets;
    private TextView tvReps;
    private FloatingActionButton fab;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_exercise_details;
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
    protected ExerciseDetailsPresenter newPresenterInstance() {
        return new ExerciseDetailsPresenter(this);
    }

    @Override
    protected void postActivityResult(int requestCode, Intent data) {
        getPresenter().postActivityResult();
    }

    @Override
    protected void initViews(View view) {
        inflateViews(view);
        initToolbar();
        initFab();
    }

    private void initToolbar() {
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Exercise Details");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    public void startEditExerciseActivity(Exercise exercise) {
        InsertExerciseActivity.start(this, exercise);
    }

    @Override
    public void setRepetition(String reps) {
        tvReps.setText(reps);
    }

    @Override
    public void setWeight(String weight) {
        tvWeight.setText(weight);
    }

    @Override
    public void setSet(String set) {
        tvSets.setText(set);
    }

    @Override
    public void setName(String name) {
       tvName.setText(name);
    }

    @Override
    public void showDialogDeleteExercise() {
        TwoOptionsDialog dialog = new TwoOptionsDialog();
        dialog.setMessage(getString(R.string.delete_exercise));
        dialog.setPositiveMsg(getString(R.string.delete));
        dialog.setPositiveCallback(new TwoOptionsDialog.PositiveCallback() {
            @Override
            public void onClickPositiveOption() {
                getPresenter().onClickDeleteExercise();
            }
        });
        dialog.show(getFragmentManager(), "DELETE_DIALOG");
    }

    private void inflateViews(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        tvName = (TextView) view.findViewById(R.id.frg_exercise_details__tv__name);
        tvWeight = (TextView) view.findViewById(R.id.frg_exercise_details__tv__weight);
        tvSets = (TextView) view.findViewById(R.id.frg_exercise_details__tv__sets);
        tvReps = (TextView) view.findViewById(R.id.frg_exercise_details__tv__reps);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
    }

    private void initFab() {
        fab.setImageResource(R.drawable.ic_edit_white_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onClickEditExercise();
            }
        });
    }

}
