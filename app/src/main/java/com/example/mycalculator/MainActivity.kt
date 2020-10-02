package com.example.mycalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var lastNumeroi: Boolean = false
    private var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun removeZeroAfterDot(result: String): String {
        var value = result
        if (result.contains(".0"))
            value = value.substring(0, result.length - 2)
        return value

    }

    fun onEquals(view: View) {

        if (lastNumeroi) {
            var tvValue = tvInput.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (!prefix.isEmpty()) {
                        one += prefix
                    }
                    tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                } else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (!prefix.isEmpty()) {
                        one += prefix
                    }
                    tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                } else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (!prefix.isEmpty()) {
                        one += prefix
                    }
                    tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                } else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (!prefix.isEmpty()) {
                        one += prefix
                    }
                    tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }

        }
    }

    fun onDigit(view: View) {
        //  Toast.makeText(this, "btn clicked", Toast.LENGTH_SHORT).show()
        tvInput.append((view as Button).text)
        lastNumeroi = true
        //if(  tvInput.text.contains("1") )
    }

    fun onClear(view: View) {
        tvInput.text = ""
        lastNumeroi = false
        lastDot = false
    }

    fun onDot(view: View) {
        if (lastNumeroi && !lastDot) {
            tvInput.append(("."))
            lastNumeroi = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        if (lastNumeroi && !isOperatorAdded(tvInput.text.toString())) {
            tvInput.append((view as Button).text)
            lastDot = false
            lastNumeroi = false
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("-") ||
                    value.contains("*") || value.contains("+")
        }
    }

}