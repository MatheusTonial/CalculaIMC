package com.tonial.calculaimc

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var peso : EditText
    private lateinit var altura : EditText
    private lateinit var total : TextView
    private lateinit var btnCalcular : Button
    private lateinit var btnLimpar : Button

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
            btnLimparOnLongClick();
            return@setOnLongClickListener true;
        }


    }



    private fun init(){
        //var peso: EditText = findViewById(R.id.peso) declarar direto dentro
        peso = findViewById(R.id.peso);
        altura = findViewById(R.id.altura);

        total = findViewById(R.id.total);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpar = findViewById(R.id.btnLimpar);
    }

    private fun btnCalcularOnClick() {
        val pesoVal : Double? = peso.text.toString().toDoubleOrNull();
        val alturaVal : Double? = altura.text.toString().toDoubleOrNull();

        if(pesoVal == null){
            peso.error = "Peso deve ser informado";
            return;
        }
        if(alturaVal == null || alturaVal == 0.toDouble()){
            altura.error = "Altura deve ser informada um valor maior que zero";
            return;
        }


        val resultado = (pesoVal  / (alturaVal * alturaVal));
        val df = DecimalFormat("0.00");
        total.text = df.format(resultado).toString();

    }

    private fun btnLimparOnClick() {
        peso.text.clear();
        altura.text.clear();
        total.text = "0.0";
        peso.error = null;
        altura.error = null;
        peso.requestFocus();
    }

    private fun btnLimparOnLongClick() {
        Toast.makeText(this, "Botao limpar", Toast.LENGTH_SHORT).show()
        true;
    }
}


