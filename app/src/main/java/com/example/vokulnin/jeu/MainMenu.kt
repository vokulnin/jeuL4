package com.example.vokulnin.jeu

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import com.exemple.vokulnin.jeu.MainActivity

class MainMenu : AppCompatActivity() {
    lateinit var play : Button
    lateinit var quit : Button
    lateinit var photo : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        play = findViewById(R.id.play)
        quit = findViewById(R.id.quit)
        photo = findViewById(R.id.photo)

        val REQUEST_IMAGE_CAPTURE = 1

         fun dispatchTakePictureIntent() {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(packageManager)?.also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
        photo.setOnClickListener(){
            dispatchTakePictureIntent()
        }
        play.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            //intent.putExtra("key", value)
            startActivity(intent)
        }

        quit.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            //intent.putExtra("key", value)
            startActivity(intent)
        }
    }
}
