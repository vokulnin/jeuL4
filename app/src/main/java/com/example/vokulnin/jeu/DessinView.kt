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
    var generate = false
    val paint: Paint = Paint()
    lateinit var Last_floor : floor
    constructor(context: Context , attrs:AttributeSet): super(context, attrs)


    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)
        canvas.drawColor(Color.WHITE)
        var toRemove = kotlin.collections.mutableListOf<floor>()
        if(main.floors.size<20){
            generate = true
        }
        if(FirstFrame){
            FirstFrame = false

         SecondFrame = true}

        else if(SecondFrame){
            SecondFrame = false
            main.generator.Generate()
            main.generator.Generate(main.floors.last().x,main.floors.last().y - main.floors.last().height)

            for(i in main.floors){
                i.draw(canvas)
                i.sX = width.toFloat()
                i.sY = height.toFloat()
                balle.sX = width.toFloat()
                balle.sY = height.toFloat()
            }
        }
        else{
            onFloor = false
            if(generate){
                main.generator.Generate(Last_floor.x,Last_floor.y - Last_floor.height)
                for(i in main.floors) {
                    i.sX = width.toFloat()
                    i.sY = height.toFloat()
                }
                generate = false
            }
            for(i in main.floors){
                i.draw(canvas)
                if( i.y+i.height <0){
                    toRemove.add(i)
                }
                if(i.Colision(balle)) onFloor = true
            }

            for(i in toRemove){
                main.floors.remove(i)

            }


            if(!onFloor){
                println("gameover")
                main.GameOver()
            }


            balle.draw(canvas)
        }


    }


}