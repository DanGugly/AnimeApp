package com.example.aniapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aniapp.databinding.ActivityHomeBinding
import com.example.aniapp.view.MenuFragment

class HomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frag_container, MenuFragment.newInstance())
            .commit()
    }
}