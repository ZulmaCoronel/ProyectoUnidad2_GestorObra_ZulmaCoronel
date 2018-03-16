package mx.edu.ittepic.proyectounidad2_zulmacoronel;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AgregarCliente extends AppCompatActivity {
    EditText nombre,domicilio,cel,mail;
    Button agregar,c;

    SQLiteDatabase db;
    ConexionSQLiteHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);


        nombre = findViewById(R.id.adesc);
        domicilio = findViewById(R.id.amontoo);
        cel = findViewById(R.id.ecelc);
        mail = findViewById(R.id.adecripo);
        agregar = findViewById(R.id.agregarc);
        c = findViewById(R.id.agregarcyo);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                con = new ConexionSQLiteHelper(ActivityAgregarCliente.this);
                con.openDB();
                String nom = nombre.getText().toString();
                String dom = domicilio.getText().toString();
                String cels = cel.getText().toString();
                String mails = mail.getText().toString();
                con.insertCliente(nom,dom,cels,mails);

                Intent agregar= new Intent(ActivityAgregarCliente.this,MainActivity.class);
                startActivity(agregar);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                con = new ConexionSQLiteHelper(ActivityAgregarCliente.this);
                con.openDB();
                String nom = nombre.getText().toString();
                String dom = domicilio.getText().toString();
                String cels = cel.getText().toString();
                String mails = mail.getText().toString();
                con.insertCliente(nom,dom,cels,mails);
                int idmaximo=0;
                try {
                    Cursor cursor = con.getAllClientesMax();
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        idmaximo=cursor.getInt(0);
                    }
                } catch (SQLException e) {

                }
                Intent agregar= new Intent(ActivityAgregarCliente.this,AgregarObra.class);
                agregar.putExtra("idcliente",idmaximo);
                startActivity(agregar);
            }
        });
    }
}

