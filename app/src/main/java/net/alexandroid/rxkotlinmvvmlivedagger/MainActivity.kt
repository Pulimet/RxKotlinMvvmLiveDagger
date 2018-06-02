package net.alexandroid.rxkotlinmvvmlivedagger

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import net.alexandroid.rxkotlinmvvmlivedagger.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setMainFragment(savedInstanceState)
    }

    private fun setMainFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }

}
