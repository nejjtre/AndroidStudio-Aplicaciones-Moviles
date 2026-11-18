package com.udh.guiaturistica.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.udh.guiaturistica.modelo.ResenaItem;

import java.util.ArrayList;
import java.util.List;

public class ResenaRepository {

    private final Database dbHelper;

    public ResenaRepository(Context context) {
        this.dbHelper = new Database(context);
    }

    public long insertarResena(ResenaItem resena) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Database.COL_LUGAR, resena.getNombreLugar());
        values.put(Database.COL_USUARIO, resena.getUsuario());
        values.put(Database.COL_CALIFICACION, resena.getCalificacion());
        values.put(Database.COL_COMENTARIO, resena.getComentario());

        long id = db.insert(Database.TABLE_RESENAS, null, values);
        db.close();
        return id;
    }

    public List<ResenaItem> obtenerResenasPorLugar(String nombreLugar) {
        List<ResenaItem> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                Database.TABLE_RESENAS,
                null,
                Database.COL_LUGAR + " = ?",
                new String[]{nombreLugar},
                null,
                null,
                Database.COL_ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Database.COL_ID));
                String lugar = cursor.getString(cursor.getColumnIndexOrThrow(Database.COL_LUGAR));
                String usuario = cursor.getString(cursor.getColumnIndexOrThrow(Database.COL_USUARIO));
                float calificacion = cursor.getFloat(cursor.getColumnIndexOrThrow(Database.COL_CALIFICACION));
                String comentario = cursor.getString(cursor.getColumnIndexOrThrow(Database.COL_COMENTARIO));

                lista.add(new ResenaItem(id, lugar, usuario, calificacion, comentario));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }

    public int actualizarResena(ResenaItem resena) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Database.COL_USUARIO, resena.getUsuario());
        values.put(Database.COL_CALIFICACION, resena.getCalificacion());
        values.put(Database.COL_COMENTARIO, resena.getComentario());

        int filas = db.update(
                Database.TABLE_RESENAS,
                values,
                Database.COL_ID + " = ?",
                new String[]{String.valueOf(resena.getId())}
        );
        db.close();
        return filas;
    }

    public int eliminarResena(int idResena) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int filas = db.delete(
                Database.TABLE_RESENAS,
                Database.COL_ID + " = ?",
                new String[]{String.valueOf(idResena)}
        );
        db.close();
        return filas;
    }
}