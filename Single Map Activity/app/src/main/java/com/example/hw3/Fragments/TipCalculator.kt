package com.example.hw3.Fragments


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.tip_calculator.view.*

import com.example.hw3.R

class TipCalculator : Fragment() {

    private lateinit var f_View: View

    private var i_r = 0.15

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        f_View = inflater.inflate(
            R.layout.tip_calculator, container,
            false
        )
        setHasOptionsMenu(true)
        f_View.rg_button.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.b15) { i_r = 0.15;
            } else if (checkedId == R.id.b18) { i_r = 0.18;
            } else if (checkedId == R.id.b20) { i_r = 0.20;
            }
        }
        f_View.calculate.setOnClickListener {
            if (!TextUtils.isEmpty(f_View.Bill_amnt.text.toString())) {
                calculateTipAndTotal()
                f_View.Bill_amnt.error = null
            } else {
                f_View.Bill_amnt.requestFocus()
                f_View.Bill_amnt.error = "Enter bill amount"
            }
        }
        return f_View
    }

    private fun calculateTipAndTotal() {
        val b_amnt = f_View.Bill_amnt.text.toString().toDouble()
        val t_amnt = String.format("%.2f", b_amnt * i_r).toDouble()
        val total = b_amnt + t_amnt;
        f_View.Tip_amnt.setText("Tip Amount:$ $t_amnt")
        f_View.Total_amnt.setText("Total Amount:$ $total")
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.send) {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:example@email.com") // client email address
            intent.putExtra(Intent.EXTRA_SUBJECT, "Total Expenses")// Subject of mail
            if (!TextUtils.isEmpty(f_View.Tip_amnt.text.toString())) {
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    f_View.Tip_amnt.text.toString()
                            + "\n" + f_View.Total_amnt.text.toString()
                )
            }
            if (intent.resolveActivity(activity?.packageManager) != null) {
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
