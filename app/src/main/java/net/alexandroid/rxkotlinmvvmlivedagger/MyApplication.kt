package net.alexandroid.rxkotlinmvvmlivedagger

import android.app.Application
import net.alexandroid.rxkotlinmvvmlivedagger.dagger.ContextModule
import net.alexandroid.rxkotlinmvvmlivedagger.dagger.DaggerMyApplicationComponent
import net.alexandroid.rxkotlinmvvmlivedagger.dagger.MyApplicationComponent
import net.alexandroid.utils.mylog.MyLog

class MyApplication : Application() {
    companion object {
        //platformStatic allow access it from java code
        @JvmStatic lateinit var component: MyApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        MyLog.init(this)
        MyLog.setTag("ZAQ")

        component = DaggerMyApplicationComponent
                .builder()
                .contextModule(ContextModule(this))
                .build()
        //component.inject(this)
    }

}