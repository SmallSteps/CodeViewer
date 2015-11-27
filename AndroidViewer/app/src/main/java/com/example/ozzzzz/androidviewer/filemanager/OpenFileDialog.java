package com.example.ozzzzz.androidviewer.filemanager;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by bogdan on 27.11.15.
 */
public class OpenFileDialog extends AlertDialog.Builder {

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private String currentPath = Environment.getExternalStorageDirectory().getAbsolutePath()/*Path()*/;
    private FilenameFilter filenameFilter;
    private Context currentContext;

    public OpenFileDialog(Context context) {
        super(context);
        currentContext = context;
        setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .setItems(getFiles(currentPath), null);
    }

    private String[] getFiles(String directoryPath){
        File directory = new File(directoryPath);

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(currentContext,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) currentContext,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions((Activity) currentContext,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
//        Дальше нужно передавать эту хреновину куда-то, но делать это в другой функции не хочется,
//        а как это сделать адекватно я не понимаю. При этом, если API ниже 23, то всё прекрасно работает.
//        Но, получается, API 23 в этом месте упадет. Что нехорошо.
        File[] files = directory.listFiles();
        Log.v("AP", directory.getAbsolutePath());
        Log.v("Files",directory.exists()+"");
        Log.v("Files", directory.isDirectory() + "");
        Log.v("Files",directory.listFiles()+"");
        String[] result = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            result[i] = files[i].getName();
        }
        return result;
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
