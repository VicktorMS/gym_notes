package br.edu.infnet.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.infnet.myapplication.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}