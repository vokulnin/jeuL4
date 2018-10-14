package com.example.vokulnin.jeu

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import com.exemple.vokulnin.jeu.MainActivity
import java.util.*

/**
 * Created by vokulnin on 12/10/2018.
 */
class Generator(turns:Int , Main:MainActivity , sprite:Bitmap) {
    var turn:Int = turns
    var main = Main

    var currentX = 0.5f
    var currentY = 0.0f
    var newX = 0.5f
    var newY = 0.0f
    val random = Random()
    var floorSprite =sprite

    fun rand(from: Int, to: Int) : Int {
        return random.nextInt(to - from) + from
    }

    fun Generate(){
                for (i in 1..turn) {

                    main.floors.add(floor(floorSprite,currentX,currentY,0.1f,1/turn.toFloat(), Paint(),main))
                    currentY +=1/turn.toFloat()
                    newX += rand(-5,5).toFloat()/10
                    if(newX<currentX){
                        main.floors.add(floor(floorSprite,newX,currentY ,Math.abs(currentX-newX),0.1f, Paint(),main))

                    }
                    else{
                        main.floors.add(floor(floorSprite,currentX,currentY,Math.abs(currentX-newX) ,0.1f, Paint(),main))

                    }
                    currentX = newX


                }


    }





}