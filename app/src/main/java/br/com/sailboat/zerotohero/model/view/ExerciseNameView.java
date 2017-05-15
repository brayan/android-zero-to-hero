package br.com.sailboat.zerotohero.model.view;

import br.com.sailboat.canoe.recycler.RecyclerItem;
import br.com.sailboat.zerotohero.helper.ViewType;

public class ExerciseNameView implements RecyclerItem {

    private String name;

    public ExerciseNameView(String name) {
        this.name = name;
    }

    @Override
    public int getViewType() {
        return ViewType.EXERCISE_DETAIL_NAME;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
