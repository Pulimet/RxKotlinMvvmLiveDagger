package net.alexandroid.rxkotlinmvvmlivedagger.dagger

import dagger.Component
import net.alexandroid.rxkotlinmvvmlivedagger.MyApplication
import net.alexandroid.rxkotlinmvvmlivedagger.ui.main.MainFragment

@MyApplicationScope
@Component(modules = [ViewModuleFactoryModule::class])
interface MyApplicationComponent {
    fun inject(myApplication: MyApplication)

    fun inject(mainFragment: MainFragment)
}
