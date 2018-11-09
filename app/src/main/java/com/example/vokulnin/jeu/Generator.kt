package com.example.vokulnin.jeu

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import com.exemple.vokulnin.jeu.MainActivity
import java.util.*

/**
 * Created by vokulnin on 12/10/2018.
 */
class Generator(turns:Int, Floorwidth:Float , Main:MainActivity, dessin: DessinView) {
    var turn:Int = turns
    var main = Main
    var floorwidth = Floorwidth
    var currentX = 0.5f
    var currentY = 0.0f
    var newX = 0.5f
    var newY = 0.0f
    val random = Random()
     var dessinView = dessin
    fun rand(from: Int, to: Int) : Int {
        return random.nextInt(to - from) + from
    }

    fun Generate(x:Float , y:Float){
        currentX = x
        currentY = y
        for (i in 1..turn) {

            main.floors.add(floor(currentX,currentY,floorwidth,1/turn.toFloat(), Paint(),main))
            currentY +=1/turn.toFloat()
            newX =currentX +  rand(-5,5).toFloat()/10
            if(newX >1){
                newX = 1f
                currentX = 0.9f
            }
            if(newX <0){
                newX = 0f
            }
            if(newX<currentX){
                var tempfloor = floor(newX,currentY ,Math.abs(currentX-newX)+ floorwidth,floorwidth, Paint(),main)
                main.floors.add(tempfloor)
                dessinView.Last_floor = tempfloor

            }
            else{
                var tempfloor = floor(currentX,currentY,Math.abs(currentX-newX)+floorwidth,floorwidth, Paint(),main)
                main.floors.add(tempfloor)
                dessinView.Last_floor = tempfloor
            }
            currentX = newX


        }


    }

    fun Generate(){
                for (i in 1..turn) {

                    main.floors.add(floor(currentX,currentY,floorwidth,1/turn.toFloat(), Paint(),main))
                    currentY +=1/turn.toFloat()
                    newX =currentX +  rand(-5,5).toFloat()/10
                    if(newX >1){
                        newX = 1f
                    }
                    if(newX <0){
                        newX = 0f
                    }
                    if(newX<currentX){
                        var tempfloor = floor(newX,currentY ,Math.abs(currentX-newX)+ floorwidth,floorwidth, Paint(),main)
                        main.floors.add(tempfloor)
                        dessinView.Last_floor = tempfloor
                    }
                    else{
                        var tempfloor = floor(currentX,currentY,Math.abs(currentX-newX)+floorwidth,floorwidth, Paint(),main)
                        main.floors.add(tempfloor)
                        dessinView.Last_floor = tempfloor
                    }
                    currentX = newX


                }


    }





}