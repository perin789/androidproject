package com.example.hw3.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hw3.R
import kotlinx.android.synthetic.main.dice_roller.view.*

import java.util.*

class DiceRoller : Fragment() {

    private lateinit var f_View: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)

        f_View = inflater.inflate(R.layout.dice_roller, container, false)
        f_View.roll_button.setOnClickListener {
            rollDice()
        }
/*
        f_View.btnNavigateToTipCalculator.setOnClickListener {
            findNavController()
                .navigate(R.id.action_diceRollerFragment_to_tipCalculatorFragment)
        }

         */
        return f_View
    }

    private fun rollDice() {
       // Toast.makeText(this, "button clicked!!", Toast.LENGTH_SHORT).show()
        val randomInt = Random().nextInt(6) + 1
        val randomInt1 = Random().nextInt(6) + 1

        val drawableResource = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        val drawableResource1 = when (randomInt1) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        f_View.dice_image.setImageResource(drawableResource)
        f_View.dice_image1.setImageResource(drawableResource1)


    }
}
