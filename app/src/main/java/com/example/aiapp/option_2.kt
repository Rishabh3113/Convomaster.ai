package com.example.aiapp

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.ai.client.generativeai.GenerativeModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.runBlocking

class option_2 : AppCompatActivity() {

    private val request=100;
    private val imagepicker=101;
    var imageuri: Uri? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option2)
        val inputedittext:TextInputEditText=findViewById(R.id.gen_text)
        val output:TextInputEditText=findViewById(R.id.gen_image)
        val text:TextView=findViewById(R.id.text2)
        val images:ImageView=findViewById(R.id.images)
        val button:ImageButton=findViewById(R.id.btn)
        val progress:ProgressBar=findViewById(R.id.bar)

        button.setOnClickListener(){


            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission. READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE ),  request )
            } else {
                opengallery()
            }

          //  info(inputedittext,output,image)


        }

        text.setOnClickListener(){
            var string:String=inputedittext.text.toString()
            if(TextUtils.isEmpty(string)){
                inputedittext.setError("please enter text to generate output")

            }
            else{
                progress.visibility=View.VISIBLE

                apicall(string,imageuri,output,images);
            }

        }








    }



    private fun apicall(
        string: String,
        imageuri: Uri?,
        output: TextInputEditText,
        images: ImageView
    ) {
        dismissKeyboardShortcutsHelper()
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = "AIzaSyA7n95qfOVrQOFpFlG2qDAwXP5SLH4yqvU"
        )
        val image:Bitmap=MediaStore.Images.Media.getBitmap(contentResolver,imageuri)

        runBlocking {
            val response = generativeModel.generateContent(image)

            output.setText(response.text)



        }

    }





    //step 2 open gallery
    private  fun   opengallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, imagepicker)

    }
    //step 3 on requestpermission
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==request){
            if(!grantResults.isEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                opengallery()
            }



        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == imagepicker) {

             imageuri = data?.data

           // findViewById<ImageView>(R.id.images).setImageURI(imageuri)
            val image:ImageView=findViewById(R.id.images)
            image.visibility= View.VISIBLE
            image.setImageURI(imageuri)


        }
    }
}






