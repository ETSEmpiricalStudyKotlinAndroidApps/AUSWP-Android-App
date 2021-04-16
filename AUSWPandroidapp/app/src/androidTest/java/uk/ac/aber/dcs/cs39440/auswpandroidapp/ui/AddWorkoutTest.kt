package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.ac.aber.dcs.cs39440.auswpandroidapp.R

@LargeTest
@RunWith(AndroidJUnit4::class)
class AddWorkoutTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun addWorkoutTest() {
        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.navigation_Tracker), withContentDescription("Tracker"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_nav_view),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val view = onView(
            allOf(
                withId(R.id.tracker_view),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        view.perform(click())

        val floatingActionButton = onView(
            allOf(
                withId(R.id.add_words),
                childAtPosition(
                    allOf(
                        withId(R.id.coordinator),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.nameEntry),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("Run"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.nameEntry), withText("Run"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(pressImeActionButton())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.dateEntry),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(replaceText("24/04/2021"), closeSoftKeyboard())

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.dateEntry), withText("24/04/2021"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(pressImeActionButton())

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.userStats),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        appCompatEditText5.perform(replaceText("5km pb - 22:31"), closeSoftKeyboard())

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.userStats), withText("5km pb - 22:31"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        appCompatEditText6.perform(pressImeActionButton())

        val floatingActionButton2 = onView(
            allOf(
                withId(R.id.add),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        floatingActionButton2.perform(click())
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
