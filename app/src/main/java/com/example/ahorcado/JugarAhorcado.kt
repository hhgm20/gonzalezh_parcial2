package com.example.ahorcado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class JugarAhorcado : AppCompatActivity() {


    private lateinit var tvPalabra: TextView
    private lateinit var etAdivinar: EditText
    private lateinit var btnAdivinar: Button
    private lateinit var tvResultado: TextView
    private lateinit var btnReiniciar: Button
    private lateinit var btnRegresar: Button

    private val listaPalabras = listOf("HOLA", "MUNDO", "ANDROID", "STUDIO", "VARIABLE", "CONSTANTE", "PROGRAMACION", "CODIGO",)
    private var palabraAdivinar = ""
    private var intentos = 6
    private var letrasAdivinadas = mutableListOf<Char>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jugar_ahorcado)

        tvPalabra = findViewById(R.id.tvPalabra)
        etAdivinar = findViewById(R.id.etAdivinar)
        btnAdivinar = findViewById(R.id.btnAdivinar)
        tvResultado = findViewById(R.id.tvResultado)
        btnReiniciar = findViewById(R.id.btnReiniciar)
        btnRegresar = findViewById(R.id.btnRegresar)

        btnReiniciar.isEnabled = false

        iniciarNuveoJuego()

        btnAdivinar.setOnClickListener {
            val guess = etAdivinar.text.toString().uppercase()

            if (guess.isNotEmpty() && guess.length == 1) {
                if (letrasAdivinadas.contains(guess[0])) {
                    Toast.makeText(this, "Ya ingresaste esta letra", Toast.LENGTH_SHORT).show()
                } else {
                    letrasAdivinadas.add(guess[0])
                    actualizarTvPalabra()

                    if (!palabraAdivinar.contains(guess[0])) {
                        intentos--
                        actualizarTvResultado()

                        if (intentos == 0) {
                            perdiste()
                        }
                    } else if (tvPalabra.text.toString() == palabraAdivinar) {
                        ganaste()
                    }
                }
            } else {
                Toast.makeText(this, "Ingresa solo una letra", Toast.LENGTH_SHORT).show()
            }

            etAdivinar.text.clear()
        }

        btnReiniciar.setOnClickListener {
            iniciarNuveoJuego()
        }

        btnRegresar.setOnClickListener {
            onBackPressed()
        }
    }

    private fun iniciarNuveoJuego() {
        palabraAdivinar = listaPalabras.random()
        intentos = 6
        letrasAdivinadas.clear()
        actualizarTvPalabra()
        actualizarTvResultado()
        btnReiniciar.isEnabled = false
    }

    private fun actualizarTvPalabra() {
        val statusPalabra = StringBuilder()

        for (char in palabraAdivinar) {
            if (letrasAdivinadas.contains(char)) {
                statusPalabra.append(char)
            } else {
                statusPalabra.append("_")
            }
            statusPalabra.append(" ")
        }

        tvPalabra.text = statusPalabra.toString()
    }

    private fun actualizarTvResultado() {
        tvResultado.text = "Intentos restantes $intentos"

        if (intentos == 0) {
            tvResultado.append("\nFin del juego! La palabra era: $palabraAdivinar")
            btnReiniciar.isEnabled = true
        }
    }

    private fun ganaste() {
        tvResultado.text = "Felicidades! Ganaste!"
        btnReiniciar.isEnabled = true
    }

    private fun perdiste() {
        tvResultado.text = "Fin del juego! La palabra era: $palabraAdivinar"
        btnReiniciar.isEnabled = true
    }
}