package com.example.vokulnin.jeu

import android.graphics.*
import com.exemple.vokulnin.jeu.MainActivity

/**
 * Created by CollFnac on 24/09/2018.
 */

class Ball(X : Float , Y : Float , w : Float , h : Float, Paint : Paint , m : MainActivity){
    val main : MainActivity = m
    var x : Float = X
    var y : Float = Y
    var widh : Float = w
    var height : Float = h
    val paint : Paint = Paint
    var speedx : Float=0f
    var speedy : Float=0f
    var sX : Float=0f
    var sY : Float=0f
     var collider = RectF(x*sX,y*sY,x*sX + widh*sX,y*sY+height*sY)

    fun SetSpeed(x : Float , y : Float){
        speedx = x
        speedy = y
    }

    fun draw (canvas : Canvas){
        canvas.drawRect(Rect(100,100,100,100),paint)

        x += speedx/500f
        y += speedy/300f
        val test = RectF(x * sX ,y* sY,
                (x*sX)*(widh*sX), (y*sY)+(height*sY))
        collider = RectF(x*sX,y*sY,x*sX + widh*sX,y*sY+height*sY)
        canvas.drawRect(collider,paint)


    }



}