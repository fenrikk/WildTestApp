package com.myapp.wildtestapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.myapp.wildtestapp.databinding.ActivityMainBinding
import com.myapp.wildtestapp.other.EXTRA_NAME
import com.myapp.wildtestapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val model: MainViewModel by viewModel()

        model.getData().observe(this) {
            if (it.pass) {
                startActivity(Intent(this, GameActivity::class.java))
            } else {
                val intent = Intent(this, WebActivity::class.java)
                intent.putExtra(EXTRA_NAME, it.link)
                startActivity(intent)
            }
        }
    }
}