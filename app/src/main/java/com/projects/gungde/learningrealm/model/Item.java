package com.projects.gungde.learningrealm.model;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by gungdeaditya on 10/06/17.
 */

public class Item extends RealmObject{

    @PrimaryKey
    private String id = UUID.randomUUID().toString();;
    @Required
    private String name;

    public Item() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
