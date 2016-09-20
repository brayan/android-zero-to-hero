package com.brayanbedritchuk.zerotohero.view.exercise.insert_or_edit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.base.BaseFragment;
import com.brayanbedritchuk.zerotohero.helper.DialogHelper;
import com.brayanbedritchuk.zerotohero.helper.ExtrasHelper;
import com.brayanbedritchuk.zerotohero.helper.UIHelper;
import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.view.exercise.insert_or_edit.presenter.InsertOrEditExercisePresenter;
import com.brayanbedritchuk.zerotohero.view.exercise.insert_or_edit.presenter.InsertOrEditExerciseView;

public class InsertOrEditExerciseFragment extends BaseFragment<InsertOrEditExercisePresenter> implements InsertOrEditExerciseView {

    private Toolbar toolbar;
    private EditText etName;
    private EditText etWeight;
    private EditText etSets;
    private EditText etReps;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_insert_or_edit_exercise, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_insert_edit, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_insert_edit_save: {
                getPresenter().onClickMenuSave();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    protected boolean hasMenu() {
        return true;
    }

    @Override
    protected InsertOrEditExercisePresenter newPresenterInstance() {
        return new InsertOrEditExercisePresenter(this);
    }

    @Override
    protected void getExtrasFromIntent(Intent intent) {
        getPresenter().onReceiveExercise(ExtrasHelper.getExercise(intent));
    }

    private void initViews(View view) {
        inflateViews(view);
        initToolbar();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }


    @Override
    public String getName() {
        return etName.getText().toString();
    }

    @Override
    public void showDialog(String message) {
        DialogHelper.showErrorMessage(getFragmentManager(), message);
    }

    @Override
    public void closeActivityWithResultCanceled() {
        getActivity().setResult(Activity.RESULT_CANCELED);
        getActivity().finish();
    }

    @Override
    public void closeActivityWithResultOk(Exercise exercise) {
        Intent intent = new Intent();
        ExtrasHelper.putExercise(exercise, intent);

        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }

    @Override
    public void openKeyboard() {
        UIHelper.openKeyboard(getActivity(), etName);
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
    public void hideKeyboard() {
        UIHelper.hideKeyboard(getActivity());
    }

    @Override
    public void updateToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    private void inflateViews(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        etName = (EditText) view.findViewById(R.id.frag_insert_or_edit_exercise__et__name);
        etWeight = (EditText) view.findViewById(R.id.frag_insert_or_edit_exercise__et__weight);
        etReps = (EditText) view.findViewById(R.id.frag_insert_or_edit_exercise__et__reps);
        etSets = (EditText) view.findViewById(R.id.frag_insert_or_edit_exercise__et__sets);
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
