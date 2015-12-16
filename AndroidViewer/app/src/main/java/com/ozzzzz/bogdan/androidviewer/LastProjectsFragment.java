package com.ozzzzz.bogdan.androidviewer;


import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LastProjectsFragment extends ListFragment {

    static interface ProjectListListener {
        void itemClicked(long id);
    }

    private ProjectListListener listener;

    public LastProjectsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (Project.projects.size() != 0) {
            String[] projectNames = new String[Project.projects.size()];

            for (int i = 0; i < projectNames.length; i++) {
                projectNames[i] = Project.projects.get(i).getName();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    inflater.getContext(),
                    android.R.layout.simple_list_item_1,
                    projectNames);

            setListAdapter(adapter);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (ProjectListListener)activity;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener != null)
            listener.itemClicked(id);
    }


}
