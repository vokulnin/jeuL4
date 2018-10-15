package com.example.vokulnin.jeu

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.exemple.vokulnin.jeu.MainActivity

class GameOverActivity : AppCompatActivity() {
    lateinit var restart : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_over)
        restart = findViewById(R.id.restartButton)

        restart.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            //intent.putExtra("key", value)
            startActivity(intent)
        }
    }
}
