package com.example.vokulnin.jeu

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.exemple.vokulnin.jeu.MainActivity
import android.graphics.BitmapFactory
import android.R.attr.bitmap
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.io.ObjectInputStream


class MainMenu : AppCompatActivity() {
    lateinit var play : Button
    lateinit var quit : Button
    lateinit var photo : ImageView
    lateinit var name : TextView
    lateinit var load : Saveslot

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        play = findViewById(R.id.play)
        quit = findViewById(R.id.quit)
        photo = findViewById(R.id.photo)
        name = findViewById(R.id.name)

        var file = File(applicationContext.filesDir , "save")
        ObjectInputStream(FileInputStream(file)).use { it ->
            //Read the family back from the file
             load = it.readObject() as Saveslot
        }
        photo.setImageBitmap(load.image.Image)
        name.text = load.name.toString() +" : " + load.score

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
