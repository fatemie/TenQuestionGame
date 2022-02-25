package com.example.tenquestiongame

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.tenquestiongame.databinding.ActivityQuectionBinding

class questionActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuectionBinding
    var questionNumber = 0
    var question = Question()
    var answerPressed = false
    var cheatList = arrayListOf(false, false,false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuectionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        question.data()
        questionNumber = intent.getIntExtra("questionNumber", 0)
      //  title = "Question $questionNumber"
        setListener()

    }


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
        }
        answerPressed = true
//        binding.truebutton.isEnabled =false
//        binding.falsebutton.isEnabled=false
//        binding.cheatbutton.isEnabled=false
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
//        binding.truebutton.isEnabled =false
//        binding.falsebutton.isEnabled=false
//        binding.cheatbutton.isEnabled=false

    }

    fun setNextButton(){
        if(questionNumber != 3) {
            questionNumber += 1
            binding.prevbutton.isEnabled = true
            setQuestion()
            answerPressed = false
//            if(question.questionList[questionNumber-1].isCheated){
//                Toast.makeText(this, "you can't use cheat", Toast.LENGTH_SHORT).show()
//            }
//            else{
//                Toast.makeText(this, "you can use cheat", Toast.LENGTH_SHORT).show()
//            }
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
//            if(question.questionList[questionNumber-1].isCheated){
//                Toast.makeText(this, "you can't use cheat", Toast.LENGTH_SHORT).show()
//            }
//            else{
//                Toast.makeText(this, "you can use cheat", Toast.LENGTH_SHORT).show()
//            }
        }
        if(questionNumber == 1){
            binding.prevbutton.isEnabled = false
        }
    }

    fun setCheatButton(){

        if(question.questionList[questionNumber-1].isCheated == false){
            val intent = Intent(this, CheatActivity::class.java).
            putExtra("questionNumber", questionNumber)
            startForResult.launch(intent)
        }
        else{
            Toast.makeText(this, "you can't use cheat", Toast.LENGTH_SHORT).show()
        }

    }

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            val cheatisUsed =  intent?.getBooleanExtra("isCheated", false)

            cheatisUsed?.let{
                if(it) {
                    Toast.makeText(this, "cheat use!", Toast.LENGTH_SHORT).show()
                    question.questionList[questionNumber-1].isCheated = true
                }
            }
        }
    }

}