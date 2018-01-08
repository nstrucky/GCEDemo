package com.udacity.gradle.builditbigger.ui;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.JokeProvider;
import com.udacity.gradle.builditbigger.JokeIdlingResource;
import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

/**
 * Created by nicks on 1/6/2018.
 */
@RunWith(AndroidJUnit4.class)
public class MyAndroidTests {

    private JokeIdlingResource idlingResource;

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource() {
        idlingResource = activityRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(idlingResource);
    }


    @Test
    public void clickTellJokeButton() {
        onView(withId(R.id.button_tell_joke)).perform(click());
        String jokeString = activityRule.getActivity().getJokeString();
        assert jokeString != null;
        String prefix = JokeProvider.JOKE_PREFIX;
        String stringBeginning = jokeString.substring(0, prefix.length());
        Log.d("TESTING", "Prefix: " + prefix +"\nJoke Prefix: " + stringBeginning);

        assertThat(stringBeginning, is(prefix));

    }


    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            Espresso.unregisterIdlingResources(idlingResource);
        }
    }


}
