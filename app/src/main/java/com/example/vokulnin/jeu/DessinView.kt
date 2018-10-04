package com.example.vokulnin.jeu

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.SurfaceView
import com.exemple.vokulnin.jeu.MainActivity

/**
 * Created by CollFnac on 26/09/2018.
 */
class DessinView : SurfaceView {
    lateinit var balle : Ball
    constructor(context: Context): super(context)
    lateinit var main : MainActivity



    constructor(context: Context , attrs:AttributeSet): super(context, attrs)

    override fun onDraw(canvas: Canvas){
        print("drawed")
        canvas.drawColor(Color.RED)
        for(i in main.floors){
            i.draw(canvas)

            if(i.Colision(balle)) println("collision!!!")
        }
        balle.draw(canvas)
    }
}