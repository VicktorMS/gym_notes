package br.edu.infnet.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.edu.infnet.myapplication.databinding.ActivityMainBinding
import br.edu.infnet.myapplication.ui.home.viewmodel.HomeSerieViewModel
import br.edu.infnet.myapplication.ui.login.LoginActivity

class HomeActivity : AppCompatActivity() {

    val viewModel by viewModels<HomeSerieViewModel>()
    /*private lateinit var appBarConfiguration: AppBarConfiguration*/
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        logoutOnClick()
    }

    private fun logoutOnClick() {
        binding.imageButtonLogout.setOnClickListener {
            viewModel.logout()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }


}



//TODO: Adapter de Série
//TODO: Layout de cadastro de usuário - API
//TODO: API
//TODO: Crud Exercicios
//TODO: Edit Delete