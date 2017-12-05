package br.com.sailboat.zerotohero.view.workout.details;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.helper.EntityHelper;
import br.com.sailboat.canoe.recycler.RecyclerItem;

public class WorkoutDetailsViewModel {

    private long workoutId = EntityHelper.NO_ID;
    private final List<RecyclerItem> recyclerItemList = new ArrayList<>();

    public long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
    }

    public List<RecyclerItem> getRecyclerItemList() {
        return recyclerItemList;
    }

}
