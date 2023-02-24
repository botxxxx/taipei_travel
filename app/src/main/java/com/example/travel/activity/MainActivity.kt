package com.example.travel.activity

import android.os.Bundle
import com.example.travel.R
import com.example.travel.api.NetworkService
import com.example.travel.databinding.ActivityMainBinding
import com.example.travel.main.EntryFragment

class MainActivity : DBSBaseViewBindingActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NetworkService.init()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, EntryFragment.newInstance())
                .commitNow()
        }
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}