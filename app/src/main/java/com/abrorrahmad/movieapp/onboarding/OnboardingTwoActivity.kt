package com.abrorrahmad.movieapp.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abrorrahmad.movieapp.R
import kotlinx.android.synthetic.main.activity_onboarding_two.*

class OnboardingTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_two)


        btn_hometwo.setOnClickListener {
            startActivity(Intent(this@OnboardingTwoActivity, OnboardingTreeActivity::class.java))
        }
    }
}
