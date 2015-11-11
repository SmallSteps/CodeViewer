package com.example.ozzzzz.androidviewer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainWindow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        String lyrics = ((TextView) findViewById(R.id.lyrics)).getText().toString().trim();

        TextView definitionView = (TextView) findViewById(R.id.lyrics);
        definitionView.setMovementMethod(LinkMovementMethod.getInstance());
        definitionView.setText(lyrics/*, TextView.BufferType.SPANNABLE*/);
        Spannable spans = (Spannable) definitionView.getText();
        BreakIterator iterator = BreakIterator.getWordInstance(Locale.US);
        iterator.setText(lyrics);
        int start = iterator.first();
        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
            String possibleWord = lyrics.substring(start, end);
            if (Character.isLetterOrDigit(possibleWord.charAt(0))) {
                ClickableSpan clickSpan = getClickableSpan(possibleWord);
                spans.setSpan(clickSpan, start, end,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }


//        String lyrics = ((TextView) findViewById(R.id.lyrics)).getText().toString();

//        coloredText(lyrics, TrimByLetter(lyrics, 'a'));

    }

    private ClickableSpan getClickableSpan(final String word) {
        return new ClickableSpan() {
            final String mWord;
            {
                mWord = word;
            }

            @Override
            public void onClick(View widget) {
                Log.d("tapped on:", mWord);
                Toast.makeText(widget.getContext(), mWord, Toast.LENGTH_SHORT)
                        .show();
            }

            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
            }
        };
    }

    public ArrayList<Pair> TrimByLetter(String text, char letter) {
        ArrayList<Pair> finded = new ArrayList<>();

//        String pattern_string = "[\\s\\r]([Aa]\\w*)";
        String pattern_string = "\\b([Aa]\\w*)";
        Pattern pattern = Pattern.compile(pattern_string);

        Matcher m = pattern.matcher(text);

        while (m.find()) {

            finded.add(Pair.create(m.start(), m.end()));
//            Log.d("Hi", "Start: " + m.start() +
//                    "; end: " + m.end() +
//                    "; word: " + m.group());
        }

        return finded;
    }

    public void coloredText(String lyrics, ArrayList<Pair> indexes) {

        SpannableString styled = new SpannableString(lyrics);
        for(Pair index : indexes) {
            styled.setSpan(new ForegroundColorSpan(Color.RED), (int)index.first, (int)index.second, 0);
        }

        ((TextView) (findViewById(R.id.lyrics))).setText(styled);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_window, menu);
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
}
