package com.ereinecke.tellmeanother.jokeDisplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class JokeActivity extends AppCompatActivity {

    private static final String LOG_TAG = JokeActivity.class.getSimpleName();
    static final String JOKE_KEY = "joke";
    private static String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_joke);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // TODO: use FloatingActionButton to refresh joke
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        if (fab != null) {
//            fab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    refreshJoke();
//                }
//            });
//        }

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            joke = getResources().getString(R.string.no_joke);
        } else {
            joke = extras.getString(JOKE_KEY);
        }
        Log.d(LOG_TAG, "JokeActivity.onCreate, joke = " + joke);
        TextView jokeView = (TextView) findViewById(R.id.joke_view);
        if ((joke != null) && (jokeView != null)) {
            jokeView.setText(joke);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_joke, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // TODO: send a broadcast to MainActivity to trigger a new joke.
    private void refreshJoke() {
        // temporary feedback
        Toast.makeText(this, "refreshing joke...", Toast.LENGTH_LONG).show();
    }

}
