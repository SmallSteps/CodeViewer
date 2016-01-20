package com.ozzzzz.bogdan.androidviewer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ozzzzz.bogdan.androidviewer.utils.textselection.TouchableTextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InspectCodeActivity extends Activity {

    private TouchableTextView codeField;
    public static final String CURRENT_PROJECT = "current_project";
    private Project currentProject;
    private ArrayList<String> codeArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspect_code);

        codeField = (TouchableTextView) findViewById(R.id.codeField);

        Intent intent = getIntent();
        currentProject = (Project) intent.getSerializableExtra(CURRENT_PROJECT);

        openFile(currentProject.getLocation());
    }

    private void openFile(String location) {
        File file = new File(location);
        if (file.isDirectory()) {
            return;
        }
        codeArray.clear();
        try {
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
                codeArray.add(in.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        updateCode();
    }

    private void updateCode() {
        StringBuilder sb = new StringBuilder();
        int linesNumberDigit = (int)(Math.log10(codeArray.size())+1);
        for(int i = 0; i < codeArray.size(); i++) {
            sb.append(i + 1);
            sb.append(" | ");
            sb.append(codeArray.get(i));
            sb.append("\n");
        }

        codeField.setText(sb.toString());
    }
}
