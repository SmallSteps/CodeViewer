package com.ozzzzz.bogdan.androidviewer;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ozzzzz.bogdan.androidviewer.utils.filemanager.OpenFileDialog;
import com.ozzzzz.bogdan.androidviewer.utils.textselection.TouchableTextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TopLevelActivity extends AppCompatActivity implements LastProjectsFragment.ProjectListListener {

    TouchableTextView textView;

    private LastProjectsFragment lastProjectsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Project.projects = new ArrayList<Project>();

        setContentView(R.layout.activity_top_level);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOpenFileClick(v);
            }
        });

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top_level, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
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
        String[] files = fileName.split("/");
        String name = files[files.length - 1];
        new Project(name, fileName);

        lastProjectsFragment.updateList();
    }

    @Override
    public void itemClicked(long id) {
        Toast.makeText(getApplicationContext(), "id:" + id + "; name + ", Toast.LENGTH_LONG).show();
    }

    /*@Override
    public void itemLongClicked(long id) {
        Toast.makeText(getApplicationContext(), "Long clicked:" + id, Toast.LENGTH_SHORT).show();
    }*/

    public void saveProjectsFile() throws IOException {

        FileOutputStream fos = openFileOutput(AndroidViewer.PROJECTSFILE, Context.MODE_PRIVATE);
        String outputString = "";
        for (Project project : Project.projects) {
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
            new Project(oneProject[0], oneProject[1]);
        }
    }
}
