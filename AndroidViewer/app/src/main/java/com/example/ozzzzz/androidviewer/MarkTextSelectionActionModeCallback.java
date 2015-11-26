package com.example.ozzzzz.androidviewer;

import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by bogdan on 26.11.15.
 */
public class MarkTextSelectionActionModeCallback implements ActionMode.Callback {
    private TouchableTextView parent;

    public MarkTextSelectionActionModeCallback(TouchableTextView parent_) {
        this.parent = parent_;
    }

    final private int DEFINITION = 1;
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        menu.add(0, DEFINITION, 0, "GETTEXT");
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        menu.removeItem(android.R.id.selectAll);
        menu.removeItem(android.R.id.copy);
        menu.removeItem(android.R.id.cut);
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case DEFINITION:
                int min = 0;
                int max = parent.getText().length();
                if (parent.isFocused()) {
                    final int selStart = parent.getSelectionStart();
                    final int selEnd = parent.getSelectionEnd();

                    min = Math.max(0, Math.min(selStart, selEnd));
                    max = Math.max(0, Math.max(selStart, selEnd));
                }
                // Perform your definition lookup with the selected text
                final CharSequence selectedText = parent.getText().subSequence(min, max);
                Log.d("tapped on:", selectedText.toString());
                // Finish and close the ActionMode
                mode.finish();
                return true;
            default:
                break;
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }
}
