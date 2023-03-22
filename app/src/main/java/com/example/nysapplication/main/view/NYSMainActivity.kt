package com.example.nysapplication.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nysapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NYSMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nys_main)
        val fm = supportFragmentManager
        var fragment: Fragment? = fm.findFragmentById(R.id.fragmentView)
        if (fragment == null) {
            fragment = LandingPageFragment()
            fm.beginTransaction().add(R.id.fragmentView, fragment).commit()

        }

    }

    fun changeFragment(fragment:Fragment){
        val fm = supportFragmentManager
        fm.beginTransaction()
            .replace(R.id.fragmentView, fragment)
            .addToBackStack("navigate")
            .commit()
    }

    companion object {

    }






}