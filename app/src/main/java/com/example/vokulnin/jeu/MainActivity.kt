package com.exemple.vokulnin.jeu

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


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
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
        var dessin = findViewById<DessinView>(R.id.canvas)
        // draw rectangle shape to canvas
        shapeDrawable = ShapeDrawable(RectShape())
        //shapeDrawable.setBounds( left., top, right, bottom)
        //shapeDrawable.getPaint().setColor(Color.parseColor("#009944"))
       // shapeDrawable.draw(canvas)


        canvas.drawBitmap(image,left,top,shapeDrawable.paint)
        fun machin(){
            dessin.invalidate()

        }

        var balle: Ball = Ball(image,left,top,100,100,shapeDrawable.paint)
        dessin.truc = balle
        dessin.setWillNotDraw(false)

        val monitor = object : Runnable {
            override fun run() {
                handler.postDelayed(this, 1)
                machin()
                left = left + 10f
                top = top + 10f
                balle.x = left
                balle.y = top
            }
        }
        handler.postDelayed(monitor, 1000)

        //imageV.background = BitmapDrawable(getResources(), bitmap)
    }
}