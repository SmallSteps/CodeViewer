package com.ozzzzz.bogdan.androidviewer;

import android.content.Context;

import java.io.FileOutputStream;
import java.util.ArrayList;
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
        if (!projects.contains(this)) {
            projects.add(0, this);
        }
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object ob) {
        if (ob == null) return false;
        if (ob.getClass() != getClass()) return false;
        Project other = (Project) ob;
        if (!location.equals(other.location)) return false;
        if (!name.equals(other.name)) return false;
        return true;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
