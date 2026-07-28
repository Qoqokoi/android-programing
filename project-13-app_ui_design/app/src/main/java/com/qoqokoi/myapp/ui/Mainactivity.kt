package com.qoqokoi.myapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.qoqokoi.myapp.R
import com.qoqokoi.myapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAdd.setOnClickListener {
            val inputName =
                binding.etDeviceName.text
                    .toString()
                    .ifEmpty { "-" }
            val message = "${getString(R.string.action_saved)} ($inputName)"

            Snackbar
                .make(binding.coordinatorLayout, message, Snackbar.LENGTH_LONG)
                .setAnchorView(binding.bottomNavigation) // <-- Mengunci posisi Snackbar di atas BottomNav
                .show()
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> true
                R.id.navigation_settings -> true
                else -> false
            }
        }
    }
}
