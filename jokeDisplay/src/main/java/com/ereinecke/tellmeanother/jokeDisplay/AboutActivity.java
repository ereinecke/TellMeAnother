package com.ereinecke.tellmeanother.jokeDisplay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ereinecke.tellmeanother.BuildConfig;


public class AboutActivity extends AppCompatActivity {

    private static final String LOG_TAG = AboutActivity.class.getSimpleName();
    private static String PACKAGE_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new AboutFragment())
                    .commit();
        }
    }

    /**
     * A fragment containing a simple TextView.
     */
    public static class AboutFragment extends Fragment {

        public AboutFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_about, container, false);
            // Get version name
            String versionName = BuildConfig.VERSION_NAME;
            if (versionName.length() == 0) {
                versionName = getString(R.string.not_available);
            }

            TextView versionText = (TextView) view.findViewById(R.id.about_version);
            versionText.setText(getString(R.string.version, versionName));

            TextView aboutText = (TextView) view.findViewById(R.id.about_text);
            if (isPaid()) {
                aboutText.setText(getString(R.string.about, R.string.prem_about_text));
            } else {
                aboutText.setText(getString(R.string.about, R.string.free_about_text));
            }

            return view;
        }
    }

    // Used to populate About text
    public static boolean isPaid() {
        // TODO: figure out if this is free or premium for About text
        // boolean isPaid = PACKAGE_NAME.contains("paid");
        boolean isPaid = true;
        Log.d(LOG_TAG, "isPaid(): " + isPaid);
        return isPaid;
    }
}
