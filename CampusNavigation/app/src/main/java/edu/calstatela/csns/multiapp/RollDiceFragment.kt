package edu.calstatela.csns.multiapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_roll_dice.*
import kotlin.random.Random

class RollDiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_roll_dice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button1.setOnClickListener {
            roll()
        }

        button2.setOnClickListener {
            findNavController()
                .navigate(R.id.action_rollDiceFragment_to_tipGeneratorFragment)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun roll() {
        val dice1 = Random.nextInt(1, 7)
        val dice2 = Random.nextInt(1, 7)

        text3.text = "Dice 1: $dice1 and Dice 2: $dice2"

    }

}
