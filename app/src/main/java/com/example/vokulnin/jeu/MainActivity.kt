package com.exemple.vokulnin.jeu

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Handler
import com.example.vokulnin.jeu.Ball
import com.example.vokulnin.jeu.DessinView
import com.example.vokulnin.jeu.R
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*
import kotlin.concurrent.schedule
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class MainActivity : AppCompatActivity() , SensorEventListener {


    private  lateinit var sensorManager: SensorManager
    private lateinit var accelerometre: Sensor

    private lateinit var balle: Ball

    override fun onCreate(savedInstanceState: Bundle?) {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometre =  sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var image : Bitmap =BitmapFactory.decodeResource(resources,R.drawable.ball) ;
        val bitmap: Bitmap = Bitmap.createBitmap(700, 1000, Bitmap.Config.ARGB_8888)
        val canvas: Canvas = Canvas(bitmap)
        var shapeDrawable: ShapeDrawable
        var handler = Handler()
        var mHandler = Handler()
        // rectangle positions
        var left : Float =  100f
        var top : Float =  100f
        var right : Int =  600
        var bottom : Int = 400
        shapeDrawable = ShapeDrawable(RectShape())


        balle= Ball(image,left,top,100,100,shapeDrawable.paint)

        var dessin = findViewById<DessinView>(R.id.canvas)
        // draw rectangle shape to canvas
        //shapeDrawable.setBounds( left., top, right, bottom)
        //shapeDrawable.getPaint().setColor(Color.parseColor("#009944"))
       // shapeDrawable.draw(canvas)


        canvas.drawBitmap(image,left,top,shapeDrawable.paint)
        fun machin(){
            dessin.invalidate()

        }

        dessin.truc = balle
        dessin.setWillNotDraw(false)
        balle.SetSpeed(1f,1f)
        balle.x = left
        balle.y = top
        val monitor = object : Runnable {
            override fun run() {
                handler.postDelayed(this, 1)
                machin()

            }
        }
        handler.postDelayed(monitor, 1000)

        //imageV.background = BitmapDrawable(getResources(), bitmap)
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