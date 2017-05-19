package br.com.sailboat.zerotohero.view.exercise.insert;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.canoe.helper.UIHelper;
import br.com.sailboat.zerotohero.R;

public class InsertExerciseFragment extends BaseFragment<InsertExercisePresenter> implements InsertExercisePresenter.View {

    private Toolbar toolbar;
    private EditText etName;
    private EditText etWeight;
    private EditText etSets;
    private EditText etReps;
    private EditText etNotes;

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
    public void setSet(String set) {
        etSets.setText(set);
    }

    @Override
    public void setRepetition(String repetition) {
        etReps.setText(repetition);
    }

    @Override
    public void setNotes(String notes) {
        etNotes.setText(notes);
    }

    @Override
    public void showKeyboard() {
        UIHelper.openKeyboard(getActivity(), etName);
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
        etNotes = (EditText) view.findViewById(R.id.frg_insert_exercise__et__notes);
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
