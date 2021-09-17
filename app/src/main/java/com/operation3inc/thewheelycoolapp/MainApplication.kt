package com.operation3inc.thewheelycoolapp

import android.app.Application
import androidx.room.Room
import com.operation3inc.thewheelycoolapp.db.AppDatabase
import com.operation3inc.thewheelycoolapp.repository.OptionRepository
import com.operation3inc.thewheelycoolapp.viewmodel.OptionViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }

    private val appModule = module {

        /**
         * Room database instance only initialised once when app started
         */
        single {
            Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "wheel-db")
                .build()
        }

        single { get<AppDatabase>().optionDao() }

        single { OptionRepository(get()) }

        viewModel { OptionViewModel(get()) }
    }
}