package com.danx0921.room_exercicio

import android.app.Application
import com.danx0921.room_exercicio.model.AppDatabase


class App : Application() {


    lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()
        db = AppDatabase.getDatabase(this)
    }


}