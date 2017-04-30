package com.ereinecke.tellmeanother;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.ereinecke.tellmeanother.backend.jokeApi.JokeApi;
import com.ereinecke.tellmeanother.jokeDisplay.JokeActivity;
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
public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    public EndpointsAsyncTask(Context context) {
        mContext = context;
    }

    Context mContext;
    private static final String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();

    private static final boolean DEV_SERVER = false;
    private static final String JOKE_KEY = "joke";
    private static final String ROOT_URL = "https://1a-dot-joke-1249.appspot.com/_ah/api/";
    private JokeApi jokeApiService = null;



    @Override
    protected String doInBackground(Void... params) {

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
                            public void initialize(AbstractGoogleClientRequest<?>
                                                           abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver
            } else {
                builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl(ROOT_URL);
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
        Intent jokeIntent = new Intent(mContext, JokeActivity.class);
        jokeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        jokeIntent.putExtra(JOKE_KEY, result);
        mContext.startActivity(jokeIntent);
    }
}
