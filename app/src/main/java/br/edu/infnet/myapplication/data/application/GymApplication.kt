package br.edu.infnet.myapplication.data.application

import android.app.Application
import br.edu.infnet.myapplication.data.repository.GymRepositoy

class GymApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        GymRepositoy.initialize()
    }
}