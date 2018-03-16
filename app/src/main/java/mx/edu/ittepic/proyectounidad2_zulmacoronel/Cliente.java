package mx.edu.ittepic.proyectounidad2_zulmacoronel;

/**
 * Created by Zulma on 15/03/2018.
 */

public class Cliente {
    int idcliente;

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }
    String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    String domicilio;

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    String cel;

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }
    String mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Cliente(int idcliente, String nombre, String domicilio, String cel, String mail) {
        this.idcliente = idcliente;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.cel = cel;
        this.mail = mail;
    }
}

