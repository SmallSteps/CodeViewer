package com.ozzzzz.bogdan.androidviewer;

import java.io.Serializable;

/**
 * Created by neterebsky on 15.12.15.
 */
public final class Project implements Serializable {

    private final String name;
    private final String location;

    public Project(String name, String location) {
        this.name = name;
        this.location = location;
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
        if (!(ob instanceof Project)) return false;
        Project other = (Project) ob;
        if (!location.equals(other.location)) return false;
        if (!name.equals(other.name)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + 31 * location.hashCode();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
