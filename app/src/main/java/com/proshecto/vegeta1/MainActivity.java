package com.proshecto.vegeta1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView emailView;
    private Button btnIngresa;


    private Object view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailView = (EditText) findViewById(R.id.textUsuario);
        btnIngresa = (Button) findViewById(R.id.btnIngresa);
        btnIngresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!emailView.getText().toString().isEmpty()){
                    Intent intent = new Intent(MainActivity.this, SegundoActivity.class);
                    intent.putExtra("nombre", emailView.getText().toString());
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "¡Identificate!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /*public void btnIngresa(View v) {
        if (!emailView.getText().toString().isEmpty()) {
            String mensaje = "Escriba un mail";
            Toast toast = Toast.makeText(this, "Escribe algo!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START, 90, 0);
        } else {
            String mensaje = "Bien escrito";
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }

    }*/
}