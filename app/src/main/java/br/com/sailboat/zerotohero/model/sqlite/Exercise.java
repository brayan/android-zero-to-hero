package br.com.sailboat.zerotohero.model.sqlite;

import br.com.sailboat.canoe.recycler.RecyclerItem;
import br.com.sailboat.zerotohero.helper.ViewType;

public class Exercise implements RecyclerItem {

    private long id;
    private String name;
    private String notes;
    private String lastModified;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public int getViewType() {
        return ViewType.EXERCISE;
    }

}
