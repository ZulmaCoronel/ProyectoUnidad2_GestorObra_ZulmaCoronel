package mx.edu.ittepic.proyectounidad2_zulmacoronel;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class AsignarEmpleado extends AppCompatActivity {
    SQLiteDatabase db;
    ConexionSQLiteHelper con;
    ArrayList<Empleado> empleados;
    RecyclerView listaObjetos;
    private RecyclerView.LayoutManager mLayoutManager;
    AdaptadorEmpleadoSeleccion adapter;
    int idcliente=0,idobra=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_empleado);

        idcliente= getIntent().getExtras().getInt("idcliente");
        idobra= getIntent().getExtras().getInt("idobra");
        con = new ConexionSQLiteHelper(AsignarEmpleado.this);
        con.openDB();

        listaObjetos = (RecyclerView) findViewById(R.id.reseleccion);
        listaObjetos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        try {
            empleados = new ArrayList<>();
            Cursor cursor = con.getAllEmpleados();
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

                empleados.add(new Empleado(cursor.getInt(0), cursor.getString(1), cursor.getString(2),idobra));
            }
        } catch (SQLException e) {

        }
        if (empleados.size() == 0) {
            listaObjetos.setVisibility(View.GONE);

            Toasty.error(getApplicationContext(), "Sin empleados, favor de agregar empleados", Toast.LENGTH_SHORT, true).show();
        } else {
            adapter = new AdaptadorEmpleadoSeleccion(empleados, getApplicationContext());
            listaObjetos.setAdapter(adapter);
        }
        adapter = new AdaptadorEmpleadoSeleccion(empleados, getApplicationContext());
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        listaObjetos.setAdapter(adapter);
    }
}

