package com.udacity.gradle.builditbigger;

/**
 * Created by nicks on 1/3/2018.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by Nick on 1/2/2018.
 */

public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    public interface JokeRetrievalListener {
        void onJokeRetrieved(String joke);
    }

    private static MyApi myApiService = null;
    private JokeRetrievalListener listener;
    private JokeIdlingResource idlingResource;

    /**
     * Although the Espresso documentation says that Espresso already waits for UI events and
     *  default instances of AsyncTask to complete before it moves onto the next test operation.
     *  In the example given, they create a thread to delay the message, but i won't do that here
     *  for time's sake.
     * @param listener
     * @param idlingResource
     */
    public EndpointsAsyncTask(JokeRetrievalListener listener,
                              JokeIdlingResource idlingResource) {
        this.listener = listener;
        this.idlingResource = idlingResource;
    }


    @Override
    protected String doInBackground(Void... params) {
        if (idlingResource != null) {
            idlingResource.setIdleState(false);
        }
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Again, this is not necessary given that Espresso already waits for AsyncTask, however
     * this is just to illustrate the mechanism by which the idlingResource would operate.
     * @param result
     */
    @Override
    protected void onPostExecute(String result) {
        if (idlingResource != null) {
            idlingResource.setIdleState(true);
        }
            listener.onJokeRetrieved(result);
    }
}