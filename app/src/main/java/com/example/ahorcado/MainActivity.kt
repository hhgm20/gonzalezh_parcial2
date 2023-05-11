package com.example.ahorcado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ahorcado.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnJugar.setOnClickListener { navegarJugarAhorcado() }
    }

    private fun navegarJugarAhorcado() {
        val intent = Intent (this, JugarAhorcado::class.java)
        startActivity(intent)
    }
}