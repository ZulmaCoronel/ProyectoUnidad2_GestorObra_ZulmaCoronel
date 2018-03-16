package mx.edu.ittepic.proyectounidad2_zulmacoronel;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AsignarEmpleadoActividad extends AppCompatActivity {
    Button boton;
    Spinner actividad;
    EditText pago;
    TextView nombreempleado;
    SQLiteDatabase db;
    ConexionSQLiteHelper con;

    int idobra=0,idempleado=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_empleado_actividad);
        boton=findViewById(R.id.btn_agregar_actividad);
        pago= findViewById(R.id.aepago);
        actividad=findViewById(R.id.aesp1);

        idobra=getIntent().getIntExtra("idobra",0);
        idempleado=getIntent().getIntExtra("idempleado",0);

        con = new ConexionSQLiteHelper(getApplicationContext());
        con.openDB();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                con.insertarObraEmpleado(actividad.getSelectedItem().toString(),pago.getText().toString(),idempleado,idobra);
                Toasty.success(getApplicationContext(), "Empleado asigando a la obra", Toast.LENGTH_SHORT, true).show();
                Intent abrir= new Intent(AsignarEmpleadoActividad.this,AsignarEmpleado.class);
                abrir.putExtra("idobra",idobra);
                startActivity(abrir);
            }
        });
    }
}

