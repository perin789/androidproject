package edu.calstatela.csns.multiapp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_random_number_generator.*
import kotlin.random.Random

class RandomNumberGeneratorFragment : Fragment() {

    private var i = 1
    private var generatedNumbers = mutableListOf(-1)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_random_number_generator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkbox1.setOnClickListener {

            text1.setText("")
            text2.setText("")

            if (checkbox1.isChecked) {

                generatedNumbers = mutableListOf(-1)

                i = 1

                button1.isClickable = true
                button1.isEnabled = true

            }

        }

        button1.setOnClickListener {
            random()
        }

        button2.setOnClickListener {
            findNavController()
                .navigate(R.id.action_randomNumberGeneratorFragment_to_rollDiceFragment)
        }
    }

    private fun random() {
        val min = text1.text.toString()
        val max = text2.text.toString()

        if (i == 1) {

            generatedNumbers = (min.toInt()..max.toInt()).toList().toMutableList()

            generatedNumbers.shuffle()
            generatedNumbers.shuffle()
            generatedNumbers.shuffle()
            generatedNumbers.shuffle()
            generatedNumbers.shuffle()

            i += 1

        }


        // Toast.makeText(this, "" + replacement.isChecked(), Toast.LENGTH_SHORT).show()

        if (!checkbox1.isChecked) {

            val number = Random.nextInt(min.toInt(), max.toInt() + 1)

            text3.text = number.toString()

        } else {

            if (generatedNumbers.size != 0) {

                text3.text = generatedNumbers[0].toString()
                generatedNumbers.removeAt(0)

            } else {

                Toast.makeText(requireContext(), "List Empty", Toast.LENGTH_SHORT).show()

            }

        }


    }

}
