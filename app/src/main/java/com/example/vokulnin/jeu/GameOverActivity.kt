package com.example.vokulnin.jeu

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.view.GestureDetectorCompat
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.exemple.vokulnin.jeu.MainActivity
import kotlinx.android.synthetic.main.game_over.*
import java.io.*

class GameOverActivity : AppCompatActivity(), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    override fun onShowPress(p0: MotionEvent?) {
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
return true
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        return true
    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return true
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return true
    }

    override fun onLongPress(p0: MotionEvent?) {
    }

    override fun onDoubleTap(p0: MotionEvent?): Boolean {
        score +=1
        score_text.text =score.toString()
        return true    }

    override fun onDoubleTapEvent(p0: MotionEvent?): Boolean {
        return true
    }

    override fun onSingleTapConfirmed(p0: MotionEvent?): Boolean {
        return true
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        this.gDetector?.onTouchEvent(event)
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event)
    }
    lateinit var restart: Button
    lateinit var GPS: Button
    lateinit var photo: Button
    lateinit var savescore: Button

    lateinit var image: ImageView
    lateinit var score_text: TextView
    lateinit var hightscore: TextView
    lateinit var picture: Bitmap
    lateinit var entername: EditText
    var score = 0


    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  data: Intent?) {
        if (resultCode == Activity.RESULT_OK
                ) {
            picture = data?.extras?.get("data") as Bitmap
            image.setImageBitmap(picture)

        }
    }

    fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, 1)
            }
        }
    }
    var gDetector: GestureDetectorCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        fun save(){
            var image = bitmapobject(picture)
            var slot = Saveslot(entername.text.toString() , score , image )
            var file = File(applicationContext.filesDir , "save")

            ObjectOutputStream(FileOutputStream(file)).use{ it -> it.writeObject(slot)}

            }
        setContentView(R.layout.game_over)
        this.gDetector = GestureDetectorCompat(this, this)
        score = intent.getStringExtra("score").toInt()

        gDetector?.setOnDoubleTapListener(this)
        restart = findViewById(R.id.restartButton)
        GPS = findViewById(R.id.gps)
        photo = findViewById(R.id.photo)
        image = findViewById(R.id.image)
        score_text = findViewById(R.id.scoreNumber)
        hightscore = findViewById(R.id.hightscore)
        entername = findViewById(R.id.entername)
        savescore = findViewById(R.id.save_score)

        score_text.text =score.toString()
        restart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        savescore.setOnClickListener {
            save()
        }

        photo.setOnClickListener {
            dispatchTakePictureIntent()
        }

        GPS.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }





        }
    }

