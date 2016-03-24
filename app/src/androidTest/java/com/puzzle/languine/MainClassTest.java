package com.puzzle.languine;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.runner.AndroidJUnit4;

import com.puzzle.languine.ui.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class MainClassTest {

    @Rule
    public final ActivityRule<MainActivity> main = new ActivityRule<>(MainActivity.class);

    @Test
    public void LockScreen() {
        onView(withContentDescription("Open navigation drawer")).perform(click());
        //open new activity
        onView(withText("Sign In")).perform(click());
        onView(withId(R.id.email_login_form)).check(ViewAssertions.matches(isDisplayed()));
        //test for fail
        onView(withId(R.id.username)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.username)).perform(typeText("cake"), closeSoftKeyboard());
        onView(withId(R.id.password)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.password)).perform(typeText("bak"), closeSoftKeyboard());
        onView(withId(R.id.button_sign_in)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.button_sign_in)).perform(click());
        //test for pass
        onView(withId(R.id.username)).perform(clearText(), typeText("cake@bake.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(clearText(), typeText("soup"), closeSoftKeyboard());
        onView(withId(R.id.button_sign_in)).perform(click());
    }

    @Test
    public void Credits() {
        onView(withContentDescription("Open navigation drawer")).perform(click());
        onView(withId(R.id.nav_view)).perform(swipeUp());
        onView(withText("Credits")).perform(click());
        onView(withText(R.string.credits_text)).check(ViewAssertions.matches(isDisplayed()));
    }

//    @Test
//    public void ExerciseMenu(){
//        onView(withContentDescription("Open navigation drawer")).perform(click());
//        onView(withText("Exercise Menu")).perform(click());
//        onView(withText("Observe")).check(ViewAssertions.matches(isDisplayed()));
//        onView(withText("Reflect")).check(ViewAssertions.matches(isDisplayed()));
//        onView(withText("Experiment")).check(ViewAssertions.matches(isDisplayed()));
//    }
//
//    @Test
//    public void AudioQuiz(){
//        onView(withId(R.id.card_list)).check(ViewAssertions.matches(isDisplayed()));
//        onView(withContentDescription("Open navigation drawer")).perform(click());
//        onView(withId(R.id.nav_view)).perform(swipeUp());
//        onView(withText("Audio Quiz")).perform(click());
//        onView(withText("Play")).perform(click());
//    }
//    @Test
//    public void TestQuestions(){
//        onView(withId(R.id.card_list)).check(ViewAssertions.matches(isDisplayed()));
//        onView(withContentDescription("Open navigation drawer")).perform(click());
//        onView(withText("Text Questions")).perform(click());
//}
//    @Test
//    public void PictureQuestions(){
//        onView(withId(R.id.card_list)).check(ViewAssertions.matches(isDisplayed()));
//        onView(withContentDescription("Open navigation drawer")).perform(click());
//        onView(withText("Picture Questions")).perform(click());
//    }

    @Test
     public void Glossery(){
        onView(withContentDescription("Open navigation drawer")).perform(click());
        onView(withId(R.id.nav_view)).perform(swipeUp());
        onView(withText("Glossary")).perform(click());
    }
//    @Test
//    public void Resources(){
//        onView(withContentDescription("Open navigation drawer")).perform(click());
//        onView(withId(R.id.nav_view)).perform(swipeUp());
//        onView(withText("Resources")).perform(click());
//    }

    @Test
    public void SAlphabeto(){
        onView(withContentDescription("Open navigation drawer")).perform(click());
        onView(withId(R.id.nav_view)).perform(swipeUp());
        onView(withText("Resources")).perform(click());
        onView(withText("El Alphabeto")).perform(click());
        onView(withText("Spanish")).perform(click());
        onView(withId(R.id.a)).perform(click());
        onView(withId(R.id.b)).perform(click());
        onView(withId(R.id.c)).perform(click());
        onView(withId(R.id.d)).perform(click());
        onView(withId(R.id.e)).perform(click());
        onView(withId(R.id.f)).perform(click());
        onView(withId(R.id.g)).perform(click());
        onView(withId(R.id.h)).perform(click());
    }
    @Test
    public void MAlphabeto(){
        onView(withContentDescription("Open navigation drawer")).perform(click());
        onView(withId(R.id.nav_view)).perform(swipeUp());
        onView(withText("Resources")).perform(click());
        onView(withText("El Alphabeto")).perform(click());
        onView(withText("Mexican")).perform(click());
        onView(withId(R.id.a)).perform(click());
        onView(withId(R.id.b)).perform(click());
        onView(withId(R.id.c)).perform(click());
        onView(withId(R.id.d)).perform(click());
        onView(withId(R.id.e)).perform(click());
        onView(withId(R.id.f)).perform(click());
        onView(withId(R.id.g)).perform(click());
        onView(withId(R.id.h)).perform(click());
    }

    @Test
    public void SNumeros(){
        onView(withContentDescription("Open navigation drawer")).perform(click());
        onView(withId(R.id.nav_view)).perform(swipeUp());
        onView(withText("Resources")).perform(click());
        onView(withText("Los Numeros")).perform(click());
        onView(withText("Spanish")).perform(click());
        onView(withId(R.id.one)).perform(click());
        onView(withId(R.id.two)).perform(click());
        onView(withId(R.id.three)).perform(click());
        onView(withId(R.id.four)).perform(click());
        onView(withId(R.id.five)).perform(click());
        onView(withId(R.id.six)).perform(click());
        onView(withId(R.id.seven)).perform(click());
        onView(withId(R.id.eight)).perform(click());
    }

    @Test
    public void MNumeros(){
        onView(withContentDescription("Open navigation drawer")).perform(click());
        onView(withId(R.id.nav_view)).perform(swipeUp());
        onView(withText("Resources")).perform(click());
        onView(withText("Los Numeros")).perform(click());
        onView(withText("Mexican")).perform(click());
        onView(withId(R.id.one)).perform(click());
        onView(withId(R.id.two)).perform(click());
        onView(withId(R.id.three)).perform(click());
        onView(withId(R.id.four)).perform(click());
        onView(withId(R.id.five)).perform(click());
        onView(withId(R.id.six)).perform(click());
        onView(withId(R.id.seven)).perform(click());
        onView(withId(R.id.eight)).perform(click());
    }
}