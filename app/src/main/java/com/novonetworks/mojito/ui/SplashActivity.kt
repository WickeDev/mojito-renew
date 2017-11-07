package com.novonetworks.mojito.ui

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.novonetworks.mojito.R
import com.novonetworks.mojito.databinding.ActivitySplashBinding
import com.novonetworks.mojito.di.ActivityScope
import com.novonetworks.mojito.util.BaseActivity
import com.novonetworks.mojito.util.get
import dagger.Module
import dagger.android.ContributesAndroidInjector
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

@Module
abstract class SplashActivityModule {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity
}

class SplashViewModel @Inject constructor(application: Application) : ViewModel() {
    var count: Int = 0

    init {
        Timber.d("init() called with: application = [ $application ]")
    }
}

class SplashActivity : BaseActivity() {

    @Inject lateinit var provider: Provider<SplashViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        val splash = provider.get(this, SplashViewModel::class)

        setContentView(binding.root)
        binding.splash = splash

        splash.count++
        Timber.d("onCreate() called with: splash = [ $splash ] count = [ ${splash.count} ]")
    }
}
