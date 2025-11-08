package com.tonial.calculaimc
import android.icu.text.NumberFormat
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var peso: EditText
    private lateinit var altura: EditText
    private lateinit var total: TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()

        btnCalcular.setOnClickListener {
            btnCalcularOnClick()
        }

        btnLimpar.setOnClickListener {
            btnLimparOnClick()
        }
        btnLimpar.setOnLongClickListener {
            btnLimparOnLongClick()
            return@setOnLongClickListener true
        }
    }

    private fun init() {
        //var peso: EditText = findViewById(R.id.peso) declarar direto dentro
        peso = findViewById(R.id.etPeso)
        altura = findViewById(R.id.etAltura)

        total = findViewById(R.id.tvTotal)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpar = findViewById(R.id.btnLimpar)
    }

    private fun btnCalcularOnClick() {
        val pesoVal: Double? = peso.text.toString().toDoubleOrNull()
        val alturaVal: Double? = altura.text.toString().toDoubleOrNull()

        if (pesoVal == null) {
            peso.error = getString(R.string.peso_valid)
            return
        }
        if (alturaVal == null || alturaVal == 0.toDouble()) {
            altura.error = getString(R.string.altura_valid)
            return
        }

        val locale:String = Locale.getDefault().language //linguagem do dispositivo
        //val contryCode:String = Locale.getDefault().country //pais do dispositivo

        val resultado: Double =  calcularIMC(pesoVal, alturaVal, locale)
        //val resultado = (pesoVal / (alturaVal * alturaVal))

        val nf: NumberFormat = NumberFormat.getNumberInstance(Locale.getDefault())

        //val df = DecimalFormat(getString(R.string.total))
        //total.text = df.format(resultado).toString()

        total.text = nf.format(resultado).toString()

    }

    private fun calcularIMC(pesoVal: Double, alturaVal: Double, locale: String): Double {
        if(locale.equals("pt"))
        {
            return pesoVal / (alturaVal * alturaVal)
        }
        else
        {
            return 703 * (pesoVal / (alturaVal * alturaVal))
        }
    }

    private fun btnLimparOnClick() {
        peso.text.clear()
        altura.text.clear()
        total.text = getString(R.string.total)
        peso.error = null
        altura.error = null
        peso.requestFocus()
    }

    private fun btnLimparOnLongClick() {
        Toast.makeText(this, getString(R.string.limpar_longClick), Toast.LENGTH_SHORT).show()
        true
    }
}


