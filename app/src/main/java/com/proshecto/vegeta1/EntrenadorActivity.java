package com.proshecto.vegeta1;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class EntrenadorActivity extends AppCompatActivity {
    private EditText txtNombre;
    private EditText txtCorreo;
    private EditText txtEdad;
    private Button btnAgendaCita;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrenador);
        txtNombre = (EditText) findViewById(R.id.txtnombre_agendaentrenador);
        txtCorreo = (EditText) findViewById(R.id.txtcorreo_agendaentrenador);
        txtEdad = (EditText) findViewById(R.id.txtedad_agendaentrenador);
        btnAgendaCita = (Button) findViewById(R.id.btnAgendaCita);
        btnAgendaCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNombre.getText().toString();
                String correo = txtCorreo.getText().toString();
                Integer edad = 0;
                String mensaje = "";
                boolean enviar = true;
                if(txtEdad.length()>0) edad = Integer.parseInt(txtEdad.getText().toString());
                if(edad==0){
                    enviar = false;
                    mensaje="Necesitas anotar tu edad!.\n";
                }
                if(nombre.length()==0){
                    enviar = false;
                    mensaje+="Necesitas anotar tu nombre!.";
                }
                if(correo.length()==0){
                    enviar = false;
                    mensaje+="Necesitas anotar tu correo!";
                }
                if(enviar){
                    String titulo = "Tu profesional del Entrenamiento te contactar??";
                    mensaje = "Tus datos son:\n";
                    mensaje+= "Nombre: " + nombre + ", Edad: " + edad.toString() + ", Correo: " + correo;
                    int notificaID = 1;
                    Notificar(titulo, mensaje, notificaID);
                    insertar();
                    Toast.makeText(getApplicationContext(), titulo, Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(EntrenadorActivity.this, SegundoActivity.class);
                    //startActivity(intent);
                }
                if(!enviar) Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
            }
        });
    }



    //metodo insertar SQLite
    public void insertar()
    {
        try
        {
            String nombre = txtNombre.getText().toString();
            String correo = txtCorreo.getText().toString();
            String edad = txtEdad.getText().toString();
            String profesional = "Entrenamiento";

            SQLiteDatabase db = openOrCreateDatabase("BD_EJEMPLO", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS persona2(id INTEGER PRIMARY KEY AUTOINCREMENT,nombre VARCHAR,apellido VARCHAR,edad VARCHAR,profesional VARCHAR)");

            String sql = "insert into persona2(nombre,apellido,edad,profesional)values(?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,nombre);
            statement.bindString(2,correo);
            statement.bindString(3,edad);
            statement.bindString(4,profesional);
            statement.execute();
            Toast.makeText(this,"Datos agregados satisfactoriamente en la base de datos.",Toast.LENGTH_LONG).show();

            txtNombre.setText("");
            txtCorreo.setText("");
            txtEdad.setText("");
            txtNombre.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Error no se pudieron guardar los datos.",Toast.LENGTH_LONG).show();
        }
    }


    // M??todo que crea y env??a la notificaci??n
    public void Notificar(String titulo, String mensaje, int notID){
        NotificationCompat.Builder creador;
        String canalID = "MiCanal01";
        Context contexto = getApplicationContext();
        NotificationManager notificador = (NotificationManager) getSystemService(contexto.NOTIFICATION_SERVICE);
        creador = new NotificationCompat.Builder(contexto, canalID);
        // Si nuestro dispositivo tiene Android 8 (API 26, Oreo) o superior
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String canalNombre = "Mensajes";
            String canalDescribe = "Canal de mensajes";
            int importancia = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel miCanal = new NotificationChannel(canalID, canalNombre, importancia);
            miCanal.setDescription(canalDescribe);
            miCanal.enableLights(true);
            miCanal.setLightColor(Color.BLUE); // Esto no lo soportan todos los dispositivos
            miCanal.enableVibration(true);
            notificador.createNotificationChannel(miCanal);
            creador = new NotificationCompat.Builder(contexto, canalID);
        }
        Bitmap iconoNotifica = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.ventrena);
        int iconoSmall = R.drawable.ventrena;
        creador.setSmallIcon(iconoSmall);
        creador.setLargeIcon(iconoNotifica);
        creador.setContentTitle(titulo);
        creador.setContentText(mensaje);
        creador.setStyle(new NotificationCompat.BigTextStyle().bigText(mensaje));
        creador.setChannelId(canalID);
        notificador.notify(notID, creador.build());
    }
}