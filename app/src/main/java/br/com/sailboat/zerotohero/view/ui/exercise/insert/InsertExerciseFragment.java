package br.com.sailboat.zerotohero.view.ui.exercise.insert;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.canoe.dialog.MessageDialog;
import br.com.sailboat.canoe.helper.UIHelper;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.Extras;
import br.com.sailboat.zerotohero.model.Exercise;

public class InsertExerciseFragment extends BaseFragment<InsertExercisePresenter> implements InsertExercisePresenter.View {

    private Toolbar toolbar;
    private EditText etName;
    private EditText etWeight;
    private EditText etSets;
    private EditText etReps;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_insert_exercise;
    }

    @Override
    protected InsertExercisePresenter newPresenterInstance() {
        return new InsertExercisePresenter(this);
    }

    @Override
    protected void initViews(View view) {
        inflateViews(view);
        initToolbar();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_insert_edit, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save: {
                getPresenter().onClickMenuSave();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public String getName() {
        return etName.getText().toString();
    }

    @Override
    public String getWeight() {
        return etWeight.getText().toString();
    }

    @Override
    public String getSets() {
        return etSets.getText().toString();
    }

    @Override
    public String getReps() {
        return etReps.getText().toString();
    }

    @Override
    public void setWeight(String weight) {
        etWeight.setText(weight);
    }

    @Override
    public void setSet(String set) {
        etSets.setText(set);
    }

    @Override
    public void setRepetition(String repetition) {
        etReps.setText(repetition);
    }

    @Override
    public void setName(String name) {
        etName.setText(name);
        etName.setSelection(etName.getText().length());
    }

    @Override
    public void updateToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    private void inflateViews(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        etName = (EditText) view.findViewById(R.id.frg_insert_exercise__et__name);
        etWeight = (EditText) view.findViewById(R.id.frg_insert_exercise__et__weight);
        etReps = (EditText) view.findViewById(R.id.frg_insert_exercise__et__reps);
        etSets = (EditText) view.findViewById(R.id.frg_insert_exercise__et__sets);
    }

    private void initToolbar() {
        initAppCompatActivity();
    }

    protected void initAppCompatActivity() {
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

}
