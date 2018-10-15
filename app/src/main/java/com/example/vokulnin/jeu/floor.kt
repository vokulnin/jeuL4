package com.example.vokulnin.jeu

import android.graphics.*
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import com.exemple.vokulnin.jeu.MainActivity

/**
 * Created by CollFnac on 03/10/2018.
 */
class floor (image : Bitmap, X : Float, Y : Float, w : Float, h : Float, Paint : Paint, m : MainActivity){
    val sprite : Bitmap =  image
    public var x : Float = X
    public var y : Float = Y
    var widh = w
    var height  = h
    var paint : Paint = Paint
    var speedx : Float=0f
    var speedy : Float=-0.5f
    var main=m
    var sX : Float=100f
    var sY : Float=100f
    lateinit var collider : RectF
    fun Colision(balle : Ball): Boolean{

        return !((balle.x > x+widh) || (balle.x+balle.widh < x) || (balle.y > y+height) || (balle.y + balle.height < y) )
    }

    fun draw (canvas : Canvas){

        val resized = Bitmap.createScaledBitmap(sprite, (widh * sX).toInt(), (height*sY).toInt(), true)
        x += speedx/500f
        y += speedy/250f
        canvas.drawBitmap(resized,x*sX,y*sY,paint)
        collider = RectF(x*sX,y*sY,x*sX + widh*sX,y*sY+height*sY)
        canvas.drawRect(collider,paint)

    }
}