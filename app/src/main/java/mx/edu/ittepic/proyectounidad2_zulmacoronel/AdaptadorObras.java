package mx.edu.ittepic.proyectounidad2_zulmacoronel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Zulma on 15/03/2018.
 */

public class AdaptadorObras extends RecyclerView.Adapter<AdaptadorObras.ViewHolderClientes> implements View.OnClickListener,View.OnCreateContextMenuListener {
    ArrayList<Obra> listaClientes;
    private View.OnClickListener listener;
    Context context;
    public int positions;


    public AdaptadorObras(ArrayList<Obra> listaClientes, Context context) {
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
        int layout= R.layout.vista_obra;
        View view= LayoutInflater.from(parent.getContext()).inflate(layout,null,false);
        view.setOnClickListener(this);
        view.setOnCreateContextMenuListener(this);
        return new ViewHolderClientes(view);
    }
    @Override
    public void onBindViewHolder(ViewHolderClientes holder, int position) {
        holder.fechaini.setText(listaClientes.get(position).getFecha_i());
        holder.fechafin.setText(listaClientes.get(position).getFecha_i());
        holder.cliente.setText(listaClientes.get(position).getClienteNombre());


        holder.descpcion.setText(listaClientes.get(position).getDescripcion());
        if ( listaClientes.get(position).getFinalizada()==1){
            holder.Finalizado.setChecked(true);
        }else
        {
            holder.Finalizado.setTextColor(Color.RED);
            holder.Finalizado.setChecked(false);
        }
        holder.montoo.setText(listaClientes.get(position).getMonto());
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
        MenuItem Editar = menu.add(Menu.NONE, 1, 1, "Asignar Empleado");
        Editar.setOnMenuItemClickListener(onEditMenu);
        MenuItem administrarObra = menu.add(Menu.NONE, 2, 2, "Administrar Obra");
        administrarObra.setOnMenuItemClickListener(onEditMenu);
        MenuItem verEmpleados = menu.add(Menu.NONE, 3, 3, "Ver Empleados Obra");
        verEmpleados.setOnMenuItemClickListener(onEditMenu);
    }
    private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case 1:
                    Intent abrir= new Intent(context,AsignarEmpleado.class);
                    abrir.putExtra("idobra",listaClientes.get(positions).getIdobra());
                    abrir.putExtra("idcliente",listaClientes.get(positions).getCliente());
                    context.startActivity(abrir);
                    break;
                case 2:
                    Intent abrir2= new Intent(context,ActivityAdministrarObra.class);
                    abrir2.putExtra("idobra",listaClientes.get(positions).getIdobra());
                    context.startActivity(abrir2);
                    break;
                case 3:
                    Intent abrir3= new Intent(context,ActivityEmpleadosObra.class);
                    abrir3.putExtra("idobra",listaClientes.get(positions).getIdobra());
                    context.startActivity(abrir3);
                    break;
            }
            return true;
        }
    };

    public class ViewHolderClientes extends RecyclerView.ViewHolder {
        CardView card;
        TextView descpcion,montoo,fechaini,fechafin,cliente;
        CheckBox Finalizado;
        public ViewHolderClientes(View itemView) {
            super(itemView);
            card= (CardView)  itemView.findViewById(R.id.card1);
            descpcion= (TextView) itemView.findViewById(R.id.descrio);
            Finalizado= itemView.findViewById(R.id.finalo);
            montoo=itemView.findViewById(R.id.montoo);
            fechaini=itemView.findViewById(R.id.fechainio);
            fechafin=itemView.findViewById(R.id.fechafino);
            cliente=itemView.findViewById(R.id.nombre_cliente);

        }
    }
}