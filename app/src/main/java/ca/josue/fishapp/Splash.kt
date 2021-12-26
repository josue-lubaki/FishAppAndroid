package ca.josue.fishapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.progressindicator.LinearProgressIndicator

class Splash : AppCompatActivity() {
    private lateinit var myProgress: LinearProgressIndicator
    var pStatus = 0
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // Desactiver le ToolBar
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        myProgress = findViewById(R.id.myProgressBar)
        Thread {
            while (pStatus < 100) {
                pStatus += 1
                handler.post { myProgress.progress = pStatus }
                try {
                    // Just to display the progress slowly
                    Thread.sleep(25)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            if (myProgress.progress == 100) {
                val intent = Intent(this@Splash, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.start()
    }
}