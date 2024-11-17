package tech.ishe.smatechpos

import android.app.Application
import androidx.room.Room
import tech.ishe.smatechpos.data.db.CartDatabase

class MainApplication : Application() {
    companion object {
        lateinit var cartDatabase: CartDatabase
    }

    override fun onCreate() {
        super.onCreate()
        cartDatabase = Room.databaseBuilder(
            applicationContext,
            CartDatabase::class.java,
            CartDatabase.NAME
        ).build()

    }
}