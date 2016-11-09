package com.example.byakko.internaldbstorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by macbook on 07/11/16.
 */

public class DBHelper extends SQLiteOpenHelper {
    Context ctx;

    public DBHelper(Context context) {
        super(context, "db_pureba", null, 1);
        ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE telefonos(id_telefono INTEGER PRIMARY KEY AUTOINCREMENT, numero TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS telefonos");
        onCreate(db);
    }

    //Variable generales
    DBHelper helper;
    SQLiteDatabase db;

    //Metodos para manejar la base de datos
    public void openDB(){
        helper = new DBHelper(ctx);
        db = helper.getWritableDatabase();
    }

    public void closeDB(){
        db.close();
    }

    //Metodos para manipular datos
    public long register(String pNumero) throws Exception{
        ContentValues values = new ContentValues();
        values.put("numero", pNumero);
        return db.insert("telefonos", null, values);
    }

    public String consult() throws Exception{
        String data = "";
        String[] columns = new String[] {"numero"};
        Cursor c = db.query("telefonos", columns, null, null, null, null, null);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            data += c.getString(c.getColumnIndex("numero")) + "\n";
        }
        return data;
    }
}
