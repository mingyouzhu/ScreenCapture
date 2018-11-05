package com.onlly.soft.screencapturelib

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.support.v4.view.ViewPager
import android.view.View
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

// 截图工具类
object CaptureUtil {

    // 截全屏
    @Throws(IOException::class)
    fun captureFullScreen(activity:Activity,outPath:File){
        try{
            var view:View = activity.window.decorView
            view.isDrawingCacheEnabled = true
            view.buildDrawingCache()
            val bitmap = view.drawingCache
            val fos = FileOutputStream(outPath)
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos)
            fos.flush()
            fos.close()
        }catch(e:Exception){
            throw IOException(e.message,e.cause)
        }
    }

    // 区域截屏
    @Throws(IOException::class)
    fun captureAreaScreen(view:View,outPath:File){
        try{
            val bitmap = Bitmap.createBitmap(view.width,view.height,Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            view.draw(canvas)
            val fos = FileOutputStream(outPath)
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos)
            fos.flush()
            fos.close()
        }catch (e:Exception){
            throw IOException(e.message,e.cause)
        }
    }


}