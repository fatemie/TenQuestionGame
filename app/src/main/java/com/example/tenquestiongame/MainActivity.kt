package com.example.tenquestiongame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tenquestiongame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setListener()

    }

    fun setListener(){
        binding.button.setOnClickListener {
            goToActivity()
        }
    }

    private fun goToActivity() {
        val intent = Intent(this, questionActivity::class.java).
        putExtra("questionNumber", 1)
        startActivity(intent)
    }
}