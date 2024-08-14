package nikita.app.cell_app.logic.application

import android.app.Application
import nikita.app.cell_app.viewmodel.MyViewModel

class App : Application() {

    val viewModel = MyViewModel()
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