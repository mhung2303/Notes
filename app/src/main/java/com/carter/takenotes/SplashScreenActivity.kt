package com.carter.takenotes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val backgroundImg : ImageView = findViewById(R.id.iv_logo)
        val sideAnimation = AnimationUtils.loadAnimation(this,R.anim.slide)

        backgroundImg.startAnimation(sideAnimation)

        Handler().postDelayed({
            val intent  = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },2400) // Delaying 2 second to open main activity

    }
}