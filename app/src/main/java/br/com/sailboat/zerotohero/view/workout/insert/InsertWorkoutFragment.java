package br.com.sailboat.zerotohero.view.workout.insert;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.model.view.ExerciseView;
import br.com.sailboat.zerotohero.view.adapter.ExercisesListAdapter;
import br.com.sailboat.zerotohero.view.adapter.SwipeExercise;
import br.com.sailboat.zerotohero.view.exercise.selector.ExerciseSelectorActivity;

public class InsertWorkoutFragment extends BaseFragment<InsertWorkoutPresenter> implements InsertWorkoutPresenter.View {

    private static final int REQUEST_EXERCISE_CHOOSER = 0;

    private Toolbar toolbar;
    private RecyclerView recycler;
    private FloatingActionButton fab;
    private EditText etWorkoutName;
    private View emptyList;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_insert_workout;
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
    protected InsertWorkoutPresenter newPresenterInstance() {
        return new InsertWorkoutPresenter(this);
    }

    @Override
    protected void onActivityResultOk(int requestCode, Intent data) {
        switch (requestCode) {
            case REQUEST_EXERCISE_CHOOSER: {
                getPresenter().onResultOkExerciseChooser(data);
            }
        }
    }

    @Override
    protected void initViews(View view) {
        inflateViews(view);
        initToolbar();
        initRecyclerView();
        initFab();
        initEmptyView();
    }

    @Override
    public void updateExercisesListAndVisibility() {
        recycler.getAdapter().notifyDataSetChanged();
        updateVisibilityOfViews();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startExercisesChooserActivity(ArrayList<ExerciseView> exercises) {
        ExerciseSelectorActivity.start(this, exercises, REQUEST_EXERCISE_CHOOSER);
    }

    @Override
    public String getTextFromWorkoutName() {
        return etWorkoutName.getText().toString();
    }

    @Override
    public void updateWorkoutNameView(String name) {
        etWorkoutName.setText(name);
        etWorkoutName.setSelection(etWorkoutName.getText().length());
    }

    @Override
    public void updateToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    private void inflateViews(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        etWorkoutName = (EditText) view.findViewById(R.id.frg_insert_workout__et__name);
        emptyList = view.findViewById(R.id.emptyList);
    }

    private void initRecyclerView() {
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        ExercisesListAdapter adapter = new ExercisesListAdapter(getPresenter());
        recycler.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new SwipeExercise(getActivity(), adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recycler);
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

    private void initFab() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onClickAddExercises();
            }
        });
    }

    private void initEmptyView() {
        ImageView imgEmpty = (ImageView) emptyList.findViewById(R.id.imgEmptyList);
        imgEmpty.setColorFilter(ContextCompat.getColor(getActivity(), R.color.md_teal_300), PorterDuff.Mode.SRC_ATOP);

        TextView tvTitle = (TextView) emptyList.findViewById(R.id.tvEmptyListTitle);
        tvTitle.setText("No exercises");

        TextView tvMessage = (TextView) emptyList.findViewById(R.id.tvEmptyListMessage);
        tvMessage.setText("Add exercises by tapping the + button");

        emptyList.setVisibility(View.GONE);
    }

    @Override
    public void updateVisibilityOfViews() {
        boolean emptyList = getPresenter().getExercises().isEmpty();

        if (emptyList) {
            recycler.setVisibility(View.GONE);
            this.emptyList.setVisibility(View.VISIBLE);

        } else {
            this.emptyList.setVisibility(View.GONE);
            recycler.setVisibility(View.VISIBLE);
        }

    }

}
