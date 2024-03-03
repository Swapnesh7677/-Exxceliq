package com.swapnesh.exxceliq.util

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

import com.swapnesh.exxceliq.PersonTestApp

/**
 * Custom runner to disable dependency injection.
 */
class AppTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, PersonTestApp::class.java.name, context)
    }
}
