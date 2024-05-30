package com.example.aiapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.ai.client.generativeai.GenerativeModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.runBlocking

class text : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text)
        val input:TextInputEditText =findViewById(R.id.user_text)
        val gen:TextInputEditText=findViewById(R.id.gen_text)
        val button:TextView=findViewById(R.id.text1);
        button.setOnClickListener(){
            if(input.text==null){
                input.error="please enter text to generate"
                input.requestFocus()
            }
            else{
                generateinfo(input,gen)
            }
        }







    }

    fun generateinfo(input: TextInputEditText, gen: TextInputEditText) {
        val generativeModel = GenerativeModel(

            modelName = "gemini-1.5-flash",

            apiKey ="AIzaSyA7n95qfOVrQOFpFlG2qDAwXP5SLH4yqvU"
        )

        val prompt = input.text.toString().trim()
        runBlocking {
            val response = generativeModel.generateContent(prompt)
            gen.setText(response.text)

        }
    }
}