package net.alexandroid.rxkotlinmvvmlivedagger.dagger

import dagger.Component
import net.alexandroid.rxkotlinmvvmlivedagger.MainActivity
import net.alexandroid.rxkotlinmvvmlivedagger.MyApplication

@MyApplicationScope
@Component(modules = [PhotosApiModule::class])
interface MyApplicationComponent {
    fun inject(myApplication: MyApplication)

    fun inject(mainActivity: MainActivity)
}