package com.exemple.vokulnin.jeu

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.widget.TextView
import com.example.vokulnin.jeu.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.game_over.*

class MainActivity : AppCompatActivity() , SensorEventListener {


    private  lateinit var sensorManager: SensorManager
    private lateinit var accelerometre: Sensor
    lateinit var generator: Generator
    lateinit var Score : TextView
    lateinit var mp : MediaPlayer
    var running = true
    var score =0
    var floors = mutableListOf<floor>()

    private lateinit var balle: Ball

    fun GameOver(){
        running = false
        mp.stop()
        val intent = Intent(this, GameOverActivity::class.java)
        intent.putExtra("score", score.toString() )
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometre =  sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        var dessin = findViewById<DessinView>(R.id.canvas)
        dessin.setWillNotDraw(false)




        var white = Paint()
        white.color = Color.RED
        balle= Ball(0.5f,0f,0.05f,0.05f,white, this)
        var backgroundSprite : Bitmap =BitmapFactory.decodeResource(resources,R.drawable.dirt) ;
        generator = Generator(10, 0.1f,this,dessin)


        mp = MediaPlayer.create (this, R.raw.musique)
        mp.start()

        Score = findViewById(R.id.Score)



        val bitmap: Bitmap = Bitmap.createBitmap(700, 1000, Bitmap.Config.ARGB_8888)
        val canvas: Canvas = Canvas(bitmap)
        var shapeDrawable: ShapeDrawable
        var handler = Handler()

        shapeDrawable = ShapeDrawable(RectShape())

        //val width = 1000



        var screenX = canvas.width
        var screenY = canvas.height

        fun machin(){
           dessin.invalidate()
        }


        dessin.balle = balle
        dessin.main = this

        val monitor = object : Runnable {
            override fun run() {
                if (running == true) {

                    machin()
                    screenX = canvas.width
                    screenY = canvas.height
                    handler.postDelayed(this, 20)

                }
            }
        }
        val scoreThread = object : Runnable {
            override fun run() {
                if (running == true) {
                    score +=1
                Score.setText("score : " + score.toString())
                handler.postDelayed(this, 1000)
            }}
        }

        handler.postDelayed(scoreThread, 1)
        handler.postDelayed(monitor, 1)

    }
    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, this.accelerometre, SensorManager.SENSOR_DELAY_NORMAL)
        // repeat that line for each sensor you want to monitor
    }
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        var valuesGyroscopex = event!!.values[0]
        var valuesGyroscopey = event!!.values[1]
        var valuesGyroscopez = event!!.values[2]
        balle.speedy = valuesGyroscopey
        balle.speedx = -valuesGyroscopex
    }

}