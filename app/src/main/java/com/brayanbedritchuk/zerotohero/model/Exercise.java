package com.brayanbedritchuk.zerotohero.model;

import java.io.Serializable;

public class Exercise implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
