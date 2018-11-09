package com.example.vokulnin.jeu

import android.graphics.BitmapFactory
import com.google.android.gms.common.util.IOUtils.toByteArray
import android.graphics.Bitmap
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.Serializable


class bitmapobject(private var currentImage: Bitmap?) : Serializable {
    var Image = currentImage
    @Throws(IOException::class)
    private fun writeObject(out: java.io.ObjectOutputStream) {

        val stream = ByteArrayOutputStream()
        Image!!.compress(Bitmap.CompressFormat.PNG, 100, stream)

        val byteArray = stream.toByteArray()

        out.writeInt(byteArray.size)
        out.write(byteArray)

    }

    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(`in`: java.io.ObjectInputStream) {


        val bufferLength = `in`.readInt()

        val byteArray = ByteArray(bufferLength)

        var pos = 0
        do {
            val read = `in`.read(byteArray, pos, bufferLength - pos)

            if (read != -1) {
                pos += read
            } else {
                break
            }

        } while (pos < bufferLength)

        Image = BitmapFactory.decodeByteArray(byteArray, 0, bufferLength)

    }
}
