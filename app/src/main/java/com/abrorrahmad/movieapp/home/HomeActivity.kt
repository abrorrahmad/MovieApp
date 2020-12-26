package com.abrorrahmad.movieapp.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.abrorrahmad.movieapp.R
import com.abrorrahmad.movieapp.home.dasboard.DashboardFragment
import com.abrorrahmad.movieapp.home.setting.SettingFragment
import com.abrorrahmad.movieapp.home.tiket.TiketFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val fragmentHome = DashboardFragment()
        val fragmentSetting = SettingFragment()
        val fragmentTiket = TiketFragment()

        setFragment(fragmentHome)

        iv_menu1.setOnClickListener{
            setFragment(fragmentHome)

           changeIcon(iv_menu1, R.drawable.ic_home)
           changeIcon(iv_menu2, R.drawable.icon_tiket)
           changeIcon(iv_menu3, R.drawable.icon_profile)

        }
        iv_menu2.setOnClickListener{
            setFragment(fragmentTiket)

           changeIcon(iv_menu1, R.drawable.ic_home2)
           changeIcon(iv_menu2, R.drawable.ic_tiket_aktif)
           changeIcon(iv_menu3, R.drawable.icon_profile)

        }
        iv_menu3.setOnClickListener{
            setFragment(fragmentSetting)

           changeIcon(iv_menu1, R.drawable.ic_home2)
           changeIcon(iv_menu2, R.drawable.icon_tiket)
           changeIcon(iv_menu3, R.drawable.ic_profile_ac)

        }
    }

    private fun setFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransacion = fragmentManager.beginTransaction()
        fragmentTransacion.replace(R.id.layout_frame, fragment)
        fragmentTransacion.commit()
    }

    private fun changeIcon(imageView: ImageView,int: Int){
        imageView.setImageResource(int)

    }
}