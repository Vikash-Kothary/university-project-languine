package com.puzzle.appname;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainClassTest {

    @Rule public final ActivityRule<LessonSelectActivity> main = new ActivityRule<>(LessonSelectActivity.class);

    @Test
    public void TextView(){
        onView(withId(R.id.textView2)).check(ViewAssertions.matches(isDisplayed()));
    }
    @Test
    public void Button1(){
        onView(withId(R.id.button)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.button)).perform(click());
    }
    @Test
    public void Button2(){
        onView(withId(R.id.button2)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.button2)).perform(click());
    }
    @Test
    public void Button3(){
        onView(withId(R.id.button3)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.button3)).perform(click());
    }
    @Test
    public void Button4(){
        onView(withId(R.id.button4)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.button4)).perform(click());
        onView(withId(R.id.login_form)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.email_login_form)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.email)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.email)).perform(typeText("cake"), closeSoftKeyboard());
        onView(withId(R.id.password)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.password)).perform(typeText("bake"), closeSoftKeyboard());
        onView(withId(R.id.email_sign_in_button)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.email_sign_in_button)).perform(click());
    }
}