package mx.edu.ittepic.proyectounidad2_zulmacoronel;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Zulma on 15/03/2018.
 */

public class Clientes extends Fragment {
    View vista;
    Button btnagregarc;

    ArrayList<Cliente> clientes;
    RecyclerView listaObjetos;
    private RecyclerView.LayoutManager mLayoutManager;
    AdaptadorCliente adapter;

    SQLiteDatabase db;
    ConexionSQLiteHelper con;

    public ClientesActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_clientes, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        vista=view;
        btnagregarc=view.findViewById(R.id.btn_agregar);
        btnagregarc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otra= new Intent(getContext(),ActivityAgregarCliente.class);
                startActivity(otra);
            }
        });

        con = new ConexionSQLiteHelper(getContext());
        con.openDB();

        listaObjetos = (RecyclerView) view.findViewById(R.id.rec_cliente);
        listaObjetos.setLayoutManager(new LinearLayoutManager(getContext()));
        clientes=new ArrayList<>();

        try {
            clientes=new ArrayList<>();
            //   StringBuffer datos = new StringBuffer();
            Cursor cursor = con.getAllClientes();
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

                clientes.add(new Cliente(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }
        } catch (SQLException e) {

        }
        if(clientes.size()==0){
            listaObjetos.setVisibility(View.GONE);
            animacion();
            Toasty.error(getContext(), "Sin clientes, favor de agregar clientes", Toast.LENGTH_SHORT, true).show();
        }else
        {
            adapter = new AdaptadorCliente(clientes, getContext());
            listaObjetos.setAdapter(adapter);
        }
        adapter=new AdaptadorCliente(clientes,getContext());
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        listaObjetos.setAdapter(adapter);
    }

    public void animacion(){
        LottieAnimationView animationView = (LottieAnimationView) vista.findViewById(R.id.animation_view);
        animationView.setVisibility(View.VISIBLE);
        animationView.setAnimation(R.raw.empty_box);
        animationView.loop(true);
        animationView.playAnimation();
    }
}

