package net.alexandroid.rxkotlinmvvmlivedagger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import net.alexandroid.utils.mylog.MyLog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        findNavController(R.id.nav_host_fragment)
                .addOnNavigatedListener { controller, destination -> MyLog.d("On navigation: " + destination.label) }
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()
}
