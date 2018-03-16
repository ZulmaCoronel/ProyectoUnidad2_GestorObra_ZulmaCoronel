package mx.edu.ittepic.proyectounidad2_zulmacoronel;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AgregarEmpleado extends AppCompatActivity {
    SQLiteDatabase db;
    ConexionSQLiteHelper con;
    EditText nombree, cele;
    Button agregare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_empleado);

        nombree = findViewById(R.id.enamee);
        cele = findViewById(R.id.acele);

        agregare = findViewById(R.id.agregare);
        agregare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                con = new ConexionSQLiteHelper(ActivityAgregarEmpleado.this);
                con.openDB();

                String nome = nombree.getText().toString();
                String celse = cele.getText().toString();
                con.insertEmpleado(nome,celse);
                Intent agregare= new Intent(ActivityAgregarEmpleado.this,MainActivity.class);
                startActivity(agregare);
            }
        });

    }

}
