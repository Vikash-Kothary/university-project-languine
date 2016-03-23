package com.puzzle.appname;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.runner.AndroidJUnit4;

import com.puzzle.appname.ui.activity.LessonSelectActivity;

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
    public final ActivityRule<LessonSelectActivity> main = new ActivityRule<>(LessonSelectActivity.class);

    @Test
    public void LockScreen() {
//        onView(withId(R.id.card_list)).check(ViewAssertions.matches(isDisplayed()));
//        onView(withContentDescription("Open navigation drawer")).perform(click());
//        //open new activity
//        onView(withText("Lock Screen")).perform(click());
//        onView(withId(R.id.login_form)).check(ViewAssertions.matches(isDisplayed()));
//        onView(withId(R.id.email_login_form)).check(ViewAssertions.matches(isDisplayed()));
//        //test for fail
//        onView(withId(R.id.email)).check(ViewAssertions.matches(isDisplayed()));
//        onView(withId(R.id.email)).perform(typeText("cake"), closeSoftKeyboard());
//        onView(withId(R.id.password)).check(ViewAssertions.matches(isDisplayed()));
//        onView(withId(R.id.password)).perform(typeText("bak"), closeSoftKeyboard());
//        onView(withId(R.id.sign_in)).check(ViewAssertions.matches(isDisplayed()));
//        onView(withId(R.id.sign_in)).perform(click());
//        onView(withId(R.id.card_list)).check(ViewAssertions.doesNotExist());
//        //test for pass
//        onView(withId(R.id.email)).perform(clearText(),typeText("cake@baker"), closeSoftKeyboard());
//        onView(withId(R.id.password)).perform(clearText(),typeText("bakes"), closeSoftKeyboard());
//        onView(withId(R.id.sign_in)).perform(click());
//        onView(withId(R.id.card_list)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void Credits(){
        onView(withId(R.id.card_list)).check(ViewAssertions.matches(isDisplayed()));
        onView(withContentDescription("Open navigation drawer")).perform(click());
        onView(withId(R.id.nav_view)).perform(swipeUp());
        onView(withText("Credits")).perform(click());
        onView(withText(R.string.credits_text)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void ExerciseMenu(){
        onView(withId(R.id.card_list)).check(ViewAssertions.matches(isDisplayed()));
        onView(withContentDescription("Open navigation drawer")).perform(click());
        onView(withText("Exercise Menu")).perform(click());
        onView(withText("Observe")).check(ViewAssertions.matches(isDisplayed()));
        onView(withText("Reflect")).check(ViewAssertions.matches(isDisplayed()));
        onView(withText("Experiment")).check(ViewAssertions.matches(isDisplayed()));
    }
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

//    @Test
//    public void QuizIntroduction(){
//        onView(withId(R.id.card_list)).check(ViewAssertions.matches(isDisplayed()));
//        onView(withContentDescription("Open navigation drawer")).perform(click());
//        onView(withId(R.id.nav_view)).perform(swipeUp());
//        onView(withText("Quiz Introduction")).perform(click());
//    }
}