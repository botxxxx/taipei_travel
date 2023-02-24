package com.example.travel.activity

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference

abstract class BaseCompatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addSecureFlags()
    }

    /**
     * @see android.view.Display.FLAG_SECURE
     * treat the content of the window as secure, preventing it from appearing in screenshots or from being viewed on non-secure displays.
     */
    private fun Activity.addSecureFlags() {
        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
    }

    override fun onPause() {
        super.onPause()
        clearReferences()
    }

    override fun onDestroy() {
        super.onDestroy()
        clearReferences()
    }

    private fun clearReferences() {
        activityReference?.clear()
    }

    private val unregisterCallBack: () -> Unit = {
        unregisterActivityLifecycleCallbacks(lifeCycleCallBack)
        activityReference?.clear()
        activityReference = null
    }
    private val lifeCycleCallBack = ActivityLifeCycleCallBack(unregisterCallBack)

    companion object {
        var activityReference: WeakReference<Activity?>? = null
    }
}

class ActivityLifeCycleCallBack(private val unregisterCallBack: (() -> Unit)) : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        BaseCompatActivity.activityReference = WeakReference(p0)
    }

    override fun onActivityStarted(p0: Activity) {
        unregisterCallBack.invoke()
    }

    override fun onActivityResumed(p0: Activity) {}

    override fun onActivityPaused(p0: Activity) {}

    override fun onActivityStopped(p0: Activity) {}

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}

    override fun onActivityDestroyed(p0: Activity) {}
}