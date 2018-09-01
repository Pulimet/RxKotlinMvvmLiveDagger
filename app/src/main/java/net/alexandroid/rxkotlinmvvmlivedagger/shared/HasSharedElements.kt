package net.alexandroid.rxkotlinmvvmlivedagger.shared

import android.view.View

interface HasSharedElements {

    fun getSharedElements(): Map<String, View>

    fun hasReorderingAllowed(): Boolean
}