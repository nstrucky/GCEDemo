package com.example.jokepresentation;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.example.jokepresentation.JokeFragment.KEY_JOKE_STRING;

public class JokeActivity extends AppCompatActivity {

    public static final String KEY_INTENT_JOKE_STRING = "keyIntentJokeString";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        String joke = getString(R.string.error_no_joke);

        if (intent != null && intent.hasExtra(KEY_INTENT_JOKE_STRING)) {
            joke = intent.getStringExtra(KEY_INTENT_JOKE_STRING);

        }

        Fragment jokeFragment = new JokeFragment();

        Bundle args = new Bundle();
        args.putString(KEY_JOKE_STRING, joke);
        jokeFragment.setArguments(args);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_joke_frag, jokeFragment)
                .commit();

    }
}

