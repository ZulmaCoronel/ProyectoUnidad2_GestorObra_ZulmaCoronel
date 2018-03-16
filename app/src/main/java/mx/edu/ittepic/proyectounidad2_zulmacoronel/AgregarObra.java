package mx.edu.ittepic.proyectounidad2_zulmacoronel;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AgregarObra extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    SQLiteDatabase db;
    ConexionSQLiteHelper con;


    DatePickerDialog fecha_ini,fecha_fini;
    Button fecha_inicio,fecha_final,agregar;
    EditText des,monto;
    int idcliente=0;


    ArrayList<Empleado> empleados;
    RecyclerView listaObjetos;
    private RecyclerView.LayoutManager mLayoutManager;
    AdaptadorEmpleadoSeleccion adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_obra);
        fecha_inicio=findViewById(R.id.fechaini2);
        fecha_final=findViewById(R.id.fechafin2);
        des=findViewById(R.id.adecripo);
        monto=findViewById(R.id.montoedit);
        agregar=findViewById(R.id.agregaro);
        idcliente= getIntent().getExtras().getInt("idcliente");

        con = new ConexionSQLiteHelper(AgregarObra.this);
        con.openDB();

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        fecha_ini = new DatePickerDialog(
                AgregarObra.this, AgregarObra.this,year,month, day);

        fecha_fini = new DatePickerDialog(
                AgregarObra.this, AgregarObra.this,year,month, day);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String fecha_iniciod=""+fecha_ini.getDatePicker().getDayOfMonth()+" "+fecha_ini.getDatePicker().getMonth()+" "+fecha_ini.getDatePicker().getYear();
                String fecha_find=""+fecha_fini.getDatePicker().getDayOfMonth()+" "+fecha_ini.getDatePicker().getMonth()+" "+fecha_ini.getDatePicker().getYear();
                // Toast.makeText(getApplicationContext(),""+fecha_iniciod,Toast.LENGTH_LONG).show();

                con.insertObra(""+des.getText().toString(),""+monto.getText().toString(),0,fecha_iniciod,fecha_find,idcliente);

                Toasty.success(getApplicationContext(), "Se creo la obra, ahora asigne empleados", Toast.LENGTH_LONG, true).show();
                Intent abrir= new Intent(AgregarObra.this,MainActivity.class);
                startActivity(abrir);

            }
        });

        fecha_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fecha_ini.show();

            }
        });
        fecha_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fecha_fini.show();
            }
        });




    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}
