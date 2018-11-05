package com.onlly.soft.screencapture

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import com.onlly.soft.screencapturelib.CaptureUtil
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Test
        findViewById<Button>(R.id.button).setOnClickListener {
            CaptureUtil.captureAreaScreen(findViewById(R.id.button),
                File(filesDir,"button.jpeg"))
            CaptureUtil.captureFullScreen(this,File(filesDir,"full.jpeg"))
        }
    }
}
