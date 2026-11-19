package com.udh.guiaturistica.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "guia_turistica_udh.db";
    public static int DATABASE_VERSION = 1;

    public static String TABLE_RESENAS = "resenas";
    public static String COL_ID = "id";
    public static String COL_LUGAR = "nombre_lugar";
    public static String COL_USUARIO = "usuario";
    public static String COL_CALIFICACION = "calificacion";
    public static String COL_COMENTARIO = "comentario";

    private static String CREATE_TABLE_RESENAS =
            "CREATE TABLE " + TABLE_RESENAS + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_LUGAR + " TEXT NOT NULL, " +
                    COL_USUARIO + " TEXT NOT NULL, " +
                    COL_CALIFICACION + " REAL NOT NULL, " +
                    COL_COMENTARIO + " TEXT NOT NULL);";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RESENAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESENAS);
        onCreate(db);
    }
}