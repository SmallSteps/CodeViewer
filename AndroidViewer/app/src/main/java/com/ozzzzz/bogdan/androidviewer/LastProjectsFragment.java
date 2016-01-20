package com.ozzzzz.bogdan.androidviewer;


import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.io.File;


/**
 * A simple {@link Fragment} subclass.
 */
public class LastProjectsFragment extends ListFragment {

    interface ProjectListListener {
        void itemClicked(long id);
    }

    private ProjectListListener listener;
    private ArrayAdapter<Project> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        adapter = new ArrayAdapter<Project>(
                inflater.getContext(),
                android.R.layout.simple_list_item_2,
                android.R.id.text1, AndroidViewer.projectManager.getProjects()
        ) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(AndroidViewer.projectManager.getProjects().get(position).getName());
                text2.setText("..." + File.separator +
                        AndroidViewer.projectManager.getProjects().get(position).getParentFolder() +
                        File.separator);
                return view;
            }
        };

        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (ProjectListListener)activity;
    }

    @Override
    public void onStart() {
        super.onStart();
        this.setEmptyText(getResources().getString(R.string.add_projects));
    }

    public void updateList() {
        adapter.notifyDataSetChanged();
    }

    public void openProject(Project project) {
        Intent intent = new Intent(getActivity(), InspectCodeActivity.class);
        intent.putExtra(InspectCodeActivity.CURRENT_PROJECT, project);
        startActivity(intent);


    }

    public void deleteProject(Project project) {
        AndroidViewer.projectManager.removeProject(project);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener != null)
            listener.itemClicked(id);
        showPopupMenu(v, id);
    }

    private void showPopupMenu(View view, final long id) {
        // Create a PopupMenu, giving it the clicked view for an anchor
        PopupMenu popup;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            popup = new PopupMenu(getActivity(), view, Gravity.CENTER);
        } else {
            popup = new PopupMenu(getActivity(), view);
        }
        // Inflate our menu resource into the PopupMenu's Menu
        popup.getMenuInflater().inflate(R.menu.menu_project, popup.getMenu());
        // Set a listener so we are notified if a menu item is clicked
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_open_project:
                        // Remove the item from the adapter
                        openProject(adapter.getItem((int) id));
                        return true;
                    case R.id.action_delete_project:
                        deleteProject(adapter.getItem((int) id));
                        return true;
                }
                return false;
            }
        });
        // Finally show the PopupMenu
        popup.show();
    }
}
