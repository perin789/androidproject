package edu.calstatela.csns.multiapp


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_tip_generator.*

class TipGeneratorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tip_generator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button1.setOnClickListener {
            calculate()
        }

        checkbox1.setOnClickListener {
            if (checkbox1.isChecked) {
                checkbox2.isChecked = false
                checkbox3.isChecked = false
            }
        }

        checkbox2.setOnClickListener {
            if (checkbox2.isChecked) {
                checkbox1.isChecked = false
                checkbox3.isChecked = false
            }
        }

        checkbox3.setOnClickListener {
            if (checkbox3.isChecked) {
                checkbox1.isChecked = false
                checkbox2.isChecked = false
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculate() {
        var tip: Double = 0.0
        val price = text1.text.toString().toDouble()

        if (checkbox1.isChecked) {
            tip = price * 0.15
        } else if (checkbox2.isChecked) {
            tip = price * 0.18
        } else if (checkbox3.isChecked) {
            tip = price * 0.20
        }

        val tipStr = String.format("%.2f", tip)
        val totalStr = String.format("%.2f", price + tip)

        text3.text = "Tip: $tipStr, Total: $totalStr"
    }

}
