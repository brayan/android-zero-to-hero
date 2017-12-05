package br.com.sailboat.zerotohero.view.exercise.details;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.helper.EntityHelper;
import br.com.sailboat.canoe.recycler.RecyclerItem;

public class ExerciseDetailsViewModel {

    private long exerciseId = EntityHelper.NO_ID;
    private final List<RecyclerItem> recyclerItemList = new ArrayList<>();

    public long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public List<RecyclerItem> getRecyclerItemList() {
        return recyclerItemList;
    }
}
