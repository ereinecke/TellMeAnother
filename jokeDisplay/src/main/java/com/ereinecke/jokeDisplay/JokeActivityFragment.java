package com.ereinecke.jokeDisplay;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ereinecke.javaJokes.Joker;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeActivityFragment extends Fragment {

    public JokeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_joke, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.joke_view);
        Joker myJoker = new Joker();
        textView.setText(myJoker.getJoke());

        /* Test call to Google Endpoints, will display a toast */
        new EndpointsAsyncTask().execute(new Pair<Context, String>(getContext(), "Max"));

        return rootView;
    }
}