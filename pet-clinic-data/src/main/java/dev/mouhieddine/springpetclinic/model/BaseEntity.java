package dev.mouhieddine.springpetclinic.model;

import java.io.Serializable;

public class BaseEntity implements Serializable {

    private long id;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}