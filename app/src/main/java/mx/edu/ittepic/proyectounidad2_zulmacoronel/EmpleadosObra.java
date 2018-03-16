package mx.edu.ittepic.proyectounidad2_zulmacoronel;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class EmpleadosObra extends AppCompatActivity {

    ArrayList<Empleado> empleados;
    RecyclerView listaObjetos;
    private RecyclerView.LayoutManager mLayoutManager;
    AdaptadorEmpleadoObra adapter;

    SQLiteDatabase db;
    ConexionSQLiteHelper con;
    int idobra=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados_obra);

        idobra=getIntent().getIntExtra("idobra",0);

        con = new ConexionSQLiteHelper(getApplicationContext());
        con.openDB();

        listaObjetos = (RecyclerView) findViewById(R.id.receo);
        listaObjetos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        try {
            empleados = new ArrayList<>();

            Cursor cursor = con.getAllEmpleadosidObra(""+idobra);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                empleados.add(new Empleado(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(4),cursor.getString(5)));            }

            adapter = new AdaptadorEmpleadoObra(empleados, getApplicationContext());
            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            listaObjetos.setAdapter(adapter);
            if (empleados.size()==0){
                Toasty.error(getApplicationContext(), "Sin empleados asignados", Toast.LENGTH_SHORT, true).show();

            }
        } catch (SQLException e) {

        }

    }
}
