package com.proshecto.vegeta1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Leer extends AppCompatActivity {
    private ListView lst1;
    private ArrayList<String> arreglo = new ArrayList<String>();
    private ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);


        try{
            SQLiteDatabase db = openOrCreateDatabase("BD_EJEMPLO", Context.MODE_PRIVATE,null);
            lst1 = findViewById(R.id.lst1);
            final Cursor c = db.rawQuery("select * from persona2",null);
            int id = c.getColumnIndex("id");
            int nombre = c.getColumnIndex("nombre");
            int correo = c.getColumnIndex("apellido");
            int edad = c.getColumnIndex("edad");
            int prof = c.getColumnIndex("profesional");
            arreglo.clear();

            arrayAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arreglo);

            lst1.setAdapter(arrayAdapter);

            final  ArrayList<Persona2> lista = new ArrayList<Persona2>();


            if(c.moveToFirst())
            {
                do{
                    Persona2 persona2 = new Persona2();
                    persona2.id = c.getString(id);
                    persona2.nombre = c.getString(nombre);
                    persona2.correo = c.getString(correo);
                    persona2.edad = c.getString(edad);
                    persona2.profesional = c.getString(prof);
                    lista.add(persona2);

                    arreglo.add(c.getString(id) + " \t " + c.getString(nombre) + " \t "  + c.getString(correo) + " \t "  + c.getString(edad)+ " \t "  + c.getString(prof) );

                } while(c.moveToNext());
                arrayAdapter.notifyDataSetChanged();
                lst1.invalidateViews();
            }

            lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, android.view.View view, int position, long l) {
                    Persona2 persona2 = lista.get(position);
                    Intent i = new Intent(getApplicationContext(), Editar.class);
                    i.putExtra("id",persona2.id);
                    i.putExtra("nombre",persona2.nombre);
                    i.putExtra("correo",persona2.correo);
                    i.putExtra("edad",persona2.edad);
                    i.putExtra("profesional",persona2.profesional);
                    startActivity(i);
                }
            });
        }
        catch (Exception e){
            Toast.makeText(this, "Ha ocurrido un error, intentalo nuevamente.", Toast.LENGTH_SHORT).show();
        }



    }

    public void btn_volver_menu(View view){
        Intent intentVolver = new Intent(this,SegundoActivity.class);
        startActivity(intentVolver);
    }
}
