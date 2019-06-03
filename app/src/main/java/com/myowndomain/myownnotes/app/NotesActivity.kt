package com.myowndomain.myownnotes.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.myowndomain.myownnotes.app.R
import android.app.Activity
import android.view.inputmethod.InputMethodManager


class NotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notes_activity)
    }

    override fun onSupportNavigateUp(): Boolean {
        hideSoftKeyboard(this)
        onBackPressed()
        return true
    }

    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputMethodManager.isActive) {
            if (activity.currentFocus != null) {
                inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
            }
        }
    }
}
