package com.myapp.wildtestapp.view.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.myapp.wildtestapp.databinding.ActivityMonsterBattleBinding

class MonsterBattle : AppCompatActivity() {

    private lateinit var binding: ActivityMonsterBattleBinding
    private var hp = 500

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMonsterBattleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.enemy.setOnClickListener {
            if (hp > 0) {
                hp -= 10
                binding.healPoint.text = "ENEMY HP: $hp/500"
            } else {
                Toast.makeText(this, "You win, your reward legendary blaster", Toast.LENGTH_LONG)
                    .show()
                binding.enemy.visibility = View.INVISIBLE
                binding.reward.visibility = View.VISIBLE
            }
        }
    }
}