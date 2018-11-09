package com.example.vokulnin.jeu

import android.graphics.Bitmap
import java.io.Serializable

/**
 * Created by vokulnin on 08/11/2018.
 */
class Saveslot(Name : String , Score : Int , currentImage : bitmapobject) : Serializable {
    var name = Name
    var score = Score
    var image = currentImage
}