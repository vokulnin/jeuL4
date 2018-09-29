package com.example.vokulnin.jeu

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.SurfaceView

/**
 * Created by CollFnac on 26/09/2018.
 */
class DessinView : SurfaceView {
    lateinit var truc : Ball
    constructor(context: Context): super(context)




    constructor(context: Context , attrs:AttributeSet): super(context, attrs)

    override fun onDraw(canvas: Canvas){

        canvas.drawColor(Color.RED)
        truc.draw(canvas)
    }
}