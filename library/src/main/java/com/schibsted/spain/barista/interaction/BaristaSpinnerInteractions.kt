package com.schibsted.spain.barista.interaction

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.widget.AdapterView
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.instanceOf

object BaristaSpinnerInteractions {

  @JvmStatic
  fun clickSpinnerItem(@IdRes id: Int, position: Int) {
    clickOn(id)
    performClick(position)
  }

  @JvmStatic
  fun clickSpinnerItem(@IdRes id: Int, modelClass: Class<*>, position: Int) {
    clickOn(id)
    performClick(position, modelClass)
  }

  private fun performClick(position: Int) {
    onData(anything())
        .inAdapterView(allOf(isAssignableFrom(AdapterView::class.java), isDisplayed()))
        .atPosition(position)
        .perform(ViewActions.click())
  }

  private fun performClick(position: Int, modelClass: Class<*>) {
    onData(`is`(instanceOf(modelClass)))
        .inAdapterView(allOf(isAssignableFrom(AdapterView::class.java), isDisplayed()))
        .atPosition(position)
        .perform(ViewActions.click())
  }
}
