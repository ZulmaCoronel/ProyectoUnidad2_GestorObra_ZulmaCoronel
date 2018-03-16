package mx.edu.ittepic.proyectounidad2_zulmacoronel;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Zulma on 15/03/2018.
 */

public class Obras extends Fragment {
    View vista;


    ArrayList<Obra> obras;
    RecyclerView listaObjetos;
    private RecyclerView.LayoutManager mLayoutManager;
    AdaptadorObras adapter;

    SQLiteDatabase db;
    ConexionSQLiteHelper con;

    public ObrasActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_obras, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        vista = view;
        animacion();
        con = new ConexionSQLiteHelper(getContext());
        con.openDB();
        listaObjetos = (RecyclerView) view.findViewById(R.id.obraslista);
        listaObjetos.setLayoutManager(new LinearLayoutManager(getContext()));
        try {
            obras = new ArrayList<>();

            Cursor cursor = con.getAllObraEmpleado();
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                obras.add(new Obra(cursor.getString(4), cursor.getString(5), cursor.getInt(0), cursor.getInt(6), cursor.getString(1), cursor.getString(2), cursor.getInt(3),cursor.getString(8)));
            }

            adapter = new AdaptadorObras(obras, getContext());
            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            listaObjetos.setAdapter(adapter);

        } catch (SQLException e) {

        }



    }

    public void animacion() {
        LottieAnimationView animationView = (LottieAnimationView) vista.findViewById(R.id.animation_view);
        animationView.setAnimation(R.raw.construction_site);
        animationView.loop(true);
        animationView.playAnimation();
    }

