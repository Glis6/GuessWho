package com.github.glis6.guesswho;

import com.github.glis6.guesswho.activities.MenuActivity;

import org.junit.Rule;
import org.junit.Test;

/**
 * @author Glis
 */
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MenuActivity> menuActivityActivityTestRule = new ActivityTestRule<MenuActivity>(MenuActivity.class);

    @Test
    public void testOnStart() {
        onView(withId(R.id.start_game)).perform(click());
        onView(withId(R.layout.activity_main)).check(matches(isDisplayed()));
    }

    @Test
    public void testOnSettings() {
        onView(withId(R.id.character_settings)).perform(click());
        onView(withId(R.layout.activity_settings)).check(matches(isDisplayed()));
    }
}
