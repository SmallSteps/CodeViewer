package com.ozzzzz.bogdan.androidviewer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.ozzzzz.bogdan.androidviewer.utils.filemanager.OpenFileDialog;
import com.ozzzzz.bogdan.androidviewer.utils.textselection.TouchableTextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TopLevelActivity extends Activity implements LastProjectsFragment.ProjectListListener {

    TouchableTextView textView;

    private LastProjectsFragment lastProjectsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_top_level);


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//
//
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onOpenFileClick(v);
//            }
//        });

        lastProjectsFragment = (LastProjectsFragment)getFragmentManager().
                findFragmentById(R.id.last_projects_container);


//        Fragment fragment = (Fragment)findViewById(R.id.last_projects_container);

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        textView = (TouchableTextView)findViewById(R.id.touchableText);
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            loadProjectsFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        try {
            saveProjectsFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top_level, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_add_project:
                onOpenFileClick(getWindow().getDecorView().getRootView());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onOpenFileClick(View view) {
        OpenFileDialog fileDialog = new OpenFileDialog(this)
                // .setFilter(".*\\.csv")
                .setOpenDialogListener(new OpenFileDialog.OpenDialogListener() {
                    @Override
                    public void OnSelectedFile(String fileName) {
                        Toast.makeText(getApplicationContext(), fileName, Toast.LENGTH_LONG).show();
                        onOpenFile(fileName);
                    }
                });
        fileDialog.show();
    }

    private void onOpenFile(String fileName) {
        String name = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
        AndroidViewer.projectManager.addProjectToHead(new Project(name, fileName));
        lastProjectsFragment.updateList();
    }

    @Override
    public void itemClicked(long id) {
        Toast.makeText(getApplicationContext(), "id:" + id + "; name + ", Toast.LENGTH_LONG).show();

    }

    public void saveProjectsFile() throws IOException {

        FileOutputStream fos = openFileOutput(AndroidViewer.PROJECTSFILE, Context.MODE_PRIVATE);
        String outputString = "";
        for (Project project : AndroidViewer.projectManager.getProjects()) {
            outputString += project.getName() + ":" + project.getLocation() + "\n";
        }
        fos.write(outputString.getBytes());
        fos.close();
    }

    public void loadProjectsFile() throws IOException {

        FileInputStream fis = openFileInput(AndroidViewer.PROJECTSFILE);
        String inputString = "";
        int c;
        while( (c = fis.read()) != -1){
            inputString += Character.toString((char)c);
        }
        Toast.makeText(getBaseContext(),"file read: " + inputString, Toast.LENGTH_SHORT).show();
        fis.close();
        if (!inputString.equals(""))
            parseProjectsFile(inputString);
    }

    public void parseProjectsFile(String inputString) {
        String[] projects = inputString.split("\n");
        for (String project : projects) {
            String[] oneProject = project.split(":");
            AndroidViewer.projectManager.addProject(new Project(oneProject[0], oneProject[1]));
        }
    }
}
