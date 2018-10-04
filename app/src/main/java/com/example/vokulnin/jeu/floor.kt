package com.example.vokulnin.jeu

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
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
    var speedy : Float=0f
    var main=m
    fun Colision(balle : Ball): Boolean{
        return !((balle.x > x+widh) || (balle.x+balle.widh < x) || (balle.y > y+height) || (balle.y + balle.height < y) )

    }

    fun draw (canvas : Canvas){
        //val resized = Bitmap.createScaledBitmap(sprite, widh.toInt(), height.toInt(), true)
        var shapeDrawable: ShapeDrawable
        canvas.drawRect(Rect((x * main.screenX.toFloat()).toInt(),(y* main.screenY.toFloat()).toInt(),
                ((x+widh)*main.screenX.toFloat()).toInt(), ((y-height)*main.screenY.toFloat()).toInt()), paint)
        //canvas.drawBitmap(resized,x,y,paint)
       // print(Rect(x.toInt(),y.toInt() ,x.toInt() + widh, y.toInt()- height))
    }
}