package child.wellness.app.loginmenus


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import child.wellness.app.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class Incorrect_LoginPassword {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(Login::class.java)

    @Test
    fun incorrect_LoginPassword() {
        val appCompatButton = onView(
            allOf(
                withId(R.id.login_text_view), withText("Are you a Parent? Log In Here"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        val editText = onView(
            allOf(
                withId(R.id.editText),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        editText.perform(replaceText("test@test.com"), closeSoftKeyboard())

        val editText2 = onView(
            allOf(
                withId(R.id.editText2),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        editText2.perform(replaceText("gh"), closeSoftKeyboard())

        val button = onView(
            allOf(
                withId(R.id.button), withText("login"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        button.perform(click())

        val editText3 = onView(
            allOf(
                withId(R.id.editText2), withText("gh"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        editText3.perform(replaceText("testingg"))

        val editText4 = onView(
            allOf(
                withId(R.id.editText2), withText("testingg"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        editText4.perform(closeSoftKeyboard())

        val button2 = onView(
            allOf(
                withId(R.id.button), withText("login"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        button2.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.textView), withText("Login"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
