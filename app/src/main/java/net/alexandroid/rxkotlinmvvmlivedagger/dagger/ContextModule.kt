package net.alexandroid.rxkotlinmvvmlivedagger.dagger

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context: Context) {

    @Provides
    @MyApplicationScope
    fun context(): Context {
        return context
    }

}
