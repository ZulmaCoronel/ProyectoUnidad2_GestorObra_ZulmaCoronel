package mx.edu.ittepic.proyectounidad2_zulmacoronel;

/**
 * Created by Zulma on 15/03/2018.
 */

public class Empleado {
    int idempleado;
    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }
    String nombree;

    public String getNombree() {
        return nombree;
    }

    public void setNombree(String nombree) {
        this.nombree = nombree;
    }
    String cele;

    public String getCele() {
        return cele;
    }

    public void setCele(String cele) {
        this.cele = cele;
    }

    int idobra;

    public int getIdobra() {
        return idobra;
    }

    public void setIdobra(int idobra) {
        this.idobra = idobra;
    }

    public Empleado(int idempleado, String nombree, String cele, int idobra) {
        this.idempleado = idempleado;
        this.nombree = nombree;
        this.cele = cele;
        this.idobra = idobra;
    }

    public Empleado(int idempleado, String nombree, String cele) {
        this.idempleado = idempleado;
        this.nombree = nombree;
        this.cele = cele;
    }
    String actividad,pago;

    public Empleado(int idempleado, String nombree, String cele, String actividad, String pago) {
        this.idempleado = idempleado;
        this.nombree = nombree;
        this.cele = cele;
        this.idobra = idobra;
        this.actividad = actividad;
        this.pago = pago;
    }

    public String getActividad() {

        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }
}

