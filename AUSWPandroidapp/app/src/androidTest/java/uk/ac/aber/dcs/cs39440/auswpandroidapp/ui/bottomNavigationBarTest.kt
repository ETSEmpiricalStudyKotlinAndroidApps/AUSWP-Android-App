package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
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
class bottomNavigationBarTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun bottomNavigationBarTest() {
        val bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_Committee), withContentDescription("Committee"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav_view),
                                        0),
                                1),
                        isDisplayed()))
        bottomNavigationItemView.perform(click())

        val bottomNavigationItemView2 = onView(
                allOf(withId(R.id.navigation_Events), withContentDescription("Events"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav_view),
                                        0),
                                2),
                        isDisplayed()))
        bottomNavigationItemView2.perform(click())

        val bottomNavigationItemView3 = onView(
                allOf(withId(R.id.navigation_Tracker), withContentDescription("Tracker"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav_view),
                                        0),
                                3),
                        isDisplayed()))
        bottomNavigationItemView3.perform(click())

        val bottomNavigationItemView4 = onView(
                allOf(withId(R.id.navigation_home), withContentDescription("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav_view),
                                        0),
                                0),
                        isDisplayed()))
        bottomNavigationItemView4.perform(click())
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

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
