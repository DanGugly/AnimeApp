package com.example.aniapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aniapp.databinding.ActivityMainBinding
import com.example.aniapp.view.MenuFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frag_container, MenuFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}