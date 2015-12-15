package com.ozzzzz.bogdan.androidviewer;

import android.content.Context;

import java.io.FileOutputStream;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by neterebsky on 15.12.15.
 */
public class Project {
    public static List<Project> projects;

    private String name;
    private String location;
    private long projectId;

    public Project(String name, String location) {
        this.name = name;
        this.location = location;
        projects.add(this);
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }


}
