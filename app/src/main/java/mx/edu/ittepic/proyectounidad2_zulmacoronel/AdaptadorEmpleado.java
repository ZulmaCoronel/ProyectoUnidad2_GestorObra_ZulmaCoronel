package mx.edu.ittepic.proyectounidad2_zulmacoronel;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Zulma on 15/03/2018.
 */

public class AdaptadorEmpleado {extends RecyclerView.Adapter<AdaptadorEmpleado.ViewHolderClientes> implements View.OnClickListener,View.OnCreateContextMenuListener {
    ArrayList<Empleado> listaClientes;
    private View.OnClickListener listener;
    Context context;
    public int positions;


    public AdaptadorEmpleado(ArrayList<Empleado> listaClientes, Context context) {
        this.listaClientes = listaClientes;
        this.context=context;
    }


    public int getPosition() {
        return positions;
    }

    public void setPosition(int position) {
        this.positions = position;
    }


    @Override
    public ViewHolderClientes onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout= R.layout.vista_empleado;
        View view= LayoutInflater.from(parent.getContext()).inflate(layout,null,false);
        view.setOnClickListener(this);
        view.setOnCreateContextMenuListener(this);
        return new ViewHolderClientes(view);
    }
    @Override
    public void onBindViewHolder(ViewHolderClientes holder, int position) {

        holder.nombre.setText(listaClientes.get(position).getNombree());
        holder.cel.setText(listaClientes.get(position).getCele());
        final int postemp=position;
        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(postemp);
                return false;
            }

        });

    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem Editar = menu.add(Menu.NONE, 1, 1, "EDITAR DATOS");
        Editar.setOnMenuItemClickListener(onEditMenu);
    }
    private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case 1:
                    Intent abrir= new Intent(context,ActivityEditarEmpleado.class);
                    abrir.putExtra("idcliente",listaClientes.get(positions).getIdempleado());
                    context.startActivity(abrir);
                    break;
            }
            return true;
        }
    };



    public class ViewHolderClientes extends RecyclerView.ViewHolder {
        CardView card;
        TextView nombre,cel;

        public ViewHolderClientes(View itemView) {
            super(itemView);
            card= (CardView)  itemView.findViewById(R.id.card1);
            nombre= (TextView) itemView.findViewById(R.id.namee);
            cel= (TextView) itemView.findViewById(R.id.cele);

        }
    }
}