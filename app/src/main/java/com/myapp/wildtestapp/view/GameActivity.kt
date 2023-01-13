package com.myapp.wildtestapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myapp.wildtestapp.R
import com.myapp.wildtestapp.databinding.ActivityGameBinding
import com.myapp.wildtestapp.view.game.AlienBattle
import com.myapp.wildtestapp.view.game.BanditBattle
import com.myapp.wildtestapp.view.game.MonsterBattle

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.left.setOnClickListener {
            startActivity(Intent(this, MonsterBattle::class.java))
        }
        binding.straight.setOnClickListener {
            startActivity(Intent(this, AlienBattle::class.java))
        }
        binding.right.setOnClickListener {
            startActivity(Intent(this, BanditBattle::class.java))
        }
    }
}