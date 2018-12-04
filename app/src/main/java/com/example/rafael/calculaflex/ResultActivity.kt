package com.example.rafael.calculaflex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.rafael.calculaflex.extensions.format
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        calculate()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun calculate(){
        val gasPrice = intent.extras.getDouble( "GAS_PRICE",  0.0 )
        val ethanolPrice = intent.extras.getDouble( "ETHANOL_PRICE",  0.0 )
        val gasAverage = intent.extras.getDouble( "GAS_AVERAGE",  0.0 )
        val ethanolAverage= intent.extras.getDouble( "GAS_AVERAGE",  0.0 )


        val performanceMycar = ethanolAverage / gasAverage
        val priceIndice = ethanolPrice / gasPrice

        if (priceIndice  <= performanceMycar)
            tvSuggestion.text = getString(R.string.label_ethanol)
        else
            tvSuggestion.text = getString(R.string.label_gasoline)

        tvGasAverageResult.text = (gasPrice / gasAverage).format(2)
        tvEthanolAverageResult.text = (ethanolPrice / ethanolAverage).format(2)

        tvFuelRatio.text = getString(R.string.label_ration_fuel , performanceMycar.format(2))

    }

}


