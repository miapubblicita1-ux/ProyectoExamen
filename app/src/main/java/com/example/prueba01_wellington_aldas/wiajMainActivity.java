package com.example.prueba01_wellington_aldas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class wiajMainActivity extends AppCompatActivity {

    private EditText wiajEtNombre;
    private EditText wiajEtApellidos;
    private EditText wiajEtDividendo;
    private EditText wiajEtDivisor;
    private EditText wiajEtParteEntera;
    private EditText wiajEtResiduo;
    private EditText wiajEtNumInvertido;
    private Button wiajBtnSiguiente;
    private Button wiajBtnMostrarResultados;

    private Intent wiajResultData;
    private static final int WIAJ_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wiaj_activity_main);

        wiajInitViews();

        wiajBtnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wiajClearFields();
                wiajStartSecondActivity();
            }
        });

        wiajBtnMostrarResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wiajResultData != null) {
                    wiajDisplayResults(wiajResultData);
                    wiajBtnMostrarResultados.setEnabled(false);
                }
            }
        });
    }

    private void wiajInitViews() {
        wiajEtNombre = findViewById(R.id.wiaj_et_nombre_main);
        wiajEtApellidos = findViewById(R.id.wiaj_et_apellidos_main);
        wiajEtDividendo = findViewById(R.id.wiaj_et_dividendo_main);
        wiajEtDivisor = findViewById(R.id.wiaj_et_divisor_main);
        wiajEtParteEntera = findViewById(R.id.wiaj_et_parte_entera_main);
        wiajEtResiduo = findViewById(R.id.wiaj_et_residuo_main);
        wiajEtNumInvertido = findViewById(R.id.wiaj_et_num_invertido_main);
        wiajBtnSiguiente = findViewById(R.id.wiaj_btn_siguiente_main);
        wiajBtnMostrarResultados = findViewById(R.id.wiaj_btn_mostrar_resultados_main);
    }

    private void wiajClearFields() {
        wiajEtNombre.setText("");
        wiajEtApellidos.setText("");
        wiajEtDividendo.setText("");
        wiajEtDivisor.setText("");
        wiajEtParteEntera.setText("");
        wiajEtResiduo.setText("");
        wiajEtNumInvertido.setText("");
        wiajBtnMostrarResultados.setEnabled(false);
    }

    private void wiajStartSecondActivity() {
        Intent intent = new Intent(this, wiajSecondActivity.class);
        startActivityForResult(intent, WIAJ_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == WIAJ_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            wiajResultData = data;
            wiajBtnMostrarResultados.setEnabled(true);
        }
    }

    private void wiajDisplayResults(Intent data) {
        String nombre = data.getStringExtra("wiaj_nombre");
        String apellidos = data.getStringExtra("wiaj_apellidos");
        int dividendo = data.getIntExtra("wiaj_dividendo", 0);
        int divisor = data.getIntExtra("wiaj_divisor", 1);
        int numero = data.getIntExtra("wiaj_numero", 0);

        int parteEntera = 0;
        int residuo = 0;
        if (divisor != 0) {
            parteEntera = dividendo / divisor;
            residuo = dividendo % divisor;
        }

        int numInvertido = wiajReverseNumber(numero);

        wiajEtNombre.setText(nombre);
        wiajEtApellidos.setText(apellidos);
        wiajEtDividendo.setText(String.valueOf(dividendo));
        wiajEtDivisor.setText(String.valueOf(divisor));
        wiajEtParteEntera.setText(String.valueOf(parteEntera));
        wiajEtResiduo.setText(String.valueOf(residuo));
        wiajEtNumInvertido.setText(String.valueOf(numInvertido));
    }

    private int wiajReverseNumber(int num) {
        int reversed = 0;
        while (num != 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }
        return reversed;
    }
}