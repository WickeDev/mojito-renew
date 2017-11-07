package com.novonetworks.mojito

import android.app.Application
import com.novonetworks.mojito.ui.SplashActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import timber.log.Timber


@dagger.Component(modules = [
AndroidSupportInjectionModule::class,
SplashActivityModule::class
])
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>() {
        @BindsInstance
        abstract fun application(application: Application): Builder
    }
}

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<App>? {
        return DaggerAppComponent.builder().application(this).create(this)
    }

    override fun onCreate() {
        super.onCreate()
        setupLog()
    }

    private fun setupLog() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}