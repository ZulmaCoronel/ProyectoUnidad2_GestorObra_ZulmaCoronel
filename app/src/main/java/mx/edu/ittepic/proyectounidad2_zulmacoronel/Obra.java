package mx.edu.ittepic.proyectounidad2_zulmacoronel;

/**
 * Created by Zulma on 15/03/2018.
 */

public class Obra {

    String fecha_i;
    String fecha_f;

    public String getClienteNombre() {
        return ClienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        ClienteNombre = clienteNombre;
    }

    String ClienteNombre;

    public Obra(String fecha_i, String fecha_f, int idobra, int cliente, String descripcion, String monto, int finalizada) {
        this.fecha_i = fecha_i;
        this.fecha_f = fecha_f;
        this.idobra = idobra;
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.monto = monto;
        this.finalizada = finalizada;
    }

    public Obra(String fecha_i, String fecha_f, int idobra, int cliente, String descripcion, String monto, int finalizada,String Cliente) {
        this.fecha_i = fecha_i;
        this.fecha_f = fecha_f;
        this.idobra = idobra;
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.monto = monto;
        this.finalizada = finalizada;
        this.ClienteNombre=Cliente;
    }

    public String getFecha_i() {

        return fecha_i;
    }

    public void setFecha_i(String fecha_i) {
        this.fecha_i = fecha_i;
    }

    public String getFecha_f() {
        return fecha_f;
    }

    public void setFecha_f(String fecha_f) {
        this.fecha_f = fecha_f;
    }

    int idobra;

    public int getIdobra() {
        return idobra;
    }

    public void setIdobra(int idobra) {
        this.idobra = idobra;
    }
    int cliente;

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    String monto;

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
    int finalizada;

    public int getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(int finalizada) {
        this.finalizada = finalizada;
    }
}

