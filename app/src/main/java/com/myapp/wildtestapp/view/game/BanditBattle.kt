package com.myapp.wildtestapp.view.game

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.myapp.wildtestapp.databinding.ActivityBanditBattleBinding

class BanditBattle : AppCompatActivity() {

    private lateinit var binding: ActivityBanditBattleBinding
    var hp = 700

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBanditBattleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.enemy.setOnClickListener {
            if (hp > 0) {
                hp -= 10
                binding.healPoint.text = "ENEMY HP: $hp/700"
            } else {
                Toast.makeText(this, "You win, your reward: legendary spaceship", Toast.LENGTH_LONG)
                    .show()
                binding.enemy.visibility = View.INVISIBLE
                binding.reward.visibility = View.VISIBLE
            }
        }
    }
}