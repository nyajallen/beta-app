package child.wellness.app

import android.app.Activity
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import child.wellness.app.parentactivity.ParentActivity
import child.wellness.app.parentactivity.ParentSettingsFragment
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import kotlin.coroutines.coroutineContext

@RunWith(AndroidJUnit4::class)
@LargeTest
class SettingsFragmentTest {

    private lateinit var typeChildName: String
    private lateinit var typeChildNumber: String
    private lateinit var typeParentName: String
    private lateinit var typeParentNumber: String

    @get:Rule
    var activityRule: ActivityScenarioRule<ParentActivity> = ActivityScenarioRule(ParentActivity::class.java)

    @Before
    fun launchFragmentAndInitValidStrings() {

        typeChildName = "Johnny Doe"
        typeChildNumber = "5555555566"
        typeParentName = "Mrs. Doe"
        typeParentNumber = "5555555544"

    }

    @Test
    fun openSettingsFragment() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)

        onView(withText("Settings")).perform(click())
    }

    @Test
    fun editChildNameSettings() {
        // Edit Child name
        onView(withId(R.id.edit_button1)).perform(click())
        onView(withId(R.id.child_edit)).perform(typeText(typeChildName), closeSoftKeyboard())
        onView(withId(R.id.done_button1)).perform(click())

        // Check that text was changed
        onView(withId(R.id.child_name_text)).check(matches(withText(typeChildName)))
    }

    @Test
    fun editChildNoSettings() {
        // Edit Child number
        onView(withId(R.id.edit_button2)).perform(click())
        onView(withId(R.id.child_phone_edit)).perform(typeText(typeChildNumber), closeSoftKeyboard())
        onView(withId(R.id.done_button2)).perform(click())

        // Check that text was changed
        onView(withId(R.id.child_number_text)).check(matches(withText(typeChildNumber)))
    }

    @Test
    fun editParentNameSettings() {
        // Edit Parent name
        onView(withId(R.id.edit_button3)).perform(click())
        onView(withId(R.id.parent_edit)).perform(typeText(typeParentName), closeSoftKeyboard())
        onView(withId(R.id.done_button3)).perform(click())

        // Check that text was changed
        onView(withId(R.id.parent_name_text)).check(matches(withText(typeParentName)))
    }

    @Test
    fun editParentNoSettings() {
        // Edit Child name
        onView(withId(R.id.edit_button4)).perform(click())
        onView(withId(R.id.parent_phone_edit)).perform(typeText(typeParentNumber), closeSoftKeyboard())
        onView(withId(R.id.done_button4)).perform(click())

        // Check that text was changed
        onView(withId(R.id.parent_number_text)).check(matches(withText(typeParentNumber)))
    }
}