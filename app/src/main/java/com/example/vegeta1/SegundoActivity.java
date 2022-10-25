package com.example.vegeta1;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SegundoActivity extends AppCompatActivity {

    private Bundle bundle;
    private TextView tvSaludo;
    String[] profesionales={"Nutricionista","Médico","Entrenador","Psicólogo"};
    int[] vprofesional = {R.drawable.vnutri, R.drawable.vmedico, R.drawable.ventrena, R.drawable.vpsico,};
    Spinner spinnerProfesional;
    Button btnMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);
        tvSaludo = (TextView) findViewById(R.id.tvSaludo);
        bundle = getIntent().getExtras();
        String saludo = bundle.getString("nombre");
        tvSaludo.append(" " + saludo + " :D");

        //https://www.youtube.com/watch?v=yBQny_Mt73M spinner
        spinnerProfesional=findViewById(R.id.id_spinner);
        ProfesionalesAdapter profesionalesAdapter = new ProfesionalesAdapter();
        spinnerProfesional.setAdapter(profesionalesAdapter);


    }


    public void btnProfesional(View view){
        Toast.makeText(this,spinnerProfesional.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
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