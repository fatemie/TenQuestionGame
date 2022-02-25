package com.example.tenquestiongame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tenquestiongame.databinding.ActivityCheatBinding

class CheatActivity : AppCompatActivity() {

    lateinit var binding: ActivityCheatBinding
    val question = Question()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListener()
    }

    fun setListener(){
        question.data()
        binding.button2.setOnClickListener {
            setShowAnswerButton()
        }
    }

    fun setShowAnswerButton(){

        if(binding.button2.text == "show answer"){
            var questionNumber = intent.getIntExtra("questionNumber", 0)

            binding.textView.text = question.questionList[questionNumber-1].answer.toString()
            question.questionList[questionNumber-1].isCheated = true

            binding.button2.text = "ok"
        }
        else{
            val resultIntent = Intent()
            resultIntent.putExtra("isCheated",true)
            setResult(RESULT_OK, resultIntent)
            finish()
        }


    }

}