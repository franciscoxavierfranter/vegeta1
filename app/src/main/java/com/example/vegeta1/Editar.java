package com.example.vegeta1;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Editar extends AppCompatActivity {

    private EditText ed_nombre,ed_correo,ed_edad,ed_id, ed_prof;
    private Button b_editar,b_eliminar,b_volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ed_nombre = findViewById(R.id.et_nombre);
        ed_correo = findViewById(R.id.et_correo);
        ed_edad = findViewById(R.id.et_edad);
        ed_prof = findViewById(R.id.et_prof);
        ed_id = findViewById(R.id.id);

        b_editar = findViewById(R.id.btn_editar);
        b_eliminar = findViewById(R.id.btn_eliminar);
        b_volver = findViewById(R.id.btn_volver);

        Intent wey = getIntent();

        String et_id = wey.getStringExtra("id").toString();
        String et_nombre = wey.getStringExtra("nombre").toString();
        String et_correo = wey.getStringExtra("correo").toString();
        String et_prof = wey.getStringExtra("profesional").toString();
        String et_edad = wey.getStringExtra("edad").toString();


        ed_id.setText(et_id);
        ed_nombre.setText(et_nombre);
        ed_correo.setText(et_correo);
        ed_edad.setText(et_edad);
        ed_prof.setText(et_prof);

        b_editar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                editar();
            }
        });

        b_eliminar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                eliminar();
            }
        });

        b_volver.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent i = new Intent(getApplicationContext(), Leer.class);
                startActivity(i);
            }
        });
    }

    public void eliminar()
    {
        try
        {
            String id = ed_id.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("BD_EJEMPLO", Context.MODE_PRIVATE,null);


            String sql = "delete from persona2 where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Datos eliminados de la base de datos.",Toast.LENGTH_LONG).show();

            ed_nombre.setText("");
            ed_correo.setText("");
            ed_edad.setText("");
            ed_prof.setText("");
            ed_nombre.requestFocus();

        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Error no se pudieron guardar los datos.",Toast.LENGTH_LONG).show();
        }
    }
    public void editar()
    {
        try
        {
            String nombre = ed_nombre.getText().toString();
            String apellido = ed_correo.getText().toString();
            String edad = ed_edad.getText().toString();
            String id = ed_id.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("BD_EJEMPLO",Context.MODE_PRIVATE,null);

            String sql = "update persona2 set nombre = ?,apellido=?,edad=? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,nombre);
            statement.bindString(2,apellido);
            statement.bindString(3,edad);
            statement.bindString(4,id);
            statement.execute();
            Toast.makeText(this,"Datos actualizados satisfactoriamente en la base de datos.",Toast.LENGTH_LONG).show();

            ed_nombre.setText("");
            ed_correo.setText("");
            ed_edad.setText("");
            ed_nombre.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Error no se pudieron guardar los datos.",Toast.LENGTH_LONG).show();
        }
    }
}