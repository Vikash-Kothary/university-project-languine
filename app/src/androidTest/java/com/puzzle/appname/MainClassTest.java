package com.puzzle.appname;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class MainClassTest {

    @Rule
    public final ActivityRule<LessonSelectActivity> main = new ActivityRule<>(LessonSelectActivity.class);

    @Test
    public void LockScreen() {
        onView(withId(R.id.card_list)).check(ViewAssertions.matches(isDisplayed()));
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Settings")).perform(click());
        onView(withText("Lock Screen")).perform(click());
        onView(withId(R.id.login_form)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.email_login_form)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.email)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.email)).perform(typeText("cake@baker"), closeSoftKeyboard());
        onView(withId(R.id.password)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.password)).perform(typeText("bakes"), closeSoftKeyboard());
        onView(withId(R.id.email_sign_in_button)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.email_sign_in_button)).perform(click());
    }

    @Test
    public void 
}