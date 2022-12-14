package br.edu.infnet.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.edu.infnet.myapplication.databinding.ActivityMainBinding
import br.edu.infnet.myapplication.ui.home.viewmodel.HomeSerieViewModel
import br.edu.infnet.myapplication.ui.login.LoginActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds

class HomeActivity : AppCompatActivity() {
    val viewModel by viewModels<HomeSerieViewModel>()
    /*private lateinit var appBarConfiguration: AppBarConfiguration*/
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        MobileAds.initialize(this) {}

        val adRequest = AdRequest.Builder().build()
        binding.apply{
            adView.loadAd(adRequest)
            logOutButton.setOnClickListener {
                viewModel.logout()
                startLoginActivity()
            }
        }

    }

    private fun startLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }


}



//TODO: Layout de cadastro de usuário - API
//TODO: API
//TODO: Crud Exercicios