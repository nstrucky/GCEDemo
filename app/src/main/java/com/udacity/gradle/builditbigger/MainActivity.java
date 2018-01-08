package com.udacity.gradle.builditbigger;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jokepresentation.JokeActivity;

import static com.example.jokepresentation.JokeActivity.KEY_INTENT_JOKE_STRING;


public class MainActivity extends AppCompatActivity implements
        EndpointsAsyncTask.JokeRetrievalListener {

    private JokeIdlingResource idlingResource;
    private String jokeString; //string used for ui testing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIdlingResource();
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void tellJoke(View view) {
        new EndpointsAsyncTask(this, idlingResource).execute();
    }

    @Override
    public void onJokeRetrieved(String joke) {
        jokeString = joke; //set for ui test
        Intent intent = new Intent(this, JokeAppActivity.class);
        intent.putExtra(KEY_INTENT_JOKE_STRING, joke);

        startActivity(intent);
    }

    @NonNull
    @VisibleForTesting
    public JokeIdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new JokeIdlingResource();
        }

        return idlingResource;
    }


    @VisibleForTesting
    public String getJokeString() {
        return jokeString;
    }


}
