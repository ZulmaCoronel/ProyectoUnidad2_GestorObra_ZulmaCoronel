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

public class EditarEmpleado extends AppCompatActivity {
    int id=0;
    SQLiteDatabase db;
    ConexionSQLiteHelper con;
    EditText nombre,cel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_empleado);
        id=getIntent().getIntExtra("idcliente",0);
        nombre=findViewById(R.id.enamee);
        cel=findViewById(R.id.ecele);
        con = new ConexionSQLiteHelper(getApplicationContext());
        con.openDB();
        try {
            Cursor cursor = con.getAllEmpleadosid(""+id);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                nombre.setText(cursor.getString(1));
                cel.setText(cursor.getString(2));
            }
        } catch (SQLException e) {

        }


        Button actualizare;


        actualizare = findViewById(R.id.actualizare);
        actualizare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = nombre.getText().toString();
                String cels = cel.getText().toString();
                con.updateEmpleado(id,nom,cels);

                Intent abrir= new Intent(ActivityEditarEmpleado.this,MainActivity.class);
                startActivity(abrir);
            }
        });


    }
}

