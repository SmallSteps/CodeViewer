package com.example.ozzzzz.androidviewer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.widget.TextView;

/**
 * Created by bogdan on 26.11.15.
 */
public class TouchableTextView extends TextView {
    @Override
    protected void onCreateContextMenu(ContextMenu menu) {
        super.onCreateContextMenu(menu);
        setCustomSelectionActionModeCallback(new MarkTextSelectionActionModeCallback(this));
    }


    public TouchableTextView(Context context) {
        super(context);
    }

    public TouchableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchableTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}