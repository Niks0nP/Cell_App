package nikita.app.cell_app.logic

import android.app.Application

class App : Application() {

    val logic = Logic()
    companion object {
        private lateinit var instance: App

        fun getInstance() : App {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}