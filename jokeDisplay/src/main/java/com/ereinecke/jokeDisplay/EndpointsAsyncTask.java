package com.ereinecke.jokeDisplay;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.ereinecke.backend.jokeApi.JokeApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * EndPointsAsyncTask executes an AsyncTask to query Google App Engine app
 * Starter code from
 *    https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
 */
public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static final String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();
    private static final boolean DEV_SERVER = false;
    private static JokeApi jokeApiService = null;
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