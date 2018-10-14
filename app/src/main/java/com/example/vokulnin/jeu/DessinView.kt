package com.example.vokulnin.jeu

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
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
    var onFloor = true
    var FirstFrame = true
    var SecondFrame = false

    val paint: Paint = Paint()

    constructor(context: Context , attrs:AttributeSet): super(context, attrs)


    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)
        println("frame")
        canvas.drawColor(Color.WHITE)
        if(FirstFrame){
            FirstFrame = false
         SecondFrame = true}

        else if(SecondFrame){
            SecondFrame = false
            for(i in main.floors){
                i.draw(canvas)
                i.sX = width.toFloat()
                i.sY = height.toFloat()
                balle.sX = width.toFloat()
                balle.sY = height.toFloat()
                if(i.Colision(balle)) true
            }
        }
        else{
            onFloor = false
            for(i in main.floors){
                i.draw(canvas)
                if(i.Colision(balle)) true
            }
            if(!onFloor)main.GameOver()


            balle.draw(canvas)
        }


    }


}