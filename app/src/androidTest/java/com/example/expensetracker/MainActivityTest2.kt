package com.example.expensetracker


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.core.AllOf.allOf

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest2 {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTest2() {
        val imageButton = onView(
            allOf(
                withId(R.id.addBtn), withContentDescription("Add transaction"),
                withParent(
                    allOf(
                        withId(R.id.main),
                        withParent(withId(R.id.coordinator))
                    )
                ),
                isDisplayed()
            )
        )
        imageButton.check(matches(isDisplayed()))

        val floatingActionButton = onView(
            allOf(
                withId(R.id.addBtn), withContentDescription("Add transaction"),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(R.id.coordinator),
                            0
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())

        val button = onView(
            allOf(
                withId(R.id.addTransactionBtn), withText("Add transaction"),
                withParent(withParent(withId(R.id.main))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val textInputEditText = onView(
            allOf(
                withId(R.id.labelInput),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.labelLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("Shopping"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.amountInput),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.amountLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(replaceText("-100"), closeSoftKeyboard())

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.descriptionInput),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.descriptionLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(replaceText("Shirt"), closeSoftKeyboard())

        val materialButton = onView(
            allOf(
                withId(R.id.addTransactionBtn), withText("Add transaction"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main),
                        1
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.label), withText("Shopping"),
                withParent(withParent(withId(R.id.recyclerview))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Shopping")))

        val textView2 = onView(
            allOf(
                withId(R.id.amount), withText("- $100.00"),
                withParent(withParent(withId(R.id.recyclerview))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("- $100.00")))

        val recyclerView = onView(
            allOf(
                withId(R.id.recyclerview),
                childAtPosition(
                    withId(R.id.main),
                    3
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val viewGroup = onView(
            allOf(
                withId(R.id.rootView),
                withParent(
                    allOf(
                        withId(android.R.id.content),
                        withParent(withId(com.google.android.material.R.id.action_bar_root))
                    )
                ),
                isDisplayed()
            )
        )
        viewGroup.check(matches(isDisplayed()))

        val textInputEditText4 = onView(
            allOf(
                withId(R.id.labelInput), withText("Shopping"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.labelLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText4.perform(replaceText("Shopping12"))

        val textInputEditText5 = onView(
            allOf(
                withId(R.id.labelInput), withText("Shopping12"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.labelLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText5.perform(closeSoftKeyboard())

        val button2 = onView(
            allOf(
                withId(R.id.updateBtn), withText("Update Transaction"),
                withParent(
                    allOf(
                        withId(R.id.rootView),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        button2.check(matches(isDisplayed()))

        val materialButton2 = onView(
            allOf(
                withId(R.id.updateBtn), withText("Update Transaction"),
                childAtPosition(
                    allOf(
                        withId(R.id.rootView),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())
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
