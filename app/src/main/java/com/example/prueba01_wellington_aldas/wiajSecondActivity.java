package com.example.prueba01_wellington_aldas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class wiajSecondActivity extends AppCompatActivity {

    private EditText wiajEtNombre;
    private EditText wiajEtApellidos;
    private EditText wiajEtPrimerNumero;
    private EditText wiajEtSegundoNumero;
    private Button wiajBtnSiguiente;
    private Button wiajBtnCerrar;

    private Intent wiajResultData;
    private static final int WIAJ_REQUEST_CODE_THIRD = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wiaj_activity_second);

        wiajInitViews();

        wiajBtnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wiajStartThirdActivity();
            }
        });

        wiajBtnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wiajResultData != null) {
                    String nombre = wiajEtNombre.getText().toString();
                    String apellidos = wiajEtApellidos.getText().toString();
                    
                    wiajResultData.putExtra("wiaj_nombre", nombre);
                    wiajResultData.putExtra("wiaj_apellidos", apellidos);
                    
                    setResult(RESULT_OK, wiajResultData);
                    finish();
                }
            }
        });
    }

    private void wiajInitViews() {
        wiajEtNombre = findViewById(R.id.wiaj_et_nombre_second);
        wiajEtApellidos = findViewById(R.id.wiaj_et_apellidos_second);
        wiajEtPrimerNumero = findViewById(R.id.wiaj_et_primer_numero_second);
        wiajEtSegundoNumero = findViewById(R.id.wiaj_et_segundo_numero_second);
        wiajBtnSiguiente = findViewById(R.id.wiaj_btn_siguiente_second);
        wiajBtnCerrar = findViewById(R.id.wiaj_btn_cerrar_second);
    }

    private void wiajStartThirdActivity() {
        Intent intent = new Intent(this, wiajThirdActivity.class);
        startActivityForResult(intent, WIAJ_REQUEST_CODE_THIRD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == WIAJ_REQUEST_CODE_THIRD && resultCode == RESULT_OK && data != null) {
            wiajResultData = data;
            
            int primerNum = data.getIntExtra("wiaj_dividendo", 0);
            int segundoNum = data.getIntExtra("wiaj_divisor", 0);
            
            wiajEtPrimerNumero.setText(String.valueOf(primerNum));
            wiajEtSegundoNumero.setText(String.valueOf(segundoNum));
            
            wiajBtnCerrar.setEnabled(true);
            wiajBtnSiguiente.setEnabled(false);
        }
    }
}