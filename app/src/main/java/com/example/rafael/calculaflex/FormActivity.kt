package com.example.rafael.calculaflex

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rafael.calculaflex.extensions.format
import com.example.rafael.calculaflex.extensions.getDouble
import com.example.rafael.calculaflex.watchers.DecimalTextWatcher
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.activity_result.*

class FormActivity : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        etGasPrice.addTextChangedListener(DecimalTextWatcher(etGasPrice))
        etEthanolPrice.addTextChangedListener(DecimalTextWatcher(etEthanolPrice))
        etGasAverage.addTextChangedListener(DecimalTextWatcher(etGasAverage,1))
        etEthanolAverage.addTextChangedListener(DecimalTextWatcher(etEthanolAverage,1))

        preferences = getSharedPreferences("user_preferences"   , Context.MODE_PRIVATE )
        val fGasPrice       = preferences.getFloat("gas_price"        , 0.00f)
        val fEthanolPrice   = preferences.getFloat("ethanol_price"    , 0.00f)
        val fGasAverage     = preferences.getFloat("gas_average"      , 0.00f)
        val fEthanolAverage = preferences.getFloat("ethanol_average"  , 0.00f)


        val gasPrice = intent.extras.getDouble( "fGasPrice",  0.0 )
        etGasPrice.setText(fGasPrice.toString())
        etEthanolPrice.setText(fEthanolPrice.toString())
        etGasAverage.setText(fGasAverage.toString())
        etEthanolAverage.setText(fEthanolAverage.toString())


        btCalculate.setOnClickListener{

            val editor = preferences.edit()
            editor.putFloat("gas_price",  etGasPrice.getDouble().toFloat())
            editor.putFloat("ethanol_price", etEthanolPrice.getDouble().toFloat())
            editor.putFloat("gas_average", etGasAverage.getDouble().toFloat())
            editor.putFloat("ethanol_average", etEthanolAverage.getDouble().toFloat())
            editor.apply()

            val proximaTela = Intent(this, ResultActivity::class.java)
            proximaTela.putExtra("GAS_PRICE", etGasPrice.getDouble())
            proximaTela.putExtra("ETHANOL_PRICE", etEthanolPrice.getDouble())
            proximaTela.putExtra("GAS_AVERAGE", etGasAverage.getDouble())
            proximaTela.putExtra("ETHANOL_AVERAGE", etEthanolAverage.getDouble())
            startActivity(proximaTela)
        }
    }
}
