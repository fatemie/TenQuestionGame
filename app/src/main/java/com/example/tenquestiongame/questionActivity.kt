package com.example.tenquestiongame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tenquestiongame.databinding.ActivityQuectionBinding

class questionActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuectionBinding
    var questionNumber = 0
    var question = Question()
    var answerPressed = false
    var cheatUsed = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuectionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        question.data()
        questionNumber = intent.getIntExtra("questionNumber", 0)
        title = "Question $questionNumber"
        setListener()

    }

//    fun onActivityResult( requestCode : Int, resultCode : Int, data : Intent) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 100) {
//            if (resultCode == RESULT_OK) {
//                var result = data.getStringExtra("resultBack")
//                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    fun setListener(){
        setQuestion()
        if(questionNumber == 1){
            binding.prevbutton.isEnabled = false
        }

        if(question.questionList[questionNumber].isCheated == false){
            Toast.makeText(this, "you can use cheat", Toast.LENGTH_SHORT).show()
        }


        binding.truebutton.setOnClickListener {
            setTrueButton()
        }
        binding.falsebutton.setOnClickListener {
            setFalseButton()
        }
        binding.nextbutton.setOnClickListener {
            setNextButton()
        }
        binding.prevbutton.setOnClickListener {
            setPrevButton()
        }
        binding.cheatbutton.setOnClickListener {
            setCheatButton()
        }
    }

    fun setQuestion(){
        binding.questionView.text = question.questionList[questionNumber-1].description
    }

    fun setTrueButton(){
        if(!answerPressed){
            if(question.questionList[questionNumber-1].answer == true){
                 Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
            }
            else{
                 Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
            }
        answerPressed = true
        }
    }

    fun setFalseButton(){
        if(!answerPressed) {
            if (question.questionList[questionNumber-1].answer == false) {
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
            }
        }
        answerPressed = true
    }

    fun setNextButton(){
        if(questionNumber != 3) {
            questionNumber += 1
            binding.prevbutton.isEnabled = true
            setQuestion()
            answerPressed = false
        }
        if(questionNumber == 3){
            binding.nextbutton.isEnabled = false
        }
    }

    fun setPrevButton(){
        if(questionNumber != 1) {
            questionNumber -= 1
            binding.nextbutton.isEnabled = true
            setQuestion()
            answerPressed = false
        }
        if(questionNumber == 1){
            binding.prevbutton.isEnabled = false
        }
    }

    fun setCheatButton(){

        if(question.questionList[questionNumber].isCheated == false){
            val intent = Intent(this, CheatActivity::class.java).
            putExtra("questionNumber", questionNumber)
            startActivity(intent)
        }
        else{
            Toast.makeText(this, "you can't use cheat", Toast.LENGTH_SHORT).show()
        }

    }

}