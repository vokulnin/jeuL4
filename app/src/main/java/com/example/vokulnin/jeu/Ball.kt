package com.example.vokulnin.jeu

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint

/**
 * Created by CollFnac on 24/09/2018.
 */

class Ball(image : Bitmap, X : Float , Y : Float , w : Int , h : Int, Paint : Paint){
    val sprite : Bitmap =  image
    public var x : Float = X
    public var y : Float = Y
    val widh : Int = w
    val height : Int = h
    val paint : Paint = Paint

    fun draw (canvas : Canvas){
        val resized = Bitmap.createScaledBitmap(sprite, widh, height, true)

        canvas.drawBitmap(resized,x,y,paint)
    }



}