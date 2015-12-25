package com.ozzzzz.bogdan.androidviewer;


import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LastProjectsFragment extends ListFragment
    /*implements AdapterView.OnItemLongClickListener*/ {

    private static final int DELETE_ID = Menu.FIRST + 1;

    static interface ProjectListListener {

        void itemClicked(long id);
//        void itemLongClicked(long id);

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

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener != null)
            listener.itemClicked(id);
    }

//    @Override
//    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//        if (listener != null)
//            listener.itemLongClicked(id);
//        return true;
//    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
//        registerForContextMenu(getListView());
//        getListView().setOnItemLongClickListener(this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.project_list_menu, menu);
    }

    public void updateList() {
        adapter.notifyDataSetChanged();
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v,
//                                    ContextMenu.ContextMenuInfo menuInfo) {
//
////        menu.
////        menu.add(0, DELETE_ID, 0, R.string.menu_delete_project);
//        super.onCreateContextMenu(menu, v, menuInfo);
//    }

    @Override
    public boolean onContextItemSelected(android.view.MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case DELETE_ID:
                Log.d("ITEM:", String.valueOf(info.id));
//                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
//                        .getMenuInfo();
//                Uri uri = Uri.parse(DatabaseContentProvider.BOOKMARK_ID_URI + Long.toString(info.id));
//                getActivity().getContentResolver().delete(uri, null, null);
                return true;
        }
        return super.onContextItemSelected(item);
    }
}
