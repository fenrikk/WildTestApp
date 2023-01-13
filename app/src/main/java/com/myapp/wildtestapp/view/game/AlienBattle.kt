package com.myapp.wildtestapp.view.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.myapp.wildtestapp.databinding.ActivityAlienBattleBinding

class AlienBattle : AppCompatActivity() {
    private lateinit var binding: ActivityAlienBattleBinding
    private var hp = 300

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlienBattleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.enemy.setOnClickListener {
            if (hp > 0) {
                hp -= 5
                binding.healPoint.text = "ENEMY HP: $hp/300"
            } else {
                Toast.makeText(this, "You win, you rescued astronaut", Toast.LENGTH_LONG).show()
                binding.enemy.visibility = View.INVISIBLE
                binding.reward.visibility = View.VISIBLE
            }
        }
    }
}