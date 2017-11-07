package com.novonetworks.mojito.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import javax.inject.Provider
import kotlin.reflect.KClass

private fun <T : ViewModel> ViewModelProvider.get(kClass: KClass<T>): T = get(kClass.java)

@Suppress("UNCHECKED_CAST")
private fun <T : ViewModel> Provider<T>.toFactory(): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return get() as T
        }
    }
}

fun <T : ViewModel> Provider<T>.get(activity: FragmentActivity, kClass: KClass<T>): T
        = ViewModelProviders.of(activity, toFactory()).get(kClass)

fun <T : ViewModel> Provider<T>.get(fragment: Fragment, kClass: KClass<T>): T
        = ViewModelProviders.of(fragment, toFactory()).get(kClass)
