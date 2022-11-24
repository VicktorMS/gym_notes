package br.edu.infnet.myapplication.login.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.infnet.myapplication.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setup()
        setContentView(view)
    }

    private fun setup() {
        TODO("Not yet implemented")
    }
}