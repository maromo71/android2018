package br.edu.faculdade.sqlitesample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by maromo on 05/07/2017.
 */

class StudentDao {
    private DBHelper dbHelper;

    public StudentDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Student student) {
        //Abrir a conexão para gravar os dados
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Student.KEY_age, student.getAge());
        values.put(Student.KEY_email, student.getEmail());
        values.put(Student.KEY_name, student.getName());

        // Inserindo uma linha
        long student_Id = db.insert(Student.TABLE, null, values);
        db.close(); // Fechando a conexão
        return (int) student_Id;
    }

    public void delete(int student_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // É uma boa prática usar o parâmetro ?, para concatenar a string
        db.delete(Student.TABLE, Student.KEY_ID + "= ?", new String[]{String.valueOf(student_Id)});
        db.close(); // Fechando a conxão
    }

    public void update(Student student) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Student.KEY_age, student.getAge());
        values.put(Student.KEY_email, student.getEmail());
        values.put(Student.KEY_name, student.getName());

        // É uma boa prática usar o parâmetro ?, para concatenar a string
        db.update(Student.TABLE, values, Student.KEY_ID + "= ?", new String[]{String.valueOf(student.getStudent_ID())});
        db.close(); // Fechando a conexão
    }

    public ArrayList<HashMap<String, String>> getStudentList() {
        //Abrindo a conexão apenas para leitura
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Student.KEY_ID + "," +
                Student.KEY_name + "," +
                Student.KEY_email + "," +
                Student.KEY_age +
                " FROM " + Student.TABLE;

        ArrayList<HashMap<String, String>> studentList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        //Percorrendo todas as linhas e adicionando à lista
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> student = new HashMap<String, String>();
                student.put("id", cursor.getString(cursor.getColumnIndex(Student.KEY_ID)));
                student.put("name", cursor.getString(cursor.getColumnIndex(Student.KEY_name)));
                studentList.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return studentList;
    }

    public Student getStudentById(int Id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Student.KEY_ID + "," +
                Student.KEY_name + "," +
                Student.KEY_email + "," +
                Student.KEY_age +
                " FROM " + Student.TABLE
                + " WHERE " +
                Student.KEY_ID + "=?";


        Student student = new Student();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(Id)});

        if (cursor.moveToFirst()) {
            do {
                student.setStudent_ID(cursor.getInt(cursor.getColumnIndex(Student.KEY_ID)));
                student.setName(cursor.getString(cursor.getColumnIndex(Student.KEY_name)));
                student.setEmail(cursor.getString(cursor.getColumnIndex(Student.KEY_email)));
                student.setAge(cursor.getInt(cursor.getColumnIndex(Student.KEY_age)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return student;
    }
}
