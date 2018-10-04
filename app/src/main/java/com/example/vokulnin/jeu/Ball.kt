package com.example.vokulnin.jeu

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import com.exemple.vokulnin.jeu.MainActivity

/**
 * Created by CollFnac on 24/09/2018.
 */

class Ball(image : Bitmap, X : Float , Y : Float , w : Float , h : Float, Paint : Paint , m : MainActivity){
    val main : MainActivity = m
    val sprite : Bitmap =  image
    var x : Float = X
    var y : Float = Y
    var widh : Float = w
    var height : Float = h
    val paint : Paint = Paint
    var speedx : Float=0f
    var speedy : Float=0f
     fun SetSpeed(x : Float , y : Float){
        speedx = x
        speedy = y
    }

    fun draw (canvas : Canvas){
        print(Rect((x * main.screenX.toFloat()).toInt(),(y* main.screenY.toFloat()).toInt(),
                ((x+widh)*main.screenX.toFloat()).toInt(), ((y-height)*main.screenY.toFloat()).toInt()))
        //val resized = Bitmap.createScaledBitmap(sprite, widh.toInt(), height.toInt(), true)
        x += speedx/100f
        y += speedy/100f
        canvas.drawRect(Rect((x * main.screenX.toFloat()).toInt(),(y* main.screenY.toFloat()).toInt(),
                ((x+widh)*main.screenX.toFloat()).toInt(), ((y-height)*main.screenY.toFloat()).toInt()), paint)
        //canvas.drawBitmap(resized,x,y,paint)

    }



}