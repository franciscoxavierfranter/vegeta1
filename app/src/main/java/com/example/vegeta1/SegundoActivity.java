package com.example.vegeta1;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SegundoActivity extends AppCompatActivity {

    private Bundle bundle;
    private TextView tvSaludo;
    String[] profesionales={"Nutricionista","Médico","Entrenador","Psicólogo"};
    int[] vprofesional = {R.drawable.vnutri, R.drawable.vmedico, R.drawable.ventrena, R.drawable.vpsico,};
    Spinner spinnerProfesional;
    Button btnMapa;
    Button btnLeer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);
        //tvSaludo = (TextView) findViewById(R.id.tvSaludo);
        //bundle = getIntent().getExtras();
        //String saludo = bundle.getString("nombre");
        //tvSaludo.append(" " + saludo + " :D");

        //https://www.youtube.com/watch?v=yBQny_Mt73M spinner
        spinnerProfesional=findViewById(R.id.id_spinner);
        ProfesionalesAdapter profesionalesAdapter = new ProfesionalesAdapter();
        spinnerProfesional.setAdapter(profesionalesAdapter);

    }

    public void btnLeer(View view){
        Intent intentLeer = new Intent(this,Leer.class);
        startActivity(intentLeer);
    }

    public void btnTemp(View view){
        Intent intentTemp = new Intent(this,Temperature.class);
        startActivity(intentTemp);
    }

    public void btnProfesional(View view){
        Toast.makeText(this,spinnerProfesional.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
        String ProfSeleccion = spinnerProfesional.getSelectedItem().toString();
        if(ProfSeleccion.equals("Nutricionista")){
            Intent intentNutri = new Intent(this,NutricionistaActivity.class);
            Button btnProfesional = findViewById(R.id.btnProfesional);
            btnProfesional.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intentNutri);
                }
            });
        }else if(ProfSeleccion.equals("Médico")){
            Intent intentMed = new Intent(this,MedicoActivity.class);
            Button btnProfesional = findViewById(R.id.btnProfesional);
            btnProfesional.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intentMed);
                }
            });
        }else if(ProfSeleccion.equals("Entrenador")){
            Intent intentEntrenador = new Intent(this,EntrenadorActivity.class);
            Button btnProfesional = findViewById(R.id.btnProfesional);
            btnProfesional.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intentEntrenador);
                }
            });
        }else if(ProfSeleccion.equals("Psicólogo")){
            Intent intentPsico = new Intent(this,PsicologoActivity.class);
            Button btnProfesional = findViewById(R.id.btnProfesional);
            btnProfesional.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intentPsico);
                }
            });
        }

    }

    public void btnMapa(View v){
        Intent intentMapa = new Intent(this,MapsActivity.class);
        Button btnMapa = findViewById(R.id.btnMapa);
        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentMapa);
            }
        });
    }

    class ProfesionalesAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return profesionales.length;
        }

        @Override
        public Object getItem(int position) {
            return profesionales[position];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(SegundoActivity.this);
            convertView = inflater.inflate(R.layout.itemspinner,null);
            ImageView iv1 = convertView.findViewById(R.id.imageProfesional);
            TextView tv1 = convertView.findViewById(R.id.tvProfesional);
            iv1.setImageResource(vprofesional[position]);
            tv1.setText(profesionales[position]);
            return convertView;

        }
    }

    
}