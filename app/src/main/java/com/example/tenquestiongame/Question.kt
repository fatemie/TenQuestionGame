package com.example.tenquestiongame

data class Question(var description  : String = "", var answer : Boolean = true, var isCheated : Boolean = false) {
    val questionList = arrayListOf<Question>()

    fun data(){
        questionList.clear()
        val question1 = Question("آیا زرافه بلندقدترین حیوان است؟", true, false)
        val question2 = Question("آیا اختاپوس ها می توانند رنگ پوست خود را تغییر دهند؟", true, false)
        val question3 = Question("اگر تمام رنگها را با هم مخلوط کنیم؟ آیا رنگین کمان خواهیم داشت؟", false, false)
        questionList.add(question1)
        questionList.add(question2)
        questionList.add(question3)
    }


}
