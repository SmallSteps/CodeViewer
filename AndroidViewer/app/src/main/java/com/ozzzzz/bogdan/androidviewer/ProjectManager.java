package com.ozzzzz.bogdan.androidviewer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bogdan on 23.12.15.
 */
public class ProjectManager {
    private List<Project> projects = new ArrayList<>();

    public List<Project> getProjects() {
        return projects;
    }

    public void addProject(Project project) {
        if (!projects.contains(project)) projects.add(project);
    }

    public void addProjectToHead(Project project) {
        if (!projects.contains(project)) projects.add(0, project);
    }

    public void moveProjectUp(Project project) {
        int index = projects.indexOf(project);
        if (index > -1) {
            projects.remove(project);
            projects.add(0, project);
        }
        //todo
    }

}
