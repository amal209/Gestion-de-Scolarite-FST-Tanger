package com.example.Gestion_Scolarite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "projet_scolarite.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME_MODULE = "modules";
    private static final String COLUMN_ID_MODULE = "_id";
    private static final String COLUMN_NAME_MODULE = "module_name";

    private static final String TABLE_NAME_FILIERE = "filieres";
    private static final String COLUMN_ID_FILIERE = "_id";
    private static final String COLUMN_NAME_FILIERE = "filiere_name";

    private static final String TABLE_NAME_STUDENT = "students";
    private static final String COLUMN_ID_STUDENT = "cne";
    private static final String COLUMN_NAME_STUDENT = "student_first_name";
    private static final String COLUMN_LAST_NAME_STUDENT = "student_last_name";
    private static final String COLUMN_STUDENT_NAME_FILIERE = "filiere_name_student";

    private static final String TABLE_NAME_NOTE = "notes";
    private static final String COLUMN_ID_NOTES = "_id";
    private static final String COLUMN_ID_NOTE = "cne_student";
    private static final String COLUMN_MODULE_NOTE = "name_module";
    private static final String COLUMN_NOTE = "note_module";




    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME_MODULE +
                        " (" + COLUMN_ID_MODULE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME_MODULE + " TEXT NOT NULL);";
        db.execSQL(query);

        String query2 = "CREATE TABLE " + TABLE_NAME_FILIERE +
                " (" + COLUMN_ID_FILIERE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME_FILIERE + " TEXT NOT NULL);";

        String query3 = "CREATE TABLE "+TABLE_NAME_STUDENT+
                " ("+COLUMN_ID_STUDENT+" VARCHAR(50) PRIMARY KEY, "
                +COLUMN_NAME_STUDENT+" TEXT NOT NULL, "
                +COLUMN_LAST_NAME_STUDENT+" TEXT NOT NULL, "
                +COLUMN_STUDENT_NAME_FILIERE+" TEXT NOT NULL, FOREIGN KEY ("+COLUMN_STUDENT_NAME_FILIERE
                +") REFERENCES "+TABLE_NAME_FILIERE+"("+COLUMN_NAME_FILIERE+"));";

        String query4 = "CREATE TABLE "+ TABLE_NAME_NOTE
                +"("+COLUMN_ID_NOTES+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COLUMN_ID_NOTE+" VARCHAR(50), "+ COLUMN_NOTE +" TEXT NOT NULL, "
                + COLUMN_MODULE_NOTE+" TEXT NOT NULL, FOREIGN KEY ("+COLUMN_ID_NOTE
                +") REFERENCES "+TABLE_NAME_STUDENT+"("+COLUMN_ID_STUDENT+"));";
        //db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MODULE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FILIERE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_NOTE);
        onCreate(db);
    }

    void addModule(String module_name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME_MODULE,module_name);
        long result = db.insert(TABLE_NAME_MODULE,null,cv);
        if (result == -1){
            Toast.makeText(context, "ERREUR !!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Le module est inséré avec succès", Toast.LENGTH_SHORT).show();
        }
    }

    void addFiliere(String filiere_name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME_FILIERE,filiere_name);
        long result = db.insert(TABLE_NAME_FILIERE,null,cv);
        if (result == -1){
            Toast.makeText(context, "ERREUR !!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "La filière est insérée avec succès", Toast.LENGTH_SHORT).show();
        }
    }

    void addStudent(String student_cne, String student_name, String student_last_name, String student_filiere_name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID_STUDENT,student_cne);
        cv.put(COLUMN_NAME_STUDENT,student_name);
        cv.put(COLUMN_LAST_NAME_STUDENT,student_last_name);
        cv.put(COLUMN_STUDENT_NAME_FILIERE,student_filiere_name);

        long result = db.insert(TABLE_NAME_STUDENT,null,cv);
        if (result == -1){
            Toast.makeText(context, "Ce CNE existe déjà", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "L'étudiant est inséré avec succès", Toast.LENGTH_SHORT).show();
        }
    }

    void addNote(String student_cne, String module_name, String student_note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID_NOTE,student_cne);
        cv.put(COLUMN_MODULE_NOTE,module_name);
        cv.put(COLUMN_NOTE,student_note);

        long result = db.insert(TABLE_NAME_NOTE,null,cv);
        if (result == -1){
            Toast.makeText(context, "ERREUR !!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "La note est insérée avec succès", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<String>readModules(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorModules = db.rawQuery("SELECT * FROM "+TABLE_NAME_MODULE,null);

        ArrayList<String> cursorModulesList = new ArrayList<>();

        if (cursorModules.moveToFirst()){
            do {
                cursorModulesList.add(cursorModules.getString(1));
            }while (cursorModules.moveToNext());
        }else{;}
        cursorModules.close();
        return cursorModulesList;

    }

    public ArrayList<String>readFilieres(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorModules = db.rawQuery("SELECT * FROM "+TABLE_NAME_FILIERE,null);

        ArrayList<String> cursorFiliereList = new ArrayList<>();

        if (cursorModules.moveToFirst()){
            do {
                cursorFiliereList.add(cursorModules.getString(1));
            }while (cursorModules.moveToNext());
        }else{;}

        cursorModules.close();
        return cursorFiliereList;

    }

    Cursor readAllStudent(){
        String query = "SELECT * FROM " + TABLE_NAME_STUDENT;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    Cursor readAllBulletins(){
        String query = "SELECT * FROM " + TABLE_NAME_NOTE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

}
