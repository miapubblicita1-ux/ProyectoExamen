package com.example.prueba01_wellington_aldas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class wiajThirdActivity extends AppCompatActivity {

    private EditText wiajEtDividendo;
    private EditText wiajEtDivisor;
    private EditText wiajEtNumero;
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
        wiajEtDividendo = findViewById(R.id.wiaj_et_dividendo_third);
        wiajEtDivisor = findViewById(R.id.wiaj_et_divisor_third);
        wiajEtNumero = findViewById(R.id.wiaj_et_numero_third);
        wiajBtnCerrar = findViewById(R.id.wiaj_btn_cerrar_third);
    }

    private void wiajReturnResults() {
        String dividendoStr = wiajEtDividendo.getText().toString();
        String divisorStr = wiajEtDivisor.getText().toString();
        String numeroStr = wiajEtNumero.getText().toString();

        int dividendo = dividendoStr.isEmpty() ? 0 : Integer.parseInt(dividendoStr);
        int divisor = divisorStr.isEmpty() ? 1 : Integer.parseInt(divisorStr);
        int numero = numeroStr.isEmpty() ? 0 : Integer.parseInt(numeroStr);

        Intent intent = new Intent();
        intent.putExtra("wiaj_dividendo", dividendo);
        intent.putExtra("wiaj_divisor", divisor);
        intent.putExtra("wiaj_numero", numero);

        setResult(RESULT_OK, intent);
        finish();
    }
}