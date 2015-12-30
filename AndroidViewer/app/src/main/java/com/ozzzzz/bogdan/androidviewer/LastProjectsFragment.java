package com.ozzzzz.bogdan.androidviewer;


import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LastProjectsFragment extends ListFragment
    /*implements AdapterView.OnItemLongClickListener*/ {



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
                android.R.layout.simple_list_item_1,
                AndroidViewer.projectManager.getProjects());

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
//        final PopupAdapter adapter = (PopupAdapter) getListAdapter();
        // Retrieve the clicked item from view's tag
        final String item = (String) view.getTag();
        // Create a PopupMenu, giving it the clicked view for an anchor
        PopupMenu popup = new PopupMenu(getActivity(), view);
        // Inflate our menu resource into the PopupMenu's Menu
        popup.getMenuInflater().inflate(R.menu.menu_project, popup.getMenu());
        // Set a listener so we are notified if a menu item is clicked
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_open_project:
                        // Remove the item from the adapter
                        Log.d("OPEN", "OPEEEEN!");
                        return true;
                    case R.id.action_delete_project:
                        deleteProject(adapter.getItem((int)id));
                        return true;
                }
                return false;
            }
        });
        // Finally show the PopupMenu
        popup.show();
    }
}
