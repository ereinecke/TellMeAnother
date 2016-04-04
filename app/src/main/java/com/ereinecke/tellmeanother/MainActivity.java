package com.ereinecke.tellmeanother;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/*
 * MainActivity: displays an ad in the free version and a button to pull a joke from the javaJokes
 * library via a Google Cloud Endpoints server.
 *
 */

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "MainActivity.onCreate");
        setContentView(R.layout.activity_main);
    }

    public void launchJokeActivity(View view) {
        {
            new EndpointsAsyncTask(this).execute();
        }
    }
}