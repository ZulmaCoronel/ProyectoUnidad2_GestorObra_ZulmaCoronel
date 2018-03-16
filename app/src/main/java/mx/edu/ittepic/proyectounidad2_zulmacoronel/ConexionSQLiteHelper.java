package mx.edu.ittepic.proyectounidad2_zulmacoronel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zulma on 15/03/2018.
 */

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public SQLiteDatabase myDB;

    public ConexionSQLiteHelper(Context context) {

        super(context, "DBarquitectos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREAR_TABLA_CLIENTE = "" +
                "CREATE TABLE CLIENTE(" +
                " ID_CLIENTE  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NOMBRE TEXT, " +
                "DOMICILIO TEXT, " +
                "TELEFONO TEXT, " +
                "CORREO TEXT )";

        sqLiteDatabase.execSQL(CREAR_TABLA_CLIENTE);

        String CREAR_TABLA_EMPLADO = "" +
                "CREATE TABLE EMPLEADO (" +
                " ID_EMPLEADO  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NOMBRE TEXT, " +
                "CEL TEXT )";

        sqLiteDatabase.execSQL(CREAR_TABLA_EMPLADO);

        String CREAR_TABLA_OBRA = "" +
                "CREATE TABLE OBRA (" +
                " ID_OBRA  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "DESCRIPCION TEXT, " +
                "MONTO TEXT, " +
                "FINALIZADA BOOLEAN, " +
                "FECHA_INI DATE, " +
                "FECHA_FIN DATE, " +
                " ID_CLIENTE  INTEGER, FOREIGN KEY(ID_CLIENTE) REFERENCES CLIENTE(ID_CLIENTE))";

        sqLiteDatabase.execSQL(CREAR_TABLA_OBRA);

        String CREAR_TABLA_OBRA_EMPLEADO = "" +
                "CREATE TABLE OBRA_EMPLEADO (" +
                " ID_OBRA_EMPLEADO  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ACTIVIDAD TEXT, " +
                "PAGO TEXT, " +
                " ID_EMPLEADO  INTEGER ,ID_OBRA  INTEGER , FOREIGN KEY(ID_EMPLEADO) REFERENCES EMPLEADO(ID_EMPLEADO)," +
                "  FOREIGN KEY(ID_OBRA) REFERENCES OBRA(ID_OBRA))";

        sqLiteDatabase.execSQL(CREAR_TABLA_OBRA_EMPLEADO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void openDB() {
        myDB = getWritableDatabase();

    }

    public void closeDB() {
        if (myDB != null && myDB.isOpen()) {
            myDB.close();

        }
    }

    /*CRU CLIENTES*/
    public Cursor getAllClientes() {

        String query = "SELECT * FROM CLIENTE ORDER BY NOMBRE asc";

        return myDB.rawQuery(query, null);

    }

    public Cursor getAllClientesID(String id) {

        String query = "SELECT * FROM CLIENTE WHERE ID_CLIENTE=" + id;

        return myDB.rawQuery(query, null);

    }

    public Cursor getAllClientesMax() {

        String query = "SELECT MAX(ID_CLIENTE) as maximo FROM CLIENTE";

        return myDB.rawQuery(query, null);

    }

    public long insertCliente(String nombre, String domicilio, String telefono, String Correo) {

        ContentValues cv = new ContentValues();
        cv.put("NOMBRE", nombre);
        cv.put("DOMICILIO", domicilio);
        cv.put("TELEFONO", telefono);
        cv.put("CORREO", Correo);

        return myDB.insert("CLIENTE", null, cv);
    }

    public long updateCliente(String id, String nombre, String domicilio, String telefono, String Correo) {

        ContentValues cv = new ContentValues();
        cv.put("NOMBRE", nombre);
        cv.put("DOMICILIO", domicilio);
        cv.put("TELEFONO", telefono);
        cv.put("CORREO", Correo);
        return myDB.update("CLIENTE", cv, "ID_CLIENTE=" + id, null);
    }

    /*CRU EMPLEADOS*/
    public Cursor getAllEmpleados() {

        String query = "SELECT * FROM EMPLEADO ORDER BY NOMBRE asc";

        return myDB.rawQuery(query, null);

    }

    public Cursor getAllEmpleadosid(String id) {

        String query = "SELECT * FROM EMPLEADO WHERE ID_EMPLEADO=" + id;

        return myDB.rawQuery(query, null);

    }

    public Cursor getAllEmpleadosidObra(String id) {

        String query = "SELECT * FROM EMPLEADO as EM,OBRA_EMPLEADO as OE WHERE EM.ID_EMPLEADO=OE.ID_EMPLEADO AND OE.ID_OBRA="+id;

        return myDB.rawQuery(query, null);

    }

    public long updateEmpleado(int id, String nombree, String celular) {

        ContentValues cv = new ContentValues();
        cv.put("NOMBRE", nombree);
        cv.put("CEL", celular);


        return myDB.update("EMPLEADO", cv, "ID_EMPLEADO=" + id, null);
    }

    public long insertEmpleado(String nombree, String celular) {

        ContentValues cv = new ContentValues();
        cv.put("NOMBRE", nombree);
        cv.put("CEL", celular);

        return myDB.insert("EMPLEADO", null, cv);
    }

    public Cursor getAllObra() {

        String query = "SELECT * FROM OBRA";

        return myDB.rawQuery(query, null);

    }

    public Cursor getAllObraEmpleado() {

        String query = "SELECT * FROM OBRA as O,CLIENTE as C where C.ID_CLIENTE=O.ID_CLIENTE ";

        return myDB.rawQuery(query, null);

    }

    public Cursor getAllObraID(String id) {

        String query = "SELECT * FROM OBRA where ID_OBRA="+id;

        return myDB.rawQuery(query, null);

    }


    public long insertObra(String descripcion, String monto, int finalizada, String fecha_i, String fecha_f, int ID_cliente) {

        ContentValues cv = new ContentValues();
        cv.put("DESCRIPCION", descripcion);
        cv.put("MONTO", monto);
        cv.put("FINALIZADA", finalizada);
        cv.put("FECHA_INI", fecha_i);
        cv.put("FECHA_FIN", fecha_f);
        cv.put("ID_CLIENTE", ID_cliente);

        return myDB.insert("OBRA", null, cv);
    }

    public long updateObra(String descripcion, String monto, int finalizada, String fecha_i, String fecha_f, int idobra) {

        ContentValues cv = new ContentValues();
        cv.put("DESCRIPCION", descripcion);
        cv.put("MONTO", monto);
        cv.put("FINALIZADA", finalizada);
        cv.put("FECHA_INI", fecha_i);
        cv.put("FECHA_FIN", fecha_f);

        return myDB.update("OBRA",cv,"ID_OBRA="+idobra,null);
    }

    /* Relacion obra---Empleado*/

    public long insertarObraEmpleado(String actividad, String pago, int idempleado, int idobra) {

        ContentValues cv = new ContentValues();
        cv.put("ACTIVIDAD", actividad);
        cv.put("PAGO", pago);
        cv.put("ID_EMPLEADO", idempleado);
        cv.put("ID_OBRA", idobra);


        return myDB.insert("OBRA_EMPLEADO", null, cv);
    }

}
