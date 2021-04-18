
package child.wellness.app
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.GeneralClickAction
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Tap
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import child.wellness.app.childactivity.MainActivity
import child.wellness.app.loginmenus.Login
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


class LoginTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<Login>
            = ActivityScenarioRule(Login::class.java)
    @Test
    fun login() {

        onView(withId(R.id.login_text_view)).perform(click())
        onView(withId(R.id.editText))
            .perform(typeText("test@test.com"), closeSoftKeyboard())
        onView(withId(R.id.editText2))
            .perform(typeText("testing"), closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
    }
}
