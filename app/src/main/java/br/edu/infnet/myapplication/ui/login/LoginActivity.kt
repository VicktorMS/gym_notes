package br.edu.infnet.myapplication.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.edu.infnet.myapplication.databinding.ActivityLoginBinding
import br.edu.infnet.myapplication.ui.home.HomeActivity
import br.edu.infnet.myapplication.ui.login.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    val viewModel by viewModels<LoginViewModel>()

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }



    override fun onStart() {
        super.onStart()
        if(viewModel.isLoggedIn()){
            startMainActivity()
        }
    }

    fun startMainActivity(){
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }


}