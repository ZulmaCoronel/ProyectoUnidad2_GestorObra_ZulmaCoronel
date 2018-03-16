package mx.edu.ittepic.proyectounidad2_zulmacoronel;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Zulma on 15/03/2018.
 */

public class Empleados extends Fragment {
    View vista;
    Button btnagregare;

    ArrayList<Empleado> empleados;
    RecyclerView listaObjetos;
    private RecyclerView.LayoutManager mLayoutManager;
    AdaptadorEmpleado adapter;
    SQLiteDatabase db;
    ConexionSQLiteHelper con;
    public EmpleadosActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_empleados, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        vista = view;
        btnagregare = view.findViewById(R.id.btnagregare);
        btnagregare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrir = new Intent(getContext(), ActivityAgregarEmpleado.class);
                startActivity(abrir);
            }
        });
        con = new ConexionSQLiteHelper(getContext());
        con.openDB();
        listaObjetos = (RecyclerView) view.findViewById(R.id.rec_empleados);
        listaObjetos.setLayoutManager(new LinearLayoutManager(getContext()));
        empleados = new ArrayList<>();
        try {
            empleados = new ArrayList<>();
            //   StringBuffer datos = new StringBuffer();
            Cursor cursor = con.getAllEmpleados();
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

                empleados.add(new Empleado(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            }
        } catch (SQLException e) {

        }
        if (empleados.size() == 0) {
            listaObjetos.setVisibility(View.GONE);
            animacion();
            Toasty.error(getContext(), "Sin empleados, favor de agregar empleados", Toast.LENGTH_SHORT, true).show();
        } else {
            adapter = new AdaptadorEmpleado(empleados, getContext());
            listaObjetos.setAdapter(adapter);
        }

        adapter = new AdaptadorEmpleado(empleados, getContext());
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        listaObjetos.setAdapter(adapter);
    }

    public void animacion(){
        LottieAnimationView animationView = (LottieAnimationView) vista.findViewById(R.id.animation_view2);
        animationView.setVisibility(View.VISIBLE);
        animationView.setAnimation(R.raw.empty_box);
        animationView.loop(true);
        animationView.playAnimation();
    }

}

