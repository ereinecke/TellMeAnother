package com.ereinecke.jokeDisplay;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ereinecke.backend.jokeApi.JokeApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

public class JokeActivity extends AppCompatActivity {

    private static final String LOG_TAG = JokeActivity.class.getSimpleName();
    public static String theJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_joke);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    refreshJoke();
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshJoke();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // pull joke from Google Endpoints
    public void refreshJoke() {
        TextView textView = (TextView) findViewById(R.id.joke_view);
        new EndpointsAsyncTask().execute();
    }

    // callback for EndpointsAsyncTask
    public void setJoke(String joke) {
        Log.d(LOG_TAG, "joke: " + joke);
        theJoke = joke;
        showJoke();
    }

    // write joke to joke_view
    private void showJoke() {
        TextView jokeView = (TextView) findViewById(R.id.joke_view);
        jokeView.setText(theJoke);
    }

    /**
     * EndPointsAsyncTask executes an AsyncTask to query Google App Engine app
     * Starter code from
     *    https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
     */
    private class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
        private static final boolean DEV_SERVER = false;
        private JokeApi jokeApiService = null;
        private Context context;

        @Override
        protected String doInBackground(Pair<Context, String>... params) {

            JokeApi.Builder builder;

            if (jokeApiService == null) {  // Only do this once
                if (DEV_SERVER) {
                    builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                            new AndroidJsonFactory(), null)
                            // options for running against local devappserver
                            // - 10.0.2.2 is localhost's IP address in Android emulator
                            // - 10.0.3.2 is localhost in Genymotion emulator
                            .setRootUrl("http://10.0.3.2:8080/_ah/api/")
                            .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                                @Override
                                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                    abstractGoogleClientRequest.setDisableGZipContent(true);
                                }
                            });
                    // end options for devappserver
                } else {
                    builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                            new AndroidJsonFactory(), null)
                            .setRootUrl("https://joke-1249.appspot.com/_ah/api/");
                }
                jokeApiService = builder.build();
            }

            try {
                return jokeApiService.tellJoke().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(LOG_TAG, "in onPostExecute: " + result);
            // JokeActivity.setJoke(result);
            // write joke to joke_view
            // TextView jokeView = (TextView) JokeActivity.findViewById(R.id.joke_view);
            // jokeView.setText(result);
        }
    }
}
