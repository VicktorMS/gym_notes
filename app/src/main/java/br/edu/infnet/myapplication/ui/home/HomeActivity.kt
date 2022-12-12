package br.edu.infnet.myapplication.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.infnet.myapplication.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class HomeActivity : AppCompatActivity() {

    /*private lateinit var appBarConfiguration: AppBarConfiguration*/
    private lateinit var binding: ActivityMainBinding
    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MobileAds.initialize(this) {}

        mAdView = binding.adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }


}


//TODO: ViewModel exercicio
//TODO: Adapter de Série
//TODO: Layout de cadastro de usuário - API
//TODO: API
//TODO: Crud Exercicios
//TODO: Edit Delete