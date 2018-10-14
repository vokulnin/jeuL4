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
import com.example.vokulnin.jeu.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() , SensorEventListener {


    private  lateinit var sensorManager: SensorManager
    private lateinit var accelerometre: Sensor
    var floors = mutableListOf<floor>()
     var screenX=10
     var screenY=10
    private lateinit var balle: Ball
    fun GameOver(){
        val intent = Intent(this, GameOverActivity::class.java)
        //intent.putExtra("key", value)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometre =  sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var ballSprite : Bitmap =BitmapFactory.decodeResource(resources,R.drawable.ball) ;
        var floorSprite : Bitmap =BitmapFactory.decodeResource(resources,R.drawable.wood1_1) ;
        var backgroundSprite : Bitmap =BitmapFactory.decodeResource(resources,R.drawable.dirt) ;
        var generator = Generator(5,this,floorSprite)
        val bitmap: Bitmap = Bitmap.createBitmap(700, 1000, Bitmap.Config.ARGB_8888)
        val canvas: Canvas = Canvas(bitmap)
        var shapeDrawable: ShapeDrawable
        var handler = Handler()
        var mHandler = Handler()
        var left : Float =  100f
        var top : Float =  100f
        var right : Int =  600
        var bottom : Int = 400
        shapeDrawable = ShapeDrawable(RectShape())
        val footingpaint = Paint ()

        val width = 1000
        var dessin = findViewById<DessinView>(R.id.canvas)

        balle= Ball(ballSprite,0.5f,0f,0.2f,0.2f,shapeDrawable.paint, this)
        generator.Generate()
        // draw rectangle shape to canvas
        //shapeDrawable.setBounds( left., top, right, bottom)
        //shapeDrawable.getPaint().setColor(Color.parseColor("#009944"))
       // shapeDrawable.draw(canvas)
        footingpaint.setARGB (255, 128, 128, 128)

        var screenX = canvas.width
        var screenY = canvas.height

        dessin.setWillNotDraw(false)
        fun machin(){
           dessin.invalidate()

        }
        canvas.drawRect(Rect(100,100,100,100),shapeDrawable.paint)
        canvas.drawRect (((width/2)-10).toFloat(), 0f, ((width/2)+ 10) .toFloat (), 40f, footingpaint)

        dessin.balle = balle
        dessin.main = this
        dessin.setWillNotDraw(false)
        balle.SetSpeed(1f,1f)
        //balle.x = left
        //balle.y = top
        val monitor = object : Runnable {
            override fun run() {
                machin()
                 screenX = canvas.width
                 screenY = canvas.height
                handler.postDelayed(this, 20)

            }
        }
        handler.postDelayed(monitor, 1)

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