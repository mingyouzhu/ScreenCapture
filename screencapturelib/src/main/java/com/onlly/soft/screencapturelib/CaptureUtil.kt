package com.onlly.soft.screencapturelib

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
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
            val view = activity.window.decorView
            view.isDrawingCacheEnabled = true
            view.buildDrawingCache()
            var b1 = view.drawingCache
            var frame = Rect()
            activity.window.decorView.getWindowVisibleDisplayFrame(frame)
            var statusBarHeight = frame.top
            val width = activity.windowManager.defaultDisplay.width
            val height = activity.windowManager.defaultDisplay.height
            val b = Bitmap.createBitmap(b1,0,statusBarHeight,width,height - statusBarHeight)
            view.destroyDrawingCache()
            val fos = FileOutputStream(outPath)
            b.compress(Bitmap.CompressFormat.JPEG,100,fos)
            fos.flush()
            fos.close()
        }catch (e:Exception){
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