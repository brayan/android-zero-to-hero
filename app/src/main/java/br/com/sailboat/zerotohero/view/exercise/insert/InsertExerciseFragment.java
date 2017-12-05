package br.com.sailboat.zerotohero.view.exercise.insert;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.canoe.helper.UIHelper;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;

public class InsertExerciseFragment extends BaseFragment<InsertExercisePresenter> implements InsertExercisePresenter.View {

    private EditText etName;
    private EditText etWeight;
    private EditText etSets;
    private EditText etReps;
    private EditText etNotes;


    public static InsertExerciseFragment newInstance(long exerciseId) {
        Bundle args = new Bundle();
        ExtrasHelper.putExerciseId(exerciseId, args);
        InsertExerciseFragment fragment = new InsertExerciseFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.frg_insert_exercise;
    }

    @Override
    protected InsertExercisePresenter newPresenterInstance() {
        return new InsertExercisePresenter(this);
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
    protected void onInitToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    protected void initViews() {
        initEditTexts();
    }

    @Override
    public String getName() {
        return etName.getText().toString().trim();
    }

    @Override
    public String getWeight() {
        return etWeight.getText().toString().trim();
    }

    @Override
    public String getSets() {
        return etSets.getText().toString().trim();
    }

    @Override
    public String getReps() {
        return etReps.getText().toString().trim();
    }

    @Override
    public String getNotes() {
        return etNotes.getText().toString().trim();
    }

    @Override
    public void setWeight(String weight) {
        etWeight.setText(weight);
    }

    @Override
    public void setSets(String sets) {
        etSets.setText(sets);
    }

    @Override
    public void setReps(String reps) {
        etReps.setText(reps);
    }

    @Override
    public void setNotes(String notes) {
        etNotes.setText(notes);
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

    @Override
    public void showKeyboard() {
        UIHelper.openKeyboard(getActivity(), etName);
    }

    private void initEditTexts() {
        etName = (EditText) getView().findViewById(R.id.frg_insert_exercise__et__name);
        etWeight = (EditText) getView().findViewById(R.id.frg_insert_exercise__et__weight);
        etReps = (EditText) getView().findViewById(R.id.frg_insert_exercise__et__reps);
        etSets = (EditText) getView().findViewById(R.id.frg_insert_exercise__et__sets);
        etNotes = (EditText) getView().findViewById(R.id.frg_insert_exercise__et__notes);
    }

}
