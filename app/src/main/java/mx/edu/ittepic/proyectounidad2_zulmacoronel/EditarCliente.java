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
import android.widget.Toast;

public class EditarCliente extends AppCompatActivity {
    int id=0;
    EditText enombre,edomicilio,ecel,email;
    Button actualizar;
    SQLiteDatabase db;
    ConexionSQLiteHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cliente);
        id=getIntent().getIntExtra("idcliente",0);

        enombre = findViewById(R.id.adescro);
        edomicilio = findViewById(R.id.amontoo);
        ecel = findViewById(R.id.ecelc);
        email = findViewById(R.id.adecripo);
        actualizar = findViewById(R.id.actualizarc);

        con = new ConexionSQLiteHelper(getApplicationContext());
        con.openDB();
        try {
            Cursor cursor = con.getAllClientesID(""+id);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                enombre.setText(cursor.getString(1));
                edomicilio.setText(cursor.getString(2));
                ecel.setText(cursor.getString(3));
                email.setText(cursor.getString(4));
            }
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
        }


        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nom = enombre.getText().toString();
                String dom = edomicilio.getText().toString();
                String cels = ecel.getText().toString();
                String correo = email.getText().toString();
                con.updateCliente(""+id,nom,dom,cels,correo);

                Intent abrir= new Intent(ActivityEditarCliente.this,MainActivity.class);
                startActivity(abrir);
            }
        });



    }
}
