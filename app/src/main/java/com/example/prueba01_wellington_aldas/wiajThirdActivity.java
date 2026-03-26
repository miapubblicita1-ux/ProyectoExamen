package com.example.prueba01_wellington_aldas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class wiajThirdActivity extends AppCompatActivity {

    private EditText wiajEtPrimerNumero;
    private EditText wiajEtSegundoNumero;
    private Button wiajBtnCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wiaj_activity_third);

        wiajInitViews();

        wiajBtnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wiajReturnResults();
            }
        });
    }

    private void wiajInitViews() {
        wiajEtPrimerNumero = findViewById(R.id.wiaj_et_primer_numero_third);
        wiajEtSegundoNumero = findViewById(R.id.wiaj_et_segundo_numero_third);
        wiajBtnCerrar = findViewById(R.id.wiaj_btn_cerrar_third);
    }

    private void wiajReturnResults() {
        String num1Str = wiajEtPrimerNumero.getText().toString().trim();
        String num2Str = wiajEtSegundoNumero.getText().toString().trim();

        if (num1Str.isEmpty()) {
            wiajEtPrimerNumero.setError("El primer número no puede estar vacío");
            return;
        }

        int num1 = Integer.parseInt(num1Str);
        if (num1 == 0) {
            wiajEtPrimerNumero.setError("El primer número no puede ser 0");
            return;
        }

        if(num2Str.isEmpty()){
            wiajEtSegundoNumero.setError("El segundo número no puede estar vacío");
            return;
        }

        int num2 = Integer.parseInt(num2Str);
        if (num2 == 0) {
            wiajEtSegundoNumero.setError("El segundo número no puede ser 0");
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("wiaj_dividendo", num1);
        intent.putExtra("wiaj_divisor", num2);

        setResult(RESULT_OK, intent);
        finish();
    }
}